package org.esgi.ddd_event_planning.conference.domain.model.staff;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;

public record Staff(Montant montant, StaffRole function) {
}
