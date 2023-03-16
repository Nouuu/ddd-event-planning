package org.esgi.ddd_event_planning.conference.domain.billeterie;

public class Estimation {
    private double tarifBillet;
    private double gainEstime;
    private double gainMaximal;
    private long participantsMinimum;

    public void calculerTarifBillet(int nombreParticipantsCible , double coutEvenement, double rentabiliteAttendue) {
        // TODO
    }

    public void calculerNombreMinimumParticipants(double coutEvenement, double tarifBillet) {
        // TODO
    }

    public void  calculerGainEstime(int nombreParticipantsCible, double coutEvenement, double tarifBillet) {
        // TODO
    }

    public void calculerGainMaximal(int nombreParticipantsCible, double coutEvenement, double tarifBillet) {
        // TODO
    }

    public double tarifBillet() {
        return tarifBillet;
    }

    public double gainEstime() {
        return gainEstime;
    }

    public double gainMaximal() {
        return gainMaximal;
    }

    public long participantsMinimum() {
        return participantsMinimum;
    }
}
