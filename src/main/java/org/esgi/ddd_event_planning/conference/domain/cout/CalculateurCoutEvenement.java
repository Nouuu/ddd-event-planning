package org.esgi.ddd_event_planning.conference.domain.cout;

import org.esgi.ddd_event_planning.conference.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.model.staff.Staff;

import java.util.List;

public class CalculateurCoutEvenement {
    private final double commission;

    public CalculateurCoutEvenement(double commission) {
        this.commission = commission;
    }

    public double calculerCoutEvenement(List<Staff> staffs, List<Intervenant> intervenants, Lieu lieu) {
        return (coutStaffs(staffs) +
                lieu.cout() +
                coutIntervenants(intervenants)
        ) * (1 + commission);
    }

    private double coutStaffs(List<Staff> staffs) {
        return staffs.stream().mapToDouble(Staff::cost).sum();
    }

    private double coutIntervenants(List<Intervenant> intervenants) {
        return intervenants.stream().mapToDouble(Intervenant::cout).sum();
    }
}
