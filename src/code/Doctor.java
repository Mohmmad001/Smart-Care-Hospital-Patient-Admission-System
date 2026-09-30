package Final;

public class Doctor extends Actor {
    private String specialization;

    public Doctor(int id, String name, String phone, String specialization, String password){
        super(id, name, phone, password);
        this.specialization = specialization;
    }

    public String getSpecialization(){
        return specialization;
    }
}