package medchek;

import medchek.dao.GenericDao;
import medchek.dao.impl.DepartmentDaoImpl;
import medchek.dao.impl.DoctorDaoImpl;
import medchek.dao.impl.HospitalDaoImpl;
import medchek.dao.impl.PatientDaoImpl;
import medchek.database.Database;
import medchek.enumm.Gender;
import medchek.models.Department;
import medchek.models.Doctor;
import medchek.models.Hospital;
import medchek.models.Patient;
import medchek.service.impl.DepartmentServiceImpl;
import medchek.service.impl.DoctorServiceImpl;
import medchek.service.impl.HospitalServiceImpl;
import medchek.service.impl.PatientServiceImpl;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(database);
        DoctorDaoImpl doctorDao = new DoctorDaoImpl(database);
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(database);
        PatientDaoImpl patientDao = new PatientDaoImpl(database);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentDao);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        PatientServiceImpl patientService = new PatientServiceImpl(patientDao);

        Scanner scanner = new Scanner(System.in);

        List<Doctor> doctors = new ArrayList<>();
        Doctor doctor = new Doctor("Asel", "Babayeva", Gender.FEMALE, 5);
        doctors.add(doctor);
        Doctor doctor1 = new Doctor("Kamchybek", "Nurmamatov", Gender.MALE, 8);
        doctors.add(doctor1);

        List<Department> departments = new ArrayList<>();
        Department department = new Department("Hirurgiya", doctors);
        departments.add(department);

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Mirbek", "Erkinbekov", 18, Gender.MALE));

        Hospital hospital = new Hospital("Maldybaeva", departments, doctors, patients);
        System.out.println("Saved hospitals: ");
        System.out.println(hospitalService.addHospital(hospital));
        for (Hospital hospital1 : hospitalService.getAllHospital()) {
            System.out.println(hospital1);
        }
        OuterLoop:
        while (true) {
            System.out.println("""
                                        
                    1. ADD HOSPITAL
                    2. FIND HOSPITAL BY ID
                    3. GET ALL HOSPITAL
                    4. GET ALL PATIENT FROM HOSPITAL
                    5. DELETE HOSPITAL BY ID
                    6. GET ALL HOSPITAL BY ADDRESS
                    7. ADD DEPARTMENT
                    8. REMOVE DEPARTMENT BY ID
                    9. UPDATE DEPARTMENT BY ID
                    10. GET ALL DEPARTMENT BY HOSPITAL
                    11. FIND DEPARTMENT BY NAME
                    12. ADD DOCTOR
                    13. REMOVE DOCTOR BY ID
                    14. UPDATE DOCTOR BY ID
                    15. FIND DOCTOR BY ID
                    16. ASSIGN DOCTOR TO DEPARTMENT
                    17. GET ALL DOCTORS BY HOSPITAL
                    18. GET ALL DOCTORS BY DEPARTMENT ID
                    19. ADD PATIENT 
                    20. REMOVE PATIENT BY ID
                    21. UPDATE PATIENT BY ID
                    22. ADD PATIENTS TO HOSPITAL
                    23. GET PATIENT BY ID
                    24. GET PATIENT BY AGE
                    25. SORT PATIENTS BY AGE
                    """);
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.println("Enter hospital address: ");
                    String address = scanner.nextLine();
                    System.out.println(hospitalService.addHospital(new Hospital(address)));
                }
                case "2" -> {
                    System.out.println("Enter the id: ");
                    Long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(hospitalService.findHospitalById(id));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "3" -> {
                    System.out.println(hospitalService.getAllHospital());
                }
                case "4" -> {
                    System.out.println("Enter the hospital id: ");
                    Long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(hospitalService.getAllPatientFromHospital(id));
                    } catch (Exception e) {
                        System.out.println("With id " + id + " not found!");
                    }
                }
                case "5" -> {
                    System.out.println("Enter the hospital id");
                    Long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(hospitalService.deleteHospitalById(id));
                    } catch (Exception e) {
                        System.out.println("Hospital with id " + id + " not found!");
                    }
                }
                case "6" -> {
                    System.out.println("Enter the hospital address: ");
                    try {
                        System.out.println(hospitalService.getAllHospitalByAddress(scanner.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Hoslpital with address not found!");
                    }
                }
                case "7" -> {
                    System.out.println("Enter the hospital id: ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter department name: ");
                    String name = new Scanner(System.in).nextLine();
                    List<Doctor> doctors1 = new ArrayList<>();
                    try {
                        System.out.println(departmentService.add(id, new Department(name, doctors1)));
                    } catch (Exception e) {
                        System.out.println("With id not found!");
                    }
                }
                case "8" -> {
                    System.out.println("Enter the id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        departmentService.removeById(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "9" -> {
                    System.out.println("Enter the id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the department name: ");
                    String name = new Scanner(System.in).nextLine();
                    try {
                        System.out.println(departmentService.updateById(id, new Department(name)));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "10" -> {
                    System.out.println("Enter the hospital id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(departmentService.getAllDepartmentByHospital(id));
                    } catch (Exception e) {
                        System.out.println("Hospital with id not found!");
                    }
                }
                case "11" -> {
                    System.out.println("Enter the department name: ");
                    String name = new Scanner(System.in).nextLine();
                    try {
                        System.out.println(departmentService.findDepartmentByName(name));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "12" -> {
                    System.out.println("Enter the hospital id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the first name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("MALE or FEMALE");
                    String answer = scanner.nextLine();
                    Gender gender = null;
                    if (answer.equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (answer.equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    }
                    System.out.println("Experience year: ");
                    int year = new Scanner(System.in).nextInt();
                    Doctor doctor2 = new Doctor(name, lastName, gender, year);
                    try {
                        System.out.println(doctorService.add(id, doctor2));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "13" -> {
                    System.out.println("Enter the doctor id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        doctorService.removeById(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "14" -> {
                    System.out.println("Enter the doctor id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the first name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("MALE or FEMALE");
                    String answer = scanner.nextLine();
                    Gender gender = null;
                    if (answer.equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (answer.equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    }
                    System.out.println("Experience year: ");
                    int year = new Scanner(System.in).nextInt();
                    Doctor doctor2 = new Doctor(name, lastName, gender, year);
                    try {
                        System.out.println(doctorService.updateById(id, doctor2));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "15" -> {
                    System.out.println("Enter the doctor by id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(doctorService.findDoctorById(id));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "16" -> {
                    System.out.println("Enter the department id:");
                    long depId = new Scanner(System.in).nextLong();
                    System.out.println("Enter the ids of doctors to assign:");
                    System.out.println("To STOP enter the 0.");
                    List<Long> longs = new ArrayList<>();
                    long ids = 1;
                    do {
                        ids = new Scanner(System.in).nextLong();
                        if (ids == 0) break;
                        longs.add(ids);
                    } while (true);
                    try {
                        System.out.println(doctorService.assignDoctorToDepartment(depId, longs));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "17" -> {
                    System.out.println("Enter the hospital id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        for (Doctor doctor2 : doctorService.getAllDoctorsByHospitalId(id)) {
                            System.out.println(doctor2);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "18" -> {
                    System.out.println("Enter the department id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        for (Doctor doctor2 : doctorService.getAllDoctorsByDepartmentId(id)) {
                            System.out.println(doctor2);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "19" -> {
                    System.out.println("Enter the hospital id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the first name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter the age: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.println("Gender: male or female: ");
                    String answer = scanner.nextLine();
                    Gender gender = null;
                    if (answer.equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (answer.equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    }
                    Patient patient = new Patient(name, lastName, age, gender);
                    try {
                        System.out.println(patientService.add(id, patient));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "20" -> {
                    System.out.println("Enter the patient id: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        patientService.removeById(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "21" -> {
                    System.out.println("Enter the patient id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the first name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter the age: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.println("Gender: male or female: ");
                    String answer = scanner.nextLine();
                    Gender gender = null;
                    if (answer.equalsIgnoreCase("male")) {
                        gender = Gender.MALE;
                    } else if (answer.equalsIgnoreCase("female")) {
                        gender = Gender.FEMALE;
                    }
                    Patient patient = new Patient(name, lastName, age, gender);
                    try {
                        System.out.println(patientService.updateById(id, patient));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "22" -> {
                    System.out.println("Enter the hospital id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Enter the patients id: ");
                    System.out.println("To STOP enter 0: ");
                    List<Patient> patients1 = new ArrayList<>();
                    long patId = 100;
                    do {
                        patId = new Scanner(System.in).nextLong();
                        if (patId == 0) break;
                        patients1.add(patientService.getPatientById(patId));
                    } while (true);
                    try {
                        System.out.println(patientService.addPatientsToHospital(id, patients1));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "23" -> {
                    System.out.println("Enter the patient's id to get: ");
                    long id = new Scanner(System.in).nextLong();
                    try {
                        System.out.println(patientService.getPatientById(id));
                    } catch (Exception e) {
                        System.out.println("Patient with id " + id + " not found!");
                    }
                }
                case "24" -> {
                    for (int i = 0; i < patientService.getPatientByAge().size(); i++) {
                        System.out.println(patientService.getPatientByAge().get(i));
                    }
                }
                case "25" -> {
                    System.out.println("asc or desc");
                    String answer = scanner.nextLine();
                    patientService.sortPatientsByAge(answer);
                }
            }
        }
    }
}