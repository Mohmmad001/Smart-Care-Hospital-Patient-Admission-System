package Final;

import java.util.ArrayList;

public class SectionReport extends ReportGenerator {

    private ArrayList<Section> sections;

    public SectionReport(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @Override
    protected String writeTitle() {
        return "================== SECTION REPORT ==================\n";
    }

    @Override
    protected String writeSummary() {
        int totalSections = sections.size();
        int totalRooms = 0;
        int totalAvailableRooms = 0;
        int totalCapacity = 0;
        int totalOccupied = 0;
        
        for (Section s : sections) {
            for (Room r : s.getRooms()) {
                totalRooms++;
                totalCapacity += r.getCapacity();
                totalOccupied += r.getOccupied();
                if (r.hasSpace()) {
                    totalAvailableRooms++;
                }
            }
        }
        
        return "SUMMARY: This report shows all hospital sections and their room distribution.\n" +
               "         Total Sections: " + totalSections + " | Total Rooms: " + totalRooms + "\n" +
               "         Available Rooms: " + totalAvailableRooms + " | Full Rooms: " + (totalRooms - totalAvailableRooms) + "\n" +
               "         Total Capacity: " + totalCapacity + " | Total Occupied: " + totalOccupied + "\n";
    }

    @Override
    protected String writeContent() {
        if (sections.isEmpty()) {
            return "No sections found in the system.\n";
        }
        
        String content = "\n";
        for (Section s : sections) {
            int availableRooms = 0;
            int sectionOccupied = 0;
            int sectionCapacity = 0;
            
            for (Room r : s.getRooms()) {
                if (r.hasSpace()) availableRooms++;
                sectionOccupied += r.getOccupied();
                sectionCapacity += r.getCapacity();
            }
            
            content += "----------------------------------------\n";
            content += "Section: " + s.getName() + " (ID: " + s.getId() + ")\n";
            content += "Total Rooms: " + s.getRooms().size() + "\n";
            content += "Available Rooms: " + availableRooms + "\n";
            content += "Occupancy: " + sectionOccupied + "/" + sectionCapacity + " patients\n";
            content += "\n";
            content += "Rooms in this section:\n";
            
            for (Room r : s.getRooms()) {
                content += "  - Room " + r.getId() + " (" + r.getType() + "): " + r.getStatus() + 
                          " (" + r.getOccupied() + "/" + r.getCapacity() + ")\n";
            }
        }
        content += "----------------------------------------\n";
        return content;
    }
}