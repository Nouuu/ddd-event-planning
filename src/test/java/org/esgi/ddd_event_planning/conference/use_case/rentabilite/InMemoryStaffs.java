package org.esgi.ddd_event_planning.conference.use_case.rentabilite;

import org.esgi.ddd_event_planning.conference.domain.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.domain.model.staff.Staffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStaffs implements Staffs {
    private final Map<String, List<Staff>> staffs = new HashMap<>();


    @Override
    public void ajouter(String eventId, Staff staff) {
        if (!staffs.containsKey(eventId)) {
            staffs.put(eventId, new ArrayList<>());
        }
        staffs.get(eventId).add(staff);
    }

    @Override
    public List<Staff> recuperer(String eventId) {
        return staffs.computeIfAbsent(eventId, k -> new ArrayList<>());
    }
}
