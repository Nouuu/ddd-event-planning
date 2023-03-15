package org.esgi.ddd_event_planning.conference.use_case.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenants;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieux;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.model.staff.Staffs;

public class CalculerCoutEvenement {
    private final Staffs staffs;
    private final Lieux lieux;
    private final Intervenants intervenants;

    private static final double COMMISSION = 1.1;

    public CalculerCoutEvenement(Staffs staffs, Lieux lieux, Intervenants intervenants) {
        this.staffs = staffs;
        this.lieux = lieux;
        this.intervenants = intervenants;
    }

    public double calculer(String eventId) {
        return (coutStaffs(eventId) +
                coutLieu(eventId) +
                coutIntervenants(eventId)
        ) * COMMISSION;
    }

    private double coutStaffs(String eventId) {
        return staffs.get(eventId).stream().mapToDouble(Staff::cost).sum();
    }

    private double coutLieu(String eventId) {
        return lieux.get(eventId).cout();
    }

    private double coutIntervenants(String eventId) {
        return intervenants.get(eventId).stream().mapToDouble(Intervenant::cout).sum();
    }
}
