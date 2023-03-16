package org.esgi.ddd_event_planning.conference.model.billeterie;

public record Estimation(
        double tarifConseille,
        double gainEstime,
        double gainMaximal,
        long participantsMinimum
) {
}
