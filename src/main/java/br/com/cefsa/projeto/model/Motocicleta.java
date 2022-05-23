/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cefsa.projeto.model;

/**
 *
 * @author sanch
 */
public class Motocicleta {
    private String modelo;
    private String marca;
    private Cliente proprietario;

    public Motocicleta(String modelo, String marca, Cliente proprietario) {
        this.modelo = modelo;
        this.marca = marca;
        this.proprietario = proprietario;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }
    
    
}