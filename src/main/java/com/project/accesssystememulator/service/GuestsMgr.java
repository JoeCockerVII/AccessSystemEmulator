package com.project.accesssystememulator.service;

import com.project.accesssystememulator.domain.entity.Guest;

/**
 * Guest manager component
 * @author ilyin
 * @since 07.08.2022
 */

public interface GuestsMgr {

    void createGuest(int virtualDaysTime);

    void visitLogger(int virtualDaysTime, Guest guest);

    void visitCancelLogger(int virtualDaysTime, Guest guest);
}
