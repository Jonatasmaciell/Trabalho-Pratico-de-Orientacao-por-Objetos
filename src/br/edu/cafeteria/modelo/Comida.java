package br.edu.cafeteria.modelo;

public class Comida extends Produto {

    private int tempoPreparo;
    private boolean vegano;
    private boolean semGluten;

    public Comida(int codigo,
                   String nome,
                   double preco,
                   int estoque,
                   int tempoPreparo,
                   boolean vegano,
                   boolean semGluten) {

        super(codigo, nome, preco, estoque);

        this.tempoPreparo = tempoPreparo;
        this.vegano = vegano;
        this.semGluten = semGluten;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public boolean isVegano() {
        return vegano;
    }

    public boolean isSemGluten() {
        return semGluten;
    }
}