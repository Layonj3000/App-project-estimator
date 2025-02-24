/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.command.EscolherPlataformaCommand;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import br.projeto.view.EscolhaPlataformaView;

/**
 *
 * @author USER
 */
public class EscolherPlataformaState extends ProjetoDeEstimativaPresenterState{
    private EscolhaPlataformaView view;//VIEW DEVE SER INJETADA PELO PRESENTER
    
    public EscolherPlataformaState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        super(projetoDeEstimativaPresenter);
        view = new EscolhaPlataformaView();
    }
    
    @Override
    public void escolherPlataforma(){
        new EscolherPlataformaCommand(projetoDeEstimativaPresenter).execute();
        projetoDeEstimativaPresenter.setState(new ManterProjetosDeEstimativaState(projetoDeEstimativaPresenter));
    }
    
    @Override
    public void voltar(){
        view.dispose();
    }
    
}
