package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.domain.model.intervenant.Intervenants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryIntervenants implements Intervenants {
    private final Map<String, List<Intervenant>> intervenants = new HashMap<>();

    @Override
    public void ajouter(String eventId, Intervenant intervenant) {
        if (!intervenants.containsKey(eventId)) {
            intervenants.put(eventId, new ArrayList<>());
        }
        intervenants.get(eventId).add(intervenant);
    }

    @Override
    public List<Intervenant> recuperer(String eventId) {
        return intervenants.computeIfAbsent(eventId, k -> new ArrayList<>());
    }
}
