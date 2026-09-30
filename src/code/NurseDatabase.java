package Final;

import java.util.ArrayList;

public class NurseDatabase {

    private ArrayList<Nurse> nurses;

    public NurseDatabase() {
        nurses = new ArrayList<>();
    }

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public void removeNurse(Nurse nurse) {
        nurses.remove(nurse);
    }

    public Nurse getNurse(int id) {
        for (Nurse n : nurses) {
            if (n.getId() == id) {
                return n;
            }
        }
        return null;
    }

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }
    
    
    public void displayAllNurses() {
        if (nurses.isEmpty()) {
            System.out.println("     No nurses found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (Nurse n : nurses) {
                System.out.println("     | ID: " + n.getId() + " | Name: " + n.getName());
            }
            System.out.println("     +----------------------------------------+");
        }
    }
}