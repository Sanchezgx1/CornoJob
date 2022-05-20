/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.Estoque;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.dao.ProdutoDAO;
import br.com.cefsa.projeto.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sanch
 */
public class EstoqueController implements Initializable {

    @FXML
    private Button btAtualiza;

    @FXML
    private Button btCadastro;

    @FXML
    private Button btDeleta;

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

    @FXML
    private TextField txDescricaoPeca;

    @FXML
    private TextField txQtd;

    @FXML
    private TextField txValor;
    
    @FXML
    private Label lbID;
    
    private Produto seleciona;

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

            Produto p = new Produto();
            ProdutoDAO dao = new ProdutoDAO();

            p.setDescricao(txDescricaoPeca.getText());
            p.setValorUni(Long.parseLong(txValor.getText()));
            p.setQuantidade(Long.parseLong(txQtd.getText()));
            dao.create(p);

            txDescricaoPeca.setText(null);
            txValor.setText(null);
            txQtd.setText(null);
            initTable();

        });
        
        btDeleta.setOnMouseClicked((MouseEvent e) -> {
            deleta();
        });
        
        btAtualiza.setOnMouseClicked((MouseEvent e) -> {
            tabela.setItems(atualizaTabela());
        });

        initTable();

        tabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                seleciona = (Produto) newValue;
                mostraDetalhes();
            }
        });

    }

    public void initTable() {
        clmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmValorUni.setCellValueFactory(new PropertyValueFactory("valorUni"));
        clmQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        clmTotal.setCellValueFactory(new PropertyValueFactory("total"));
        tabela.setItems(atualizaTabela());
    }

    public ObservableList<Produto> atualizaTabela() {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deleta(){
        if(seleciona != null){
        ProdutoDAO dao = new ProdutoDAO();
        dao.delete(seleciona);
        }else
        {
            Alert a = new Alert(AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
       tabela.setItems(atualizaTabela());
    }
    
    public void mostraDetalhes(){
        if(seleciona != null){
            lbID.setText(seleciona.getId().toString());
            txDescricaoPeca.setText(seleciona.getDescricao());
            txValor.setText(seleciona.getValorUni().toString());
            txQtd.setText(seleciona.getQuantidade().toString());
        }else{
            lbID.setText("");
            txDescricaoPeca.setText("");
            txValor.setText("");
            txQtd.setText("");
        }
    }
    
}
