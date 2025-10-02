package br.com.guiromao.meli.mercadolivre.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Page<T> {
    private List<T> conteudo;
    private int numeroPagina;
    private int tamanhoPagina;
    private long totalItens;
    private int totalPaginas;

    public Page(List<T> conteudo, int numeroPagina, int tamanhoPagina, long totalItens, int totalPaginas) {
        this.conteudo = conteudo;
        this.numeroPagina = numeroPagina;
        this.tamanhoPagina = tamanhoPagina;
        this.totalItens = totalItens;
        this.totalPaginas = totalPaginas;
    }
}
