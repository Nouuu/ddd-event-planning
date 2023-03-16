package org.esgi.ddd_event_planning.conference.use_case.billeterie;

import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieux;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLieux implements Lieux {
    private final Map<String, Lieu> lieux = new HashMap<>();

    @Override
    public void ajouter(String eventId, Lieu lieu) {
        lieux.put(eventId, lieu);
    }

    @Override
    public Lieu recuperer(String eventId) {
        return lieux.get(eventId);
    }
}
