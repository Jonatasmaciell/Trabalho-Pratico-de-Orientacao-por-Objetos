package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public int calcularXP(double valorCompra) {

        return (int) (valorCompra * 2);

    }

    public void pagarComXP(double valorCompra)
            throws PontosInsuficientesException {

        int xpNecessario =
                (int) (valorCompra * CONVERSAO_XP);

        if(getXp() < xpNecessario){

            throw new PontosInsuficientesException(
                    "XP insuficiente.");

        }

        removerXP(xpNecessario);

    }

}