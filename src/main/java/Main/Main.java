package Main;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Programa principal do controle de estoque..
 */
public class Main {

    static Produto[] estoque = new Produto[100];//criando um vetor para armazenar 100 objetos da classe produto

    static StringBuilder historicoMov = new StringBuilder();

    static int total = 0;

<<<<<<< HEAD
<<<<<<< HEAD
    static int marcadorBuscarNome = 0; //Variável na qual guarda a posição do vetor através da procura por nome
=======
    static int marcadorBuscarNome = 0;
>>>>>>> ad80b04 (init: Inicio do projeto)

=======
>>>>>>> e291402 (Adcionando o menu de reajustes, tela de reajuste, e processo de reajuste dos processos para um único produto.)
    public static void main(String[] args) {
        //String que vai conter o texto do menu
        String menu = """
                XYZ COMERCIO DE PRODUTOS LTDA.
                SISTEMA DE CONTROLE DE ESTOQUE
                
                1 - CADASTRO DE PRODUTOS
                2 - MOVIMENTAÇÃO
                3 - REAJUSTE DE PREÇOS
                4 - RELATÓRIOS
                0 - FINALIZAR
                
                OPÇÃO :""";
        //variável que guarda a escolha do usuário
        String opmenu;
        //loop do menu principal
        do {
            opmenu = JOptionPane.showInputDialog(null, menu);
            //if para verificar se o usuário realmente deseja sair
            if (opmenu == null) {
                int finalizar = JOptionPane.showConfirmDialog(null, "DESEJA REALMENTE SAIR?", "SAIR", JOptionPane.YES_NO_OPTION);
                if (finalizar == JOptionPane.YES_NO_OPTION) {
                    break;
                } else {
                    continue;
                }
            }
            //estrutura pra ler a opção que o usuário escolheu e decidir qual método vai executar
            switch (opmenu) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Abrindo Cadastro...");
                    menuCadastro();//chama o metodo menuCadastro
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abrindo Movimentação...");
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Abrindo Reajuste...");
                    menuReajuste();//chama o metodo menuReajuste
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios...");
                    menuRelatorios();//chama o método menuRelatorios()
                    break;
                case "0":
                    int confirm = JOptionPane.showConfirmDialog(null, "DESEJA REALMENTE FINALIZAR?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
                        opmenu = "0";
                    } else {
                        opmenu = "";
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!");
                    break;
            }
        } while (!opmenu.equals("0"));//mantém o loop até o usuário escolher sair
    }

