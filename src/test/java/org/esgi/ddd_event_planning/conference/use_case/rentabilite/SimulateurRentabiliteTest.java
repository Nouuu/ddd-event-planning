package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.BilleterieCalculateur;
import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulateurRentabiliteTest {
    Evenements evenements;
    SimulateurRentabilite simulateurRentabilite;
    static final double COMMISSION = 0.1;

    @BeforeEach
    void setUp() {
        evenements = new InMemoryEvenements();
        simulateurRentabilite = new SimulateurRentabilite(evenements, new BilleterieCalculateur());
    }
    @Test
    @DisplayName("Doit lever une erreur quand l'événement est inconnu")
    void computeThrows() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Estimation estimation = simulateurRentabilite.simulerBilleterie(new EvenementId("1"), 0.1, COMMISSION);
        });

        assertTrue(thrown.getMessage().contains("Evenement not found"));
    }

    @Test
    @DisplayName("Doit retourner une estimation de billeterie avec 10% de rentabilité attendue")
    void compute() {
        //Arrange
        Evenement evenement = new Evenement(
                new EvenementId("1"),
                100,
                150,
                Collections.emptyList(),
                Collections.emptyList(),
                new Lieu(new Montant(2000, "EUR"), 100)
        );
        evenements.ajouter(evenement);

        //Act
        Estimation estimation = simulateurRentabilite.simulerBilleterie(new EvenementId("1"), 0.1, COMMISSION);

        //Assert
        assertEquals(2200, estimation.coutEvenement());
        assertEquals(24.2, estimation.tarifBillet());
        assertEquals(91, estimation.nombreMinimumParticipants());
        assertEquals(220, estimation.gainEstime());
        assertEquals(1430, estimation.gainMaximal());
    }
}
