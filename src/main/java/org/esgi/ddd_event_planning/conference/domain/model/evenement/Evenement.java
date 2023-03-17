package org.esgi.ddd_event_planning.conference.domain.model.evenement;

import org.esgi.ddd_event_planning.conference.domain.model.Montant;
import org.esgi.ddd_event_planning.conference.domain.model.Pourcentage;
import org.esgi.ddd_event_planning.conference.domain.model.intervenant.Intervenant;
import org.esgi.ddd_event_planning.conference.domain.model.lieu.Lieu;
import org.esgi.ddd_event_planning.conference.domain.model.staff.Staff;

import java.util.List;


public class Evenement {
    private final EvenementId evenementId;
    private Integer participantCible;
    private Integer participantMax;
    private final List<Staff> staffs;
    private final List<Intervenant> intervenants;
    private Lieu lieu;

    public Evenement(EvenementId evenementId, int participantCible, int participantMax, List<Staff> staffs, List<Intervenant> intervenants, Lieu lieu) {
        this.evenementId = evenementId;
        this.participantCible = participantCible;
        this.participantMax = participantMax;
        this.staffs = staffs;
        this.intervenants = intervenants;
        this.lieu = lieu;
    }

    public Montant coutOrganisation(Pourcentage commission) {
        var intervenantTarif = intervenants.stream()
                .map(Intervenant::montant)
                .reduce(Montant::add)
                .orElse(new Montant(0, "EUR"));
        var staffTarif = staffs.stream()
                .map(Staff::montant)
                .reduce(Montant::add)
                .orElse(new Montant(0, "EUR"));
        var coutLieu = lieu.montant();
        return intervenantTarif.add(staffTarif).add(coutLieu).multiply(1 + commission.valeur());
    }

    public EvenementId evenementId() {
        return evenementId;
    }

    public int participantCible() {
        return participantCible;
    }

    public int participantMax() {
        return participantMax;
    }
}