    /**
     * Menu do cadastro
     */
    public static void menuCadastro() {

        //String que vai conter o texto do menu
        String menuCad = """
        --- CADASTRO DE PRODUTOS ---
        1 - Incluir Produto
        2 - Alterar Produto
        3 - Consultar produto
        4 - Excluir Produto
        0 - Voltar ao Menu Principal
        
        OPÇÃO:""";

        //variável que guarda a escolha do usuário
        String op;
        //loop do menu de cadastro
        do {
            //se o usuário colocar algum valor nulo o menu encerra e volta pro principal
            op = JOptionPane.showInputDialog(null, menuCad);
            if (op == null) {
                break;
            }
            //estrutura pra ler a opção que o usuário escolheu e decidir qual método vai executar
            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Incluir Produto");
                    incluirProd();//chama o método incluirProd()
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Alterar Produto");
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Excluir Produto");
                    break;
                case "0":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (!"0".equals(op));//mantém o loop até o usuário escolher sair

    }

    /**
     * sistema de inclusão
     */
    public static void incluirProd() {
        //verifica se tem espaço no vetor
        if (total >= estoque.length) {
            JOptionPane.showMessageDialog(null, "Cadastro cheio!");//alerta estar cheio caso esteja
            return;//não executa o resto dos comandos caso esteja cheio
        }
        try {
            int thisId = Integer.parseInt(JOptionPane.showInputDialog("Digite o id: "));//atribui o id digitado pelo usuário na variável thisId

            if (buscarPorId(thisId) != -1) {//chama o método buscarPorId() e verifica se o id ja foi cadastratado

                JOptionPane.showMessageDialog(null, "Id já cadastrado");//alerta caso o id já esteja em uso

            } else {
                String nomeDigitado = JOptionPane.showInputDialog("Digite o nome do produto a ser cadastrado: ");//atribui o nome digitado pelo usuário na variável nomeDigitado
                double precoDigitado = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto a ser casdastrado: "));//atribui o preço digitado pelo usuário na variável precoDigitado
                double qtdDigitada = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade do produto a ser cadastrado: "));//atribui a quantidade digitado pelo usuário na variável qtdDigitada
                String unidadeDigitada = JOptionPane.showInputDialog("Digite a unidade do produto a ser cadastrado: ");//atribui a unidade digitada pelo usuário na variável unidadeDigitada

                Produto novoProduto = new Produto(thisId, nomeDigitado, precoDigitado, qtdDigitada, unidadeDigitada);//usa o construtor pra criar um objeto da classe produto iniciada com os valores digitados

                estoque[total] = novoProduto;//atribui o objeto temporário criado no vetor estoque[]

                //confirma que o produto foi cadastrado e mostra os dados do produto que o usuário cadastrou
                JOptionPane.showMessageDialog(null, """
                                                Produto cadastrado com sucesso!
                                                id: """ + estoque[total].id + "\n"
                        + "nome : " + estoque[total].nome + "\n"
                        + "preço: " + estoque[total].preco + "R$\n"
                        + "quantidade: " + estoque[total].quantidade + " " + estoque[total].unidade);

                //prepara o total pro proximo produto
                total++;
            }
        } catch (NumberFormatException error) {//alerta caso o usúario digite letras ao invés de números nos campos que necessitam números
            JOptionPane.showMessageDialog(null, "Digite apenas números nos campos Id, preço e quantidade");
        }
    }

    /**
     * Menu de reajuste de preços
     */
    public static void menuReajuste() {

        String menu = """
    --- REAJUSTE DE PREÇOS ---
    
    1 - Reajustar um produto
    2 - Reajustar todos os produtos
    0 - Voltar
    
    OPÇÃO:
    """;
        String op;

        do {
            op = JOptionPane.showInputDialog(null, menu);

            if (op == null) {
                break;
            }

            switch (op) {

                case "1":
                    reajustarUmProduto();
                    break;

                case "2":
                    reajustarTodosProdutos();
                    break;

                case "0":
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

        } while (!op.equals("0"));
    }

    /**
     * Buscar produto pelo nome
     */
    public static int buscarPorNome(String nomeProcurado) {

        for (int i = 0; i < total; i++) {

            if (estoque[i].nome.equalsIgnoreCase(nomeProcurado)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Reajustar apenas um produto
     */
    public static void reajustarUmProduto() {

        if (total == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Nenhum produto cadastrado!"
            );

            return;
        }

        String nome = JOptionPane.showInputDialog(
                "Digite o nome do produto:"
        );

        if (nome == null) {
            return;
        }

        int posicao = buscarPorNome(nome);

        if (posicao == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Produto não encontrado!"
            );

        } else {

            Produto p = estoque[posicao];

            try {

                double percentual = Double.parseDouble(
                        JOptionPane.showInputDialog(
                                "Produto: " + p.nome
                                + "\nPreço atual: R$ " + p.preco
                                + "\n\nDigite o percentual de reajuste:"
                        )
                );

                double novoPreco = p.preco
                        + (p.preco * percentual / 100);

                int confirma = JOptionPane.showConfirmDialog(
                        null,
                        "Preço atual: R$ " + p.preco
                        + "\nNovo preço: R$ " + novoPreco
                        + "\n\nConfirmar reajuste?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirma == JOptionPane.YES_OPTION) {

                    p.preco = novoPreco;

                    JOptionPane.showMessageDialog(
                            null,
                            "Reajuste realizado com sucesso!"
                    );
                }

            } catch (NumberFormatException erro) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas números!"
                );
            }
        }
    }
   
    /**
 * Reajustar todos os produtos
 */
public static void reajustarTodosProdutos() {

    if (total == 0) {

        JOptionPane.showMessageDialog(
                null,
                "Nenhum produto cadastrado!"
        );

        return;
    }

    try {

        double percentual = Double.parseDouble(
                JOptionPane.showInputDialog(
                        "Digite o percentual de reajuste para TODOS os produtos:"
                )
        );

        int confirma = JOptionPane.showConfirmDialog(
                null,
                "Deseja aplicar " + percentual +
                "% de reajuste em todos os produtos?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirma == JOptionPane.YES_OPTION) {

            for (int i = 0; i < total; i++) {

                estoque[i].preco = estoque[i].preco +
                        (estoque[i].preco * percentual / 100);
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Reajuste aplicado com sucesso!"
            );
        }

    } catch (NumberFormatException erro) {

        JOptionPane.showMessageDialog(
                null,
                "Digite apenas números!"
        );
    }
}
    

    /**
     * Menu de relatórios
     */
    public static void menuRelatorios() {
        //String que vai conter o texto do menu
        String menuRel = """
        --- RELATÓRIOS ---
        1 - Relatórios de Preços
        2 - Relatórios de Movimentação
        3 - Relatórios Financeiros de Estoque
        0 - Voltar ao Menu Principal
        
        OPÇÃO:""";

        //variável que guarda a escolha do usuário
        String op;
        //loop do menu de cadastro
        do {
            //se o usuário colocar algum valor nulo o menu encerra e volta pro principal
            op = JOptionPane.showInputDialog(null, menuRel);
            if (op == null) {
                break;
            }
            //estrutura pra ler a opção que o usuário escolheu e decidir qual método vai executar
            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios de Preços...");
                    relatorioPrecos();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios de Movimentação...");
                    relatorioMovimentacao();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios Financeiros de Estoque...");
                    relatorioFinanceiro();
                    break;
                case "0":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

        } while (!"0".equals(op));//mantém o loop até o usuário escolher sair
    }

    public static void relatorioPrecos() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio!");
            return;
        }

