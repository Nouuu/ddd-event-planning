package org.esgi.ddd_event_planning.conference.model.staff;

import java.util.List;

public interface Staffs {
    void add(String eventId, Staff staff);
    List<Staff> get(String eventId);
}
