package br.edu.cafeteria.excecao;

public class EstoqueInsuficienteException extends Exception {

    // Constantes de mensagens
    public static final String MENSAGEM_ESTOQUE_INSUFICIENTE = "Estoque insuficiente.";
    
    private int estoqueDisponivel;

    public EstoqueInsuficienteException(String mensagem) {
        super(mensagem);
        this.estoqueDisponivel = -1;
    }

    public EstoqueInsuficienteException(String mensagem, int estoqueDisponivel) {
        super(mensagem);
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public int getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public String getMensagemFormatada() {
        String msg = "\n✗ Erro ao adicionar item: " + this.getMessage();
        if (estoqueDisponivel >= 0) {
            msg += "\n  Estoque disponível: " + estoqueDisponivel + " unidades";
        }
        return msg;
    }

}