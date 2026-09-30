package Final;

import java.util.ArrayList;

public class MedicalProcedureDatabase {

    private ArrayList<MedicalProcedure> procedures;

    public MedicalProcedureDatabase() {
        procedures = new ArrayList<>();
    }

    public void addProcedure(MedicalProcedure procedure) {
        procedures.add(procedure);
    }

    public void removeProcedure(MedicalProcedure procedure) {
        procedures.remove(procedure);
    }

    public MedicalProcedure getProcedure(int id) {
        for (MedicalProcedure p : procedures) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<MedicalProcedure> getProcedures() {
        return procedures;
    }
    
   
    public void displayAllProcedures() {
        if (procedures.isEmpty()) {
            System.out.println("     No procedures found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (MedicalProcedure p : procedures) {
                System.out.println("     | ID: " + p.getId() + " | Name: " + p.getName() + " | Status: " + (p.isDone() ? "DONE" : "PENDING"));
            }
            System.out.println("     +----------------------------------------+");
        }
    }
}