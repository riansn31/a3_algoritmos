package Main;

import javax.swing.JOptionPane;

/**
 * Programa principal do controle de estoque..
 */
public class Main {

    static int[] id = new int[100];//armazena os id's
    static String[] nome = new String[100];//armazena os nomes
    static double[] preco = new double[100];//armazena os preços
    static String[] unidade = new String[100];//armazena as unidades
    static double[] quantidade = new double[100];//armazena as quantidades

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
        if (total >= id.length) {
            JOptionPane.showMessageDialog(null, "Cadastro cheio!");//alerta estar cheio caso esteja
        } else {
            int thisId = Integer.parseInt(JOptionPane.showInputDialog("Digite o id: "));//pede o id para o usuario e armazena na variável thisId
            //chama o método buscarPorId() e verifica se o id ja foi cadastratado
            if (buscarPorId(thisId) != -1) {
                JOptionPane.showMessageDialog(null, "Id já cadastrado");//alerta caso o id já esteja em uso
            } else {
                id[total] = thisId;//atribui o id do produto no respectivo vetor
                nome[total] = JOptionPane.showInputDialog("Digite o nome do produto a ser cadastrado: ");//atribui o id do produto no respectivo vetor
                preco[total] = Integer.parseInt(JOptionPane.showInputDialog("Digite o preço do produto a ser casdastrado: "));//atribui o preço do produto no respectivo vetor
                quantidade[total] = Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade do produto a ser cadastrado: "));//atribui a quantidade do produto no respectivo vetor
                unidade[total] = JOptionPane.showInputDialog("Digite a unidade do produto a ser cadastrado: ");//atribui a unidade do produto no respectivo vetor
                //confirma que o produto foi cadastrado e mostra os dados do produto que o usuário cadastrou
                JOptionPane.showMessageDialog(null, """
                                                    Produto cadastrado com sucesso!
                                                    id: """ + id[total] + "\n"
                        + "nome : " + nome[total] + "\n"
                        + "preço: " + preco[total] + "R$\n"
                        + "quantidade: " + quantidade[total] + " " + unidade[total]);
                //prepara o total pro proximo produto
                total++;

            }
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

    static int buscarPorId(int ids) {

        // percorre o vetor procurando o ID
        for (int i = 0; i < total; i++) {

            if (id[i] == ids) {
                return i; // retorna a posição encontrada
            }
        }

        return -1; // não encontrou
    }
}
