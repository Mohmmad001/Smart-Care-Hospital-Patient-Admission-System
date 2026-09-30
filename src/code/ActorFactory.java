package Final;

public class ActorFactory {

    public static Actor createActor(
            String type,
            int id,
            String name,
            String phone,
            String extra,
            String password
    ) {
        if (type.equalsIgnoreCase("doctor")) {
            return new Doctor(id, name, phone, extra, password);
        }
        else if (type.equalsIgnoreCase("nurse")) {
            return new Nurse(id, name, phone, password);
        }
        else if (type.equalsIgnoreCase("admin")) {
            return new Admin(id, name, phone, password);
        }
        else if (type.equalsIgnoreCase("patient")) {
            return new Patient(id, name, phone, password);
        }
        return null;
    }
}