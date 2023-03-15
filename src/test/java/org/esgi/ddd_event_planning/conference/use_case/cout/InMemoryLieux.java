package org.esgi.ddd_event_planning.conference.use_case.cout;

import org.esgi.ddd_event_planning.conference.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieux;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLieux implements Lieux {
    private final Map<String, Lieu> lieux = new HashMap<>();

    @Override
    public void add(String eventId, Lieu lieu) {
        lieux.put(eventId, lieu);
    }

    @Override
    public Lieu get(String eventId) {
        return lieux.get(eventId);
    }
}
