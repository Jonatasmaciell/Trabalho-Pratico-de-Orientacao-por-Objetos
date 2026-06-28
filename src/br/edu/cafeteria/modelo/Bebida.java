package br.edu.cafeteria.modelo;

public class Bebida extends Produto {

    private String tamanho;
    private double cafeinaMg;

    public Bebida(int codigo,
                  String nome,
                  double preco,
                  int estoque,
                  String tamanho,
                  double cafeinaMg) {

        super(codigo, nome, preco, estoque);

        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;
    }

    public String getTamanho() {
        return tamanho;
    }

    public double getCafeinaMg() {
        return cafeinaMg;
    }
}