package com.project.accesssystememulator.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * @author ilyin
 * @since 04.08.2022
 */
@Getter
@Setter
@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee extends Person{
    LocalDateTime hireTime; //= LocalDateTime.now();
    LocalDateTime firedTime;
//    int departmentId;

    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    public Employee(LocalDateTime hireTime, Department department) {
        this.hireTime = hireTime;
//        this.departmentId = departmentId;
        this.department = department;
        this.type = Role.EMPLOYEE;
    }


}
