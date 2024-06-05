# Case Java I - Prospertech

O case consiste em desenvolver um serviço web utilizando o Spring Boot e o Docker. O serviço web fernecerá uma API REST básica para manipular dados de uma entidade Produto.

## Tecnologias
- Java 17
- Maven
- Spring Boot
- Spring JPA
- Spring Web
- PostgreSQL
- Docker

## Como rodar o projeto
1. Clone este repositório

    `git clone https://github.com/KayllaneGPina/case-java-I.git`

2. Entre no diretório principal

    `cd case-java`

3. Construa e inicie a aplicação utilizando o Docker Compose

    `docker-compose up --build`

4. A aplicação estará disponível na url

    `http://localhost:8080/api/products`