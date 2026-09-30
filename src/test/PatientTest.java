package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientTest {
    
    private PatientDatabase patientDB;
    private Patient patient;
    
    @BeforeEach
    void setUp() {
        patientDB = new PatientDatabase();
        patient = new Patient(1000, "John Doe", "0793333333", "pass123");
        patientDB.addPatient(patient);
    }
    
    @Test
    void test1_AddNewPatient_Success() {
        Patient newPatient = new Patient(2000, "Jane Smith", "0794444444", "pass123");
        patientDB.addPatient(newPatient);
        
        Patient found = patientDB.getPatient(2000);
        assertNotNull(found);
    }
    
    @Test
    void test2_GetExistingPatient_ReturnsPatient() {
        Patient found = patientDB.getPatient(1000);
        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }
    
    @Test
    void test3_GetNonExistentPatient_ReturnsNull() {
        Patient found = patientDB.getPatient(9999);
        assertNull(found);
    }
}