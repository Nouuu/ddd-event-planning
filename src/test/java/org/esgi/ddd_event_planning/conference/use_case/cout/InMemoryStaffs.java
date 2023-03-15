package org.esgi.ddd_event_planning.conference.use_case.cout;

import org.esgi.ddd_event_planning.conference.model.staff.Staff;
import org.esgi.ddd_event_planning.conference.model.staff.Staffs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStaffs implements Staffs {
    private final Map<String, List<Staff>> staffs = new HashMap<>();


    @Override
    public void add(String eventId, Staff staff) {
        if (!staffs.containsKey(eventId)) {
            staffs.put(eventId, new ArrayList<>());
        }
        staffs.get(eventId).add(staff);
    }

    @Override
    public List<Staff> get(String eventId) {
        return staffs.computeIfAbsent(eventId, k -> new ArrayList<>());
    }
}
