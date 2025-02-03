

# Sistema de E-Commerce

Este é um sistema básico de E-Commerce desenvolvido para gerenciar o cadastro de produtos, clientes e a realização de compras. O sistema foi projetado para simular o funcionamento de uma loja virtual, com validações robustas e manipulação de dados.

---

## Funcionalidades Implementadas

### Cadastro de Produtos

- **Endpoints**:
  - `POST /produtos/salvar`: Cadastra um novo produto.
  - `PUT /produtos/atualizar`: Atualiza um produto existente.
  - `DELETE /produtos/{id}`: Deleta um produto pelo ID.
  - `GET /produtos`: Lista todos os produtos cadastrados.
  - `GET /produtos/buscar/{nome}`: Busca produtos pelo nome.

- **Validações**:
  - Nome do produto deve ser único.
  - Preço deve ser maior que 0.
  - Quantidade deve ser maior ou igual a 0.

---

### Cadastro de Clientes

- **Endpoints**:
  - `POST /clientes/salvar`: Cadastra um novo cliente.
  - `PUT /clientes/atualizar/{cpf}`: Atualiza os dados de um cliente pelo CPF.
  - `DELETE /clientes/excluir/{id}`: Deleta um cliente pelo ID.
  - `GET /clientes/buscar/{id}`: Busca um cliente pelo ID.
  - `GET /clientes`: Lista todos os clientes cadastrados.
  - `GET /clientes/buscar/cpf/{cpf}`: Busca um cliente pelo CPF.

- **Validações**:
  - CPF deve ser único e válido.
  - Email deve ser único e válido.
  - Nome, CPF e Email não podem ser nulos ou vazios.

---

### Realização de Compras

- **Endpoints**:
  - `POST /compras`: Registra uma nova compra.

- **Funcionalidades**:
  - Identificação do cliente pelo CPF.
  - Registro da compra no sistema.
  - Atualização da quantidade de produtos no estoque.

- **Validações**:
  - Produtos com quantidade 0 não podem ser comprados.
  - Caso um produto esteja em falta, o sistema retorna erro 400 com a mensagem:
    ```json
    {
      "erro": "Produto em falta: [nome do produto]"
    }
    ```
  - Caso múltiplos produtos estejam em falta, a mensagem lista todos os produtos indisponíveis.
  - Nenhuma compra é realizada se algum produto estiver em falta.

---

## Regras de Negócio

1. **Cadastro de Produtos**:
   - Nome do produto deve ser único.
   - Preço deve ser maior que 0.
   - Quantidade deve ser maior ou igual a 0.

2. **Cadastro de Clientes**:
   - CPF deve ser único e válido.
   - Email deve ser único e válido.
   - Nome, CPF e Email não podem ser nulos ou vazios.

3. **Realização de Compras**:
   - O sistema verifica se o produto está disponível em estoque (quantidade maior que 0).
   - Caso contrário, retorna erro 400 com a mensagem de que o produto está em falta.
   - Nenhuma compra é realizada se algum produto estiver em falta.
   - O sistema reduz a quantidade do produto no estoque após a compra.

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento da API RESTful.
- **H2 Database**: Banco de dados em memória para testes.
- **Maven**: Gerenciador de dependências.
- **JUnit**: Framework para testes unitários.

---

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/e-commerce.git
   cd e-commerce
   ```

2. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

3. Acesse a API:
   - URL base: `http://localhost:8080`

---

## Estrutura do Projeto

- **Controller**: Contém os endpoints da API.
- **Service**: Contém a lógica de negócios.
- **Repository**: Contém a interface para acesso ao banco de dados.
- **Model**: Contém as classes de domínio (Produto, Cliente, Compra).
- **DTO**: Contém os objetos de transferência de dados.
- **Exceptions**: Contém as classes de exceções personalizadas.

---

## Exemplos de Requisições

### Cadastro de Produto

**POST /produtos/salvar**

Request Body:
```json
{
  "nome": "Produto1",
  "preco": 100.0,
  "quantidade": 10
}
```

---

### Cadastro de Cliente

**POST /clientes/salvar**

Request Body:
```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@email.com"
}
```

---

### Realização de Compra

**POST /compras**

Request Body:
```json
{
  "cpf": "12345678900",
  "produtos": [
    { "nome": "Produto1" },
    { "nome": "Produto2" }
  ]
}
```

---

## Testes

- Para executar os testes, utilize o comando:
  ```bash
  mvn test
  ```

---

## Contribuição

1. Faça um fork do repositório.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça o commit das suas alterações:
   ```bash
   git commit -m "Minha nova feature"
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

