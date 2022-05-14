/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.model;


public class Produto {
    
    private Long id;
    private String descricao;
    private Long valorUni;
    private Long quantidade;
    private Long total;

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

    public Long getValorUni() {
        return valorUni;
    }

    public void setValorUni(Long valorUni) {
        this.valorUni = valorUni;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    
    public Long valorTotal(){
        this.valorUni = valorUni;
        this.quantidade = quantidade;
        total = valorUni*quantidade;
        return total;
    }
    
}
