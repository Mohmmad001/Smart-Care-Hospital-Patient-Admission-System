package Final;

import java.util.ArrayList;

public class Patient extends Actor {

	private ArrayList<MedicalProcedure> procedures = new ArrayList<>();
    private Doctor assignedDoctor;
    private Section assignedSection;
    private Room assignedRoom;
    private String admissionStartDate;
    private String admissionEndDate;
    private boolean isAdmitted;

    public Patient(int id, String name, String phone, String password) {
        super(id, name, phone, password);
        this.isAdmitted = false;
    }

    // Admit patient - simple and direct
    public void admit(Doctor doctor, Section section, String startDate, String endDate) {
        // Find available room from the section
        Room availableRoom = section.findAvailableRoom();
        
        if (availableRoom == null) {
            System.out.println("No available rooms in " + section.getName() + " section!");
            return;
        }
        
        // Add patient to the room
        availableRoom.addPatient(this);
        
        // Set patient data
        this.assignedDoctor = doctor;
        this.assignedSection = section;
        this.assignedRoom = availableRoom;
        this.admissionStartDate = startDate;
        this.admissionEndDate = endDate;
        this.isAdmitted = true;
        
        System.out.println("\n✓ Patient " + name + " admitted successfully!");
        System.out.println("  Section: " + section.getName());
        System.out.println("  Room: " + availableRoom.getId() + " (" + availableRoom.getType() + ")");
        System.out.println("  Period: " + startDate + " to " + endDate);
        System.out.println("  Doctor: " + doctor.getName());
    }
    
    // Discharge patient
    public void discharge() {
        if (assignedRoom != null) {
            assignedRoom.removePatient(this);
        }
        
        this.assignedDoctor = null;
        this.assignedSection = null;
        this.assignedRoom = null;
        this.admissionStartDate = "";
        this.admissionEndDate = "";
        this.isAdmitted = false;
        
        System.out.println(" Patient " + name + " discharged successfully!");
    }
    
    // Getters
    public boolean isAdmitted() {
        return isAdmitted;
    }
    
    public String getAdmissionStartDate() {
        return admissionStartDate;
    }
    
    public String getAdmissionEndDate() {
        return admissionEndDate;
    }
    
    public String getAdmissionPeriod() {
        if (!isAdmitted) {
            return "Not admitted";
        }
        return admissionStartDate + " to " + admissionEndDate;
    }
    
    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }
    
    public Section getAssignedSection() {
        return assignedSection;
    }
    
    public Room getAssignedRoom() {
        return assignedRoom;
    }
    
    public void setAssignedDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }
    
    // Procedure methods
    public void addProcedure(MedicalProcedure p) {
        procedures.add(p);
    }

    public ArrayList<MedicalProcedure> getProcedures() {
        return procedures;
    }
}