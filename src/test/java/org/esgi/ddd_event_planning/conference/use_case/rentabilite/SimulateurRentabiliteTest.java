package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.BilleterieCalculateur;
import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulateurRentabiliteTest {
    Evenements evenements;
    BilleterieCalculateur billeterieCalculateur;
    static final double COMMISSION = 0.1;

    @BeforeEach
    void setUp() {
        evenements = new InMemoryEvenements();
        billeterieCalculateur = new BilleterieCalculateur();
    }
    @Test
    @DisplayName("Doit lever une erreur quand l'événement est inconnu")
    void computeThrows() {
        SimulateurRentabilite simulateurRentabilite = new SimulateurRentabilite(evenements, billeterieCalculateur);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            Estimation estimation = simulateurRentabilite.simulerBilleterie(new EvenementId("1"), new Montant(0, "EUR"), COMMISSION);
        });

        assertTrue(thrown.getMessage().contains("Evenement not found"));
    }
}
