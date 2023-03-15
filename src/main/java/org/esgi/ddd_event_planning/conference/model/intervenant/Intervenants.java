package org.esgi.ddd_event_planning.conference.model.intervenant;

import java.util.List;

public interface Intervenants {
    void add(String eventId, Intervenant intervenant);
    List<Intervenant> get(String eventId);
}
