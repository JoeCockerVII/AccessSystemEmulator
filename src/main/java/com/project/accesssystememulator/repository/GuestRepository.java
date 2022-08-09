package com.project.accesssystememulator.repository;

import com.project.accesssystememulator.domain.entity.Employee;
import com.project.accesssystememulator.domain.entity.Guest;
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
public interface GuestRepository extends JpaRepository<Guest, UUID> {

    @Query(value = "SELECT guests.* FROM employees" +
            "                 inner join guests on guests.employee_id = employees.id where employees.fired_time IS NOT NULL and guests.visit_date IS NOT NULL;", nativeQuery = true)
    Set<Guest> getGuestsOfFiredEmployees();

    @Query(value = "SELECT * FROM guests ORDER BY random() LIMIT 1;", nativeQuery = true)
    Guest getRandomGuest();

    Guest getGuestByCARD(UUID card);
}
