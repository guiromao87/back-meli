package br.com.guiromao.meli.mercadolivre.domain.enuns;

public enum Category {
    BOOK("Livro"),
    ELECTRONICS("Tecnologia"),
    FASHION("Moda"),
    AUTOMOTIVE("Automovel"),
    HOME("Casa"),
    HARDWARE("Hardware"),
    MUSIC("Musica"),
    SPORT("Esporte"),
    TOY("Brinquedos"),
    BEAUTY("Beleza"),

    ;

    private final String value;

    Category(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
