package com.biblioteca.model;

public class Usuario {

    private String nome;
    private int quantidadeLivrosEmprestados;
    private boolean possuiMultaPendente;
    private double valorMulta;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeLivrosEmprestados = 0;
        this.possuiMultaPendente = false;
        this.valorMulta = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeLivrosEmprestados() {
        return quantidadeLivrosEmprestados;
    }

    public void adicionarEmprestimo() {
        this.quantidadeLivrosEmprestados++;
    }

    public void removerEmprestimo() {
        if (this.quantidadeLivrosEmprestados > 0) {
            this.quantidadeLivrosEmprestados--;
        }
    }

    public boolean isPossuiMultaPendente() {
        return possuiMultaPendente;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void aplicarMulta(double valor) {
        if (valor > 0) {
            this.valorMulta += valor;
            this.possuiMultaPendente = true;
        }
    }

    public void quitarMulta() {
        this.valorMulta = 0.0;
        this.possuiMultaPendente = false;
    }
}