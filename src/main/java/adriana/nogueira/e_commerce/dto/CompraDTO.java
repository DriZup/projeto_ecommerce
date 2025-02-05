package adriana.nogueira.e_commerce.dto;

import java.util.List;

public class CompraDTO {
    private String cpf;
    private List<ProdutoDTO> produtos;
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}