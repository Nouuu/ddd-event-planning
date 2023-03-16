package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.BilleterieCalculateur;
import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;

public class SimulateurRentabilite {
    private final Evenements evenements;
    private final BilleterieCalculateur calculateur;

    public SimulateurRentabilite(Evenements evenements, BilleterieCalculateur calculateur) {
        this.evenements = evenements;
        this.calculateur = calculateur;
    }

    public Estimation simulerBilleterie(EvenementId evenementId, double rentabiliteAttendue, double commission) {
        Evenement evenement = evenements.recuperer(evenementId);
        Montant coutEvenement = evenement.coutOrganisation(commission);

        Montant montantBillet = calculateur.calculerTarifBillet(evenement.participantCible(), coutEvenement, rentabiliteAttendue);
        int nombreMinimumParticipants = calculateur.calculerNombreMinimumParticipants(coutEvenement, montantBillet);
        Montant gainEstime = calculateur.calculerGainEstime(evenement.participantCible(), coutEvenement, montantBillet);
        Montant gainMaximal = calculateur.calculerGainMaximal(evenement.participantMax(), coutEvenement, montantBillet);

        return Estimation.of(coutEvenement, montantBillet, nombreMinimumParticipants, gainEstime, gainMaximal);
    }

}
