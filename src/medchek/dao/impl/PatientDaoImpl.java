package medchek.dao.impl;

import medchek.dao.GenericDao;
import medchek.dao.PatientDao;
import medchek.database.Database;
import medchek.models.Hospital;
import medchek.models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    public Patient findPatient(Long id) {
        Patient patient1 = null;
        for (Hospital hospital : database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    patient1 = patient;
                }
            }
        }
        return patient1;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getPatients().add(patient);
            }
        }
        return "Successfully added patient to hospital with id " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.hospitals) {
            hospital.getPatients().remove(findPatient(id));
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : database.hospitals) {
            for (Patient hospitalPatient : hospital.getPatients()) {
                if (hospitalPatient.getId().equals(id)) {
                    hospitalPatient = patient;
                }
            }
        }
        return "Successfully updated patient with id " + id;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        for (Hospital hospital : database.hospitals) {
            if (hospital.getId().equals(id)) {
                hospital.setPatients(patients);
            }
        }
        return "Successfully added patients to hospital with id " + id;
    }

    @Override
    public Patient getPatientById(Long id) {
        return findPatient(id);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer, Patient> map = new HashMap<>();
        for (Hospital hospital : database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                map.put(patient.getAge(), patient);
            }
        }
        return map;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients =new ArrayList<>();
        for (Hospital hospital : database.hospitals) {
             patients.addAll(hospital.getPatients());
        }
        if(ascOrDesc.equalsIgnoreCase("asc")){
            patients.sort(Comparator.comparing(Patient::getAge));
        }
        else if(ascOrDesc.equalsIgnoreCase("desc")){
            patients.sort(Comparator.comparing(Patient::getAge).reversed());
        }
        return patients;
    }
}
