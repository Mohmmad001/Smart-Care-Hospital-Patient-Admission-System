package Final;

public class SystemAccessProxy implements ISystemAccess {
    
    private SystemAccess realSystem;
    private AdminDatabase adminDB;
    private DoctorDatabase doctorDB;
    private NurseDatabase nurseDB;
    
    public SystemAccessProxy(AdminDatabase adminDB, DoctorDatabase doctorDB, NurseDatabase nurseDB) {
        this.adminDB = adminDB;
        this.doctorDB = doctorDB;
        this.nurseDB = nurseDB;
        this.realSystem = new SystemAccess(adminDB, doctorDB, nurseDB);
    }
    
    @Override
    public Admin adminLogin(int id, String password) {
        // First check if admin exists in database
        Admin admin = adminDB.getAdmin(id);
        if (admin != null && admin.getPassword().equals(password)) {
            // If exists and password matches, return from real system
            return realSystem.adminLogin(id, password);
        } else {
            System.out.println("   [ERROR] Invalid Admin ID or password!");
            return null;
        }
    }
    
    @Override
    public Doctor doctorLogin(int id, String password) {
        // First check if doctor exists in database
        Doctor doctor = doctorDB.getDoctor(id);
        if (doctor != null && doctor.getPassword().equals(password)) {
            // If exists and password matches, return from real system
            return realSystem.doctorLogin(id, password);
        } else {
            System.out.println("   [ERROR] Invalid Doctor ID or password!");
            return null;
        }
    }
    
    @Override
    public Nurse nurseLogin(int id, String password) {
        // First check if nurse exists in database
        Nurse nurse = nurseDB.getNurse(id);
        if (nurse != null && nurse.getPassword().equals(password)) {
            // If exists and password matches, return from real system
            return realSystem.nurseLogin(id, password);
        } else {
            System.out.println("   [ERROR] Invalid Nurse ID or password!");
            return null;
        }
    }
}