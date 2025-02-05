package adriana.nogueira.e_commerce.dto;

import adriana.nogueira.e_commerce.model.Produto;

public class ProdutoDTO {
    private String nome;
    private double preco;
    private int quantidade;

    public ProdutoDTO(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public static ProdutoDTO fromModel(Produto produto) {
        return new ProdutoDTO(produto.getNome(), produto.getPreco(), produto.getQuantidade());
    }

    public Produto toModel() {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setPreco(this.preco);
        produto.setQuantidade(this.quantidade);
        return produto;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}