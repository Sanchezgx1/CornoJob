/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.Estoque;
import br.com.cefsa.projeto.Orcamento;
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
public class TelaOpcaoController implements Initializable {

    @FXML
    private Button btOrcamento;

    @FXML
    private Button btEstoque;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btFinanceiro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btOrcamento.setOnMouseClicked((MouseEvent e) -> {
            Orcamento or = new Orcamento();
            try {
                or.start(new Stage());
                TelaOpcao.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Cadastro Estoque");
                alert.show();
            }
        });

        btEstoque.setOnMouseClicked((MouseEvent e) -> {
            Estoque es = new Estoque();
            try {
                es.start(new Stage());
                TelaOpcao.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel abrir a tela");
                alert.show();
            }
        });

        btFuncionario.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Funcionario");
        });

        btFinanceiro.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Financeiro");
        });
    }

}
