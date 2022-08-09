package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.Employee;
import com.project.accesssystememulator.repository.EmployeeRepository;
import com.project.accesssystememulator.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * @author ilyin
 * @since 07.08.2022
 */

@Service
@Primary
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Set<Employee> getRandomEmployees(int employeeNumbers){
        return employeeRepository.getRandomEmployees(employeeNumbers);
    }

    @Override
    @Transactional
    public Employee getRandomEmployee(){
        return employeeRepository.getRandomEmployee();
    }

    @Override
    @Transactional
    public Set<Employee> getHideEmployees(){
        return employeeRepository.getEmployeesByFiredTimeIsNull();
    }

    @Override
    @Transactional
    public Employee getEmployeeByCard(UUID card){
        return employeeRepository.getEmployeeByCARD(card);
    }
}
