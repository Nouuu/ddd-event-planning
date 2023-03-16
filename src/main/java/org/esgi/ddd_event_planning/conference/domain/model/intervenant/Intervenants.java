package org.esgi.ddd_event_planning.conference.domain.model.intervenant;

import java.util.List;

public interface Intervenants {
    void ajouter(String eventId, Intervenant intervenant);

    List<Intervenant> recuperer(String eventId);
}
