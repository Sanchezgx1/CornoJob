package br.com.cefsa.projeto.controller;

import br.com.cefsa.projeto.Login;
import br.com.cefsa.projeto.TelaOpcao;
import br.com.cefsa.projeto.dao.PessoaDAO;
import br.com.cefsa.projeto.model.Pessoa;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnInscrever;

    @FXML
    private TextField txUsuario;

    @FXML
    private PasswordField txSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnEntrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                logar();
            }
        });

        btnEntrar.setOnMouseClicked((MouseEvent e) -> {
            logar();
        });

        btnInscrever.setOnMouseClicked((MouseEvent e) -> {

            Pessoa p = new Pessoa();
            PessoaDAO dao = new PessoaDAO();

            p.setUsuaio(txUsuario.getText());
            p.setSenha(txSenha.getText());
            dao.create(p);
        });
    }

    public void fecha() {
        Login.getStage().close();
    }

    public void logar() {
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> pessoas = dao.getList();

        for (int x = 0; x < pessoas.size(); x++) {
            if (txUsuario.getText().equals(pessoas.get(x).getUsuaio()) && txSenha.getText().equals(pessoas.get(x).getSenha())) {
                TelaOpcao t = new TelaOpcao();
                x = pessoas.size();
                fecha();
                try {
                    t.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (x == pessoas.size()-1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Login Invalido");
                    alert.setContentText("O erro aconteceu devido ao usuario ser ivalido");
                    alert.show();
                }else{
                    
                }
            }
        }
    }

}
