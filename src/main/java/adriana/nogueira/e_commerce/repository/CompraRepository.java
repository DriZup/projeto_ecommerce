package adriana.nogueira.e_commerce.repository;

import adriana.nogueira.e_commerce.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}