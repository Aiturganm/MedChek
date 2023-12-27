package medchek.service.impl;

import medchek.dao.impl.HospitalDaoImpl;
import medchek.models.Hospital;
import medchek.models.Patient;
import medchek.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private final HospitalDaoImpl hospitalDao;

    public HospitalServiceImpl(HospitalDaoImpl hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        hospitalDao.addHospital(hospital);
        hospital.setId(hospital.hospIdMaker());
        return "Successfully added hospital!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        boolean isTrue = true;
        for (Hospital hospital : getAllHospital()) {
            if(hospital.getId().equals(id)){
                return hospitalDao.findHospitalById(id);
            }else isTrue = false;
        }
        if(!isTrue){
            throw new RuntimeException();
        }return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            return hospitalDao.deleteHospitalById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            return hospitalDao.getAllHospitalByAddress(address);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
