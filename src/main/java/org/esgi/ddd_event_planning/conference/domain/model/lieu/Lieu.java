package org.esgi.ddd_event_planning.conference.domain.model.lieu;

import org.esgi.ddd_event_planning.conference.domain.model.Tarif;

public record Lieu(Tarif tarif, double surfaceEnM2) {
}
