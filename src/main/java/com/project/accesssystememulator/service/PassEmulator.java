package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Employee;
import com.project.accesssystememulator.domain.entity.Guest;

import java.util.UUID;

/**
 * Guest manager component
 * @author ilyin
 * @since 08.08.2022
 */
public interface PassEmulator {

    void passEmulation(int virtualDaysTime);

    void employeeLogger(int virtualDaysTime, Employee employee);

    void guestLogger(int virtualDaysTime, Guest guest);

    void unknownCardLogger(UUID card);
}
