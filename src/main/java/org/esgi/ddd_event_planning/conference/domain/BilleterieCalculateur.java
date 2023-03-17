package org.esgi.ddd_event_planning.conference.domain;

import org.esgi.ddd_event_planning.shared_kernel.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.Pourcentage;

public final class BilleterieCalculateur {
    public Montant calculerTarifBillet(int nombreDeParticipantCible, Montant coutEvenement, Pourcentage rentabiliteAttendue) {
        var tarif = coutEvenement.multiply(1 + rentabiliteAttendue.valeur()).divide(nombreDeParticipantCible);
        return new Montant(Math.ceil(tarif.montant() * 10) / 10, tarif.devise());
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
