# DevShowcase API

API REST desenvolvida em Java 17 e Spring Boot 4.0.8 para a Tarefa 1 do projeto DevShowcase.

A aplicação permite cadastrar e consultar perfis de desenvolvedores, cadastrar e listar tecnologias e cadastrar e listar projetos. Os projetos podem estar relacionados a um perfil e utilizar várias tecnologias.

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.0.8
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- PostgreSQL
- H2 Database
- Springdoc OpenAPI
- Maven
- Git e GitHub

## Estrutura do projeto

O projeto foi organizado em camadas para facilitar a manutenção e a organização do código:

```text
src/main/java/com/devshowcase/api/

├── controller/
├── dto/
│   ├── request/
│   └── response/
├── exception/
├── model/
├── repository/
└── service/