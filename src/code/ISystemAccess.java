package Final;

public interface ISystemAccess {
    Admin adminLogin(int id, String password);
    Doctor doctorLogin(int id, String password);
    Nurse nurseLogin(int id, String password);
}