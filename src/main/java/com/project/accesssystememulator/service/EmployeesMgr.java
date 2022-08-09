package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Employee;

/**
 * Employee manager component
 * @author ilyin
 * @since 05.08.2022
 */
public interface EmployeesMgr {

    void createEmployee(int virtualDaysTime);

    void hireLogger(int virtualDaysTime, Employee e);

    void fireLogger(int virtualDaysTime, Employee e);
}
