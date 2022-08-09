package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Guest;

import java.util.Set;
import java.util.UUID;

/**
 * Service to work with GuestRepository
 * @author ilyin
 * @since 07.08.2022
 */
public interface GuestService {

    void save(Guest guest);

    Set<Guest> getGuestsOfFiredEmployees();

    Guest getRandomGuest();

    void delete(UUID id);

    Guest getGuestByCard(UUID card);

}
