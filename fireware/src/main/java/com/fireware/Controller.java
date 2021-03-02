package com.fireware;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.xml.ws.soap.Addressing;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javafx.scene.image.ImageView;

import java.util.Scanner;

public class Controller {
    @FXML
    private Button start, choosePlan, randomPlan, showChart, getLessons, visitWebsite;
    @FXML
    private CheckBox watterSuply, isEmergencyFast;
    @FXML
    private TextField extinguishersCount, fireOutsCount, result, peopleInSchool, teachers, floorNumber, square;
    @FXML
    private ChoiceBox<String> fireType, fireForce;
    @FXML
    private ImageView image;
    private FileChooser fileChooser = new FileChooser();
    private Random random = new Random();

    public void initVariablesFromFile(){
        File file = initFileChooser(fileChooser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result of your choise is: ");
        alert.setContentText("You have chosen : \n" + file.getName());
        alert.showAndWait();

        try{
            Path imageFile = Paths.get(file.getAbsolutePath());
            Image image = new Image(imageFile.toUri().toURL().toExternalForm());
            this.image.setFitHeight(image.getHeight());
            this.image.setImage(image);




        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File initFileChooser(FileChooser chooser){
        chooser.setTitle("This is file chooser");
        return chooser.showOpenDialog(new Stage());
    }

    public void doStart(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hello, Nadia! ");
        alert.showAndWait();
    }

    public void choosePlanImage(){
        initVariablesFromFile();
    }

    public void setRandomValues(){
        extinguishersCount.setText(String.valueOf(random.nextInt(10)));
        fireOutsCount.setText(String.valueOf(random.nextInt(4)));
        peopleInSchool.setText(String.valueOf(random.nextInt(1200)));
        teachers.setText(String.valueOf(random.nextInt(70)));
        floorNumber.setText(String.valueOf(random.nextInt(5)));
        square.setText(String.valueOf(random.nextInt(2000)));
        fireType.setValue(fireType.getItems().get(random.nextInt(fireType.getItems().size())));
        fireForce.setValue(fireForce.getItems().get(random.nextInt(fireForce.getItems().size())));
        watterSuply.setSelected(random.nextBoolean());
        isEmergencyFast.setSelected(random.nextBoolean());
    }
}