        StringBuilder relatorio = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
        String dataHoje = dtf.format(LocalDateTime.now());

        relatorio.append(dataHoje).append(" LISTA DE PREÇOS PG 001\n\n");
        relatorio.append(String.format("%-30s %-10s %12s\n", "PRODUTO", "UND", "PREÇO"));
        relatorio.append("----------------------------------------------------------\n");

        for (int i = 0; i < total; i++) {
            Produto p = estoque[i];

            String nomeFormatado = p.nome;
            if (nomeFormatado.length() > 30) {
                nomeFormatado = nomeFormatado.substring(0, 27) + "...";
            }

            String unidadeFormatada = p.unidade.toUpperCase();
            if (unidadeFormatada.length() > 10) {
                unidadeFormatada = unidadeFormatada.substring(0, 10);
            }

            relatorio.append(String.format("%-30s %-10s %12.2f\n",
                    nomeFormatado,
                    unidadeFormatada,
                    p.preco));
        }

        JTextArea textArea = new JTextArea(relatorio.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JOptionPane.showMessageDialog(null, textArea, "Relatório de Preços", JOptionPane.PLAIN_MESSAGE);
    }

    public static void relatorioMovimentacao() {
        if (historicoMov.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma movimentação registrada no histórico!");
            return;
        }

        StringBuilder relatorio = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        String dataHoje = dtf.format(LocalDateTime.now());

        relatorio.append(dataHoje).append(" HISTÓRICO DE MOVIMENTAÇÕES PG 001\n\n");
        relatorio.append(String.format("%-20s | %-9s | %-10s | %s\n", "PRODUTO", "TIPO", "QTD", "SALDO FINAL"));
        relatorio.append("----------------------------------------------------------------------\n");

        relatorio.append(historicoMov.toString());
        relatorio.append("----------------------------------------------------------------------\n");
        relatorio.append("FIM DO RELATÓRIO DE HISTÓRICO");

        JTextArea textArea = new JTextArea(relatorio.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JOptionPane.showMessageDialog(null, textArea, "Histórico de Movimentação", JOptionPane.PLAIN_MESSAGE);
    }

    public static void relatorioFinanceiro() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio!");
            return;
        }

        StringBuilder relatorio = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
        String dataHoje = dtf.format(LocalDateTime.now());

        double somaQuantidades = 0;
        double somaValorTotal = 0;

        relatorio.append(dataHoje).append(" BALANÇO FÍSICO-FINANCEIRO PG 001\n\n");
        relatorio.append(String.format("%-20s %-8s %12s %6s %15s\n", "PRODUTO", "UND", "PREÇO UNIT.", "QTDE", "PREÇO TOTAL"));
        relatorio.append("----------------------------------------------------------------------\n");

        for (int i = 0; i < total; i++) {
            Produto p = estoque[i];
            double precoTotalItem = p.preco * p.quantidade;

            somaQuantidades += p.quantidade;
            somaValorTotal += precoTotalItem;

            String nomeFormatado = p.nome;
            if (nomeFormatado.length() > 20) {
                nomeFormatado = nomeFormatado.substring(0, 17) + "...";
            }

            String unidadeFormatada = p.unidade.toUpperCase();
            if (unidadeFormatada.length() > 8) {
                unidadeFormatada = unidadeFormatada.substring(0, 8);
            }

            relatorio.append(String.format("%-20s %-8s %12.2f %6.0f %15.2f\n",
                    nomeFormatado,
                    unidadeFormatada,
                    p.preco,
                    p.quantidade,
                    precoTotalItem));
        }

