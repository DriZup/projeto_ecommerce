package adriana.nogueira.e_commerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoEmFaltaException extends RuntimeException {
    private final List<String> produtosEmFalta;

    public ProdutoEmFaltaException(List<String> produtosEmFalta) {
        super();
        this.produtosEmFalta = produtosEmFalta;
    }

    public List<String> getProdutosEmFalta() {
        return produtosEmFalta;
    }
}
