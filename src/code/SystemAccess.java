package Final;

public class SystemAccess implements ISystemAccess {
    
    private AdminDatabase adminDB;
    private DoctorDatabase doctorDB;
    private NurseDatabase nurseDB;
    
    public SystemAccess(AdminDatabase adminDB, DoctorDatabase doctorDB, NurseDatabase nurseDB) {
        this.adminDB = adminDB;
        this.doctorDB = doctorDB;
        this.nurseDB = nurseDB;
    }
    
    @Override
    public Admin adminLogin(int id, String password) {
        
        return adminDB.getAdmin(id);
    }
    
    @Override
    public Doctor doctorLogin(int id, String password) {

        return doctorDB.getDoctor(id);
    }
    
    @Override
    public Nurse nurseLogin(int id, String password) {
        
        return nurseDB.getNurse(id);
    }
}