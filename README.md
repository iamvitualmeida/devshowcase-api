# DevShowcase API

API REST desenvolvida em **Java 17** com **Spring Boot 4.0.8** para gerenciamento de perfis de desenvolvedores, projetos, tecnologias e feedbacks.

O projeto foi desenvolvido em etapas, começando pela modelagem do domínio, persistência e criação dos endpoints básicos e evoluindo para regras de negócio, tratamento global de erros, paginação, filtros, documentação da API e publicação em nuvem.

---

## 1. Objetivo do projeto

O DevShowcase API tem como objetivo disponibilizar uma API REST para permitir o cadastro e consulta de informações relacionadas a desenvolvedores e seus projetos.

A API permite:

* cadastrar perfis de desenvolvedores;
* consultar perfis;
* cadastrar tecnologias;
* listar tecnologias;
* cadastrar projetos;
* listar projetos;
* relacionar projetos com tecnologias;
* registrar avaliações dos projetos;
* calcular a média das avaliações;
* registrar upvotes nos projetos;
* filtrar projetos por tecnologia;
* utilizar paginação na listagem de projetos;
* tratar erros de validação e recursos inexistentes;
* disponibilizar documentação interativa através do Swagger/OpenAPI.

---

# 2. Tecnologias utilizadas

O projeto utiliza as seguintes tecnologias:

* **Java 17**
* **Spring Boot 4.0.8**
* **Spring Web**
* **Spring Data JPA**
* **Spring Validation**
* **PostgreSQL**
* **H2**
* **Maven**
* **Docker**
* **Swagger / OpenAPI**
* **Git e GitHub**
* **Render**

---

# 3. Arquitetura do projeto

O projeto foi organizado utilizando uma separação por responsabilidades.

```text
src/main/java/com/devshowcase/api
│
├── controller
├── dto
├── exception
├── model
├── repository
└── service
```

## Controller

Responsável por receber as requisições HTTP e encaminhá-las para os serviços da aplicação.

## DTO

Responsável pelos objetos utilizados para entrada e saída de dados da API.

Os DTOs também possuem validações para evitar o recebimento de informações inválidas.

## Model

Contém as entidades que representam os dados persistidos no banco de dados.

## Repository

Responsável pelo acesso aos dados utilizando Spring Data JPA.

## Service

Contém as regras de negócio da aplicação.

## Exception

Contém as classes utilizadas para tratamento global de erros.

---

# 4. Modelagem do domínio

O sistema possui quatro entidades principais:

* Profile
* Project
* Technology
* Feedback

## Profile

Representa o perfil de um desenvolvedor.

Principais informações:

* ID
* nome
* e-mail
* biografia

## Project

Representa um projeto desenvolvido por um perfil.

Principais informações:

* ID
* título
* descrição
* URL
* média das avaliações
* quantidade de upvotes

## Technology

Representa uma tecnologia utilizada em um projeto.

Principais informações:

* ID
* nome

## Feedback

Representa uma avaliação realizada sobre um projeto.

Principais informações:

* ID
* nota
* comentário
* projeto relacionado

---

# 5. Relacionamentos

O sistema possui os seguintes relacionamentos:

```text
Profile 1:N Project

Project N:N Technology

Project 1:N Feedback
```

## Profile → Project

Um perfil pode possuir vários projetos.

Um projeto pertence a um perfil.

```text
Profile 1 ─────── N Project
```

## Project → Technology

Um projeto pode utilizar várias tecnologias.

Uma tecnologia pode estar relacionada a vários projetos.

```text
Project N ─────── N Technology
```

Essa relação é armazenada através da tabela intermediária:

```text
project_technologies
```

## Project → Feedback

Um projeto pode receber vários feedbacks.

Cada feedback pertence a um projeto.

```text
Project 1 ─────── N Feedback
```

---

# 6. Endpoints da API

## Profiles

### Criar perfil

```http
POST /api/profiles
```

Exemplo:

```json
{
  "name": "Vitor Almeida",
  "email": "vitor@example.com",
  "bio": "Desenvolvedor de sistemas"
}
```

Resposta esperada:

```http
201 Created
```

---

### Consultar perfil

```http
GET /api/profiles/{id}
```

Exemplo:

```http
GET /api/profiles/1
```

---

# 7. Technologies

### Criar tecnologia

```http
POST /api/technologies
```

Exemplo:

```json
{
  "name": "Java"
}
```

