package medchek.dao.impl;

import medchek.dao.HospitalDao;
import medchek.database.Database;
import medchek.models.Hospital;
import medchek.models.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {
    private final Database database;

    public HospitalDaoImpl(Database database) {
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
    public String addHospital(Hospital hospital) {
        database.hospitals.add(hospital);
        return "Successfully added!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return findHospital(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return findHospital(id).getPatients();
    }

    @Override
    public String deleteHospitalById(Long id) {
        database.hospitals.remove(findHospital(id));
        return "Successfully deleted hospital with id " + id;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> map = new HashMap<>();
        int counter = 0;
        for (Hospital hospital : database.hospitals) {
            if(hospital.getAddress().equals(address)){
                map.put(++counter+"-hospital ", hospital);
            }
        }
        return map;
    }
}
