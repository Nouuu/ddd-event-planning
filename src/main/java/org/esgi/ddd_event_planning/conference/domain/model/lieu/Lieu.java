package org.esgi.ddd_event_planning.conference.domain.model.lieu;

import org.esgi.ddd_event_planning.shared_kernel.domain.model.Montant;

public record Lieu(Montant montant, double surfaceEnM2) {
    public Lieu {
        if (surfaceEnM2 < 0) {
            throw new IllegalArgumentException("Surface cannot be negative");
        }
    }
}
