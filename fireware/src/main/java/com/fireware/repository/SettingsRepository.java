package com.fireware.repository;

import com.fireware.domain.SettingsDTO;
import com.fireware.domain.entity.FirewareEntity;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<FirewareEntity, Integer> {
    FirewareEntity getFirstById(Integer id);
}
