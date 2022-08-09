package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.Department;
import com.project.accesssystememulator.repository.DepartmentRepository;
import com.project.accesssystememulator.service.DepartmentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ilyin
 * @since 07.08.2022
 */
@Service
@Primary
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final List<Department> departments;


    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository  = departmentRepository;
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            departments.add(new Department("Department"+ (i+1) ));
        }
        this.departments = new ArrayList<>(departments);
    }

    @Override
    @Transactional
    public void createDepartments() {
        departmentRepository.saveAll(departments);
    }

    @Override
    @Transactional
    public Department getRandomDepartment() {
        return departmentRepository.getRandomDepartment();
    }

}
