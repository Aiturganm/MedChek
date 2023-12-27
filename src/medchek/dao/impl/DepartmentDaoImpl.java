package medchek.dao.impl;

import medchek.dao.DepartmentDao;
import medchek.dao.GenericDao;
import medchek.database.Database;
import medchek.models.Department;
import medchek.models.Hospital;

import java.util.List;

public class DepartmentDaoImpl implements GenericDao<Department>, DepartmentDao {
    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }


    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : database.hospitals) {
            if(hospital.getId().equals(hospitalId)){
                hospital.getDepartments().add(department);
            }
        }
        return "Successfully added department to hospital with id " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : database.hospitals) {
            hospital.getDepartments().removeIf(department -> department.getId().equals(id));
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : database.hospitals) {
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if(hospitalDepartment.getId().equals(id)){
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());

                }
            }
        }
        return "Successfully updated department!";
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        List<Department> departments = null;
        for (Hospital hospital : database.hospitals) {
            if(hospital.getId().equals(id)){
                departments = hospital.getDepartments();
            }
        }
        return departments;
    }

    @Override
    public Department findDepartmentByName(String name) {
        Department department1 = null;
        for (Hospital hospital : database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if(department.getDepartmentName().equals(name)){
                    department1 = department;
                }
            }
        }
        return department1;
    }
}
