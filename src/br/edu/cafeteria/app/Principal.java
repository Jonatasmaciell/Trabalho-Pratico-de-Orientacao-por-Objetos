package br.edu.cafeteria.app;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.Atendente;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.DiaEventoGeek;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static List<Produto> produtos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static Atendente atendente = new Atendente(1, "Gerenciador de Sistema");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("      BYTE & BREW");
            System.out.println("==============================");
            System.out.println("1 - Produtos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuProdutos(scanner);
                    break;
                case 2:
                    menuClientes(scanner);
                    break;
                case 3:
                    menuPedidos(scanner);
                    break;
                case 0:
                    System.out.println("\nPrograma encerrado.");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    // ==================== MENU PRODUTOS ====================
    private static void menuProdutos(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n======================");
            System.out.println("MENU PRODUTOS");
            System.out.println("======================");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Pesquisar Produto");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("5 - Listar Produtos");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    pesquisarProduto(scanner);
                    break;
                case 3:
                    atualizarProduto(scanner);
                    break;
                case 4:
                    removerProduto(scanner);
                    break;
                case 5:
                    listarProdutos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarProduto(Scanner scanner) {
        System.out.println("\n--- Cadastrar Produto ---");

        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo de Produto:");
        System.out.println("1 - Bebida");
        System.out.println("2 - Comida");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        try {
            if (tipo == 1) {
                System.out.print("Tamanho (P/M/G): ");
                String tamanho = scanner.nextLine().trim().toUpperCase();

                System.out.print("Cafeína (mg): ");
                double cafeina = scanner.nextDouble();
                scanner.nextLine();

                Bebida bebida = new Bebida(codigo, nome, preco, estoque, tamanho, cafeina);
                produtos.add(bebida);
                System.out.println("Bebida cadastrada com sucesso!");
            } else if (tipo == 2) {
                System.out.print("Tempo de Preparo (minutos): ");
                int tempo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("É vegano? (S/N): ");
                boolean vegano = scanner.nextLine().toUpperCase().startsWith("S");

                System.out.print("É sem glúten? (S/N): ");
                boolean semGluten = scanner.nextLine().toUpperCase().startsWith("S");

                Comida comida = new Comida(codigo, nome, preco, estoque, tempo, vegano, semGluten);
                produtos.add(comida);
                System.out.println("Comida cadastrada com sucesso!");
            } else {
                System.out.println("Tipo inválido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private static void pesquisarProduto(Scanner scanner) {
        System.out.println("\n--- Pesquisar Produto ---");
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            exibirDetalheProduto(produto);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void atualizarProduto(Scanner scanner) {
        System.out.println("\n--- Atualizar Produto ---");
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Opções de atualização:");
        System.out.println("1 - Atualizar Preço");
        System.out.println("2 - Atualizar Estoque");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Novo preço: R$ ");
            double novoPreco = scanner.nextDouble();
            scanner.nextLine();

            produto.setPrecoBase(novoPreco);

            System.out.println("Preço atualizado com sucesso!");
         
        } else if (opcao == 2) {
            System.out.print("Novo estoque: ");
            int novoEstoque = scanner.nextInt();
            scanner.nextLine();
            produto.setEstoque(novoEstoque);
            System.out.println("Estoque atualizado com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void removerProduto(Scanner scanner) {
        System.out.println("\n--- Remover Produto ---");
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        produtos.remove(produto);
        System.out.println("Produto removido com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n=== Lista de Produtos ===");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            exibirDetalheProduto(p);
        }
    }

    private static void exibirDetalheProduto(Produto p) {
        System.out.println("---");
        System.out.println("Código: " + p.getCodigo());
        System.out.println("Nome: " + p.getNome());
        System.out.println("Preço: R$ " + String.format("%.2f", p.getPrecoBase()));
        System.out.println("Estoque: " + p.getEstoque());

        if (p instanceof Bebida) {
            Bebida b = (Bebida) p;
            System.out.println("Tipo: Bebida");
            System.out.println("Tamanho: " + b.getTamanho());
            System.out.println("Cafeína: " + b.getCafeinaMg() + "mg");
        } else if (p instanceof Comida) {
            Comida c = (Comida) p;
            System.out.println("Tipo: Comida");
            System.out.println("Tempo de Preparo: " + c.getTempoPreparo() + " min");
            System.out.println("Vegano: " + (c.isVegano() ? "Sim" : "Não"));
            System.out.println("Sem Glúten: " + (c.isSemGluten() ? "Sim" : "Não"));
        }
    }

    // ==================== MENU CLIENTES ====================
    private static void menuClientes(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n======================");
            System.out.println("MENU CLIENTES");
            System.out.println("======================");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Pesquisar Cliente");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Remover Cliente");
            System.out.println("5 - Listar Clientes");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    pesquisarCliente(scanner);
                    break;
                case 3:
                    atualizarCliente(scanner);
                    break;
                case 4:
                    removerCliente(scanner);
                    break;
                case 5:
                    listarClientes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastrar Cliente ---");

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Tipo de Cliente:");
        System.out.println("1 - Standard");
        System.out.println("2 - VIP");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        try {
            if (tipo == 1) {
                ClienteStandard cliente = new ClienteStandard(nome, cpf);
                clientes.add(cliente);
                System.out.println("Cliente Standard cadastrado com sucesso!");
            } else if (tipo == 2) {
                ClienteVIP cliente = new ClienteVIP(nome, cpf);
                clientes.add(cliente);
                System.out.println("Cliente VIP cadastrado com sucesso!");
            } else {
                System.out.println("Tipo inválido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void pesquisarCliente(Scanner scanner) {
        System.out.println("\n--- Pesquisar Cliente ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            exibirDetalheCliente(cliente);
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private static void atualizarCliente(Scanner scanner) {
        System.out.println("\n--- Atualizar Cliente ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Opções de atualização:");
        System.out.println("1 - Adicionar XP");
        System.out.println("2 - Remover XP");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Quanto de XP adicionar: ");
            int xp = scanner.nextInt();
            scanner.nextLine();
            cliente.adicionarXP(xp);
            System.out.println("XP adicionado com sucesso!");
        } else if (opcao == 2) {
            System.out.print("Quanto de XP remover: ");
            int xp = scanner.nextInt();
            scanner.nextLine();
            cliente.removerXP(xp);
            System.out.println("XP removido com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void removerCliente(Scanner scanner) {
        System.out.println("\n--- Remover Cliente ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        clientes.remove(cliente);
        System.out.println("Cliente removido com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n=== Lista de Clientes ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente c : clientes) {
            exibirDetalheCliente(c);
        }
    }

    private static void exibirDetalheCliente(Cliente c) {
        System.out.println("---");
        System.out.println("Nome: " + c.getNome());
        System.out.println("CPF: " + c.getCpf());
        System.out.println("XP: " + c.getXp());
        System.out.println("Tipo: " + (c instanceof ClienteVIP ? "VIP" : "Standard"));
    }

    // ==================== MENU PEDIDOS ====================
    private static void menuPedidos(Scanner scanner) {
        int opcao;
        Pedido pedidoAtual = null;

        do {
            System.out.println("\n======================");
            System.out.println("MENU PEDIDOS");
            System.out.println("======================");

            if (pedidoAtual != null) {
                System.out.println("Pedido #" + pedidoAtual.getNumero() + " em andamento");
            }

            System.out.println("1 - Abrir Pedido");
            System.out.println("2 - Adicionar Item");
            System.out.println("3 - Finalizar Pedido");
            System.out.println("4 - Listar Pedidos");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    pedidoAtual = abrirPedido(scanner);
                    break;
                case 2:
                    if (pedidoAtual != null) {
                        adicionarItemPedido(scanner, pedidoAtual);
                    } else {
                        System.out.println("Erro: Nenhum pedido aberto!");
                    }
                    break;
                case 3:
                    if (pedidoAtual != null) {
                        finalizarPedido(scanner, pedidoAtual);
                        pedidoAtual = null;
                    } else {
                        System.out.println("Erro: Nenhum pedido aberto!");
                    }
                    break;
                case 4:
                    listarPedidos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }

    private static Pedido abrirPedido(Scanner scanner) {
        System.out.println("\n--- Abrir Pedido ---");

        if (clientes.isEmpty()) {
            System.out.println("Erro: Nenhum cliente cadastrado!");
            return null;
        }

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return null;
        }

        Pedido novoPedido = new Pedido(cliente, atendente);
        System.out.println("Pedido #" + novoPedido.getNumero() + " aberto com sucesso!");

        return novoPedido;
    }

    private static void adicionarItemPedido(Scanner scanner, Pedido pedido) {
        System.out.println("\n--- Adicionar Item ---");

        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        try {
            pedido.adicionarItem(produto, quantidade);
            System.out.println("\n✓ Item adicionado ao pedido!");
            System.out.println("  Produto: " + produto.getNome());
            System.out.println("  Quantidade: " + quantidade);
            System.out.println("  Subtotal: R$ " + String.format("%.2f", produto.getPrecoBase() * quantidade));
        } catch (EstoqueInsuficienteException e) {
            System.out.println(e.getMensagemFormatada());
        }
    }

    private static void finalizarPedido(Scanner scanner, Pedido pedido) {
        System.out.println("\n--- Finalizar Pedido ---");

        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: Pedido vazio!");
            return;
        }

        System.out.println("Itens do pedido:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("- " + item.getProduto().getNome() + " x" + item.getQuantidade() +
                    " = R$ " + String.format("%.2f", item.subtotal()));
        }

        System.out.println("\nDeseja aplicar promoção?");
        System.out.println("1 - Dia Evento Geek (10% em bebidas)");
        System.out.println("2 - Sem promoção");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            pedido.setPromocao(new DiaEventoGeek());
        }

        double total = pedido.calcularTotal();

        System.out.println("\nTotal do pedido: R$ " + String.format("%.2f", total));

        System.out.println("\nForma de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - XP (Cliente VIP)");
        System.out.print("Escolha: ");
        int formaPagamento = scanner.nextInt();
        scanner.nextLine();

        try {

            pedido.finalizarVenda(formaPagamento == 2);

            pedidos.add(pedido);

            System.out.println("\nPedido finalizado com sucesso!");
            System.out.println("Número: " + pedido.getNumero());
            System.out.println("Total: R$ " + String.format("%.2f", total));

        } catch (PontosInsuficientesException e) {

            System.out.println(e.getMensagemFormatada());

        }
    }

    private static void listarPedidos() {
        System.out.println("\n=== Lista de Pedidos ===");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido finalizado.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("---");
            System.out.println("Pedido #" + p.getNumero());
            System.out.println("Itens: " + p.getItens().size());
            double total = p.calcularTotal();
            System.out.println("Total: R$ " + String.format("%.2f", total));
        }
    }

 
    private static Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    private static Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }



}
