package Final;

import java.util.ArrayList;

public class AdminDatabase {

    private ArrayList<Admin> admins;

    public AdminDatabase() {
        admins = new ArrayList<>();
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    public Admin getAdmin(int id) {
        for (Admin a : admins) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }
    
    // Display all admins
    public void displayAllAdmins() {
        if (admins.isEmpty()) {
            System.out.println("     No admins found.");
        } else {
            System.out.println("\n     +----------------------------------------+");
            for (Admin a : admins) {
                System.out.println("     | ID: " + a.getId() + " | Name: " + a.getName());
            }
            System.out.println("     +----------------------------------------+");
        }
    }
}