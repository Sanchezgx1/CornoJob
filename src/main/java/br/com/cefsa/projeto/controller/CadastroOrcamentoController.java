/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.CadastroOrcamento;
import br.com.cefsa.projeto.Orcamento;
import br.com.cefsa.projeto.dao.FuncionarioDAO;
import br.com.cefsa.projeto.dao.ProdutoDAO;
import br.com.cefsa.projeto.model.Funcionario;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Button btsalvaFun;

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
    private TableView<Produto> tbpecaOrc;

    @FXML
    private TextField txcliente;

    @FXML
    private TextField txHoraFun;

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

    @FXML
    private Label lbvalorT;

    @FXML
    private Label lbID;

    @FXML
    private ComboBox<Funcionario> cbfuncionario;

    private Produto seleciona;

    private Funcionario seleciona2;

    private static List<Produto> produtos = new ArrayList<>();

    private static List<Funcionario> func = new ArrayList<>();

    private double precoTotal = 0;

    private Funcionario funcionarioEscolhido = new Funcionario();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        carregaFuncionario();

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

        btCadastrarPeca.setOnMouseClicked((MouseEvent e) -> {
            initTable2();
        });

        tbPeca.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                seleciona = (Produto) newValue;
                mostraPeca();
            }
        });

        cbfuncionario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {
            @Override
            public void changed(ObservableValue<? extends Funcionario> ov, Funcionario oldValue, Funcionario newValue) {
                seleciona2 = (Funcionario) newValue;
                atribuiFuncionario(seleciona2.getId());
            }
        });

        txHoraFun.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                calculaPrecos();
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
            lbID.setText(seleciona.getId().toString());
            txnomePeca.setText(seleciona.getDescricao());
            txvalorPeca.setText(seleciona.getValorUni().toString());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possivel voltar Inserir a Peça");
            alert.show();
        }
    }

    public List<Produto> getList() {

        Produto p = new Produto();
        p.setId(Long.parseLong(lbID.getText()));
        p.setDescricao(txnomePeca.getText());
        p.setValorUni(Double.parseDouble(txvalorPeca.getText()));
        p.setQuantidade(Long.parseLong(txqtdPeca.getText()));

        if (produtos.size() > 0) {
            for (Produto product : produtos) {
                if (product.getId().equals(p.getId())) {
                    produtos.remove(product);
                    break;
                }
            }
        }

        if (Double.parseDouble(txqtdPeca.getText()) > seleciona.getQuantidade()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Quantidade máxima é: " + seleciona.getQuantidade());
            alert.show();
            p.setQuantidade(seleciona.getQuantidade());
        }
        if (p.getQuantidade() > 0) {
            produtos.add(p);

        }
        calculaPrecos();
        return produtos;
    }

    public void initTable2() {
        clmpecaOrc.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmvalorUniOrc.setCellValueFactory(new PropertyValueFactory("valorUni"));
        clmquantidadeOrc.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tbpecaOrc.setItems(atualizaTabela2());
    }

    public ObservableList<Produto> atualizaTabela2() {
        return FXCollections.observableArrayList(getList());
    }

    public void atribuiFuncionario(Long idSelecionado) {
        for (Funcionario funcionario : func) {
            if (funcionario.getId() == idSelecionado) {
                funcionarioEscolhido = funcionario;
                break;
            }
        }
    }

    public void calculaPrecos() {
        precoTotal = 0;
        try {
            if (funcionarioEscolhido != null && txHoraFun.getText() != null) {
                precoTotal += funcionarioEscolhido.getValorH() * Double.parseDouble(txHoraFun.getText());
            }
        }catch (Exception ex){
            
        }
        
        if (produtos.size() > 0) {
            for (Produto p : produtos) {
                precoTotal += p.getQuantidade() * p.getValorUni();
            }
        }
        lbvalorT.setText(Double.toString(precoTotal));
    }

    public void carregaFuncionario() {

        cbfuncionario.setItems(pegaFuncionario());

    }

    public static ObservableList<Funcionario> pegaFuncionario() {
        FuncionarioDAO fdao = new FuncionarioDAO();
        func = fdao.getList();
        return FXCollections.observableArrayList(fdao.getList());
    }

}
