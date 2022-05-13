/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroEstoque;
import br.com.cefsa.projeto.Estoque;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author sanch
 */
public class EstoqueController implements Initializable {

    @FXML
    private Button btCadastro;

    @FXML
    private Button btVoltar;

    @FXML
    private TableView<Produto> tbvestoque;

    @FXML
    private TableColumn<Produto, String> tcPeca;

    @FXML
    private TableColumn<Produto, Float> tcValorU;

    @FXML
    private TableColumn<Produto, Long> tcQuantidade;

    @FXML
    private TableColumn<Produto, Long> tcTotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            TelaOpcao t = new TelaOpcao();
            try {
                t.start(new Stage());
                Estoque.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Tela de Opcoes");
                alert.show();
            }
        });

        btCadastro.setOnMouseClicked((MouseEvent e) -> {
            CadastroEstoque t = new CadastroEstoque();
            try {
                t.start(new Stage());
                Estoque.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Cadastro de Itens");
                alert.show();
            }
        });

    }
}
