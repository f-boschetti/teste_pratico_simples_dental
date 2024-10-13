# Teste Prático Simples Dental

## Descrição
Este projeto permite a criação, leitura, atualização e exclusão (CRUD) de contatos e profissionais associados.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação utilizada para o desenvolvimento.
- **Spring Boot**: Framework para construção de aplicações Java.
- **JPA (Hibernate)**: Biblioteca para gerenciamento de persistência de dados.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados.
- **JUnit & Mockito**: Frameworks utilizados para testes unitários.
- **Swagger**: Documentação.

## Funcionalidades

### Gerenciamento de Contatos
O sistema permite gerenciar contatos, oferecendo as seguintes funcionalidades:

1. **Criar Contato**
    - Endpoint: `POST /contatos`
    - Cria um novo contato com os dados fornecidos.
    - **Requisição**: JSON com nome, contato e ID do profissional.
    - **Resposta**: Contato criado com o ID gerado.

2. **Buscar Contatos**
    - Endpoint: `GET /contatos`
    - Recupera uma lista de contatos filtrados pela string de pesquisa.
    - **Parâmetros**:
        - `q`: String de pesquisa para filtrar contatos.
        - `fields`: Lista opcional de campos a serem retornados.
    - **Resposta**: Lista de contatos correspondentes aos critérios.

3. **Atualizar Contato**
    - Endpoint: `PUT /contatos/{id}`
    - Atualiza os dados do contato com o ID especificado.
    - **Requisição**: JSON com os campos que devem ser atualizados.
    - **Resposta**: Contato atualizado.

4. **Excluir Contato**
    - Endpoint: `DELETE /contatos/{id}`
    - Realiza a exclusão lógica do contato com o ID especificado.
    - **Resposta**: Confirmação de que o contato foi excluído.

### Gerenciamento de Profissionais
O gerenciamento de profissionais funciona de maneira semelhante ao gerenciamento de contatos, com funcionalidades de criar, buscar, atualizar e excluir profissionais. Cada profissional pode ter múltiplos contatos associados.

## Estrutura do Projeto
- **Entities**: Contém as classes que representam as entidades do banco de dados (Contato, Profissional).
- **DTOs**: Contém os Data Transfer Objects utilizados para transferir dados entre a API e a lógica de negócios.
- **Repositories**: Interfaces que definem operações de persistência de dados usando Spring Data JPA.
- **Services**: Contêm a lógica de negócios para gerenciar contatos e profissionais.
- **Controllers**: Implementam os endpoints da API REST.