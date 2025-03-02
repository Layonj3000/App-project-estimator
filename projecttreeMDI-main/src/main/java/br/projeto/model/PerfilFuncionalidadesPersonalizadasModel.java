/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

/**
 *
 * @author USER
 */
public class PerfilFuncionalidadesPersonalizadasModel {
    private Integer id;
    private String nome;
    private Integer valor;
    private PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel;

    public PerfilFuncionalidadesPersonalizadasModel(Integer id, String nome, Integer valor, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.perfilProjetoDeEstimativaModel = perfilProjetoDeEstimativaModel;
    }

    public PerfilFuncionalidadesPersonalizadasModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public PerfilProjetoDeEstimativaModel getPerfilProjetoDeEstimativaModel() {
        return perfilProjetoDeEstimativaModel;
    }
    
    public Integer getIdPerfilProjetoDeEstimativaModel(){
        return perfilProjetoDeEstimativaModel.getId();
    }

    public void setPerfilProjetoDeEstimativaModel(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        this.perfilProjetoDeEstimativaModel = perfilProjetoDeEstimativaModel;
    }

    @Override
    public String toString() {
        return "PerfilFuncionalidadesPersonalizadas{" + "id=" + id +
                ", nome=" + nome +
                ", valor=" + valor + 
                ", perfilProjetoDeEstimativa=" + perfilProjetoDeEstimativaModel + '}';
    }
}
