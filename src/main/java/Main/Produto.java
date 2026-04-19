package Main;
/**
 * Inicia a classe Produto
 */
public class Produto {
    //criando um "molde" para a classe Produto
    public int id;
    public String nome;
    public double preco;
    public double quantidade;
    public String unidade;

    //criando um construtor para o objeto não iniciar nulo
    public Produto(int id, String nome, double preco, double quantidade, String unidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }
}
