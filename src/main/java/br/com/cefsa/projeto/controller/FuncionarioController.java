package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.FuncionarioTela;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.dao.FuncionarioDAO;
import br.com.cefsa.projeto.model.Funcionario;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FuncionarioController implements Initializable {

    @FXML
    private Button btAtualiza;

    @FXML
    private Button btCadastro;

    @FXML
    private Button btDeleta;

    @FXML
    private Button btVolta;

    @FXML
    private TableColumn<Funcionario, String> clmNomeFun;

    @FXML
    private TableColumn<Funcionario, Long> clmValorH;

    @FXML
    private TableView<Funcionario> tabelaFunc;
    
    @FXML
    private TextField txNome;

    @FXML
    private TextField txValorH;
    
    @FXML
    private Label lbId;

    private Funcionario seleciona;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btVolta.setOnMouseClicked((MouseEvent e) -> {
            TelaOpcao t = new TelaOpcao();
            try {
                t.start(new Stage());
                FuncionarioTela.getStage().close();
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Não foi possivel voltar a Tela de Opcoes");
                alert.show();
            }
        });
        
        btCadastro.setOnMouseClicked((MouseEvent e) -> {

            Funcionario f = new Funcionario();
            FuncionarioDAO dao = new FuncionarioDAO();

            f.setNome(txNome.getText());
            f.setValorH(Long.parseLong(txValorH.getText()));
            dao.create(f);

            txNome.setText(null);
            txValorH.setText(null);
            initTable();

        });

        btDeleta.setOnMouseClicked((MouseEvent e) -> {
            deleta();
        });

        btAtualiza.setOnMouseClicked((MouseEvent e) -> {
            atualiza();
            tabelaFunc.setItems(atualizaTabela());
        });

        initTable();

        tabelaFunc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                seleciona = (Funcionario) newValue;
                mostraDetalhes();
            }
        });
        
    }  
    
    public void initTable() {
        clmNomeFun.setCellValueFactory(new PropertyValueFactory("nome"));
        clmValorH.setCellValueFactory(new PropertyValueFactory("valorH"));
        tabelaFunc.setItems(atualizaTabela());
    }

    public ObservableList<Funcionario> atualizaTabela() {
        FuncionarioDAO dao = new FuncionarioDAO();
        return FXCollections.observableArrayList(dao.getList());
    }

    public void deleta() {
        if (seleciona != null) {
            FuncionarioDAO dao = new FuncionarioDAO();
            dao.delete(seleciona);
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Selecione um Funcionario");
            a.show();
        }
        tabelaFunc.setItems(atualizaTabela());
    }

    public void mostraDetalhes() {
        if (seleciona != null) {
            lbId.setText(seleciona.getId().toString());
            txNome.setText(seleciona.getNome());
            txValorH.setText(seleciona.getValorH().toString());
        } else {
            lbId.setText("");
            txNome.setText("");
            txValorH.setText("");
        }
    }

    public void atualiza() {

        Long id = Long.parseLong(lbId.getText()), valorH = Long.parseLong(txValorH.getText());
        String nome = txNome.getText();

        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario f = new Funcionario(id, nome, valorH);
        dao.update(f);
    }
    
}
