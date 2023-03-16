package org.esgi.ddd_event_planning.conference.use_case.billeterie;

import org.esgi.ddd_event_planning.conference.domain.billeterie.CalculateurPrixBillet;
import org.esgi.ddd_event_planning.conference.domain.cout.CalculateurCoutEvenement;
import org.esgi.ddd_event_planning.conference.domain.gain.CalculateurGain;
import org.esgi.ddd_event_planning.conference.domain.participant.CalculateurParticipantsMinimum;
import org.esgi.ddd_event_planning.conference.model.billeterie.Estimation;

public class SimulateurBilleterie {
    private final CalculateurCoutEvenement calculateurCoutEvenement;
    private final CalculateurPrixBillet calculateurPrixBillet;
    private final CalculateurParticipantsMinimum calculateurParticipantsMinimum;
    private final CalculateurGain calculateurGain;

    public SimulateurBilleterie(CalculateurCoutEvenement calculateurCoutEvenement, CalculateurPrixBillet calculateurPrixBillet, CalculateurParticipantsMinimum calculateurParticipantsMinimum, CalculateurGain calculateurGain) {
        this.calculateurCoutEvenement = calculateurCoutEvenement;
        this.calculateurPrixBillet = calculateurPrixBillet;
        this.calculateurParticipantsMinimum = calculateurParticipantsMinimum;
        this.calculateurGain = calculateurGain;
    }


    public Estimation simulerBilleterie(String evenementId, double rentabiliteAttendue) {

        double coutEvenement = calculateurCoutEvenement.calculerCoutEvenement(evenementId);
        double tarifConseille = calculateurPrixBillet.calculerPrixBilletRentable(evenementId, coutEvenement, rentabiliteAttendue);
        int participantsMinimum = calculateurParticipantsMinimum.calculerNombreMinimumParticipants(tarifConseille, coutEvenement);
        double gainEstime = calculateurGain.calculerGainEstime(evenementId, coutEvenement, tarifConseille);
        double gainMaximum = calculateurGain.calculerGainMaximum(evenementId, coutEvenement, tarifConseille);


        return new Estimation(
                tarifConseille,
                gainEstime,
                gainMaximum,
                participantsMinimum
        );
    }
}
