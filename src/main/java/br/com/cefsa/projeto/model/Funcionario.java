/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.model;

/**
 *
 * @author sanch
 */
public class Funcionario {
    
    private Long id;
    private String nome;
    private Long valorH;

    public Funcionario(Long id, String nome, Long valorH) {
        this.id = id;
        this.nome = nome;
        this.valorH = valorH;
    }

    
    public Funcionario(String nome, Long valorH) {
        this.nome = nome;
        this.valorH = valorH;
    }

    public Funcionario() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getValorH() {
        return valorH;
    }

    public void setValorH(Long valorH) {
        this.valorH = valorH;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
}
