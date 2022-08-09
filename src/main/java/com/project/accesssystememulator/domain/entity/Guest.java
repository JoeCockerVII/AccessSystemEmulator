package com.project.accesssystememulator.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "guests")
@NoArgsConstructor
public class Guest extends Person{
    LocalDateTime visitDate;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    public Guest(LocalDateTime visitDate, Employee employee) {
        this.visitDate = visitDate;
        this.employee = employee;
        this.type = Role.GUEST;
    }
}
