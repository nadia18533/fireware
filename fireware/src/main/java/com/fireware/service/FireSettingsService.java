package com.fireware.service;

import com.fireware.configuration.ApplicationContextProvider;
import com.fireware.domain.SettingsDTO;
import com.fireware.domain.SettingsView;
import com.fireware.domain.entity.FirewareEntity;
import com.fireware.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class FireSettingsService {

    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private Random random;

    public void setRandomValues(SettingsView settingsView){
        settingsView.getExtinguishersCount().setText(String.valueOf(random.nextInt(10)));
        settingsView.getFireOutsCount().setText(String.valueOf(random.nextInt(4)));
        settingsView.getPeopleInSchool().setText(String.valueOf(random.nextInt(1200)));
        settingsView.getTeachers().setText(String.valueOf(random.nextInt(70)));
        settingsView.getFloorNumber().setText(String.valueOf(random.nextInt(5)));
        settingsView.getSquare().setText(String.valueOf(random.nextInt(2000)));
        settingsView.getFireType().setValue(settingsView.getFireType().getItems().get(random.nextInt(settingsView.getFireType().getItems().size())));
        settingsView.getFireForce().setValue(settingsView.getFireForce().getItems().get(random.nextInt(settingsView.getFireForce().getItems().size())));
        settingsView.getWatterSuply().setSelected(random.nextBoolean());
        settingsView.getIsEmergencyFast().setSelected(random.nextBoolean());
    }

    public void setValuesAccordingToPlan(SettingsView settingsView, String fileName){
        settingsRepository = (SettingsRepository) ApplicationContextProvider.getApplicationContext().getBean("settingsRepository");
        FirewareEntity firewareEntity = this.settingsRepository.getFirstById(Integer.valueOf(fileName));
        if (firewareEntity != null) {
            SettingsDTO settingsDTO = SettingsDTO.extractSettingsDto(firewareEntity);
            settingsView.getExtinguishersCount().setText(settingsDTO.getExtinguishers().toString());
            settingsView.getFireOutsCount().setText(settingsDTO.getFireOutsCount().toString());
            settingsView.getPeopleInSchool().setText(settingsDTO.getTodayInSchool().toString());
            settingsView.getTeachers().setText(settingsDTO.getTeachers().toString());
            settingsView.getFloorNumber().setText(settingsDTO.getFloor().toString());
            settingsView.getSquare().setText(settingsDTO.getSquare().toString());
            settingsView.getFireType().setValue(settingsDTO.getFireType());
            settingsView.getFireForce().setValue(settingsDTO.getFireForce());
            settingsView.getWatterSuply().setSelected(settingsDTO.getWaterSupply());
            settingsView.getIsEmergencyFast().setSelected(settingsDTO.getEmergencyIsFast());
        }
    }

    public void saveEntity(FirewareEntity firewareEntity){
        settingsRepository = (SettingsRepository) ApplicationContextProvider.getApplicationContext().getBean("settingsRepository");
        settingsRepository.save(firewareEntity);
    }


}
