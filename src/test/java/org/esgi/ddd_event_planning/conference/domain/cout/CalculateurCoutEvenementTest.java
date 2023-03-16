package org.esgi.ddd_event_planning.conference.domain.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenants;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieux;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.model.staff.Staffs;
import org.esgi.ddd_event_planning.conference.use_case.cout.InMemoryIntervenants;
import org.esgi.ddd_event_planning.conference.use_case.cout.InMemoryLieux;
import org.esgi.ddd_event_planning.conference.use_case.cout.InMemoryStaffs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Une conférence")
class CalculateurCoutEvenementTest {
    Staffs staffs;
    Lieux lieux;
    Intervenants intervenants;
    CalculateurCoutEvenement calculateurCoutEvenement;

    @BeforeEach
    void setUp() {
        staffs = new InMemoryStaffs();
        lieux = new InMemoryLieux();
        intervenants = new InMemoryIntervenants();
        calculateurCoutEvenement = new CalculateurCoutEvenement(staffs, lieux, intervenants);
    }

    @Test
    @DisplayName("Doit couter 0 quand l'événement est vide")
    void computeEmpty() {
        lieux.ajouter("1", new Lieu(0));
        assertEquals(0, calculateurCoutEvenement.calculerCoutEvenement("1"));
    }

    @Test
    @DisplayName("Doit couter 11 quand l'événement a un staff qui coute 10")
    void computeOneStaff() {
        staffs.ajouter("1", new Staff(10));
        lieux.ajouter("1", new Lieu(0));
        assertEquals(11, calculateurCoutEvenement.calculerCoutEvenement("1"));
    }

    @Test
    @DisplayName("Doit couter 11000 quand l'événement a un lieu qui coute 10000")
    void computeLocation() {
        lieux.ajouter("1", new Lieu(10000));
        assertEquals(11000, calculateurCoutEvenement.calculerCoutEvenement("1"));
    }

    @Test
    @DisplayName("Doit couter 110000 quand l'événement a un intervenant qui coute 100000 - #Solomon")
    void computeIntervenant() {
        lieux.ajouter("1", new Lieu(0));
        intervenants.ajouter("1", new Intervenant(100000));
        assertEquals(110000, calculateurCoutEvenement.calculerCoutEvenement("1"),0.001);
    }

    @Test
    @DisplayName("Doit couter 1232 quand l'événement a deux intervenants qui coutent 10 et un lieu qui coute 100 et un staff qui coute 1000")
    void computeAll() {
        lieux.ajouter("1", new Lieu(100));
        intervenants.ajouter("1", new Intervenant(10));
        intervenants.ajouter("1", new Intervenant(10));
        staffs.ajouter("1", new Staff(1000));
        assertEquals(1232, calculateurCoutEvenement.calculerCoutEvenement("1"),0.001);
    }
}