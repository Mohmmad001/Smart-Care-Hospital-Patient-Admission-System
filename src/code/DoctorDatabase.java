package Final;

import java.util.ArrayList;

public class DoctorDatabase {

    private ArrayList<Doctor> doctors;

    public DoctorDatabase() {
        doctors = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public Doctor getDoctor(int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    
   
    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("     No doctors found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (Doctor d : doctors) {
                System.out.println("     | ID: " + d.getId() + " | Name: " + d.getName() + " | Spec: " + d.getSpecialization());
            }
            System.out.println("     +----------------------------------------+");
        }
    }
}