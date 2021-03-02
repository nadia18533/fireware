package com.fireware.controller;

import com.fireware.configuration.ApplicationContextProvider;
import com.fireware.domain.SettingsDTO;
import com.fireware.domain.SettingsView;
import com.fireware.domain.entity.FirewareEntity;
import com.fireware.service.FireSettingsService;
import com.fireware.service.RecommendationsService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.sql.DataSourceDefinition;
import java.io.File;
import java.net.MalformedURLException;

@Data
@Component
public class Controller {
    @FXML
    private CheckBox watterSuply, isEmergencyFast;
    @FXML
    private TextField extinguishersCount, fireOutsCount, result, peopleInSchool, teachers, floorNumber, square, level;
    @FXML
    private ChoiceBox<String> fireType;
    @FXML
    private ChoiceBox<Integer> fireForce;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView image;
    @FXML
    private TextArea messages;

    private FileChooser fileChooser = new FileChooser();
    private  FireSettingsService fireSettingsService ;
    private static int id = 0;

    public void initVariablesFromFile() throws MalformedURLException {
        File file = initFileChooser(fileChooser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result of your choise is: ");
        alert.setContentText("You have chosen : \n" + file.getName());
        alert.showAndWait();
        String fileName = file.getName().substring(0, file.getName().indexOf("."));
//        id = Integer.valueOf(fileName);
        level.setText(fileName);
        image.setImage(new Image(file.toURI().toURL().toString()));
    }

    private File initFileChooser(FileChooser chooser){
        chooser.setTitle("This is file chooser");
        return chooser.showOpenDialog(new Stage());
    }

    public void doStart() throws InterruptedException {
        SettingsDTO settingsDTO = this.getSettingView().getSettingsDTOValuesFromView();

        String result = RecommendationsService.getRecommendationsAndResult(settingsDTO, this.getSettingView());

        progressBar.setProgress(1);
        if (result.equals("Success")){
            Thread.sleep(300);

            progressBar.setStyle("-fx-accent: green;");
        } else {
            Thread.sleep(300);

            progressBar.setStyle("-fx-accent: red");
        }

    }

    public void choosePlanImage(){
        try {
            initVariablesFromFile();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    /*
    Setting random values to all text fields
     */
    public void setRandomValues(){
        fireSettingsService = (FireSettingsService) ApplicationContextProvider.getApplicationContext().getBean("fireSettingsService");
        fireSettingsService.setRandomValues(this.getSettingView());
    }

    public void saveSettings() {
        fireSettingsService = (FireSettingsService) ApplicationContextProvider.getApplicationContext().getBean("fireSettingsService");
        FirewareEntity firewareEntity = SettingsDTO.convertDTO(this.getSettingView().getSettingsDTOValuesFromView());
        firewareEntity.setId(id);
        fireSettingsService.saveEntity(firewareEntity);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result of your saving settings ");
        alert.setContentText("You have successfully saved settings!");
        alert.showAndWait();
    }

    //////////////////////////////////////////////////////////////////
    /// PRIVATE METHODS
    //////////////////////////////////////////////////////////////////

    private SettingsView getSettingView(){
        return SettingsView.builder().extinguishersCount(this.extinguishersCount)
                .fireForce(this.fireForce)
                .fireOutsCount(this.fireOutsCount)
                .fireType(this.fireType)
                .floorNumber(this.floorNumber)
                .isEmergencyFast(this.isEmergencyFast)
                .peopleInSchool(this.peopleInSchool)
                .result(this.result)
                .square(this.square)
                .teachers(this.teachers)
                .watterSuply(this.watterSuply)
                .progressBar(this.progressBar)
                .messages(this.messages)
                .build();
    }
}
