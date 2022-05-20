/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.dao;

import br.com.cefsa.projeto.connection.ConnectionFactory;
import br.com.cefsa.projeto.model.Produto;
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
public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void create(Produto p) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto(descricao,valorUni,quantidade,total) values(?,?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUni());
            stmt.setLong(3, p.getQuantidade());
            stmt.setDouble(4, p.valorTotal(p.getValorUni(), p.getQuantidade()));
            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void update(Produto p) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao = ?, valorUni = ?, quantidade = ?, total = ? WHERE id = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUni());
            stmt.setLong(3, p.getQuantidade());
            stmt.setDouble(4, p.valorTotal(p.getValorUni(), p.getQuantidade()));
            stmt.setLong(5, p.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Produto p) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setLong(1, p.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Deletado com Sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> getList() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM PRODUTO ORDER BY 2 ASC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto p = new Produto();

                p.setId(rs.getLong("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorUni(rs.getDouble("valorUni"));
                p.setQuantidade(rs.getLong("quantidade"));
                p.setTotal(rs.getDouble("total"));
                produtos.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;
    }

}
