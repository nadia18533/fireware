package com.fireware.domain;

import com.fireware.domain.SettingsDTO;
import javafx.scene.control.*;
import lombok.Builder;
import lombok.Data;
import sun.rmi.server.InactiveGroupException;

@Data
@Builder
public class SettingsView {
    private CheckBox watterSuply;
    private CheckBox isEmergencyFast;
    private TextField extinguishersCount;
    private TextField fireOutsCount;
    private TextField result;
    private TextField peopleInSchool;
    private TextField teachers;
    private TextField floorNumber;
    private TextField square;
    private TextField level;
    private ChoiceBox<String> fireType;
    private ChoiceBox<Integer> fireForce;
    private ProgressBar progressBar;
    private TextArea messages;
    
    public SettingsDTO getSettingsDTOValuesFromView(){
        return SettingsDTO.builder()
                .fireForce(this.getFireForce().getValue())
                .emergencyIsFast(this.getIsEmergencyFast().isSelected())
                .extinguishers(Integer.valueOf(this.getExtinguishersCount().getText()))
                .fireOutsCount(Integer.valueOf(this.getFireOutsCount().getText()))
                .fireType(this.getFireType().getValue())
                .floor(Integer.valueOf(this.getFloorNumber().getText()))
                .todayInSchool(Integer.valueOf(this.getPeopleInSchool().getText()))
                .square(Double.parseDouble(this.getSquare().getText()))
                .waterSupply(this.getWatterSuply().isSelected())
                .teachers(Integer.valueOf(this.getTeachers().getText()))
                .result("")
                .build();
                
    }
}
