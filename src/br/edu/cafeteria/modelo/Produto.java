package br.edu.cafeteria.modelo;

public abstract class Produto {

    private int codigo;
    private String nome;
    private double precoBase;
    private int estoque;

    public Produto(int codigo, String nome, double precoBase, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = estoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void baixarEstoque(int quantidade) {
        estoque -= quantidade;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + precoBase;
    }
}