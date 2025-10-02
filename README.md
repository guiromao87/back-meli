# Desafio Backend Mercado Livre

Crie uma API de backend que forneça todos os dados necessários para dar suporte a uma página de detalhes do item, inspirada no Mercado Livre.

## Tecnologias utilizadas

    * Java - Versão 21
    * Maven
    * Spring Boot, Spring MVC
    * Json
    * Junit para teste unitários
    * Docker
    * Swagger

## Para rodar localmente

Como requisito precisamos ter o java(versão 21 no mínimo) instalado, clonar o projeto, ir na raiz e rodar: 

```
./mvnw spring-boot:run
```
Assim o tomcat embarcado do spring boot rodará na porta 8080. 

Podemos também rodar com o Docker:

Precisamos do Docker instalado e na raiz do projeto, rodamos o comando: 
```
docker build -t nome-da-imagem .
```
E rodamos com Docker run:
```
docker run -p 8080:8080 nome-da-imagem
```
Com isso temos o projeto rodando na porta 8080.

## Testando os endpoints
