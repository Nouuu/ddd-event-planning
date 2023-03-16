package org.esgi.ddd_event_planning.conference.domain;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;

public final class BilleterieCalculateur {
    private BilleterieCalculateur() {
        throw new AssertionError();
    }
    public static Montant calculerTarifBillet(int nombreDeParticipantCible, Montant coutEvenement, Montant rentabiliteAttendue) {
        return coutEvenement.multiply(1 + rentabiliteAttendue.montant() / 100).divide(nombreDeParticipantCible);
    }

    public static int calculerNombreMinimumParticipants(Montant coutEvenement, Montant montantBillet) {
        return (int) Math.ceil(coutEvenement.divide(montantBillet).montant());
    }

    public static Montant calculerGainEstime(int nombreParticipantsCible, Montant coutEvenement, Montant montantBillet) {
        return montantBillet.multiply(nombreParticipantsCible).subtract(coutEvenement);
    }

    public static Montant calculerGainMaximal(int nombreParticipantsMax, Montant coutEvenement, Montant montantBillet) {
        return montantBillet.multiply(nombreParticipantsMax).subtract(coutEvenement);
    }
}