Resposta esperada:

```http
201 Created
```

---

### Listar tecnologias

```http
GET /api/technologies
```

---

# 8. Projects

### Criar projeto

```http
POST /api/projects
```

Exemplo:

```json
{
  "title": "DevShowcase",
  "description": "API para apresentação de projetos de desenvolvedores",
  "url": "https://github.com/iamvitualmeida/devshowcase-api",
  "profileId": 1,
  "technologyIds": [1]
}
```

Resposta esperada:

```http
201 Created
```

---

### Listar projetos

```http
GET /api/projects
```

---

### Listar projetos com paginação

A API permite controlar a página e a quantidade de projetos retornados.

```http
GET /api/projects?page=0&size=10
```

Exemplo:

```text
page=0
size=10
```

A resposta contém informações de paginação, como:

* número da página;
* tamanho da página;
* quantidade total de registros;
* quantidade total de páginas.

---

### Filtrar projetos por tecnologia

Também é possível filtrar os projetos utilizando o ID da tecnologia.

```http
GET /api/projects?technologyId=1&page=0&size=10
```

Nesse exemplo, serão retornados os projetos relacionados à tecnologia de ID `1`.

---

# 9. Upvote

É possível adicionar um upvote a um projeto.

```http
PUT /api/projects/{id}/upvote
```

Exemplo:

```http
PUT /api/projects/1/upvote
```

Cada chamada incrementa a quantidade de upvotes do projeto em 1.

Exemplo de resultado:

```json
{
  "id": 1,
  "title": "DevShowcase",
  "averageRating": 5.0,
  "upvotes": 1
}
```

---

# 10. Feedback

É possível registrar uma avaliação para um projeto.

```http
POST /api/projects/{id}/feedbacks
```

Exemplo:

```http
POST /api/projects/1/feedbacks
```

Body:

```json
{
  "rating": 5,
  "comment": "Projeto muito bom"
}
```

Resposta esperada:

```http
201 Created
```

---

# 11. Média das avaliações

Cada projeto possui um campo:

```text
averageRating
```

A nota deve estar entre **1 e 5**.

Quando um novo feedback é registrado, a API calcula novamente a média das avaliações daquele projeto.

Exemplo:

```text
Feedback 1 = 5
Feedback 2 = 3

Média = 4.0
```

A média é armazenada no projeto e apresentada nas respostas da API.

---

# 12. Validações

A API utiliza Bean Validation para validar os dados recebidos.

Entre as validações implementadas estão:

* nome obrigatório;
* e-mail obrigatório;
* formato de e-mail válido;
* título obrigatório;
* perfil obrigatório;
* nome da tecnologia obrigatório;
* URL válida;
* nota obrigatória;
* nota entre 1 e 5;
* comentário obrigatório.

Exemplo de uma nota inválida:

```json
{
  "rating": 6,
  "comment": "Nota inválida"
}
```

A API retorna:

```http
400 Bad Request
```

---

# 13. Tratamento global de erros

A aplicação possui um tratamento global de exceções através do `GlobalExceptionHandler`.

## Erro 400

É utilizado quando os dados enviados não passam pelas validações.

Exemplo:

```json
{
  "rating": "A nota deve ser entre 1 e 5"
}
```

---

## Erro 404

É utilizado quando um recurso solicitado não existe.

Exemplo:

```http
POST /api/projects/999/feedbacks
```

Resposta:

```json
{
  "message": "Projeto não encontrado"
}
```

Status:

```http
404 Not Found
```

---

## Erro 500

Erros inesperados são tratados globalmente e retornam uma mensagem genérica para o cliente.

---

# 14. Validações

A API possui validações para evitar o cadastro de dados inválidos.

Entre as principais validações estão:

* Título do projeto obrigatório;
* Perfil obrigatório para criação de projeto;
* Nota do feedback obrigatória;
* Nota do feedback deve estar entre 1 e 5;
* Comentário do feedback obrigatório;
* URL do projeto deve possuir formato válido.

Quando uma validação é violada, a API retorna o status HTTP `400 Bad Request` com uma mensagem explicando o problema.

---

# 15. Tratamento global de erros

A aplicação possui um tratamento global de exceções utilizando `@RestControllerAdvice`.

São tratados principalmente:

### 400 Bad Request

Utilizado quando os dados enviados são inválidos.

Exemplo:

