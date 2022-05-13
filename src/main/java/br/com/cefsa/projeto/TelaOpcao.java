package br.com.cefsa.projeto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author sanch
 */
public class TelaOpcao extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TelaOpcao.fxml"));

        Image applicationIcon = new Image(getClass().getResourceAsStream("/icons/Logo.png"));
        stage.getIcons().add(applicationIcon);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/telaopcao.css");

        stage.setTitle(" Two Wheels Workshop ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TelaOpcao.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
