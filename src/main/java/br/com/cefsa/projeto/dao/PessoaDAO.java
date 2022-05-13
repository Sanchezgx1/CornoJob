/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.dao;

import br.com.cefsa.projeto.connection.ConnectionFactory;
import br.com.cefsa.projeto.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sanch
 */
public class PessoaDAO {

    private Connection con;

    public PessoaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void create(Pessoa p) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pessoa(nome,senha) values(?,?)");
            stmt.setString(1, p.getUsuaio());
            stmt.setString(2, p.getSenha());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Pessoa> getList() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setUsuaio(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                pessoas.add(p);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro, Lista não foi retornada" + ex);
            return null;
        }
        return pessoas;
    }

}
