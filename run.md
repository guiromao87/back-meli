## Para rodar localmente

Como requisito precisamos ter o java(versão 21 no mínimo) instalado, clonar o projeto, entrar na raiz e rodar:

```
./mvnw spring-boot:run
```
Assim o tomcat embarcado do spring boot rodará na porta 8080.

Podemos também rodar com o Docker:

Precisamos do Docker instalado e na raiz do projeto, rodamos o comando para gerar a imagem:
```
docker build -t nome-da-imagem .
```
E rodamos com Docker run para subir o container com a aplicação:
```
docker run -p 8080:8080 nome-da-imagem
```
Com isso temos o projeto rodando na porta 8080.