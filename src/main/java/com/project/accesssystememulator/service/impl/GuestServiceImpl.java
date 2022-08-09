package com.project.accesssystememulator.service.impl;

import com.project.accesssystememulator.domain.entity.Guest;
import com.project.accesssystememulator.repository.GuestRepository;
import com.project.accesssystememulator.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * @author ilyin
 * @since 07.08.2022
 */

@Service
@Primary
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Override
    @Transactional
    public void save(Guest guest) {
        guestRepository.save(guest);
    }

    @Override
    @Transactional
    public Set<Guest> getGuestsOfFiredEmployees(){
        return guestRepository.getGuestsOfFiredEmployees();
    }

    @Override
    @Transactional
    public Guest getRandomGuest(){
        return guestRepository.getRandomGuest();
    }
    @Override
    @Transactional
    public void delete(UUID id){
        guestRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Guest getGuestByCard(UUID card){
        return guestRepository.getGuestByCARD(card);
    }


}
