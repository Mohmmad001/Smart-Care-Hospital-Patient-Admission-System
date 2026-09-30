package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    
    private AdminDatabase adminDB;
    private DoctorDatabase doctorDB;
    private NurseDatabase nurseDB;
    private ISystemAccess system;
    
    @BeforeEach
    void setUp() {
        adminDB = new AdminDatabase();
        doctorDB = new DoctorDatabase();
        nurseDB = new NurseDatabase();
        
        adminDB.addAdmin(new Admin(1, "Admin", "0790000000", "admin123"));
        doctorDB.addDoctor(new Doctor(100, "Doctor", "0791111111", "Cardiology", "doc123"));
        nurseDB.addNurse(new Nurse(200, "Nurse", "0792222222", "nurse123"));
        
        system = new SystemAccessProxy(adminDB, doctorDB, nurseDB);
    }
    
    @Test
    void test1_ValidAdminLogin_ReturnsAdmin() {
        Admin result = system.adminLogin(1, "admin123");
        assertNotNull(result);
    }
    
    @Test
    void test2_ValidDoctorLogin_ReturnsDoctor() {
        Doctor result = system.doctorLogin(100, "doc123");
        assertNotNull(result);
    }
    
    @Test
    void test3_InvalidLogin_ReturnsNull() {
        Admin result = system.adminLogin(999, "wrong");
        assertNull(result);
    }
}