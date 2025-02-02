package adriana.nogueira.e_commerce.dto;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;

    // Construtores
    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Método toString (opcional, para facilitar o debug)
    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}