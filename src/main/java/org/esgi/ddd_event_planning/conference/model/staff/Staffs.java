package org.esgi.ddd_event_planning.conference.model.staff;

import java.util.List;

public interface Staffs {
    void ajouter(String eventId, Staff staff);

    List<Staff> recuperer(String eventId);
}