/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

import br.projeto.enums.SimNao;

/**
 *
 * @author David Potentini
 */
public class ProjetosFuncionalidadesPersonalizadasModel {
    private Integer id;
    private String nome;
    private SimNao selecionado;
    private ProjetoDeEstimativaModel projetoDeEstimativaModel;

    public ProjetosFuncionalidadesPersonalizadasModel(Integer id, String nome, SimNao selecionado, ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        this.id = id;
        this.nome = nome;
        this.selecionado = selecionado;
        this.projetoDeEstimativaModel = projetoDeEstimativaModel;
    }

    public ProjetosFuncionalidadesPersonalizadasModel() {
       
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

    public SimNao getSelecionado() {
        return selecionado;
    }
    
    public Integer getSelecionadoValor(){
        return selecionado != null ? selecionado.getValor() : 0;
    }

    public void setSelecionado(Integer selecionado) {
        this.selecionado = (selecionado != null? SimNao.fromValue(selecionado): SimNao.NAO);
    }

    public ProjetoDeEstimativaModel getProjetoDeEstimativaModel() {
        return projetoDeEstimativaModel;
    }
    
    public Integer getIdProjetoDeEstimativa(){
        return getProjetoDeEstimativaModel().getId();
    }

    public void setProjetoDeEstimativaModel(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        this.projetoDeEstimativaModel = projetoDeEstimativaModel;
    }

    @Override
    public String toString() {
        return "ProjetosFuncionalidadesPersonalizadasModel{" + "id=" + id + 
                ", nome=" + nome + 
                ", selecionado=" + selecionado + 
                ", projetoDeEstimativaModel=" + projetoDeEstimativaModel + '}';
    }
    
    
}
