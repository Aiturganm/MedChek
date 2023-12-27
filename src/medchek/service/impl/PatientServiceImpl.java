package medchek.service.impl;

import medchek.dao.impl.PatientDaoImpl;
import medchek.models.Patient;
import medchek.service.GenericService;
import medchek.service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {
    private final PatientDaoImpl patientDao;

    public PatientServiceImpl(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            patient.setId(patient.patIdMaker());
            return patientDao.add(hospitalId, patient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            patientDao.removeById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
            patientDao.updateById(id, patient);
            return "Successfully updated!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            patientDao.addPatientsToHospital(id, patients);
            return "Successfully added!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return patientDao.getPatientById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }
}
