package org.esgi.ddd_event_planning.conference.domain.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Une conférence")
class CalculateurCoutEvenementTest {
    CalculateurCoutEvenement calculateurCoutEvenement;

    static final double COMMISSION = 0.1;

    @BeforeEach
    void setUp() {
        calculateurCoutEvenement = new CalculateurCoutEvenement(COMMISSION);
    }

    @Test
    @DisplayName("Doit couter 0 quand l'événement est vide")
    void computeEmpty() {
        assertEquals(0, calculateurCoutEvenement.calculerCoutEvenement(
                List.of(),
                List.of(),
                new Lieu(0)
        ));
    }

    @Test
    @DisplayName("Doit couter 11 quand l'événement a un staff qui coute 10")
    void computeOneStaff() {
        assertEquals(11, calculateurCoutEvenement.calculerCoutEvenement(
                List.of(new Staff(10)),
                List.of(),
                new Lieu(0)
        ));
    }

    @Test
    @DisplayName("Doit couter 11000 quand l'événement a un lieu qui coute 10000")
    void computeLocation() {
        assertEquals(11000, calculateurCoutEvenement.calculerCoutEvenement(
                List.of(),
                List.of(),
                new Lieu(10000)
        ));
    }

    @Test
    @DisplayName("Doit couter 110000 quand l'événement a un intervenant qui coute 100000 - #Solomon")
    void computeIntervenant() {
        assertEquals(110000, calculateurCoutEvenement.calculerCoutEvenement(
                List.of(),
                List.of(new Intervenant(100000)),
                new Lieu(0)
        ), 0.001);
    }

    @Test
    @DisplayName("Doit couter 1232 quand l'événement a deux intervenants qui coutent 10 et un lieu qui coute 100 et un staff qui coute 1000")
    void computeAll() {
        assertEquals(1232, calculateurCoutEvenement.calculerCoutEvenement(
                List.of(new Staff(1000)),
                List.of(new Intervenant(10), new Intervenant(10)),
                new Lieu(100)
                ), 0.001);
    }
}