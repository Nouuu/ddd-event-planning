package org.esgi.ddd_event_planning.conference.model.evenement;

public interface Evenements {
    void ajouter(Evenement evenement);
    Evenement recuperer(String eventId);
}
