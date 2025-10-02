package br.com.guiromao.meli.mercadolivre.domain.enuns;

public enum Category {
    BOOK("Livro"),
    TECNOLOGY("Tecnologia"),
    FASHION("Moda"),
    VEHICLE("Automovel");

    private final String value;

    Category(String value) { this.value = value; }

    public String getValue() {
        return value;
    }
}
