package Final;

import java.util.ArrayList;

public class MedicalProcedureReport extends ReportGenerator {

    private ArrayList<MedicalProcedure> procedures;

    public MedicalProcedureReport(ArrayList<MedicalProcedure> procedures) {
        this.procedures = procedures;
    }

    @Override
    protected String writeTitle() {
        return "================== MEDICAL PROCEDURE REPORT ==================\n";
    }

    @Override
    protected String writeSummary() {
        int total = procedures.size();
        int completed = 0;
        int pending = 0;
        
        for (MedicalProcedure p : procedures) {
            if (p.isDone()) {
                completed++;
            } else {
                pending++;
            }
        }
        
        double completionRate = (total > 0) ? (completed * 100.0 / total) : 0;
        
        return "SUMMARY: This report shows all medical procedures available in the system.\n" +
               "         Total Procedures: " + total + "\n" +
               "         Completed: " + completed + " | Pending: " + pending + "\n" +
               "         Completion Rate: " + String.format("%.1f", completionRate) + "%\n";
    }

    @Override
    protected String writeContent() {
        if (procedures.isEmpty()) {
            return "No medical procedures found in the system.\n";
        }
        
        String content = "\n";
        
        content += "COMPLETED PROCEDURES:\n";
        boolean hasCompleted = false;
        for (MedicalProcedure p : procedures) {
            if (p.isDone()) {
                content += "  - " + p.getName() + " (ID: " + p.getId() + ")\n";
                hasCompleted = true;
            }
        }
        if (!hasCompleted) {
            content += "  (none)\n";
        }
        
        content += "\nPENDING PROCEDURES:\n";
        boolean hasPending = false;
        for (MedicalProcedure p : procedures) {
            if (!p.isDone()) {
                content += "  - " + p.getName() + " (ID: " + p.getId() + ")\n";
                hasPending = true;
            }
        }
        if (!hasPending) {
            content += "  (none)\n";
        }
        
        return content;
    }
}