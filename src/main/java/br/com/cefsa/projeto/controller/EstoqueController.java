/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroEstoque;
import br.com.cefsa.projeto.Estoque;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.dao.ProdutoDAO;
import br.com.cefsa.projeto.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Produto> tabela;

    @FXML
    private TableColumn<Produto, String> clmDescricao;

    @FXML
    private TableColumn<Produto, Float> clmValorUni;

    @FXML
    private TableColumn<Produto, Long> clmQuantidade;

    @FXML
    private TableColumn<Produto, Long> clmTotal;

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

        initTable();
        
    }
    
    public void initTable(){
        clmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmValorUni.setCellValueFactory(new PropertyValueFactory("valorUni"));
        clmQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        clmTotal.setCellValueFactory( new PropertyValueFactory("total"));
        tabela.setItems(atualizaTabela());
    }
    
    public ObservableList<Produto> atualizaTabela(){
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.getList());
    }
}
