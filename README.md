# Desafio Backend Mercado Livre

Crie uma API de backend que forneça todos os dados necessários para dar suporte a uma página de detalhes do item, inspirada no Mercado Livre.

## Tecnologias utilizadas

    * Java - Versão 21
    * Maven
    * Spring Boot, Spring MVC
    * Json
    * Junit para teste unitários
    * Docker
    * OpenAPI com Swagger
    
---

## Design da Api

Como a proposta seria fazer uma api para o frontend do Mercado Livre criei o objeto Products, que atualmente servirá para armazenar valores de uma lista json de produtos gerados por IA mas que posteriormente poderia virar uma Entidade.
Optei pelos campos:

- `id` (Long)
- `name` (String)
- `description` (String)
- `imageUrl` (String)
- `price` (BigDecimal)
- `category` (Enum)
- `review` (double)

Baseado em uma página de produtos mais genérica do próprio site.

A api possui dois endpoints:

| Método | Endpoint            | Descrição                                 | Parâmetros                                                                                   | Retorno                     |
|--------|---------------------|-------------------------------------------|----------------------------------------------------------------------------------------------|-----------------------------|
| GET    | `/products`         | Lista produtos paginados                  | `page`, `size` (paginacao)                                                                   | Produtos em Json
| GET    | `/products/{id}`    | Busca produto por ID                      | `id` (path variable)                                                                         | Produto ou 404 se não encontrado |

OBS: Os parâmetros `page` e `size` da paginação são opcionais caso queira trocar pelos valores default da anotação `@PageableDefault`, basta passa-los como query parameter da url.

---

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

---

## Extras

### Documentação com Swagger e teste

Para facilitar os testes da api disponibilizei a colection do postman na raiz do projeto ou caso contrário está configurado o swagger para melhor visualização dos endpoints, para acessa-lo devemos rodar a aplicação e entrar no endereço: 
`http://localhost:8080/swagger-ui.html`

### CORS

Como na especificação do teste a api irá servir a um frontend deixei configurado o CORS para não termos problemas de integração entre o Frontend e o Backend.

### Tratamento de erros

O projeto possui algumas exceptions de negócio, trata id inválido, lista vazia e possui uma classe de erro advice que captura uma exception e a transforma em um retorno http amigável para o Frontend.


