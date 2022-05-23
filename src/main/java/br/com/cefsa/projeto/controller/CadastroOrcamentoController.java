/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroOrcamento;
import br.com.cefsa.projeto.Orcamento;
import br.com.cefsa.projeto.dao.ProdutoDAO;
import br.com.cefsa.projeto.model.Produto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sanch
 */
public class CadastroOrcamentoController implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btacrescenta;

    @FXML
    private Button btsalvaFun;

    @FXML
    private Button btsubtrai;

    @FXML
    private TableColumn<Produto, String> clmpeca;

    @FXML
    private TableColumn<Produto, Double> clmValorUni;

    @FXML
    private TableColumn<Produto, Long> clmquantidade;

    @FXML
    private TableColumn<?, String> clmpecaOrc;

    @FXML
    private TableColumn<?, Double> clmvalorUniOrc;

    @FXML
    private TableColumn<?, Long> clmquantidadeOrc;

    @FXML
    private TableView<Produto> tbPeca;

    @FXML
    private TableView<?> tbpecaOrc;

    @FXML
    private TextField txcliente;

    @FXML
    private TextField txhoraFun;

    @FXML
    private TextField txmarca;

    @FXML
    private TextField txmodelo;

    @FXML
    private TextField txnomeFun;
    
    @FXML
    private Button btCadastrarPeca;
    
    @FXML
    private TextField txnomePeca;

    @FXML
    private TextField txqtdPeca;

    @FXML
    private TextField txvalorPeca;

    private Produto seleciona;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Orcamento or = new Orcamento();
            try {
                or.start(new Stage());
                CadastroOrcamento.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Orçamento");
                alert.show();
            }
        });
        
        btacrescenta.setOnMouseClicked((MouseEvent e) -> {
            
        });
        
        tbPeca.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                seleciona = (Produto) newValue;
                mostraPeca();
            }
        });
        
    }

    public void initTable() {
        clmpeca.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmValorUni.setCellValueFactory(new PropertyValueFactory("valorUni"));
        clmquantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tbPeca.setItems(atualizaTabela());
    }

    public ObservableList<Produto> atualizaTabela() {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.getList());
    }

    public void mostraPeca() {
        if (seleciona != null) {
            txnomePeca.setText(seleciona.getDescricao());
            txvalorPeca.setText(seleciona.getValorUni().toString());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar Inserir a Peça");
                alert.show();
        }
    }
    
    public List<Produto> getList(){
        List<Produto> produtos = new ArrayList<>();
        
        Produto p = new Produto();
        
        p.setDescricao(txnomePeca.getText());
        p.setValorUni(Double.parseDouble(txvalorPeca.getText()));
        p.setQuantidade(Long.parseLong(txqtdPeca.getText()));
        produtos.add(p);
        
        return produtos;
    }
    
    public void initTable2(){
        clmpecaOrc.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmvalorUniOrc.setCellValueFactory(new PropertyValueFactory("valorUni"));
        clmquantidadeOrc.setCellValueFactory(new PropertyValueFactory("quantidade"));
        //tbpecaOrc.setItems(atualizaTabela2());
    }
    
    public ObservableList<Produto> atualizaTabela2() {
        return FXCollections.observableArrayList(getList());
    }

}
