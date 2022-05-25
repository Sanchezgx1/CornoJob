package br.com.cefsa.projeto.dao;

import br.com.cefsa.projeto.connection.ConnectionFactory;
import br.com.cefsa.projeto.model.Orcamento;
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
public class OrcamentoDAO {

    private Connection con;

    public OrcamentoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void create(Orcamento o) {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        try {
            stmt = con.prepareStatement("INSERT INTO orcamento(marca, modelo, cliente, telefone, total, data) values(?,?,?,?,?,CURDATE())");
            stmt.setString(1, o.getMarcaMoto());
            stmt.setString(2, o.getModeloMoto());
            stmt.setString(3, o.getNomeCliente());
            stmt.setString(4, o.getNumeroCliente());
            stmt.setDouble(5, o.getTotal());
            stmt.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        } finally {
        }

    }

    public void createPedido(Produto p, Long orcamentoid, Boolean fechaconexao) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pedidopeca(idpedido, idpeca, quantidade) VALUES(?,?,?)");
            stmt.setLong(1, orcamentoid);
            stmt.setLong(2, p.getId());
            stmt.setLong(3, p.getQuantidade());
            stmt.execute();

            if (fechaconexao == true) {
                stmt.close();
                con.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar" + ex);
        } finally {
            if (fechaconexao) {
                ConnectionFactory.closeConnection(con, stmt);
            }
        }

    }

    public void delete(Orcamento o) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM orcamento WHERE id = ?");
            stmt.setLong(1, o.getId());
            stmt.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);
        } finally {
        }

    }

    public void deletePeca(Orcamento o) {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM PEDIDOPECA WHERE IDPEDIDO = ?");
            stmt.setLong(1, o.getId());
            stmt.execute();
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Orcamento> getList() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Orcamento> orc = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM orcamento ORDER BY 1 DESC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Orcamento orcamento = new Orcamento();

                orcamento.setId(rs.getLong("id"));
                orcamento.setMarcaMoto(rs.getString("marca"));
                orcamento.setModeloMoto(rs.getString("modelo"));
                orcamento.setNomeCliente(rs.getString("cliente"));
                orcamento.setNumeroCliente(rs.getString("telefone"));
                orcamento.setTotal(rs.getDouble("total"));
                orcamento.setData(rs.getDate("data"));
                orc.add(orcamento);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return orc;
    }

    public Long getUltimoOrcamento() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Long idparaRetornar = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM orcamento");
            rs = stmt.executeQuery();
            System.out.println(rs);

            while (rs.next()) {
                Orcamento orcamento = new Orcamento();
                orcamento.setId(rs.getLong("idpedidopeca"));
                idparaRetornar = orcamento.getId();

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return idparaRetornar;
    }

    public List<Orcamento> orcamentoId() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Orcamento> orc = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT MAX(ID) as id FROM ORCAMENTO");
            rs = stmt.executeQuery();
            rs.next();
            Orcamento orcamento = new Orcamento();

            orcamento.setId(rs.getLong("id"));
            orc.add(orcamento);

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return orc;
    }

}
