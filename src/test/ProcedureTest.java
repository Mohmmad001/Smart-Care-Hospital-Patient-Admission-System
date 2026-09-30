package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcedureTest {
    
    private Patient patient;
    private MedicalProcedure procedure;
    
    @BeforeEach
    void setUp() {
        patient = new Patient(1000, "John Doe", "0793333333", "pass123");
        procedure = new MedicalProcedure(1, "Blood Test");
    }
    
    @Test
    void test1_AddProcedureToPatient_CountIncreases() {
        int before = patient.getProcedures().size();
        patient.addProcedure(procedure);
        int after = patient.getProcedures().size();
        
        assertEquals(before+1 , after);
    }
    
    @Test
    void test2_MarkProcedureAsDone_IsTrue() {
        procedure.markDone();
        assertTrue(procedure.isDone());
    }
    
    @Test
    void test3_NewProcedure_IsNotDone() {
        assertFalse(procedure.isDone());
    }
}