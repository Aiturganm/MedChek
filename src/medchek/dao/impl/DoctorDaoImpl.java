package medchek.dao.impl;

import medchek.dao.DoctorDao;
import medchek.dao.GenericDao;
import medchek.database.Database;
import medchek.models.Department;
import medchek.models.Doctor;
import medchek.models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements GenericDao<Doctor>, DoctorDao {
    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    public Hospital findHospital(Long hospitalId){
        Hospital hospital1 = null;
        for (Hospital hospital : database.hospitals) {
            if(hospital.getId().equals(hospitalId)){
                hospital1 = hospital;
            }
        }
        return hospital1;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        findHospital(hospitalId).getDoctors().add(doctor);
        return "Successfully added doctor to hospital with id " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)){
                    hospital.getDoctors().remove(doctor);
                }
            }
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : database.hospitals) {
            for (Doctor hospitalDoctor : hospital.getDoctors()) {
                if(hospitalDoctor.getId().equals(id)){
                    hospitalDoctor.setFirstName(doctor.getFirstName());
                    hospitalDoctor.setLastName(doctor.getLastName());
                    hospitalDoctor.setExperienceYear(doctor.getExperienceYear());
                    hospitalDoctor.setGender(doctor.getGender());
                }
            }
        }
        return "Successfully updated doctor with id " + id;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        Doctor doctor1 = null;
        for (Hospital hospital : database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if(doctor.getId().equals(id)){
                    doctor1 = doctor;
                }
            }
        }
        return doctor1;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        List<Doctor> doctors = new ArrayList<>();
        for (Hospital hospital : database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                for (Long l : doctorsId) {
                    if (doctor.getId().equals(l)){
                        doctors.add(doctor);
                    }
                }
            }
        }
        for (Hospital hospital : database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if(department.getId().equals(departmentId)){
                    department.setDoctors(doctors);
                }
            }
        }
        return "Successfully assigned doctor to department!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return findHospital(id).getDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        List<Doctor> doctors = null;
        for (Hospital hospital : database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if(department.getId().equals(id)){
                    doctors = department.getDoctors();
                }
            }
        }
        return doctors;
    }
}
