package org.esgi.ddd_event_planning.conference.use_case.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryIntervenants implements Intervenants {
    private final Map<String, List<Intervenant>> intervenants = new HashMap<>();

    @Override
    public void add(String eventId, Intervenant intervenant) {
        if (!intervenants.containsKey(eventId)) {
            intervenants.put(eventId, new ArrayList<>());
        }
        intervenants.get(eventId).add(intervenant);
    }

    @Override
    public List<Intervenant> get(String eventId) {
        return intervenants.computeIfAbsent(eventId, k -> new ArrayList<>());
    }
}
