package br.com.cefsa.projeto;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CadastroEstoque extends Application{

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CadastroEstoque.fxml"));

        Image applicationIcon = new Image(getClass().getResourceAsStream("/icons/Logo.png"));
        stage.getIcons().add(applicationIcon);

        Scene scene = new Scene(root);
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
        CadastroEstoque.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

}
