/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroEstoque;
import br.com.cefsa.projeto.Estoque;
import br.com.cefsa.projeto.TelaOpcao;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sanch
 */
public class CadastroEstoqueController implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    private Button btCadastroEstoque;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Estoque es = new Estoque();
            try {
                es.start(new Stage());
                CadastroEstoque.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Cadastro Estoque");
                alert.show();
            }
        });

        btCadastroEstoque.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Cadastro Feito");
        });
    }

}
