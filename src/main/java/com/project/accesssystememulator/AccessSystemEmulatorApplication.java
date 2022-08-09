package com.project.accesssystememulator;

import com.project.accesssystememulator.service.DepartmentService;
import com.project.accesssystememulator.service.EmployeesMgr;
import com.project.accesssystememulator.domain.entity.VirtualTime;
import com.project.accesssystememulator.service.GuestsMgr;
import com.project.accesssystememulator.service.PassEmulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AccessSystemEmulatorApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AccessSystemEmulatorApplication.class, args);

        DepartmentService departmentService = applicationContext.getBean(DepartmentService.class);
        EmployeesMgr employeesMgr = applicationContext.getBean(EmployeesMgr.class);
        GuestsMgr guestsMgr = applicationContext.getBean(GuestsMgr.class);
        PassEmulator passEmulator = applicationContext.getBean(PassEmulator.class);

        departmentService.createDepartments();

        for (int i = 0; i < VirtualTime.getDaysNumbers(); i++) {
            employeesMgr.createEmployee(i);
            guestsMgr.createGuest(i);
            passEmulator.passEmulation(i);

        }

    }
}


//            try {
//                Thread.currentThread().sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }