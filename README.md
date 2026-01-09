# Baiters Burger - API de Produtos

API REST de gerenciamento de produtos para a solução Baiters Burger, permitindo operações de criação, busca, atualização e exclusão.

## Tecnologias

-   **Java 25** e **Spring Boot 3**: Plataforma e framework principal para construção da API.
-   **Maven**: Gerenciador de dependências e build do projeto.
-   **Spring Data JPA**: Para persistência de dados.
-   **MySQL**: Banco de dados relacional para produção.
-   **H2 Database**: Banco de dados em memória para testes.
-   **Lombok**: Para reduzir código boilerplate (getters, setters, construtores).
-   **MapStruct**: Para mapeamento entre DTOs, modelos de domínio e entidades.
-   **JUnit 5, Mockito & Cucumber**: Para testes unitários, de integração e BDD.

## Arquitetura

O projeto é estruturado utilizando os conceitos da **Arquitetura Hexagonal (Portas e Adaptadores)**, que visa isolar a lógica de negócio (core) das preocupações de infraestrutura (frameworks, bancos de dados, etc).

A estrutura de pacotes reflete essa arquitetura:

-   `src/main/java/br/com/fiap/baitersburger/products/`
    -   **`core`**: O "hexágono", contendo a lógica de negócio pura.
        -   `domain`: Modelos de domínio, exceções de negócio e as interfaces (Portas) de saída.
        -   `application`: Casos de uso (Use Cases) e as interfaces (Portas) de entrada.
    -   **`adapters`**: Os "adaptadores" que implementam as portas e conectam o core com o mundo exterior.
        -   `in`: Adaptadores de entrada (Web Controller REST).
        -   `out`: Adaptadores de saída (repositórios que interagem com o banco de dados).
    -   **`infrastructure`**: Configurações do Spring, como a injeção de dependências para montar a aplicação.

## Endpoints da API

A API expõe os seguintes endpoints sob o prefixo `/api/v1/products`:

| Método HTTP | Rota                   | Descrição                                             |
| ----------- | ---------------------- | ----------------------------------------------------- |
| `POST`      | `/`                    | Cria um novo produto.                                 |
| `GET`       | `/{id}`                | Busca um produto pelo seu ID.                         |
| `GET`       | `?category={category}` | Busca todos os produtos de uma determinada categoria. |
| `PUT`       | `/{id}`                | Atualiza um produto existente.                        |
| `DELETE`    | `/{id}`                | Remove um produto pelo seu ID.                        |

## Testes

A estratégia de testes é dividida em:

-   **Testes de unidade**: Focados em testar os casos de uso e adaptadores de forma isolada, utilizando mocks (`Mockito`) para simular dependências externas.
-   **Testes de integração/BDD**: Utilizando `Cucumber`, os testes validam os fluxos da aplicação de ponta a ponta (controller -> use case -> banco de dados), garantindo que os componentes funcionem corretamente em conjunto.

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-highlight.svg)](https://sonarcloud.io/summary/new_code?id=lucasnabeto_baiters-burger-products-app)

## High-Level Design (HLD)

A solução completa é implantada na AWS e utiliza uma arquitetura serveless e conteinerizada para alta disponibilidade e escalabilidade. Todo o ambiente foi provisionado utilizando subnets públicas.

O fluxo da requisição é o seguinte:

1. O **Cliente** envia uma requisição para o **API Gateway** com suas `Client Credentials`.
2. O **API Gateway** aciona um **Lambda Authorizer** para validar as credenciais.
3. O Lambda Authorizer consulta o **AWS Cognito** para verificar a validade das credenciais.
4. Se as credenciais forem válidas, o Lambda retorna uma política de `Allow` para o API Gateway.
5. O API Gateway encaminha a requisição para a aplicação de produtos.
6. A aplicação, conteinerizada e orquestrada pelo **AWS ECS com Fargate**, processa a requisição.
7. A aplicação se conecta ao banco de dados **AWS Aurora (MySQL)** para persistir ou consultar dados.

### Desenho da Solução

```
+----------+      +---------------------+      +--------------------+      +-------------+
|          |----->|                     |----->|                    |----->|             |
| Client   |  1.  |    API Gateway      |  5.  |   AWS ECS Fargate  |  7.  | AWS Aurora  |
|          |      |                     |      | (Aplicação Produto)|      |   (MySQL)   |
+----------+      +----------+----------+      |                    |      |             |
                             |                 +--------------------+      +-------------+
                             | 2.
                             v
                  +----------+----------+
                  |                     |
                  |  Lambda Authorizer  |
                  |                     |
                  +----------+----------+
                             | 3.
                             v
                  +----------+----------+
                  |                     |
                  |    AWS Cognito      |
                  |                     |
                  +---------------------+
```

### Repositórios relacionados

Este repositório (`baiters-burger-products`) contém o código-fonte da aplicação de produtos e a configuração da Task Definition e Service do ECS.

Os outros componentes da infraestrutura estão em seus próprios repositórios:

-   **Infraestrutura Core (VPC, Subnets, Aurora, ECS Cluster):** [lucasnabeto/baiters-burger-products-infra](https://github.com/lucasnabeto/baiters-burger-products-infra)
-   **API Gateway e Lambda Authorizer:** [lucasnabeto/baiters-burger-products-api-gateway](https://github.com/lucasnabeto/baiters-burger-products-api-gateway)

## Guia de uso

**1. Executar a aplicação:**

Para iniciar a API, execute o seguinte comando na raiz do projeto:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

**2. Executar os testes:**

Para rodar todos os testes (unitários e de integração), utilize:

```bash
./mvnw test
```
