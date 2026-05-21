# Movieflix API

API REST para cadastro, consulta e gerenciamento de filmes, categorias, servicos de streaming e usuarios. O projeto usa autenticacao JWT, banco PostgreSQL, migrations com Flyway e documentacao interativa com Swagger/OpenAPI.

## Sumario

- [Sobre o projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Requisitos](#requisitos)
- [Configuracao](#configuracao)
- [Como executar](#como-executar)
- [Swagger](#swagger)
- [Autenticacao](#autenticacao)
- [Endpoints](#endpoints)
- [Exemplos de requisicao](#exemplos-de-requisicao)
- [Migrations](#migrations)
- [Testes](#testes)
- [Estrutura do projeto](#estrutura-do-projeto)

## Sobre o projeto

O Movieflix e uma API para organizar um catalogo de filmes. Cada filme pode estar vinculado a uma ou mais categorias e a um ou mais servicos de streaming. A API tambem possui cadastro de usuarios e login para gerar o token JWT usado nas rotas protegidas.

## Tecnologias

| Tecnologia | Uso |
| --- | --- |
| Java 21 | Linguagem principal |
| Spring Boot 3.5 | Framework da aplicacao |
| Spring Web | Criacao dos endpoints REST |
| Spring Security | Autenticacao e protecao das rotas |
| JWT | Token de acesso |
| Spring Data JPA | Persistencia de dados |
| PostgreSQL | Banco de dados |
| Flyway | Versionamento do banco |
| MapStruct | Mapeamento entre entidades e DTOs |
| Lombok | Reducao de boilerplate |
| Springdoc OpenAPI | Swagger UI e contrato OpenAPI |
| Docker Compose | Ambiente local do PostgreSQL |

## Requisitos

- Java 21
- Docker e Docker Compose
- Maven ou Maven Wrapper

## Configuracao

Crie ou ajuste o arquivo `.env` na raiz do projeto:

```env
COMPOSE_PROJECT_NAME=postgres-movieflix
POSTGRES_DB=movieflixdb
POSTGRES_USERNAME=admin_postgres
POSTGRES_PASSWORD=sua_senha
POSTGRES_PORT_HOST=5432
POSTGRES_PORT_CONTAINER=5432
```

Confira tambem o arquivo `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movieflixdb
spring.datasource.username=admin_postgres
spring.datasource.password=sua_senha
movieflix.security.token.secret=sua-secret-super-secreta-aqui
```

## Como executar

Suba o banco de dados:

```bash
docker compose up -d
```

Execute a aplicacao:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A API ficara disponivel em:

```text
http://localhost:8080
```

## Swagger

Com a aplicacao rodando, acesse a documentacao interativa:

```text
http://localhost:8080/swagger-ui.html
```

Contrato OpenAPI em JSON:

```text
http://localhost:8080/v3/api-docs
```

## Autenticacao

As rotas de cadastro e login sao publicas. As demais rotas precisam de token JWT.

Header obrigatorio nas rotas protegidas:

```http
Authorization: Bearer seu_token_jwt
```

Fluxo recomendado:

1. Cadastre um usuario em `POST /movieflix/auth/register`.
2. Faca login em `POST /movieflix/auth/login`.
3. Copie o token retornado.
4. No Swagger, clique em `Authorize` e informe o token no formato `Bearer seu_token_jwt`.

O token JWT expira em 1 hora.

## Endpoints

### Autenticacao

| Metodo | Endpoint | Descricao | Auth |
| --- | --- | --- | --- |
| POST | `/movieflix/auth/register` | Cadastra um novo usuario | Nao |
| POST | `/movieflix/auth/login` | Autentica o usuario e retorna um JWT | Nao |

### Categorias

| Metodo | Endpoint | Descricao | Auth |
| --- | --- | --- | --- |
| POST | `/movieflix/category` | Cadastra uma categoria | Sim |
| GET | `/movieflix/category` | Lista todas as categorias | Sim |
| GET | `/movieflix/category/{id}` | Busca uma categoria por ID | Sim |
| PUT | `/movieflix/category/{id}` | Atualiza uma categoria | Sim |
| DELETE | `/movieflix/category/{id}` | Remove uma categoria | Sim |

### Streamings

| Metodo | Endpoint | Descricao | Auth |
| --- | --- | --- | --- |
| POST | `/movieflix/streaming` | Cadastra um servico de streaming | Sim |
| GET | `/movieflix/streaming` | Lista todos os streamings | Sim |
| GET | `/movieflix/streaming/{id}` | Busca um streaming por ID | Sim |
| PUT | `/movieflix/streaming/{id}` | Atualiza um streaming | Sim |
| DELETE | `/movieflix/streaming/{id}` | Remove um streaming | Sim |

### Filmes

| Metodo | Endpoint | Descricao | Auth |
| --- | --- | --- | --- |
| POST | `/movieflix/movies` | Cadastra um filme | Sim |
| GET | `/movieflix/movies` | Lista todos os filmes | Sim |
| GET | `/movieflix/movies/{id}` | Busca um filme por ID | Sim |
| PUT | `/movieflix/movies/{id}` | Atualiza um filme | Sim |
| DELETE | `/movieflix/movies/{id}` | Remove um filme | Sim |
| GET | `/movieflix/movies/search?category={id}` | Lista filmes por categoria | Sim |
| GET | `/movieflix/movies/rating` | Lista os 5 filmes com maior avaliacao | Sim |

## Exemplos de requisicao

### Cadastro de usuario

```http
POST /movieflix/auth/register
Content-Type: application/json
```

```json
{
  "name": "Pedro Carvalho",
  "email": "pedro@movieflix.com",
  "password": "senha1234"
}
```

### Login

```http
POST /movieflix/auth/login
Content-Type: application/json
```

```json
{
  "email": "pedro@movieflix.com",
  "password": "senha1234"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Cadastro de categoria

```http
POST /movieflix/category
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```

```json
{
  "name": "Acao"
}
```

### Cadastro de streaming

```http
POST /movieflix/streaming
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```

```json
{
  "name": "Netflix"
}
```

### Cadastro de filme

```http
POST /movieflix/movies
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```

```json
{
  "title": "Interestelar",
  "description": "Uma equipe viaja por um buraco de minhoca em busca de um novo lar para a humanidade.",
  "releaseDate": "06/11/2014",
  "rating": 8.6,
  "categories": [1, 2],
  "streamings": [1, 3]
}
```

## Migrations

O projeto usa Flyway para criar e atualizar as tabelas do banco automaticamente.

Diretorio das migrations:

```text
src/main/resources/db/migration
```

As migrations sao aplicadas ao iniciar a aplicacao.

## Testes

Execute:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

## Estrutura do projeto

```text
src/main/java/com/phc/movieflix
|-- config
|-- controller
|   `-- docs
|-- dtos
|   |-- request
|   `-- response
|-- entity
|-- exceptions
|-- mapper
|-- repository
`-- service
```

## Observacoes

- As datas de filmes usam o formato `dd/MM/yyyy`.
- Os endpoints protegidos exigem `Authorization: Bearer <token>`.
- A documentacao dos controllers fica separada nas interfaces `*Docs`, dentro de `controller/docs`.
- A documentacao interativa fica disponivel pelo Swagger UI.
