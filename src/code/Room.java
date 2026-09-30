package Final;

import java.util.ArrayList;

public class Room {

    private int id;
    private String type;
    private int capacity;
    private ArrayList<Patient> patients;

    public Room(int id, String type){
        this.id = id;
        this.type = type;
        this.patients = new ArrayList<>();

        if(type.equals("PRIVATE")){
            this.capacity = 1;
        } else if(type.equals("SHARED_2")){
            this.capacity = 2;
        } else if(type.equals("SHARED_4")){
            this.capacity = 4;
        } else {
            this.capacity = 1;
        }
    }

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getOccupied(){
        return patients.size();
    }

    public boolean hasSpace(){
        return patients.size() < capacity;
    }

    public void addPatient(Patient patient){
        if(hasSpace()){
            patients.add(patient);
            System.out.println("  → Patient added to Room " + id);
        }
    }

    public void removePatient(Patient patient){
        patients.remove(patient);
        System.out.println("  → Patient removed from Room " + id);
    }
    
    public ArrayList<Patient> getPatients(){
        return patients;
    }
    
    public void displayPatients(){
        if(patients.isEmpty()){
            System.out.println("    No patients in this room");
        } else {
            for(Patient p : patients){
                System.out.println("    - " + p.getName() + " (ID: " + p.getId() + ")");
            }
        }
    }

    public String getStatus(){
        return (patients.size() >= capacity) ? "FULL" : "AVAILABLE";
    }
}