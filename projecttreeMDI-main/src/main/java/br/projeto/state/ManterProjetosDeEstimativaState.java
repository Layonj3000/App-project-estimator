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
public class ManterProjetosDeEstimativaState extends ProjetoDeEstimativaPresenterState{
    
    public ManterProjetosDeEstimativaState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        super(projetoDeEstimativaPresenter);
    }
    
    @Override
    public void salvar(){
        throw new RuntimeException("Não foi possível salvar a partir desse estado.");//IDENTIFICAR O NOME DO ESTADO
    }
    
    @Override
    public void update(){
        //REALIZAR IMPLEMENTAÇÃO
    }
    
    @Override
    public void voltar(){
        //REALIZAR IMPLEMENTAÇÃO
    }
    
}
