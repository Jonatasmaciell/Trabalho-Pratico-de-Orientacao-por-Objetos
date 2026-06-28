package br.edu.cafeteria.modelo;

public abstract class Cliente {

    // 10 XP = R$1,00
    public static final int CONVERSAO_XP = 10;

    private String nome;
    private String cpf;
    private int xp;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.xp = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getXp() {
        return xp;
    }

    public void adicionarXP(int pontos) {
        xp += pontos;
    }

    public void removerXP(int pontos) {
        xp -= pontos;
    }

    public abstract int calcularXP(double valorCompra);

}