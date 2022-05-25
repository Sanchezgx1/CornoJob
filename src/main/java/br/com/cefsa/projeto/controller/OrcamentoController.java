/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroOrcamento;
import br.com.cefsa.projeto.OrcamentoTela;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.dao.OrcamentoDAO;
import br.com.cefsa.projeto.dao.ProdutoDAO;
import br.com.cefsa.projeto.model.Orcamento;
import br.com.cefsa.projeto.model.Produto;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrcamentoController implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    private Button btCadastro;
    
    @FXML
    private Button btdeletaOrc;

    @FXML
    private TableView<Orcamento> tbOrcamento;

    @FXML
    private TableColumn<Orcamento, String> clmCliente;

    @FXML
    private TableColumn<Orcamento, Long> clmCodigo;

    @FXML
    private TableColumn<Orcamento, Date> clmData;

    @FXML
    private TableColumn<Orcamento, String> clmMarca;

    @FXML
    private TableColumn<Orcamento, String> clmModelo;

    @FXML
    private TableColumn<Orcamento, Double> clmValorOrc;

    @FXML
    private TableColumn<Orcamento, String> clmTelefone;
    
    private Orcamento seleciona;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            TelaOpcao to = new TelaOpcao();
            try {
                to.start(new Stage());
                OrcamentoTela.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Cadastro Estoque");
                alert.show();
            }
        });

        btCadastro.setOnMouseClicked((MouseEvent e) -> {
            CadastroOrcamento co = new CadastroOrcamento();
            try {
                co.start(new Stage());
                OrcamentoTela.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel abrir a Cadastro Orçamento");
                alert.show();
            }

        });
        
        btdeletaOrc.setOnMouseClicked((MouseEvent e) -> {
            deleta();
        });
        
        tbOrcamento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                seleciona = (Orcamento) newValue;
            }
        });
    }

    public void initTable() {
        clmCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        clmMarca.setCellValueFactory(new PropertyValueFactory("marcaMoto"));
        clmModelo.setCellValueFactory(new PropertyValueFactory("modeloMoto"));
        clmCliente.setCellValueFactory(new PropertyValueFactory("nomeCliente"));
        clmTelefone.setCellValueFactory(new PropertyValueFactory("numeroCliente"));
        clmValorOrc.setCellValueFactory(new PropertyValueFactory("total"));
        clmData.setCellValueFactory(new PropertyValueFactory("data"));
        tbOrcamento.setItems(atualizaTabela());
    }

    public ObservableList<Orcamento> atualizaTabela() {
        OrcamentoDAO dao = new OrcamentoDAO();
        return FXCollections.observableArrayList(dao.getList());
    }
    
    public void deleta() {
        if (seleciona != null) {
            OrcamentoDAO dao = new OrcamentoDAO();
            dao.delete(seleciona);
            
            Orcamento oc = new Orcamento();
            oc.setId(seleciona.getId());
            dao.deletePeca(oc);
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Usuario");
            a.show();
        }
        tbOrcamento.setItems(atualizaTabela());
    }

}
