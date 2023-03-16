package org.esgi.ddd_event_planning.conference.model.lieu;

public interface Lieux {
    void ajouter(String eventId, Lieu lieu);

    Lieu recuperer(String eventId);
}
