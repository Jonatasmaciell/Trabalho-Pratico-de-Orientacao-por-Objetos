package br.edu.cafeteria.excecao;

public class PontosInsuficientesException extends Exception {

    
    public static final String MENSAGEM_XP_INSUFICIENTE = "XP insuficiente.";
    
    private int xpNecessario;
    private int xpDisponivel;

    public PontosInsuficientesException(String mensagem) {
        super(mensagem);
        this.xpNecessario = -1;
        this.xpDisponivel = -1;
    }

    public PontosInsuficientesException(String mensagem, int xpNecessario, int xpDisponivel) {
        super(mensagem);
        this.xpNecessario = xpNecessario;
        this.xpDisponivel = xpDisponivel;
    }

    public int getXpNecessario() {
        return xpNecessario;
    }

    public int getXpDisponivel() {
        return xpDisponivel;
    }

    public String getMensagemFormatada() {
        String msg = "\n✗ Erro: Cliente não possui XP suficiente!";
        msg += "\n  " + this.getMessage();
        if (xpNecessario >= 0 && xpDisponivel >= 0) {
            msg += "\n  XP necessário: " + xpNecessario;
            msg += "\n  XP disponível: " + xpDisponivel;
        }
        return msg;
    }

}