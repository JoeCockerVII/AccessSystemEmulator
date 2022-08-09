package com.project.accesssystememulator.repository;

import com.project.accesssystememulator.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author ilyin
 * @since 06.08.2022
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    @Query(value = "SELECT * FROM departments ORDER BY random() LIMIT 1", nativeQuery = true)
    Department getRandomDepartment();

}
