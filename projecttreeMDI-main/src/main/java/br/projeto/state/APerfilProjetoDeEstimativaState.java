/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.presenter.PerfilProjetoDeEstimativaPresenter;

/**
 *
 * @author USER
 */
public abstract class APerfilProjetoDeEstimativaState {
    protected PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter;

    public APerfilProjetoDeEstimativaState(PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter) {
        this.perfilProjetoDeEstimativaPresenter = perfilProjetoDeEstimativaPresenter;
        //CRIAR UM METODO REMOVE LISTENERS E ADICIONAR A CHAMADA AQUI
    }

    public void salvar(){
        throw new RuntimeException("Não foi possível salvar a partir desse estado.");//IDENTIFICAR O NOME DO ESTADO
    }
  
    public void update(){
        throw new RuntimeException("Não foi possível atualizar a partir desse estado.");//IDENTIFICAR O NOME DO ESTADO
    }
    
    public void voltar(){
        throw new RuntimeException("Não foi possível voltar a partir desse estado.");//IDENTIFICAR O NOME DO ESTADO
    }
    
    
}
