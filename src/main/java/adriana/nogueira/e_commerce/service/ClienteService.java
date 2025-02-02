package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;

import java.util.Optional;

public interface ClienteService {

    Cliente salvarCliente(Cliente cliente);

    void atualizarCliente(Cliente cliente);

    void excluirCliente(Long id);

    Cliente buscarPorId(Long id);

    Optional<Cliente> buscarPorCpf(String cpf);


    Iterable<Cliente> buscarTodos();
}
