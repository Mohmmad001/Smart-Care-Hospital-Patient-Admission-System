package Final;

import java.util.ArrayList;

public class RoomReport extends ReportGenerator {

    private ArrayList<Room> rooms;

    public RoomReport(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    protected String writeTitle() {
        return "================== ROOM REPORT ==================\n";
    }

    @Override
    protected String writeSummary() {
        int total = rooms.size();
        int available = 0;
        int full = 0;
        int totalCapacity = 0;
        int totalOccupied = 0;
        
        for (Room r : rooms) {
            totalCapacity += r.getCapacity();
            totalOccupied += r.getOccupied();
            if (r.hasSpace()) {
                available++;
            } else {
                full++;
            }
        }
        
        double occupancyRate = (totalCapacity > 0) ? (totalOccupied * 100.0 / totalCapacity) : 0;
        
        return "SUMMARY: This report shows all rooms and their current status.\n" +
               "         Total Rooms: " + total + " | Available: " + available + " | Full: " + full + "\n" +
               "         Total Capacity: " + totalCapacity + " | Currently Occupied: " + totalOccupied + "\n" +
               "         Overall Occupancy Rate: " + String.format("%.1f", occupancyRate) + "%\n";
    }

    @Override
    protected String writeContent() {
        if (rooms.isEmpty()) {
            return "No rooms found in the system.\n";
        }
        
        String content = "\n";
        for (Room r : rooms) {
            content += "----------------------------------------\n";
            content += "Room ID: " + r.getId() + "\n";
            content += "Room Type: " + r.getType() + "\n";
            content += "Capacity: " + r.getCapacity() + " patients\n";
            content += "Currently Occupied: " + r.getOccupied() + " patients\n";
            content += "Status: " + r.getStatus() + "\n";
            content += "Available Space: " + (r.getCapacity() - r.getOccupied()) + " spots\n";
        }
        content += "----------------------------------------\n";
        return content;
    }
}