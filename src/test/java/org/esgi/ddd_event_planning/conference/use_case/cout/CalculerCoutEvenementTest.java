package org.esgi.ddd_event_planning.conference.use_case.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenants;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieux;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.model.staff.Staffs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Un événement")
class CalculerCoutEvenementTest {
    Staffs staffs;
    Lieux lieux;
    Intervenants intervenants;
    CalculerCoutEvenement calculerCoutEvenement;

    @BeforeEach
    void setUp() {
        staffs = new InMemoryStaffs();
        lieux = new InMemoryLieux();
        intervenants = new InMemoryIntervenants();
        calculerCoutEvenement = new CalculerCoutEvenement(staffs, lieux, intervenants);
    }

    @Test
    @DisplayName("Doit couter 0 quand l'événement est vide")
    void computeEmpty() {
        lieux.add("1", new Lieu(0));
        assertEquals(0, calculerCoutEvenement.calculer("1"));
    }

    @Test
    @DisplayName("Doit couter 11 quand l'événement a un staff qui coute 10")
    void computeOneStaff() {
        staffs.add("1", new Staff(10));
        lieux.add("1", new Lieu(0));
        assertEquals(11, calculerCoutEvenement.calculer("1"));
    }

    @Test
    @DisplayName("Doit couter 11000 quand l'événement a un lieu qui coute 10000")
    void computeLocation() {
        lieux.add("1", new Lieu(10000));
        assertEquals(11000, calculerCoutEvenement.calculer("1"));
    }

    @Test
    @DisplayName("Doit couter 110000 quand l'événement a un intervenant qui coute 100000 - #Solomon")
    void computeIntervenant() {
        lieux.add("1", new Lieu(0));
        intervenants.add("1", new Intervenant(100000));
        assertEquals(110000, calculerCoutEvenement.calculer("1"),0.001);
    }

    @Test
    @DisplayName("Doit couter 1232 quand l'événement a deux intervenants qui coutent 10 et un lieu qui coute 100 et un staff qui coute 1000")
    void computeAll() {
        lieux.add("1", new Lieu(100));
        intervenants.add("1", new Intervenant(10));
        intervenants.add("1", new Intervenant(10));
        staffs.add("1", new Staff(1000));
        assertEquals(1232, calculerCoutEvenement.calculer("1"),0.001);
    }
}