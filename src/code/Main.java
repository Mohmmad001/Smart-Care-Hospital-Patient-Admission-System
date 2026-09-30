package Final;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Database variables
    static DoctorDatabase doctorDB = new DoctorDatabase();
    static NurseDatabase nurseDB = new NurseDatabase();
    static PatientDatabase patientDB = new PatientDatabase();
    static AdminDatabase adminDB = new AdminDatabase();
    static SectionDatabase sectionDB = new SectionDatabase();
    static MedicalProcedureDatabase procedureDB = new MedicalProcedureDatabase();

    // Proxy Pattern - System access controlled through proxy
    static ISystemAccess system = new SystemAccessProxy(adminDB, doctorDB, nurseDB);
    
    // Data Initializer
    static DataInitializer initializer = new DataInitializer(doctorDB, nurseDB, patientDB, adminDB, sectionDB, procedureDB);

    private static Scanner scanner = new Scanner(System.in);
    private static Actor currentUser = null;

    public static void main(String[] args) {
        initializer.initialize();

        System.out.println("========================================");
        System.out.println("   Welcome to Smart Care Hospital System");
        System.out.println("========================================");

        while (true) {
            if (currentUser == null) {
                // Login Menu
                System.out.println("\n+--------------------------------------+");
                System.out.println("|              LOGIN MENU               |");
                System.out.println("+--------------------------------------+");
                System.out.println("   1. Login as Admin");
                System.out.println("   2. Login as Doctor");
                System.out.println("   3. Login as Nurse");
                System.out.println("   4. Exit System");
                System.out.print("\n   Choose option: ");
                
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    System.out.print("   Admin ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("   Password: ");
                    String password = scanner.nextLine();
                    
                    // Using Proxy for login
                    currentUser = system.adminLogin(id, password);
                    if (currentUser != null) {
                        System.out.println("   Welcome Admin " + currentUser.getName() + "!");
                    }
                } 
                else if (choice.equals("2")) {
                    System.out.print("   Doctor ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("   Password: ");
                    String password = scanner.nextLine();
                    
                    // Using Proxy for login
                    currentUser = system.doctorLogin(id, password);
                    if (currentUser != null) {
                        System.out.println("   Welcome Dr. " + currentUser.getName() + "!");
                    }
                } 
                else if (choice.equals("3")) {
                    System.out.print("   Nurse ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("   Password: ");
                    String password = scanner.nextLine();
                    
                    // Using Proxy for login
                    currentUser = system.nurseLogin(id, password);
                    if (currentUser != null) {
                        System.out.println("   Welcome Nurse " + currentUser.getName() + "!");
                    }
                } 
                else if (choice.equals("4")) {
                    System.out.println("\n   Thank you for using Smart Care System!");
                    break;
                }
            }
            
            // ==================== ADMIN MENU ====================
            else if (currentUser instanceof Admin) {
                Admin admin = (Admin) currentUser;
                
                System.out.println("\n+--------------------------------------+");
                System.out.println("|             ADMIN MENU                |");
                System.out.println("+--------------------------------------+");
                System.out.println("   1. Manage Doctors");
                System.out.println("   2. Manage Nurses");
                System.out.println("   3. Manage Admins");
                System.out.println("   4. Manage Patients");
                System.out.println("   5. Manage Sections");
                System.out.println("   6. Manage Rooms");
                System.out.println("   7. Manage Medical Procedures");
                System.out.println("   8. Generate Reports");
                System.out.println("   9. Logout");
                System.out.print("\n   Choose option: ");
                
                String choice = scanner.nextLine().trim();

                // ========== 1. MANAGE DOCTORS ==========
                if (choice.equals("1")) {
                    System.out.println("\n   --- Manage Doctors ---");
                    System.out.println("     1. Add Doctor");
                    System.out.println("     2. Remove Doctor");
                    System.out.println("     3. View All Doctors");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        System.out.print("     Doctor ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (doctorDB.getDoctor(id) != null) {
                            System.out.println("     [ERROR] Doctor ID already exists!");
                        } else {
                            System.out.print("     Name: ");
                            String name = scanner.nextLine();
                            System.out.print("     Phone: ");
                            String phone = scanner.nextLine();
                            System.out.print("     Specialization: ");
                            String spec = scanner.nextLine();
                            System.out.print("     Password: ");
                            String password = scanner.nextLine();

                            Doctor newDoctor = (Doctor) ActorFactory.createActor("doctor", id, name, phone, spec, password);
                            addResidence(newDoctor);
                            doctorDB.addDoctor(newDoctor);
                            System.out.println("     [OK] Doctor added successfully!");
                        }
                    } 
                    else if (sub.equals("2")) {
                        System.out.print("     Enter Doctor ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Doctor doctor = doctorDB.getDoctor(id);
                        if (doctor != null) {
                            doctorDB.removeDoctor(doctor);
                            System.out.println("     [OK] Doctor removed!");
                        } else {
                            System.out.println("     [ERROR] Doctor not found!");
                        }
                    } 
                    else if (sub.equals("3")) {
                        doctorDB.displayAllDoctors();
                    }
                }

                // ========== 2. MANAGE NURSES ==========
                else if (choice.equals("2")) {
                    System.out.println("\n   --- Manage Nurses ---");
                    System.out.println("     1. Add Nurse");
                    System.out.println("     2. Remove Nurse");
                    System.out.println("     3. View All Nurses");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        System.out.print("     Nurse ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (nurseDB.getNurse(id) != null) {
                            System.out.println("     [ERROR] Nurse ID already exists!");
                        } else {
                            System.out.print("     Name: ");
                            String name = scanner.nextLine();
                            System.out.print("     Phone: ");
                            String phone = scanner.nextLine();
                            System.out.print("     Password: ");
                            String password = scanner.nextLine();

                            Nurse newNurse = (Nurse) ActorFactory.createActor("nurse", id, name, phone, null, password);
                            addResidence(newNurse);
                            nurseDB.addNurse(newNurse);
                            System.out.println("     [OK] Nurse added successfully!");
                        }
                    } 
                    else if (sub.equals("2")) {
                        System.out.print("     Enter Nurse ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Nurse nurse = nurseDB.getNurse(id);
                        if (nurse != null) {
                            nurseDB.removeNurse(nurse);
                            System.out.println("     [OK] Nurse removed!");
                        } else {
                            System.out.println("     [ERROR] Nurse not found!");
                        }
                    } 
                    else if (sub.equals("3")) {
                        nurseDB.displayAllNurses();
                    }
                }

                // ========== 3. MANAGE ADMINS ==========
                else if (choice.equals("3")) {
                    System.out.println("\n   --- Manage Admins ---");
                    System.out.println("     1. Add Admin");
                    System.out.println("     2. Remove Admin");
                    System.out.println("     3. View All Admins");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        System.out.print("     Admin ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (adminDB.getAdmin(id) != null) {
                            System.out.println("     [ERROR] Admin ID already exists!");
                        } else {
                            System.out.print("     Name: ");
                            String name = scanner.nextLine();
                            System.out.print("     Phone: ");
                            String phone = scanner.nextLine();
                            System.out.print("     Password: ");
                            String password = scanner.nextLine();

                            Admin newAdmin = (Admin) ActorFactory.createActor("admin", id, name, phone, null, password);
                            addResidence(newAdmin);
                            adminDB.addAdmin(newAdmin);
                            System.out.println("     [OK] Admin added successfully!");
                        }
                    } 
                    else if (sub.equals("2")) {
                        System.out.print("     Enter Admin ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Admin adminUser = adminDB.getAdmin(id);
                        if (adminUser != null && adminUser.getId() != 1) {
                            adminDB.removeAdmin(adminUser);
                            System.out.println("     [OK] Admin removed!");
                        } else if (adminUser != null && adminUser.getId() == 1) {
                            System.out.println("     [ERROR] Cannot remove default system admin!");
                        } else {
                            System.out.println("     [ERROR] Admin not found!");
                        }
                    } 
                    else if (sub.equals("3")) {
                        adminDB.displayAllAdmins();
                    }
                }

                // ========== 4. MANAGE PATIENTS ==========
                else if (choice.equals("4")) {
                    System.out.println("\n   --- Manage Patients ---");
                    System.out.println("     1. Add Patient ");
                    System.out.println("     2. View All Patients");
                    System.out.println("     3. Remove Patient");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        System.out.print("     Patient ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (patientDB.getPatient(id) != null) {
                            System.out.println("     [ERROR] Patient ID already exists!");
                        } else {
                            System.out.print("     Name: ");
                            String name = scanner.nextLine();
                            System.out.print("     Phone: ");
                            String phone = scanner.nextLine();
                            System.out.print("     Password: ");
                            String password = scanner.nextLine();

                            Patient newPatient = (Patient) ActorFactory.createActor("patient", id, name, phone, null, password);
                            addResidence(newPatient);
                            patientDB.addPatient(newPatient);
                            System.out.println("     [OK] Patient added successfully!");
                        }
                    }
                    else if (sub.equals("2")) {
                        patientDB.displayAllPatients();
                    }
                    else if (sub.equals("3")) {
                        System.out.print("     Enter Patient ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Patient patient = patientDB.getPatient(id);
                        if (patient != null) {
                            if (patient.isAdmitted()) {
                                patient.discharge();
                            }
                            patientDB.removePatient(patient);
                            System.out.println("     [OK] Patient removed!");
                        } else {
                            System.out.println("     [ERROR] Patient not found!");
                        }
                    }
                }

                // ========== 5. MANAGE SECTIONS ==========
                else if (choice.equals("5")) {
                    System.out.println("\n   --- Manage Sections ---");
                    System.out.println("     1. View All Sections");
                    System.out.println("     2. Add New Section");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        sectionDB.displayAllSections();
                    } 
                    else if (sub.equals("2")) {
                        System.out.print("     Section ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (sectionDB.getSection(id) != null) {
                            System.out.println("     [ERROR] Section ID already exists!");
                        } else {
                            System.out.print("     Section Name: ");
                            String name = scanner.nextLine();
                            Section newSection = new Section(id, name);
                            sectionDB.addSection(newSection);
                            System.out.println("     [OK] Section added successfully!");
                        }
                    }
                }

                // ========== 6. MANAGE ROOMS ==========
                else if (choice.equals("6")) {
                    System.out.println("\n   --- Manage Rooms ---");
                    System.out.println("     1. View All Rooms");
                    System.out.println("     2. Add Room to Section");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        sectionDB.displayAllRooms();
                    } 
                    else if (sub.equals("2")) {
                        ArrayList<Section> sections = sectionDB.getSections();
                        if (sections.isEmpty()) {
                            System.out.println("     No sections available. Add a section first!");
                        } else {
                            System.out.println("\n     Available Sections:");
                            for (int i = 0; i < sections.size(); i++) {
                                System.out.println("       " + (i + 1) + ". " + sections.get(i).getName());
                            }
                            System.out.print("     Select section number: ");
                            int secIdx = Integer.parseInt(scanner.nextLine()) - 1;
                            
                            if (secIdx >= 0 && secIdx < sections.size()) {
                                System.out.print("     Room ID: ");
                                int roomId = Integer.parseInt(scanner.nextLine());
                                System.out.print("     Room Type (PRIVATE/SHARED_2/SHARED_4): ");
                                String type = scanner.nextLine().toUpperCase();
                                
                                if (type.equals("PRIVATE") || type.equals("SHARED_2") || type.equals("SHARED_4")) {
                                    Room newRoom = new Room(roomId, type);
                                    sections.get(secIdx).addRoom(newRoom);
                                    System.out.println("     [OK] Room added to " + sections.get(secIdx).getName() + " section!");
                                } else {
                                    System.out.println("     [ERROR] Invalid room type!");
                                }
                            } else {
                                System.out.println("     [ERROR] Invalid section!");
                            }
                        }
                    }
                }

                // ========== 7. MANAGE MEDICAL PROCEDURES ==========
                else if (choice.equals("7")) {
                    System.out.println("\n   --- Manage Medical Procedures ---");
                    System.out.println("     1. View All Procedures");
                    System.out.println("     2. Add Procedure");
                    System.out.println("     3. Remove Procedure");
                    System.out.print("     Choose: ");
                    String sub = scanner.nextLine().trim();

                    if (sub.equals("1")) {
                        procedureDB.displayAllProcedures();
                    } 
                    else if (sub.equals("2")) {
                        System.out.print("     Procedure ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (procedureDB.getProcedure(id) != null) {
                            System.out.println("     [ERROR] Procedure ID already exists!");
                        } else {
                            System.out.print("     Procedure Name: ");
                            String name = scanner.nextLine();
                            procedureDB.addProcedure(new MedicalProcedure(id, name));
                            System.out.println("     [OK] Procedure added successfully!");
                        }
                    } 
                    else if (sub.equals("3")) {
                        System.out.print("     Enter Procedure ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        MedicalProcedure proc = procedureDB.getProcedure(id);
                        if (proc != null) {
                            procedureDB.removeProcedure(proc);
                            System.out.println("     [OK] Procedure removed!");
                        } else {
                            System.out.println("     [ERROR] Procedure not found!");
                        }
                    }
                }

                // ========== 8. GENERATE REPORTS ==========
                else if (choice.equals("8")) {
                    System.out.println("\n   --- Generate Reports ---");
                    System.out.println("     1. Patient Report");
                    System.out.println("     2. Room Report");
                    System.out.println("     3. Section Report");
                    System.out.println("     4. Medical Procedure Report");
                    System.out.print("     Choose report type: ");
                    String reportType = scanner.nextLine().trim();

                    ReportGenerator generator = null;
                    String fileName = "";

                    if (reportType.equals("1")) {
                        generator = new PatientReport(patientDB.getPatients());
                        fileName = "src/Final/patient_report";
                    } 
                    else if (reportType.equals("2")) {
                        ArrayList<Room> allRooms = new ArrayList<>();
                        for (Section s : sectionDB.getSections()) {
                            allRooms.addAll(s.getRooms());
                        }
                        generator = new RoomReport(allRooms);
                        fileName = "src/Final/room_report";
                    } 
                    else if (reportType.equals("3")) {
                        generator = new SectionReport(sectionDB.getSections());
                        fileName = "src/Final/section_report";
                    } 
                    else if (reportType.equals("4")) {
                        generator = new MedicalProcedureReport(procedureDB.getProcedures());
                        fileName = "src/Final/procedure_report";
                    } 
                    else {
                        System.out.println("     [ERROR] Invalid report type!");
                        continue;
                    }

                    String reportContent = generator.generateReport(admin);
                    System.out.println("\n" + reportContent);

                    System.out.println("\n     Export to file?");
                    System.out.println("       1. Save as TXT");
                    System.out.println("       2. Save as XML");
                    System.out.println("       3. No, just display");
                    System.out.print("       Choice: ");
                    String exportChoice = scanner.nextLine().trim();

                    FileManager fm = FileManager.getInstance();

                    if (exportChoice.equals("1")) {
                        fm.saveToTxt(fileName + ".txt", reportContent);
                        System.out.println("     [OK] Report saved to " + fileName + ".txt");
                    } 
                    else if (exportChoice.equals("2")) {
                        fm.saveToXml(fileName + ".xml", reportContent);
                        System.out.println("     [OK] Report saved to " + fileName + ".xml");
                    } 
                    else {
                        System.out.println("     Report not saved.");
                    }
                }

                // ========== 9. LOGOUT ==========
                else if (choice.equals("9")) {
                    currentUser = null;
                    System.out.println("\n   [OK] Logged out successfully!");
                }
            }
            
            // ==================== DOCTOR MENU ====================
            else if (currentUser instanceof Doctor) {
                Doctor doctor = (Doctor) currentUser;
                
                System.out.println("\n+--------------------------------------+");
                System.out.println("|            DOCTOR MENU                |");
                System.out.println("+--------------------------------------+");
                System.out.println("   1. Admit Patient");
                System.out.println("   2. Discharge Patient");
                System.out.println("   3. Add Medical Procedures for Patient");
                System.out.println("   4. View Patient History");
                System.out.println("   5. View My Patients");
                System.out.println("   6. Logout");
                System.out.print("\n   Choose option: ");
                
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    System.out.print("   Enter Patient ID: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(patientId);

                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found! Please ask nurse to register first.");
                    } 
                    else if (patient.isAdmitted()) {
                        System.out.println("   Patient is already admitted until: " + patient.getAdmissionEndDate());
                    } 
                    else {
                        ArrayList<Section> sections = sectionDB.getSections();
                        if (sections.isEmpty()) {
                            System.out.println("   No sections available!");
                        } else {
                            System.out.println("\n   Available Sections:");
                            for (int i = 0; i < sections.size(); i++) {
                                Section sec = sections.get(i);
                                Room available = sec.findAvailableRoom();
                                if (available != null) {
                                    System.out.println("     " + (i + 1) + ". " + sec.getName() + " - Room " + available.getId() + " available");
                                } else {
                                    System.out.println("     " + (i + 1) + ". " + sec.getName() + " - NO ROOMS AVAILABLE");
                                }
                            }

                            System.out.print("   Select section: ");
                            int secIdx = Integer.parseInt(scanner.nextLine()) - 1;

                            if (secIdx >= 0 && secIdx < sections.size()) {
                                Section selectedSection = sections.get(secIdx);
                                Room availableRoom = selectedSection.findAvailableRoom();

                                if (availableRoom != null) {
                                    System.out.print("   Start Date (YYYY-MM-DD): ");
                                    String startDate = scanner.nextLine();
                                    System.out.print("   End Date (YYYY-MM-DD): ");
                                    String endDate = scanner.nextLine();

                                    patient.admit(doctor, selectedSection, startDate, endDate);
                                } else {
                                    System.out.println("   [ERROR] No available rooms in this section!");
                                }
                            }
                        }
                    }
                }
                
                else if (choice.equals("2")) {
                    System.out.print("   Enter Patient ID to discharge: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(patientId);

                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } 
                    else if (!patient.isAdmitted()) {
                        System.out.println("   Patient is not currently admitted!");
                    } 
                    else if (patient.getAssignedDoctor() != null && patient.getAssignedDoctor().getId() != doctor.getId()) {
                        System.out.println("   [ERROR] Not authorized! Patient assigned to Dr. " + patient.getAssignedDoctor().getName());
                    } 
                    else {
                        patient.discharge();
                    }
                }
                
                else if (choice.equals("3")) {
                    System.out.print("   Enter Patient ID: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(patientId);

                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } 
                    else {
                        ArrayList<MedicalProcedure> procedures = procedureDB.getProcedures();
                        if (procedures.isEmpty()) {
                            System.out.println("   No procedures available!");
                        } else {
                            System.out.println("\n   Available Procedures:");
                            procedureDB.displayAllProcedures();

                            System.out.print("   Enter Procedure ID to add (0 to finish): ");
                            while (true) {
                                int procId = Integer.parseInt(scanner.nextLine());
                                if (procId == 0) break;
                                
                                MedicalProcedure selected = procedureDB.getProcedure(procId);
                                if (selected != null) {
                                    MedicalProcedure patientProc = new MedicalProcedure(selected.getId(), selected.getName());
                                    patient.addProcedure(patientProc);
                                    System.out.println("     [OK] Added: " + selected.getName());
                                } else {
                                    System.out.println("     [ERROR] Invalid procedure ID!");
                                }
                                System.out.print("   Another ID (0 to finish): ");
                            }
                            System.out.println("   [OK] All procedures added!");
                        }
                    }
                }
                
                else if (choice.equals("4")) {
                    System.out.print("   Enter Patient ID: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(patientId);

                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } else {
                        System.out.println("\n   +----------------------------------------+");
                        System.out.println("   |            PATIENT HISTORY             |");
                        System.out.println("   +----------------------------------------+");
                        System.out.println("   | ID: " + patient.getId());
                        System.out.println("   | Name: " + patient.getName());
                        System.out.println("   | Phone: " + patient.phone);
                        System.out.println("   | Status: " + (patient.isAdmitted() ? "ADMITTED" : "DISCHARGED"));
                        
                        if (patient.isAdmitted()) {
                            System.out.println("   | Admission Period: " + patient.getAdmissionPeriod());
                            System.out.println("   | Doctor: " + patient.getAssignedDoctor().getName());
                            System.out.println("   | Section: " + patient.getAssignedSection().getName());
                            System.out.println("   | Room: " + patient.getAssignedRoom().getId());
                        }
                        
                        System.out.println("   +----------------------------------------+");
                        System.out.println("   | Procedures:");
                        ArrayList<MedicalProcedure> procedures = patient.getProcedures();
                        if (procedures.isEmpty()) {
                            System.out.println("   |   No procedures prescribed.");
                        } else {
                            for (MedicalProcedure p : procedures) {
                                System.out.println("   |   - " + p.getName() + ": " + (p.isDone() ? "[DONE]" : "[PENDING]"));
                            }
                        }
                        System.out.println("   +----------------------------------------+");
                    }
                }
                
                else if (choice.equals("5")) {
                    System.out.println("\n   +----------------------------------------+");
                    System.out.println("   |              MY PATIENTS                |");
                    System.out.println("   +----------------------------------------+");
                    boolean found = false;
                    for (Patient p : patientDB.getPatients()) {
                        if (p.getAssignedDoctor() != null && p.getAssignedDoctor().getId() == doctor.getId()) {
                            found = true;
                            System.out.println("   | ID: " + p.getId() + " | Name: " + p.getName());
                            System.out.println("   |   Status: " + (p.isAdmitted() ? "ADMITTED" : "DISCHARGED"));
                            if (p.isAdmitted()) {
                                System.out.println("   |   Room: " + p.getAssignedRoom().getId());
                                System.out.println("   |   Until: " + p.getAdmissionEndDate());
                            }
                            System.out.println("   +----------------------------------------+");
                        }
                    }
                    if (!found) {
                        System.out.println("   | No patients assigned to you.");
                        System.out.println("   +----------------------------------------+");
                    }
                }
                
                else if (choice.equals("6")) {
                    currentUser = null;
                    System.out.println("\n   [OK] Logged out successfully!");
                }
            }
            
            // ==================== NURSE MENU ====================
            else if (currentUser instanceof Nurse) {
                System.out.println("\n+--------------------------------------+");
                System.out.println("|            NURSE MENU                 |");
                System.out.println("+--------------------------------------+");
                System.out.println("   1. Register New Patient ");
                System.out.println("   2. View Patient Information");
                System.out.println("   3. View Patient Medical Procedures");
                System.out.println("   4. Mark Procedure as Done");
                System.out.println("   5. Logout");
                System.out.print("\n   Choose option: ");
                
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    System.out.print("   Patient ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    if (patientDB.getPatient(id) != null) {
                        System.out.println("   [ERROR] Patient ID already exists!");
                    } else {
                        System.out.print("   Name: ");
                        String name = scanner.nextLine();
                        System.out.print("   Phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("   Password: ");
                        String password = scanner.nextLine();
                        
                        Patient newPatient = (Patient) ActorFactory.createActor("patient", id, name, phone, null, password);
                        addResidence(newPatient);
                        patientDB.addPatient(newPatient);
                        System.out.println("   [OK] Patient registered successfully!");
                        System.out.println(newPatient.getResidenceInfo());
                    }
                }
                
                else if (choice.equals("2")) {
                    System.out.print("   Enter Patient ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(id);
                    
                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } else {
                        System.out.println("\n   +----------------------------------------+");
                        System.out.println("   |          PATIENT INFORMATION           |");
                        System.out.println("   +----------------------------------------+");
                        System.out.println("   | ID: " + patient.getId());
                        System.out.println("   | Name: " + patient.getName());
                        System.out.println("   | Phone: " + patient.phone);
                        System.out.println("   | Status: " + (patient.isAdmitted() ? "ADMITTED" : "NOT ADMITTED"));
                        
                        if (patient.isAdmitted()) {
                            System.out.println("   | Section: " + patient.getAssignedSection().getName());
                            System.out.println("   | Room: " + patient.getAssignedRoom().getId());
                            System.out.println("   | Admission Period: " + patient.getAdmissionPeriod());
                            System.out.println("   | Doctor: " + patient.getAssignedDoctor().getName());
                        }
                        
                        if (patient.residence != null) {
                            System.out.println("   +----------------------------------------+");
                            System.out.println("   | Residence Info:");
                            String[] lines = patient.getResidenceInfo().split("\n");
                            for (String line : lines) {
                                System.out.println("   | " + line);
                            }
                        }
                        System.out.println("   +----------------------------------------+");
                    }
                }
                
                else if (choice.equals("3")) {
                    System.out.print("   Enter Patient ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(id);
                    
                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } else {
                        System.out.println("\n   +----------------------------------------+");
                        System.out.println("   |   Medical Procedures for " + patient.getName());
                        System.out.println("   +----------------------------------------+");
                        ArrayList<MedicalProcedure> procedures = patient.getProcedures();
                        if (procedures.isEmpty()) {
                            System.out.println("   |   No procedures prescribed.");
                        } else {
                            for (MedicalProcedure p : procedures) {
                                System.out.println("   |   " + p.getId() + ". " + p.getName() + " - " + (p.isDone() ? "[DONE]" : "[PENDING]"));
                            }
                        }
                        System.out.println("   +----------------------------------------+");
                    }
                }
                
                else if (choice.equals("4")) {
                    System.out.print("   Enter Patient ID: ");
                    int patientId = Integer.parseInt(scanner.nextLine());
                    Patient patient = patientDB.getPatient(patientId);
                    
                    if (patient == null) {
                        System.out.println("   [ERROR] Patient not found!");
                    } else {
                        ArrayList<MedicalProcedure> procedures = patient.getProcedures();
                        if (procedures.isEmpty()) {
                            System.out.println("   No procedures for this patient.");
                        } else {
                            System.out.println("\n   Pending Procedures:");
                            ArrayList<MedicalProcedure> pending = new ArrayList<>();
                            for (MedicalProcedure p : procedures) {
                                if (!p.isDone()) {
                                    pending.add(p);
                                    System.out.println("     " + p.getId() + ". " + p.getName());
                                }
                            }
                            
                            if (pending.isEmpty()) {
                                System.out.println("   All procedures completed!");
                            } else {
                                System.out.print("   Enter Procedure ID to mark as done: ");
                                int procId = Integer.parseInt(scanner.nextLine());
                                
                                for (MedicalProcedure p : pending) {
                                    if (p.getId() == procId) {
                                        p.markDone();
                                        System.out.println("   [OK] Procedure marked as DONE!");
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                
                else if (choice.equals("5")) {
                    currentUser = null;
                    System.out.println("\n   [OK] Logged out successfully!");
                }
            }
        }
    }

    // Add residence info to any actor
    private static void addResidence(Actor actor) {
        System.out.print("   Is Jordanian? (yes/no): ");
        String isJordanian = scanner.nextLine().toLowerCase();
        
        ResidenceInfo base = new ResidenceInfo() {
            public String getInfo() { return "   Resident Information:"; }
        };
        
        if (isJordanian.equals("yes")) {
            System.out.print("   National Number: ");
            String nationalNo = scanner.nextLine();
            System.out.print("   ID Card Number: ");
            String idCard = scanner.nextLine();
            actor.setResidence(new JordanianInfo(base, nationalNo, idCard));
        } else {
            System.out.print("   Nationality: ");
            String nationality = scanner.nextLine();
            System.out.print("   Residence Number: ");
            String resNo = scanner.nextLine();
            actor.setResidence(new NonJordanianInfo(base, resNo, nationality));
        }
    }
}