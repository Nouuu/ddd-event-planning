package org.esgi.ddd_event_planning.conference.domain.model;

public record Pourcentage(double valeur) {
    public Pourcentage {
        if (valeur < 0 || valeur > 1) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 0 et 1");
        }
    }
}
