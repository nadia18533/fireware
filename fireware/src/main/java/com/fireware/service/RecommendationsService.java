package com.fireware.service;

import com.fireware.domain.SettingsDTO;
import com.fireware.domain.SettingsView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.stereotype.Service;
@Data
public class RecommendationsService {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RecommendationsService.stage = stage;
    }

    public static String getRecommendationsAndResult(SettingsDTO settingsDTO, SettingsView settingsView){
        StringBuilder stringBuilder = new StringBuilder();
        double counter = 100;
        if (settingsDTO.getTodayInSchool() / settingsDTO.getTeachers() > 20 ){
            stringBuilder.append("You should have more teachers in school");
            counter-=7;
        }
        if (!settingsDTO.getEmergencyIsFast()){
            stringBuilder.append("\nWith emergency you have more " +
                    "\n chances to survive!");
            counter-=5;
        }
        if (!settingsDTO.getWaterSupply()){
            stringBuilder.append("\n You must have water supply!");
            counter-=7;
        }
        if (settingsDTO.getExtinguishers()/settingsDTO.getSquare() < 0.02){
            stringBuilder.append("\nToo small count of extinguishers ");
            counter-=7;
        }
        if (settingsDTO.getFireOutsCount() == 0 ){
            stringBuilder.append("\nYou don`t have fire outs! ");
            counter-=8;
        }
        if (settingsDTO.getFloor() > 6 ){
            stringBuilder.append("\n It`s too hight floor! ");
            counter-=7;
        }
        if (settingsDTO.getFireType().equals("Fast")){
            stringBuilder.append("\nIt`s  too fast fire. " +
                    "\n You have too small chances! ");
            counter-=10;
        }
        double toMinus = 1.75*settingsDTO.getFireForce();
        counter-=toMinus;

        settingsView.getMessages().setText(stringBuilder.toString());
        if (counter > 75) {
            settingsView.getResult().setText("Success");
            return "Success";
        } else {
            settingsView.getResult().setText("Failed");
            return "Failed";
        }
    }

}
