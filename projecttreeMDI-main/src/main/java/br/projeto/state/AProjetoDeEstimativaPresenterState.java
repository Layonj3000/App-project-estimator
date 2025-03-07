/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.presenter.ProjetoDeEstimativaPresenter;

/**
 *
 * @author USER
 */
public abstract class AProjetoDeEstimativaPresenterState {
    protected ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    

    public AProjetoDeEstimativaPresenterState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        //CRIAR UM METODO REMOVE LISTENERS E ADICIONAR A CHAMADA AQUI
    }
    
    public void salvar(){
        throw new RuntimeException("Não foi possível salvar a partir desse estado.");//IDENTIFICAR O NOME DO ESTADO
    }
   
    
    public void voltar(){
        throw new RuntimeException("Não foi possível voltar a partir desse estado");
    }
    
    public void escolherPlataforma(){
        throw new RuntimeException("Não foi possível escolher uma plataforma a partir desse estado.");
    }
}
