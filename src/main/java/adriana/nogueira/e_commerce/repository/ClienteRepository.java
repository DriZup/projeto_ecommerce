package adriana.nogueira.e_commerce.repository;

import adriana.nogueira.e_commerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Cliente> findByCpf(String cpf);

    Cliente findByEmail(String email);
}
