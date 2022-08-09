package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.*;
import com.project.accesssystememulator.domain.exception.EntityNotFoundDbException;
import com.project.accesssystememulator.service.EmployeeService;
import com.project.accesssystememulator.service.GuestService;
import com.project.accesssystememulator.service.PassEmulator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author ilyin
 * @since 08.08.2022
 */
@Service
@RequiredArgsConstructor
public class PassEmulatorImpl implements PassEmulator {

    private final EmployeeService employeeService;
    private final GuestService guestService;

    @Override
    public void passEmulation(int virtualDaysTime) {

        for (int i = 0; i < 10; i++) {
            int cardNotFindExceptionCounter = 0;
            Person person = null;
            UUID card = cardCodeEmulation();

            try {
                Person employee = Optional.ofNullable(employeeService.getEmployeeByCard(card)).orElseThrow(() -> new EntityNotFoundDbException("Employee"));
                person = employee;
            }catch(EntityNotFoundDbException e){
                cardNotFindExceptionCounter++;
            }

            try {
                Person guest = Optional.ofNullable(guestService.getGuestByCard(card)).orElseThrow(() -> new EntityNotFoundDbException("Guest"));
                person = guest;
            }catch(EntityNotFoundDbException e){
                cardNotFindExceptionCounter++;
            }

            if(cardNotFindExceptionCounter == 2){
                unknownCardLogger(card);
            }else{
                Role role = person.getType();
                if(person.getType().equals(Role.EMPLOYEE)){
                    employeeLogger(virtualDaysTime, (Employee) person);
                }else if(person.getType().equals(Role.GUEST)){
                    guestLogger(virtualDaysTime, (Guest) person);
                }
            }

        }
    }

    public UUID cardCodeEmulation(){
        UUID card = UUID.randomUUID();
        if(Math.random()<0.8){
            if (Math.random() < 0.5) {
                try {
                    card = Optional.ofNullable(employeeService.getRandomEmployee()).map(Person::getCARD).orElseThrow(() -> new EntityNotFoundDbException("Employee"));
                }catch (EntityNotFoundDbException ignored){}
            } else {
                try {
                    card = Optional.ofNullable(guestService.getRandomGuest()).map(Person::getCARD).orElseThrow(() -> new EntityNotFoundDbException("Guest"));
                }catch (EntityNotFoundDbException ignored){}
            }
        }

        return card;
    }

    @Override
    public void employeeLogger(int virtualDaysTime, Employee e) {
        boolean isFiredEmpty = Optional.ofNullable(e.getFiredTime()).isEmpty();
        if(isFiredEmpty){
            infoLogger(virtualDaysTime,e,"Предоставлен доступ сотруднику");
        }
        else if(e.getFiredTime().isAfter(VirtualTime.getStartDate().plusDays(virtualDaysTime))){
            infoLogger(virtualDaysTime,e, "Предоставлен доступ сотруднику");
        }
        else{
            infoLogger(virtualDaysTime,e, "Доступ запрещен сотруднику");
        }
    }

    public void infoLogger(int virtualDaysTime, Employee e, String message) {
        System.out.print("{" + VirtualTime.getStartDate().plusDays(virtualDaysTime)+"}. ");
        System.out.print (message + " {" + e.getId() + "}. ");
        System.out.print ("Отдел: {" + e.getDepartment().getName()+ "}. ");
        System.out.println ("Карта: {" + e.getCARD()+ "}.");
    }

    @Override
    public void guestLogger(int virtualDaysTime, Guest g) {
        boolean isVisitEmpty = Optional.ofNullable(g.getVisitDate()).isEmpty();
        if(isVisitEmpty){
            System.out.print("{" + VirtualTime.getStartDate().plusDays(virtualDaysTime)+"}. ");
            System.out.print("Предоставлен доступ гостю {" + g.getId() + "}. ");
            System.out.print("Пришел к {" + g.getEmployee().getId() + "} ");
            System.out.print("из отдела: {" + g.getEmployee().getDepartment().getName() + "}.");
            System.out.println ("Карта: {" + g.getCARD()+ "}.");
        }
        else{
            System.out.print("{" + VirtualTime.getStartDate().plusDays(virtualDaysTime)+"}. ");
            System.out.print("Доступ запрещен гостю {" + g.getId() + "}. ");
            System.out.println ("Карта: {" + g.getCARD()+ "}.");
        }
    }

    @Override
    public void unknownCardLogger(UUID card) {
        System.out.println("Поднесена неизвестная карта: " + card);
    }
}
