package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.Employee;
import com.project.accesssystememulator.domain.entity.Guest;
import com.project.accesssystememulator.domain.entity.VirtualTime;
import com.project.accesssystememulator.service.EmployeeService;
import com.project.accesssystememulator.service.GuestService;
import com.project.accesssystememulator.service.GuestsMgr;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author ilyin
 * @since 07.08.2022
 */
@Service
@RequiredArgsConstructor
public class GuestsMgrImpl implements GuestsMgr {

    private final EmployeeService employeeService;
    private final GuestService guestService;

    public void createGuest(int virtualDaysTime){
        Set<Employee> employeesHire = employeeService.getHideEmployees();
        for (Employee e:employeesHire) {
            if(Math.random()<0.5){
//                LocalDateTime startDate = e.getHireTime();
                LocalDateTime startDate = VirtualTime.getStartDate().plusDays(virtualDaysTime);
                LocalDateTime endDate = startDate.plusMonths(6);
                LocalDateTime visitDate = VirtualTime.getRandomDataFromInterval(startDate,endDate);
                Guest guest = new Guest(visitDate,e);
                guestService.save(guest);
                visitLogger(virtualDaysTime,guest);
            }
        }

        Set<Guest> firedEmployeeGuests = guestService.getGuestsOfFiredEmployees();
        for (Guest g:firedEmployeeGuests) {
            LocalDateTime d1 = g.getVisitDate();
            LocalDateTime d2 = g.getEmployee().getFiredTime();

            if(g.getVisitDate().isAfter(g.getEmployee().getFiredTime())){
//                guestService.delete(g.getId());
                visitCancelLogger(virtualDaysTime, g);
                g.setVisitDate(null);
                guestService.save(g);

            }
        }


    }

    public void visitLogger(int virtualDaysTime, Guest g){
        LocalDateTime currentDay = VirtualTime.getStartDate().plusDays(virtualDaysTime);
        Employee e = g.getEmployee();
        System.out.print("Гостю " + g.getId() + " назначена встреча с сотрудником " + e.getId());
        System.out.print(" Отдел: " + e.getDepartment().getName());
        System.out.print(" Дата: {" + g.getVisitDate() + "}");
        System.out.print(" До встречи осталось: {" + VirtualTime.getGetDaysBetweenTwoDate(currentDay,g.getVisitDate())+"}\n");

        if(VirtualTime.getGetDaysBetweenTwoDate(currentDay,g.getVisitDate()) < 0) {
            int x = 1;
        }
    }

    public void visitCancelLogger(int virtualDaysTime, Guest g){
        Employee e = g.getEmployee();
        System.out.print("Встреча гостя " + g.getId() + " с сотрудником " + e.getId() + " отменена");
        System.out.print(" Отдел: " + e.getDepartment().getName());
        System.out.print(" Дата встречи: {" + g.getVisitDate() + "} , дата увольнения сотрудника: {" + e.getFiredTime()+"}\n");
    }
}
