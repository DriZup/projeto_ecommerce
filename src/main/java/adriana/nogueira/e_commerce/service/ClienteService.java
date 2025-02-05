package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;

import java.util.Optional;

public interface ClienteService {


        Cliente salvarCliente(Cliente cliente);

        Cliente findByCpf(String cpf);

}
