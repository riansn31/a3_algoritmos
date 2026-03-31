
package Main;
import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
        String menu = """
                XYZ COMERCIO DE PRODUTOS LTDA.
                SISTEMA DE CONTROLE DE ESTOQUE
                
                1 - CADASTRO DE PRODUTOS
                2 - MOVIMENTAÇÃO
                3 - REAJUSTE DE PREÇOS
                4 - RELATÓRIOS
                0 - FINALIZAR
                
                OPÇÃO :""";
        String opmenu;
        do{
            opmenu = JOptionPane.showInputDialog(null, menu);
            if (opmenu == null){
                int finalizar = JOptionPane.showConfirmDialog(null,"DESEJA REALMENTE SAIR?", "SAIR" , JOptionPane.YES_NO_OPTION);
                if (finalizar == JOptionPane.YES_NO_OPTION)break;
                else continue;
            }
            switch(opmenu){
                case "1": 
                    JOptionPane.showMessageDialog(null, "Abrindo Cadastro...");
                    break;
                case "2": 
                    JOptionPane.showMessageDialog(null, "Abrindo Movimentação...");
                case "3": 
                    JOptionPane.showMessageDialog(null, "Abrindo Reajuste...");
                    break;
                case "4": 
                    JOptionPane.showMessageDialog(null, "Abrindo Relatórios...");
                    break;
                case "0": {
                    int confirm = JOptionPane.showConfirmDialog(null,"DESEJA REALMENTE FINALIZAR?", "Confirmação" , JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Sistema encerrado.");
                        opmenu = "0";
                    }else{
                        opmenu = "";
                    }
                }
                default:
                    JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA!");
                    break;
            }
        }
        while(!opmenu.equals("0"));
    }
}
