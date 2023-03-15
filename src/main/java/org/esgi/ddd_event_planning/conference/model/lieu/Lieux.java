package org.esgi.ddd_event_planning.conference.model.lieu;

public interface Lieux {
    void add(String eventId, Lieu lieu);
    Lieu get(String eventId);
}
