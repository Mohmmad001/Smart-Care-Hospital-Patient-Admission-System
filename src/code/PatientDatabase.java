package Final;

import java.util.ArrayList;

public class PatientDatabase {

    private ArrayList<Patient> patients;

    public PatientDatabase() {
        patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public Patient getPatient(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
    
    //  Display all patients with details
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("     No patients found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (Patient p : patients) {
                System.out.println("     | ID: " + p.getId() + " | Name: " + p.getName() + " | Phone: " + p.phone);
                if (p.isAdmitted()) {
                    System.out.println("     |   -> Room: " + p.getAssignedRoom().getId() + " | Doctor: " + p.getAssignedDoctor().getName());
                }
                System.out.println("     |   -> Procedures: " + p.getProcedures().size());
            }
            System.out.println("     +----------------------------------------+");
        }
    }
}