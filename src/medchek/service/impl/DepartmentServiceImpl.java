package medchek.service.impl;

import medchek.dao.impl.DepartmentDaoImpl;
import medchek.models.Department;
import medchek.service.DepartmentService;
import medchek.service.GenericService;

import java.util.List;



public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final DepartmentDaoImpl departmentDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        departmentDao.add(hospitalId, department);
        department.setId(department.depIdMaker());
        return "Successfully added department to hospital with id " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        try {
            departmentDao.removeById(id);
            System.out.println("Successfully deleted!");
        } catch (Exception e) {
            throw new RuntimeException("Department with id " + id + " not found!");
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        try {
            departmentDao.updateById(id, department);
            return "Successfully updated department with id " + id;
        } catch (Exception e) {
            throw new RuntimeException("Department with id " + id + " not found!");
        }
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
            return departmentDao.getAllDepartmentByHospital(id);
        } catch (Exception e) {
            throw new RuntimeException("Department with hospital id " + id + " not found!");
        }
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            return departmentDao.findDepartmentByName(name);
        } catch (Exception e) {
            throw new RuntimeException("Department with name " + name + " name not found!");
        }
    }
}
