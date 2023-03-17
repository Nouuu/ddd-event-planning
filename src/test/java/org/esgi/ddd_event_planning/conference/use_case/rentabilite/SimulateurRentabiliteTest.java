package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.BilleterieCalculateur;
import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;
import org.esgi.ddd_event_planning.conference.domain.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.domain.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.domain.model.staff.StaffRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Un simulateur de rentabilité")
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
    @DisplayName("Ne peut se calculer si l'événement n'existe pas")
    void computeThrows() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () ->
                simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION)
        );

        assertTrue(thrown.getMessage().contains("Evenement not found"));
    }

    @Test
    @DisplayName("Ne peut se calculer si la surface du lieu choisis est négative")
    void computeEventWithNegativeSurface() {

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            //Arrange
            Evenement evenement = new Evenement(
                    new EvenementId("1"),
                    100,
                    150,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    new Lieu(new Montant(2000, "EUR"), -100)
            );
            evenements.ajouter(evenement);
            //Act
            simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("Surface cannot be negative"));
    }


    @Test
    @DisplayName("Ne peut se calculer si la rentabilité attendu est négative")
    void computeWithNegativePercentage() {

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
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
            simulateurRentabilite.simulerBilleterie("1", -0.1, COMMISSION);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("La valeur doit être comprise entre 0 et 1"));
    }


    @Test
    @DisplayName("Ne peut se calculer si le rendement attendu est négatif")
    void computeWithPercentageOverOne() {

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
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
            simulateurRentabilite.simulerBilleterie("1", -1.2, COMMISSION);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("La valeur doit être comprise entre 0 et 1"));
    }

    @Test
    @DisplayName("Ne peut se calculer si les devises sont différentes")
    void computeEventWithDifferentDevise() {

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            //Arrange
            Evenement evenement = new Evenement(
                    new EvenementId("1"),
                    100,
                    150,
                    Collections.emptyList(),
                    List.of(new Intervenant(new Montant(1000, "USD"))),
                    new Lieu(new Montant(2000, "EUR"), 100)
            );
            evenements.ajouter(evenement);
            //Act
            simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION);
        });

        //Assert
        assertTrue(thrown.getMessage().contains("Cannot add two Tarif with different devise"));
    }

    @Test
    @DisplayName("Doit renvoyer une estimation de billeterie avec 10% de rentabilité attendue (sans intervenants ni staff)")
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
        Estimation estimation = simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION);

        //Assert
        assertEquals(2200, estimation.coutEvenement());
        assertEquals(24.2, estimation.tarifBillet());
        assertEquals(91, estimation.nombreMinimumParticipants());
        assertEquals(220, estimation.gainEstime());
        assertEquals(1430, estimation.gainMaximal());
    }
    @Test
    @DisplayName("Doit renvoyer une estimation de billeterie avec 10% de rentabilité attendue (sans staff)")
    void computeWithIntervenant() {
        //Arrange
        Evenement evenement = new Evenement(
                new EvenementId("1"),
                100,
                150,
                Collections.emptyList(),
                List.of(new Intervenant(new Montant(1000, "EUR")), new Intervenant(new Montant(1000, "EUR"))),
                new Lieu(new Montant(2000, "EUR"), 100)
        );
        evenements.ajouter(evenement);

        //Act
        Estimation estimation = simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION);

        //Assert
        assertEquals(4400, estimation.coutEvenement());
        assertEquals(48.4, estimation.tarifBillet());
        assertEquals(91, estimation.nombreMinimumParticipants());
        assertEquals(440, estimation.gainEstime());
        assertEquals(2860, estimation.gainMaximal());
    }

    @Test
    @DisplayName("Doit renvoyer une estimation de billeterie avec 10% de rentabilité attendue")
    void computeWithStaffAndStaff() {
        //Arrange
        Evenement evenement = new Evenement(
                new EvenementId("1"),
                100,
                150,
                List.of(
                        new Staff(new Montant(1000, "EUR"), StaffRole.SECURITE),
                        new Staff(new Montant(1000, "EUR"), StaffRole.TECHNICIEN)
                ),
                List.of(
                        new Intervenant(new Montant(1000, "EUR")),
                        new Intervenant(new Montant(1000, "EUR"))
                ),
                new Lieu(new Montant(2000, "EUR"), 100)
        );
        evenements.ajouter(evenement);

        //Act
        Estimation estimation = simulateurRentabilite.simulerBilleterie("1", 0.1, COMMISSION);

        //Assert
        assertEquals(2200, estimation.coutEvenement());
        assertEquals(24.2, estimation.tarifBillet());
        assertEquals(91, estimation.nombreMinimumParticipants());
        assertEquals(220, estimation.gainEstime());
        assertEquals(1430, estimation.gainMaximal());
    }
}
