package Final;

import java.util.ArrayList;

public class PatientReport extends ReportGenerator {

    private ArrayList<Patient> patients;

    public PatientReport(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    @Override
    protected String writeTitle() {
        return "================== PATIENT REPORT ==================\n";
    }

    @Override
    protected String writeSummary() {
        int total = patients.size();
        int admitted = 0;
        int discharged = 0;
        
        for (Patient p : patients) {
            if (p.isAdmitted()) {
                admitted++;
            } else {
                discharged++;
            }
        }
        
        return "SUMMARY: This report shows all registered patients in the system.\n" +
               "         Total Patients: " + total + " | Admitted: " + admitted + " | Discharged/Not Admitted: " + discharged + "\n";
    }

    @Override
    protected String writeContent() {
        if (patients.isEmpty()) {
            return "No patients found in the system.\n";
        }
        
        String content = "\n";
        for (Patient p : patients) {
            content += "----------------------------------------\n";
            content += "Patient ID: " + p.getId() + "\n";
            content += "Name: " + p.getName() + "\n";
            content += "Phone: " + p.phone + "\n";
            
            if (p.isAdmitted()) {
                content += "Status: ADMITTED\n";
                content += "Admission Period: " + p.getAdmissionPeriod() + "\n";
                content += "Assigned Doctor: " + p.getAssignedDoctor().getName() + "\n";
                content += "Section: " + p.getAssignedSection().getName() + "\n";
                content += "Room: " + p.getAssignedRoom().getId() + " (" + p.getAssignedRoom().getType() + ")\n";
            } else {
                content += "Status: NOT ADMITTED / DISCHARGED\n";
            }
            
            content += "Medical Procedures: " + p.getProcedures().size() + "\n";
            for (MedicalProcedure proc : p.getProcedures()) {
                content += "  - " + proc.getName() + ": " + (proc.isDone() ? "DONE" : "PENDING") + "\n";
            }
        }
        content += "----------------------------------------\n";
        return content;
    }
}