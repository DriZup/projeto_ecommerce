package adriana.nogueira.e_commerce.repository;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.service.ClienteService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


   Cliente findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
