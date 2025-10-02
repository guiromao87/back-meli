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

Precisamos do Docker e do Maven instalados, geramos uma imagem do projeto através do arquivo Dockerfile, para isso, 
na raiz do projeto, rodamos o comando: 
```
mvn clean package
```
Assim o plugin do maven (já está configurado) gera uma imagem docker do projeto e rodamos com Docker run:
```
docker run -p 8080:8080 guiromao/seven-mars:1.0.0
```
Com isso temos o projeto rodando na porta 8080.

## Testando os endpoints
