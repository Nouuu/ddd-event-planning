package org.esgi.ddd_event_planning.conference.domain.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenants;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieux;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.model.staff.Staffs;

public class CalculateurCoutEvenement {
    private final Staffs staffs;
    private final Lieux lieux;
    private final Intervenants intervenants;

    private static final double COMMISSION = 1.1;

    public CalculateurCoutEvenement(Staffs staffs, Lieux lieux, Intervenants intervenants) {
        this.staffs = staffs;
        this.lieux = lieux;
        this.intervenants = intervenants;
    }

    public double calculerCoutEvenement(String evenementId) {
        return (coutStaffs(evenementId) +
                coutLieu(evenementId) +
                coutIntervenants(evenementId)
        ) * COMMISSION;
    }

    private double coutStaffs(String evenementId) {
        return staffs.recuperer(evenementId).stream().mapToDouble(Staff::cost).sum();
    }

    private double coutLieu(String evenementId) {
        return lieux.recuperer(evenementId).cout();
    }

    private double coutIntervenants(String evenementId) {
        return intervenants.recuperer(evenementId).stream().mapToDouble(Intervenant::cout).sum();
    }
}
