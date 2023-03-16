package org.esgi.ddd_event_planning.conference.domain.billeterie;

import org.esgi.ddd_event_planning.conference.model.evenement.Evenements;

public class CalculateurPrixBillet {
    private final Evenements evenements;

    public CalculateurPrixBillet(Evenements evenements) {
        this.evenements = evenements;
    }

    public double calculerPrixBilletRentable(String evenementId, double cout, double rentabiliteAttendue) {
        return 0;
    }
}
