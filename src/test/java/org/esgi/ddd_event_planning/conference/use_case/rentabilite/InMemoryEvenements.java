package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenement;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.EvenementId;
import org.esgi.ddd_event_planning.conference.domain.model.evenement.Evenements;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEvenements implements Evenements {
    private final List<Evenement> evenements = new ArrayList<>();
    @Override
    public void ajouter(Evenement evenement) {
        if (evenements.stream().noneMatch(e -> evenement.evenementId().equals(e.evenementId()))) {
            evenements.add(evenement);
        }
    }

    @Override
    public Evenement recuperer(EvenementId eventId) {
        if (evenements.stream().anyMatch(e -> e.evenementId().equals(eventId))) {
            return evenements.stream().filter(e -> e.evenementId().equals(eventId)).findFirst().get();
        }
        throw new RuntimeException("Evenement not found");
    }
}
