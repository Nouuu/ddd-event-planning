package org.esgi.ddd_event_planning.conference.domain;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;

public final class BilleterieCalculateur {
    public Montant calculerTarifBillet(int nombreDeParticipantCible, Montant coutEvenement, double rentabiliteAttendue) {
        return coutEvenement.multiply(1 + rentabiliteAttendue).divide(nombreDeParticipantCible);
    }

    public int calculerNombreMinimumParticipants(Montant coutEvenement, Montant montantBillet) {
        return (int) Math.ceil(coutEvenement.divide(montantBillet).montant());
    }

    public Montant calculerGainEstime(int nombreParticipantsCible, Montant coutEvenement, Montant montantBillet) {
        return montantBillet.multiply(nombreParticipantsCible).subtract(coutEvenement);
    }

    public Montant calculerGainMaximal(int nombreParticipantsMax, Montant coutEvenement, Montant montantBillet) {
        return montantBillet.multiply(nombreParticipantsMax).subtract(coutEvenement);
    }
}
