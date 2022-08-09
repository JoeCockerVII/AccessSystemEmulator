package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.Department;
import com.project.accesssystememulator.domain.entity.Employee;
import com.project.accesssystememulator.domain.entity.VirtualTime;
import com.project.accesssystememulator.service.EmployeeService;
import com.project.accesssystememulator.service.EmployeesMgr;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author ilyin
 * @since 05.08.2022
 */
@Service
@RequiredArgsConstructor
public class EmployeesMgrImpl implements EmployeesMgr {

    private final EmployeeService employeeService;
    private final DepartmentServiceImpl departmentService;

    public void createEmployee(int virtualDaysTime){
        LocalDateTime startDate = VirtualTime.getStartDate().plusDays(virtualDaysTime);
        LocalDateTime endDate = VirtualTime.getEndDate();
        LocalDateTime hireTime = VirtualTime.getRandomDataFromInterval(startDate,endDate);

        Department department = departmentService.getRandomDepartment();
        Employee employee = new Employee(hireTime,department);
        employeeService.save(employee);
        hireLogger(virtualDaysTime,employee);

        if( (virtualDaysTime+1)%5 == 0){
            int randomEmployeesNumber = VirtualTime.getRandomNumber(1,3);
            Set<Employee> employees = employeeService.getRandomEmployees(randomEmployeesNumber);
            LocalDateTime firedTime;
            for (Employee e:employees) {
                firedTime = VirtualTime.getRandomDataFromInterval(e.getHireTime(),endDate);
                e.setFiredTime(firedTime);
                employeeService.save(e);
                fireLogger(virtualDaysTime,e);
            }
        }
        int tp = 0;
    }

    public void hireLogger(int virtualDaysTime, Employee e){
        System.out.print("{" + VirtualTime.getStartDate().plusDays(virtualDaysTime)+"}.");
        System.out.print(" Сотрудник " + e.getId() + " нанят " + "{" + e.getHireTime() + "}.");
        System.out.print(" Отдел: {" + e.getDepartment().getName()+ "}.\n");
    }

    public void fireLogger(int virtualDaysTime, Employee e){
        System.out.print("{" + VirtualTime.getStartDate().plusDays(virtualDaysTime)+"}. ");
        System.out.print("Сотрудник " + e.getId() + " уволен " + "{" + e.getFiredTime() + "}. ");
        System.out.print("Отдел: {" + e.getDepartment().getName()+ "}.");
        System.out.println(" Проработал: {" + VirtualTime.getGetDaysBetweenTwoDate(e.getHireTime(),e.getFiredTime()) +"дней }");
    }
}