        relatorio.append("----------------------------------------------------------------------\n");
        relatorio.append(String.format("TOTAL DE ITENS NO ESTOQUE : %04.0f\n", somaQuantidades));
        relatorio.append(String.format("VALOR TOTAL DO ESTOQUE    : R$ %,.2f\n", somaValorTotal));

        JTextArea textArea = new JTextArea(relatorio.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, textArea, "Balanço Financeiro", JOptionPane.PLAIN_MESSAGE);
    }

    static int buscarPorId(int idProcurado) {

        // percorre o vetor procurando o ID
        for (int i = 0; i < total; i++) {

            if (estoque[i].id == idProcurado) {
                return i; // retorna a posição encontrada
            }
        }

        return -1; // não encontrou
    }
<<<<<<< HEAD

    /**
     * Exibe o menu principal de movimentação de produtos.
     * Permite ao usuário escolher entre dar entrada em produtos, 
     * registrar saída ou voltar ao menu principal.
     */
    public static void menuMovimentacao() {
        String menuMov = """
                         --- MOVIMENTAÇÃO ---
                         1 - Entrada de Produtos
                         2 - Saída de Produtos
                         0 - Voltar ao Menu Principal
                         
                         OPÇÃO: """;

        String op;

        do {
            op = JOptionPane.showInputDialog(null, menuMov);
            
            // Tratamento caso o usuário clique em "Cancelar" ou feche a janela
            if (op == null) {
                break;
            }

            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Abrindo Entrada de Produtos");
                    entradaProd();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abrindo Saída de Produtos");
                    saidaProd();
                    break;
                case "0":
                    break;
                default:
                    JOptionPane.showInputDialog(null, "Opção Inválida");
            }
        } while (!op.equals("0"));
    }

    /**
     * Realiza a operação de entrada (adição) de produtos no estoque.
     * Verifica se o estoque está vazio. Caso não esteja, solicita o nome do produto,
     * valida a existência, solicita a quantidade a ser adicionada e, 
     * mediante confirmação do usuário, atualiza o saldo do produto.
     */
    public static void entradaProd() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio");
            return;
        }
        String nomeProd;
        do {
            nomeProd = JOptionPane.showInputDialog(null, "Digite o nome do produto que receberá a entrada");
            if (buscarNome(nomeProd).equals(nomeProd) && !nomeProd.equals("")) {
                JOptionPane.showMessageDialog(null, "Quatidade atual do Produto: " + estoque[marcadorBuscarNome].quantidade
                        + estoque[marcadorBuscarNome].unidade);
                double quantidade = estoque[marcadorBuscarNome].quantidade;
                double entrada = 0;
                do {
                    try {
                        entrada = Double.parseDouble(JOptionPane.showInputDialog(null, "Quantidade de entrada:"));
                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(null, "Digite apenas números nos campo");
                    }
                } while (entrada == 0);
                JOptionPane.showMessageDialog(null, "Quantidade final: " + (quantidade + entrada) + estoque[marcadorBuscarNome].unidade);
                int escolha = JOptionPane.showConfirmDialog(null, "Confirma entrada \n" + estoque[marcadorBuscarNome].nome + "\n" + (quantidade
                        + entrada) + estoque[marcadorBuscarNome].unidade, "Confirma", JOptionPane.YES_NO_OPTION);
                if (escolha == JOptionPane.YES_NO_OPTION) {
                    estoque[marcadorBuscarNome].quantidade = quantidade + entrada;
                    historicoMov.append(String.format("%-20s | ENTRADA | %10.2f | SALDO: %.2f\n",
                            estoque[marcadorBuscarNome].nome, entrada, estoque[marcadorBuscarNome].quantidade));

<<<<<<< HEAD
                    break;
                }
=======
                JOptionPane.showMessageDialog(null, "Quatidade atual do Produto: " + estoque[marcadorBuscarNome].quantidade
                        + estoque[marcadorBuscarNome].unidade);
                break;
>>>>>>> ad80b04 (init: Inicio do projeto)
            } else {
                JOptionPane.showMessageDialog(null, "Produto Inválido");
            }
        } while (!buscarNome(nomeProd).equals(nomeProd));
    }
    /**
     * Realiza a operação de saída (remoção) de produtos do estoque.
     * Verifica se o estoque está vazio. Após identificar o produto, 
     * solicita a quantidade de saída garantindo que o saldo final não seja negativo ou zero.
     * Mediante confirmação, atualiza o saldo do produto.
     */
     public static void saidaProd() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio");
            return;
        }
        String nomeProd;
        do {
            nomeProd = JOptionPane.showInputDialog(null, "Digite o nome do produto que receberá a saída");
            if (buscarNome(nomeProd).equals(nomeProd) && !nomeProd.equals("")) {
                JOptionPane.showMessageDialog(null, "Quatidade atual do Produto: " + estoque[marcadorBuscarNome].quantidade
                        + estoque[marcadorBuscarNome].unidade);
                double quantidade = estoque[marcadorBuscarNome].quantidade;
                double saida = 0;
                do {
                    try {
                        saida = Double.parseDouble(JOptionPane.showInputDialog(null, "Quantidade de saída:"));
                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(null, "Digite apenas números nos campo");
                    }
                    
                    // Inválida se a saída deixar o estoque zerado ou negativo
                    if ((quantidade - saida) <= 0) {
                        JOptionPane.showMessageDialog(null, "Saída inválida");
                    }
                } while (saida == 0 || (quantidade - saida) <= 0);
                
                JOptionPane.showMessageDialog(null, "Quantidade final: " + (quantidade - saida) + estoque[marcadorBuscarNome].unidade);
                int escolha = JOptionPane.showConfirmDialog(null, "Confirma saída \n" + estoque[marcadorBuscarNome].nome + "\n" + (quantidade -
                        saida) + estoque[marcadorBuscarNome].unidade, "Confirma", JOptionPane.YES_NO_OPTION);
                
                // Atualiza o estoque caso o usuário confirme
                if (escolha == JOptionPane.YES_NO_OPTION) {
                    estoque[marcadorBuscarNome].quantidade = quantidade - saida;
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Produto Inválido");
            }
        } while (!buscarNome(nomeProd).equals(nomeProd));
    }
     
     /**
     * Busca um produto no vetor de estoque pelo nome exato.
     * Se o produto for encontrado, a variável global 'marcadorBuscarNome'
     * é atualizada com o índice correspondente no vetor.
     *
     * @param nomeProcurado O nome do produto a ser pesquisado.
     * @return O nome do produto caso seja encontrado, ou uma String vazia ("") caso contrário.
     */
    static String buscarNome(String nomeProcurado) {
        for (int i = 0; i < total; i++) {

            if (estoque[i].nome.equals(nomeProcurado)) {
                marcadorBuscarNome = i; // Salva a posição (índice) do produto encontrado
                return nomeProcurado;
            }
        }

        return "";
    }

    public static void menuReajuste() {
        String menuRea = """
                         --- REAJUSTE GERAL OU DE UM PRODUTO ?  ---
                         1 - Geral
                         2 - Produto
                         0 - Voltar ao Menu Principal
                         
                         OPÇÃO: """;

        String op;

        do {
            op = JOptionPane.showInputDialog(null, menuRea);
            if (op == null) {
                break;
            }

            switch (op) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Abrindo Reajuste geral ");
                    reajusteMul();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abrindo Rejuste Unitário");
                    reajusteUni();
                    break;
                case "0":
                    JOptionPane.showMessageDialog(null, "Encerrando reajuste");
                    break;
                default:
                    JOptionPane.showInputDialog(null, "Opção Inválida");
            }
        } while (!op.equals("0"));
    }

    public static void reajusteMul() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio! Não há o que reajustar.");
            return;
        }
        StringBuilder lista = new StringBuilder("--- PRODUTOS ANTES DO REAJUSTE ---\n");
        for (int i = 0; i < total; i++) {
            lista.append(String.format("ID: %d | %s | Preço Atual: R$ %.2f\n",
                    estoque[i].id, estoque[i].nome, estoque[i].preco));
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    public static void reajusteUni() {
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Estoque vazio! Não há o que reajustar.");
            return;
        }
        String nomeProcurado = JOptionPane.showInputDialog("Digite o nome exato do produto para reajuste:");
        String resultado = buscarNome(nomeProcurado);
        if (!resultado.equals("")) {
            Produto p = estoque[marcadorBuscarNome];

        String mensagem = "--- DADOS DO PRODUTO SELECIONADO ---\n" +
                      "ID: " + p.id + "\n" +
                      "Nome: " + p.nome + "\n" +
                      "Preço Atual: R$ " + p.preco + "\n" +
                      "Qtd em Estoque: " + p.quantidade + " " + p.unidade;
         JOptionPane.showMessageDialog(null, mensagem);
          JOptionPane.showInputDialog(null, "Digite o novo valor para " + p.nome + ":");
    } else {
        JOptionPane.showMessageDialog(null, "Produto não encontrado");
    }
        }
    }
=======
}
>>>>>>> e291402 (Adcionando o menu de reajustes, tela de reajuste, e processo de reajuste dos processos para um único produto.)
