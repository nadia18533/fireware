package com.fireware;

import com.fireware.service.RecommendationsService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.rmi.runtime.NewThreadAction;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

@SpringBootApplication
public class FirewareApplication  extends Application {

    public static void main(String[] args) {

        SpringApplication.run(FirewareApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/java/com/fireware/sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Nadia Tarapata. Fireware learning&emulation project");
        File file = new File ("src/main/java/com/fireware/logo.png");

        primaryStage.getIcons().add(new Image(file.toURI().toURL().toString()));
        primaryStage.setScene(new Scene(root));
        RecommendationsService.setStage(primaryStage);
        primaryStage.show();
        primaryStage.setResizable(false);

    }
}
