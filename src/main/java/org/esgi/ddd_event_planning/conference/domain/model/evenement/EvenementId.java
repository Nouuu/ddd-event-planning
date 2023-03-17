package org.esgi.ddd_event_planning.conference.domain.model.evenement;

import java.util.Objects;

public record EvenementId(String identifiant) {
    public EvenementId {
        Objects.requireNonNull(identifiant);
    }
}
