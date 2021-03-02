package com.fireware.domain;

import com.fireware.domain.entity.FirewareEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettingsDTO {
    private String fireType;
    private Integer fireForce;
    private Integer todayInSchool;
    private Integer teachers;
    private Integer floor;
    private Integer extinguishers;
    private Integer fireOutsCount;
    private Double square;
    private Boolean waterSupply;
    private Boolean emergencyIsFast;
    private String result;

    public static SettingsDTO extractSettingsDto(FirewareEntity firewareEntity){
        return SettingsDTO.builder()
                .fireType(firewareEntity.getFireType())
                .fireForce(Integer.parseInt(firewareEntity.getFireForce()))
                .todayInSchool(firewareEntity.getChild())
                .teachers(firewareEntity.getTeachers())
                .floor(firewareEntity.getFloor())
                .extinguishers(firewareEntity.getExtinguishers())
                .fireOutsCount(firewareEntity.getFireOutsCount())
                .square(firewareEntity.getSquare())
                .waterSupply(firewareEntity.getWaterSupply())
                .emergencyIsFast(firewareEntity.getEmergencyFast())
                .build();
    }

    public static FirewareEntity convertDTO(SettingsDTO settingsDTO) {
        return FirewareEntity.builder()
                .fireType(settingsDTO.getFireType())
                .fireForce(settingsDTO.getFireForce().toString())
                .child(settingsDTO.getTodayInSchool())
                .teachers(settingsDTO.getTeachers())
                .floor(settingsDTO.getFloor())
                .extinguishers(settingsDTO.getExtinguishers())
                .fireOutsCount(settingsDTO.getFireOutsCount())
                .square(settingsDTO.getSquare())
                .waterSupply(settingsDTO.getWaterSupply())
                .emergencyFast(settingsDTO.getEmergencyIsFast())
                .build();
    }
}
