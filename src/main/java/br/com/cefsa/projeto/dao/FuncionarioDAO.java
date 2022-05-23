/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.dao;

import br.com.cefsa.projeto.connection.ConnectionFactory;
import br.com.cefsa.projeto.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sanch
 */
public class FuncionarioDAO {

    private Connection con;

    public FuncionarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void create(Funcionario f) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (nome,valorH) values(?,?)");
            stmt.setString(1, f.getNome());
            stmt.setLong(2, f.getValorH());
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean update(Funcionario f) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome = ?, valorH = ? WHERE id = ?");
            stmt.setString(1, f.getNome());
            stmt.setLong(2, f.getValorH());
            stmt.setLong(3, f.getId());
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Funcionario p) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setLong(1, p.getId());
            stmt.execute();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Deletado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Funcionario> getList() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario ORDER BY 2 ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario f = new Funcionario();

                f.setId(rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setValorH(rs.getLong("valorH"));
                funcionarios.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionarios;
    }

}
