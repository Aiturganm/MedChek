package medchek.service.impl;

import medchek.dao.impl.DoctorDaoImpl;
import medchek.models.Doctor;
import medchek.service.DoctorService;
import medchek.service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {
    private final DoctorDaoImpl doctorDao;

    public DoctorServiceImpl(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public String add(Long hospitalId, Doctor doctor) {
        doctorDao.add(hospitalId, doctor);
        doctor.setId(doctor.docIdMaker());
        return "Successfully added doctor " + doctor.getFirstName() + " to hospital with id " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        try {
            doctorDao.removeById(id);
        } catch (Exception e) {
            throw new RuntimeException("Doctor with id " + id + " not found!");
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
            doctorDao.updateById(id, doctor);
            return "Successfully updated doctor with id " + id;
        } catch (Exception e) {
            throw new RuntimeException("Doctor with id " + id + " not found!");
        }
    }

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            return doctorDao.findDoctorById(id);
        } catch (Exception e) {
            throw new RuntimeException("Doctor with id " + id + " not found!");
        }
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
            doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
            return "Successfully assigned doctor to department with id " + departmentId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            return doctorDao.getAllDoctorsByDepartmentId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
