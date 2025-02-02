package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.dto.CompraDTO;
import adriana.nogueira.e_commerce.exceptions.ProdutoIndisponivelException;
import adriana.nogueira.e_commerce.model.Compra;

public interface CompraService {
    Compra realizarCompra(CompraDTO compraDTO) throws ProdutoIndisponivelException;

}