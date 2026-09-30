package Final;

public class DataInitializer {
    
    private DoctorDatabase doctorDB;
    private NurseDatabase nurseDB;
    private PatientDatabase patientDB;
    private AdminDatabase adminDB;
    private SectionDatabase sectionDB;
    private MedicalProcedureDatabase procedureDB;
    
    public DataInitializer(DoctorDatabase doctorDB, NurseDatabase nurseDB, PatientDatabase patientDB, 
                           AdminDatabase adminDB, SectionDatabase sectionDB, MedicalProcedureDatabase procedureDB) {
        this.doctorDB = doctorDB;
        this.nurseDB = nurseDB;
        this.patientDB = patientDB;
        this.adminDB = adminDB;
        this.sectionDB = sectionDB;
        this.procedureDB = procedureDB;
    }
    
    public void initialize() {
        // Sections
        Section cardio = new Section(1, "Cardiology");
        Section neuro = new Section(2, "Neurology");
        Section pedia = new Section(3, "Pediatrics");
        sectionDB.addSection(cardio);
        sectionDB.addSection(neuro);
        sectionDB.addSection(pedia);
        
        // Rooms
        cardio.addRoom(new Room(101, "PRIVATE"));
        cardio.addRoom(new Room(102, "SHARED_2"));
        cardio.addRoom(new Room(103, "SHARED_4"));
        neuro.addRoom(new Room(201, "PRIVATE"));
        neuro.addRoom(new Room(202, "SHARED_2"));
        pedia.addRoom(new Room(301, "SHARED_2"));
        pedia.addRoom(new Room(302, "SHARED_4"));
        
        // Procedures
        procedureDB.addProcedure(new MedicalProcedure(1, "Blood Test"));
        procedureDB.addProcedure(new MedicalProcedure(2, "X-Ray"));
        procedureDB.addProcedure(new MedicalProcedure(3, "MRI Scan"));
        procedureDB.addProcedure(new MedicalProcedure(4, "ECG"));
        procedureDB.addProcedure(new MedicalProcedure(5, "Blood Pressure Monitoring"));
        
        
        adminDB.addAdmin(new Admin(1, "System Admin", "0790000000", "admin123"));
        
        doctorDB.addDoctor(new Doctor(100, "Dr. Smith", "0791111111", "Cardiology", "doc123"));
        doctorDB.addDoctor(new Doctor(101, "Dr. Johnson", "0791111112", "Neurology", "doc123"));
        
        nurseDB.addNurse(new Nurse(200, "Nurse Anna", "0792222222", "nurse123"));
        nurseDB.addNurse(new Nurse(201, "Nurse Maria", "0792222223", "nurse123"));
        
        
        Patient samplePatient = new Patient(1000, "John Doe", "0793333333", "patient123");
        ResidenceInfo base = new ResidenceInfo() {
            public String getInfo() { return "Resident Information:"; }
        };
        samplePatient.setResidence(new JordanianInfo(base, "987654321", "1234567890"));
        patientDB.addPatient(samplePatient);
        
        // Print initialization message
        System.out.println("+--------------------------------------+");
        System.out.println("|     System initialized!              |");
        System.out.println("|                                      |");
        System.out.println("|     ADMIN Login:                     |");
        System.out.println("|       ID: 1                          |");
        System.out.println("|       Password: admin123             |");
        System.out.println("|                                      |");
        System.out.println("|     DOCTOR Login:                    |");
        System.out.println("|       ID: 100 or 101                 |");
        System.out.println("|       Password: doc123               |");
        System.out.println("|                                      |");
        System.out.println("|     NURSE Login:                     |");
        System.out.println("|       ID: 200 or 201                 |");
        System.out.println("|       Password: nurse123             |");
        System.out.println("+--------------------------------------+");
    }
}