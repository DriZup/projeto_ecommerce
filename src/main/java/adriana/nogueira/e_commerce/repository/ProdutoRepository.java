package adriana.nogueira.e_commerce.repository;

import adriana.nogueira.e_commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);

    Iterable<Produto> findByNomeContainingIgnoreCase(String nome);
}
