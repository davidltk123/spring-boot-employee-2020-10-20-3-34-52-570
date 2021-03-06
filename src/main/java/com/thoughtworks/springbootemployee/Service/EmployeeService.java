package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository1 employeeRepository;

    public EmployeeService(EmployeeRepository1 employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee getById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getPaginatedAll(Integer page, Integer pageSize) {
        page = page - 1;
        return employeeRepository.findAll().stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(String id, Employee employeeUpdate) {
        if(getById(id) != null){
            employeeUpdate.setId(id);
            return employeeRepository.save(employeeUpdate);
        }
        return null;
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }
}
