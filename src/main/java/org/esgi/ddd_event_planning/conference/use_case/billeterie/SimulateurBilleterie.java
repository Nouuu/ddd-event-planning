package org.esgi.ddd_event_planning.conference.use_case.billeterie;

import org.esgi.ddd_event_planning.conference.domain.Estimation;
import org.esgi.ddd_event_planning.conference.domain.CalculateurCoutEvenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;
import org.esgi.ddd_event_planning.conference.domain.model.intervenant.Intervenants;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieux;
import org.esgi.ddd_event_planning.conference.domain.model.staff.Staffs;

public class SimulateurBilleterie {
    private final Evenements evenements;
    private final Staffs staffs;
    private final Lieux lieux;
    private final Intervenants intervenants;

    public SimulateurBilleterie(Evenements evenements, Staffs staffs, Lieux lieux, Intervenants intervenants) {
        this.evenements = evenements;
        this.staffs = staffs;
        this.lieux = lieux;
        this.intervenants = intervenants;
    }


    public Estimation simulerBilleterie(String evenementId, double rentabiliteAttendue, double commission) {
        Estimation estimation = new Estimation();
        Evenement evenement = evenements.recuperer(evenementId);

        double coutEvenement = new CalculateurCoutEvenement(commission)
                .calculerCoutEvenement(staffs.recuperer(evenementId), intervenants.recuperer(evenementId), lieux.recuperer(evenementId));

        estimation.calculerTarifBillet(evenement.participantCible(), coutEvenement, rentabiliteAttendue);
        estimation.calculerNombreMinimumParticipants(coutEvenement, estimation.tarifBillet());
        estimation.calculerGainEstime(evenement.participantCible(), coutEvenement, estimation.tarifBillet());
        estimation.calculerGainMaximal(evenement.participantMax(), coutEvenement, estimation.tarifBillet());

        return estimation;
    }
}
