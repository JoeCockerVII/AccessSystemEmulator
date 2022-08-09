package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Employee;

import java.util.Set;
import java.util.UUID;

/**
 * Service Interface to work with EmployeeRepository
 * @author ilyin
 * @since 06.08.2022
 */
public interface EmployeeService {

    void save(Employee employee);

    Set<Employee> getRandomEmployees(int employeeNumbers);

    Employee getRandomEmployee();

    Set<Employee> getHideEmployees();

    Employee getEmployeeByCard(UUID card);

}
