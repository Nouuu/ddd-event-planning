package org.esgi.ddd_event_planning.conference.domain.model.staff;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;

public record Staff(Montant montant, StaffFunction function) {
    public static Staff creerStaff(double tarif, String devise, StaffFunction function) {
        return new Staff(new Montant(tarif, devise), function);
    }
}
