/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.model;

import br.com.cefsa.projeto.dao.ProdutoDAO;
import java.util.List;


public class Produto {
    
    private Long id;
    private String descricao;
    private Double valorUni;
    private Long quantidade;
    private Double total;

    public Produto() {
    }

    public Produto(Long id, String descricao, Double valorUni, Long quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.valorUni = valorUni;
        this.quantidade = quantidade;
    }

    public Produto(String descricao, Double valorUni, Long quantidade) {
        this.descricao = descricao;
        this.valorUni = valorUni;
        this.quantidade = quantidade;
    }

    public Produto(String nome, Long valorH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUni() {
        return valorUni;
    }

    public void setValorUni(Double valorUni) {
        this.valorUni = valorUni;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double valorTotal(double valor, long qtd){
        valor = valorUni;
        qtd = quantidade;
        double total = valorUni*Double.parseDouble(quantidade.toString());
        return total;
    }
    
    public void listaProduto(){
        List<Produto> produto = new ProdutoDAO().getList();
        
    }
}
