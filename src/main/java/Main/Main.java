package Main;

import javax.swing.JOptionPane;

/**
 * Programa principal do controle de estoque..
 */
public class Main {

    static Produto[] estoque = new Produto[100];//criando um vetor para armazenar 100 objetos da classe produto

    static int total = 0;

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
                
                Produto novoProduto = new Produto(thisId,nomeDigitado,precoDigitado,qtdDigitada,unidadeDigitada);//usa o construtor pra criar um objeto da classe produto iniciada com os valores digitados
                
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
     * Menu de relatórios
     */
    public static void menuRelatorios() {
        //String que vai conter o texto do menu
        String menuRel = """
        --- RELATÓRIOS ---
        1 - Relatórios de Inventário
        2 - Relatórios de Alerta
        3 - Relatórios de Movimentação
        4 - Relatórios Financeiros de Estoque
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
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios de Inventário...");
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios de Alerta...");
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios de Movimentação...");
                    break;
                case "4":
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios Financeiros de Estoque...");
                    break;
                case "0":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

        } while (!"0".equals(op));//mantém o loop até o usuário escolher sair

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
}
