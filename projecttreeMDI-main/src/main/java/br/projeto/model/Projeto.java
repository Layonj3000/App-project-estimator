package br.projeto.model;

import java.util.List;
import java.util.Map;

public class Projeto {
    private String nome;
    private String criador;
    private String dataCriacao;
    private String status;
    private boolean compartilhado;
    private String compartilhadoPor;
    private List<String> perfis;
    private Map<String, Integer> funcionalidadesEscolhidas;

    public Projeto(String nome, String criador, String dataCriacao, String status, boolean compartilhado,
                   String compartilhadoPor, List<String> perfis, Map<String, Integer> funcionalidadesEscolhidas) {
        if (perfis == null || perfis.isEmpty() || perfis.size() > 2) {
            throw new IllegalArgumentException("Um projeto deve conter entre 1 e 2 tipos.");
        }

        this.nome = nome;
        this.criador = criador;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.compartilhado = compartilhado;
        this.compartilhadoPor = compartilhadoPor;
        this.perfis = perfis;
        this.funcionalidadesEscolhidas = funcionalidadesEscolhidas;
    }

    public String getNome() {
        return nome;
    }

    public String getCriador() {
        return criador;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompartilhado() {
        return compartilhado;
    }

    public String getCompartilhadoPor() {
        return compartilhadoPor;
    }

    public List<String> getPerfis() {
        return perfis;
    }

    public Map<String, Integer> getFuncionalidadesEscolhidas() {
        return funcionalidadesEscolhidas;
    }
}
