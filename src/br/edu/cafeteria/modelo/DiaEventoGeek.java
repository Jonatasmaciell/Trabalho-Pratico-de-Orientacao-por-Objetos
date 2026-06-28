package br.edu.cafeteria.modelo;

import br.edu.cafeteria.servico.Promocional;

public class DiaEventoGeek implements Promocional {

    @Override
    public double aplicarDesconto(Pedido pedido) {

        double desconto = 0;

        for(ItemPedido item : pedido.getItens()) {

            if(item.getProduto() instanceof Bebida) {

                desconto += item.subtotal() * 0.10;

            }

        }

        return desconto;

    }

}