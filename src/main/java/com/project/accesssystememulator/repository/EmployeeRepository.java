package com.project.accesssystememulator.repository;

import com.project.accesssystememulator.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * @author ilyin
 * @since 06.08.2022
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(value = "SELECT * FROM employees where fired_time IS NULL ORDER BY random() LIMIT ?;", nativeQuery = true)
    Set<Employee> getRandomEmployees(int n);

    @Query(value = "SELECT * FROM employees ORDER BY random() LIMIT 1;", nativeQuery = true)
    Employee getRandomEmployee();

    @Query(value = "SELECT * FROM employees where fired_time IS NULL;", nativeQuery = true)
    Set<Employee> getEmployeesByFiredTimeIsNull();

    Employee getEmployeeByCARD(UUID card);
}
