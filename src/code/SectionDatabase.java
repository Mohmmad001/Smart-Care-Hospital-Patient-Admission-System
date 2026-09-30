package Final;

import java.util.ArrayList;

public class SectionDatabase {

    private ArrayList<Section> sections;

    public SectionDatabase() {
        sections = new ArrayList<>();
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }

    public Section getSection(int id) {
        for (Section s : sections) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public Section getSectionByName(String name) {
        for (Section s : sections) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }
    
    public Room findAvailableRoomInAnySection() {
        for (Section s : sections) {
            Room available = s.findAvailableRoom();
            if (available != null) {
                return available;
            }
        }
        return null;
    }
    
    // NEW: Display all sections
    public void displayAllSections() {
        if (sections.isEmpty()) {
            System.out.println("     No sections found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (Section s : sections) {
                System.out.println("     | ID: " + s.getId() + " | Name: " + s.getName() + " | Rooms: " + s.getRooms().size());
            }
            System.out.println("     +----------------------------------------+");
        }
    }
    
    // NEW: Display all rooms in all sections
    public void displayAllRooms() {
        if (sections.isEmpty()) {
            System.out.println("     No sections found.");
        } else {
            for (Section s : sections) {
                System.out.println("\n     +---------- " + s.getName() + " ----------+");
                if (s.getRooms().isEmpty()) {
                    System.out.println("     |   No rooms in this section.");
                } else {
                    for (Room r : s.getRooms()) {
                        System.out.println("     |   Room " + r.getId() + " (" + r.getType() + ")");
                        System.out.println("     |     Capacity: " + r.getCapacity());
                        System.out.println("     |     Occupied: " + r.getOccupied());
                        System.out.println("     |     Status: " + r.getStatus());
                    }
                }
                System.out.println("     +--------------------------------------+");
            }
        }
    }
}