package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Department;

/**
 * Service Interface to work with DepartmentRepository
 * @author ilyin
 * @since 06.08.2022
 */
public interface DepartmentService {

    void createDepartments();

    Department getRandomDepartment();
}
