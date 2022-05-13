package br.com.cefsa.projeto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Login extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

        Image applicationIcon = new Image(getClass().getResourceAsStream("/icons/Logo.png"));
        stage.getIcons().add(applicationIcon);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Login.css");

        stage.setTitle(" Two Wheels Workshop ");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