```json
{
  "rating": "A nota deve ser entre 1 e 5"
}
```

### 404 Not Found

Utilizado quando um recurso solicitado não existe.

Exemplo:

```json
{
  "message": "Projeto não encontrado"
}
```

### 500 Internal Server Error

Utilizado para erros internos inesperados da aplicação.

Exemplo:

```json
{
  "message": "Ocorreu um erro interno no servidor"
}
```

---

# 16. Banco de dados

## Ambiente local

Durante o desenvolvimento local, a aplicação utiliza o banco de dados H2 em memória.

Configuração principal:

```properties
spring.datasource.url=jdbc:h2:mem:devshowcase
```

O H2 facilita os testes locais sem a necessidade de configurar um banco PostgreSQL externo.

## Ambiente de produção

Em produção, a aplicação utiliza PostgreSQL hospedado na Render.

As informações de acesso são configuradas por variáveis de ambiente.

Variáveis utilizadas:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

A senha do banco não é armazenada no código-fonte.

---

# 17. Docker

O projeto possui um `Dockerfile` para criação da imagem da aplicação.

O processo utiliza duas etapas:

1. Compilação do projeto utilizando Maven e Java 17;
2. Execução da aplicação utilizando Java 17 JRE.

O Docker também é utilizado pelo serviço da aplicação na Render.

---

# 18. Executando o projeto localmente

Para executar o projeto localmente, é necessário possuir:

* Java 17;
* Git;
* Maven, ou utilizar o Maven Wrapper disponibilizado pelo projeto.

Clone o repositório:

```bash
git clone https://github.com/iamvitualmeida/devshowcase-api.git
```

Entre na pasta:

```bash
cd devshowcase-api
```

Execute a aplicação utilizando o Maven Wrapper:

### Windows

```bash
mvnw.cmd spring-boot:run
```

Após iniciar, a API estará disponível em:

```text
http://localhost:8080
```

---

# 19. Swagger / OpenAPI

A aplicação possui documentação interativa utilizando Swagger/OpenAPI.

## Ambiente local

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui.html
```

## Ambiente de produção

A documentação pública está disponível em:

```text
https://devshowcase-api-doro.onrender.com/swagger-ui.html
```

O Swagger permite visualizar os endpoints disponíveis e realizar requisições diretamente pela interface.

---

# 20. Deploy em produção

O projeto foi publicado utilizando a plataforma Render.

A aplicação está conectada ao repositório público do GitHub e utiliza Docker para execução.

O banco de dados de produção utiliza PostgreSQL.

A configuração de produção utiliza variáveis de ambiente para armazenar as informações de conexão com o banco de dados.

A aplicação está disponível publicamente em:

```text
https://devshowcase-api-doro.onrender.com
```

Swagger:

```text
https://devshowcase-api-doro.onrender.com/swagger-ui.html
```

---

# 21. Controle de versão

O projeto utiliza Git para controle de versão.

As principais versões da atividade foram organizadas em branches e tags.

A implementação da Tarefa 2 está na branch:

```text
tarefa-2
```

A versão correspondente à Tarefa 1 foi identificada pela tag:

```text
tarefa-1
```

---

# 22. Repositório

Repositório público do projeto:

```text
https://github.com/iamvitualmeida/devshowcase-api
```

---

# 23. Testes realizados

Durante a implementação da Tarefa 2 foram realizados testes utilizando Postman.

Foram testados:

* Criação de perfil;
* Criação de tecnologia;
* Criação de projeto;
* Listagem de projetos;
* Paginação;
* Filtro de projetos por tecnologia;
* Registro de upvote;
* Registro de feedback;
* Atualização da média das avaliações;
* Validação de nota inválida;
* Projeto inexistente;
* Resposta `400 Bad Request`;
* Resposta `404 Not Found`.

Também foi verificado o funcionamento da documentação Swagger em produção.

---

# 24. Status do projeto

O projeto está funcional e publicado em ambiente de produção.

A Tarefa 2 contempla:

* Regras avançadas;
* Feedbacks;
* Média de avaliações;
* Upvotes;
* Filtro por tecnologia;
* Paginação;
* Validações;
* Tratamento global de erros;
* Swagger/OpenAPI;
* PostgreSQL em produção;
* Docker;
* Deploy na Render;
* Configuração por variáveis de ambiente;
* Controle de versão utilizando Git e GitHub.

A etapa técnica da aplicação está concluída.