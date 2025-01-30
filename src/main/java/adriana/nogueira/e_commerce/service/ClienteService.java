package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;

public interface ClienteService {

    Cliente salvarCliente(Cliente cliente);

    void atualizarCliente(Cliente cliente);

    void excluirCliente(Long id);

    Cliente buscarPorId(Long id);


}
