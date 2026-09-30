# Smart Care: Hospital Patient Admission System

A Java console application that automates patient admission and discharge in a
hospital. It manages patients, doctors, nurses, sections, rooms and medical
procedures, with role-based login and exportable reports.

## Features

- **Role-based access**: separate menus for Admin, Doctor and Nurse
- **Patient admission and discharge** with admission start/end dates and room assignment
- **Room and section tracking**: private rooms or rooms shared by 2 or 4 patients, with availability checks
- **Medical procedures** (medications, lab tests, radiology, monitoring) that nurses can mark as done
- **Residence info**: Jordanian (national number, ID card number) or non-Jordanian (residence number, nationality)
- **Reports** on patients, rooms, sections or procedures, chosen at run time and exported as text or XML (title, summary, content, then generation date and admin name)
- **File persistence** through a single access point

## Roles

| Role | Can do |
|------|--------|
| Admin | Add doctors, nurses, admins, sections, rooms and procedures; generate reports |
| Doctor | Admit and discharge patients, add procedures, view patient history |
| Nurse | Register patients, view history and procedures, mark procedures as done |

## Design patterns

| Pattern | Class | Problem it solves |
|---------|-------|-------------------|
| Singleton | `FileManager` | One shared file-access point, no conflicting file handles |
| Template Method | `ReportGenerator` | Every report has the same structure; subclasses only define the content |
| Factory Method | `ActorFactory` | Actor creation is kept out of the UI code |
| Decorator | `ResidenceInfo` / `ResidenceDecorator` | Residence details are added without a class explosion |
| Proxy | `SystemAccessProxy` | Credentials are validated before the real system is reached |

## OOP and SOLID

- **Encapsulation and abstraction**: private fields, and abstract `Actor` and `ReportGenerator`
- **Inheritance and polymorphism**: `Admin`, `Doctor`, `Nurse` and `Patient` extend `Actor`; each report type overrides `writeContent()`
- **SOLID**: one job per class (entity, repository, service and UI layers), extension through interfaces such as `ISystemAccess` and `ResidenceInfo`, and dependencies on abstractions

## Project structure

```
src/
├── Main.java                     # UI controller and menus
├── Actor.java, Admin.java, Doctor.java, Nurse.java, Patient.java
├── Section.java, Room.java, MedicalProcedure.java
├── ResidenceInfo.java, ResidenceDecorator.java,
│   JordanianInfo.java, NonJordanianInfo.java
├── ActorFactory.java, FileManager.java, DataInitializer.java
├── ISystemAccess.java, SystemAccess.java, SystemAccessProxy.java
├── ReportGenerator.java, PatientReport.java, RoomReport.java,
│   SectionReport.java, MedicalProcedureReport.java
├── *Database.java                # In-memory repositories
test/
├── LoginTest.java, PatientTest.java, ProcedureTest.java
docs/
└── UML_Class_diagram.png
```

## Run it

Requires JDK 11 or later.

```bash
cd src
javac *.java
java Main
```

Sample data (doctors, nurses, sections, rooms) is loaded by `DataInitializer`
on startup.

## Tests

JUnit 5 tests cover three areas, three cases each:

- Login (valid and invalid credentials)
- Patient management
- Medical procedures

Run them from your IDE, or with the JUnit console launcher.

## UML

The full class diagram is in `docs/UML_Class_diagram.png`.
