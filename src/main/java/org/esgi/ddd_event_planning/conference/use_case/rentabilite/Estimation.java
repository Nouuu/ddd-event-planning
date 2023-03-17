package org.esgi.ddd_event_planning.conference.use_case.rentabilite;


import org.esgi.ddd_event_planning.shared_kernel.domain.model.Montant;

public record Estimation(double coutEvenement, double tarifBillet, int nombreMinimumParticipants,
                        double gainEstime, double gainMaximal, String currency) {

    public static Estimation of(Montant coutEvenement, Montant montantBillet,
                                int nombreMinimumParticipants, Montant gainEstime, Montant gainMaximal) {
        return new Estimation(coutEvenement.montant(), montantBillet.montant(),
            nombreMinimumParticipants, gainEstime.montant(), gainMaximal.montant(), coutEvenement.devise()
        );
    }
}
