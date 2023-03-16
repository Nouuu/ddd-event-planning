package org.esgi.ddd_event_planning.conference.domain.model.staff;

import org.esgi.ddd_event_planning.conference.domain.model.Tarif;

public record Staff(Tarif tarif, StaffFunction function) {
    public static Staff creerStaff(double tarif, String devise, StaffFunction function) {
        return new Staff(new Tarif(tarif, devise), function);
    }
}
