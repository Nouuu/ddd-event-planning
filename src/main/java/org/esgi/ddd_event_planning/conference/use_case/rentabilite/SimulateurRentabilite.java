package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.BilleterieCalculateur;
import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;

public class SimulateurRentabilite {
    private final Evenements evenements;

    public SimulateurRentabilite(Evenements evenements) {
        this.evenements = evenements;
    }

    public Estimation simulerBilleterie(EvenementId evenementId, Montant rentabiliteAttendue, double commission) {
        Evenement evenement = evenements.recuperer(evenementId);
        Montant coutEvenement = evenement.coutOrganisation(commission);
        Montant montantBillet = BilleterieCalculateur.calculerTarifBillet(evenement.participantCible(), coutEvenement, rentabiliteAttendue);
        int nombreMinimumParticipants = BilleterieCalculateur.calculerNombreMinimumParticipants(coutEvenement, montantBillet);
        Montant gainEstime = BilleterieCalculateur.calculerGainEstime(evenement.participantCible(), coutEvenement, montantBillet);
        Montant gainMaximal = BilleterieCalculateur.calculerGainMaximal(evenement.participantMax(), coutEvenement, montantBillet);

        return Estimation.of(coutEvenement, montantBillet, nombreMinimumParticipants, gainEstime, gainMaximal);
    }

}
