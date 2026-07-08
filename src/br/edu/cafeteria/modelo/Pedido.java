package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.*;
import br.edu.cafeteria.servico.Promocional;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int contador = 1;

    private int numero;

    private Cliente cliente;

    private Atendente atendente;

    private List<ItemPedido> itens;

    private Promocional promocao;

    public Pedido(Cliente cliente, Atendente atendente) {

        this.numero = contador++;

        this.cliente = cliente;

        this.atendente = atendente;

        itens = new ArrayList<>();

    }

    public int getNumero() {
        return numero;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setPromocao(Promocional promocao) {
        this.promocao = promocao;
    }
    
    public void adicionarItem(Produto produto)
            throws EstoqueInsuficienteException {

        adicionarItem(produto,1);

    }

    public void adicionarItem(Produto produto,
                              int quantidade)
            throws EstoqueInsuficienteException {

        if(produto.getEstoque() < quantidade){

            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente.");

        }

        itens.add(
                new ItemPedido(produto,quantidade));

    }
    
    public double calcularTotal(){

        double total = 0;

        for(ItemPedido item : itens){

            total += item.subtotal();

        }

        if(promocao != null){

            total -= promocao.aplicarDesconto(this);

        }

        return total;

    }
    
   public void finalizarVenda(boolean usarXP)
        throws PontosInsuficientesException {

    double total = calcularTotal();

    if (usarXP) {

        if (!(cliente instanceof ClienteVIP)) {
            throw new PontosInsuficientesException(
                    "Apenas clientes VIP podem pagar com XP.");
        }

        ClienteVIP vip = (ClienteVIP) cliente;

        int xpNecessario = (int) (total * 10);

        if (vip.getXp() < xpNecessario) {
            throw new PontosInsuficientesException(
                    "XP insuficiente para finalizar a compra.");
        }

        vip.removerXP(xpNecessario);

    } else {

        if (cliente != null) {
            cliente.adicionarXP(cliente.calcularXP(total));
        }

    }

    for (ItemPedido item : itens) {
        item.getProduto().baixarEstoque(item.getQuantidade());
    }
}
}
