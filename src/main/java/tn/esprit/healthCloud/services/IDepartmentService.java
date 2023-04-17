package tn.esprit.healthCloud.services;

import java.util.List;
import tn.esprit.healthCloud.entities.Department;

public interface IDepartmentService {
    Department createDepartment(Department department);
    Department updateDepartment(int id, Department department);
    void deleteDepartment(int id);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    List<Department> searchDepartments(String searchTerm);

}