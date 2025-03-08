/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.command.PreencherTabelaFuncionalidadesProjetoCommand;
import br.projeto.command.SalvarProjetoDeEstimativaCommand;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import br.projeto.view.ManterProjetoDeEstimativaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author David Potentini
 */
public class ProjetoDeEstimativaMantidoState extends AProjetoDeEstimativaPresenterState{
    private ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private List<Integer> idPerfisSelecionados;
    private Integer projetoId;
    
    public ProjetoDeEstimativaMantidoState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter,  List<Integer> idPerfisSelecionados, Integer projetoId){
        super(projetoDeEstimativaPresenter);
        
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        this.idPerfisSelecionados = idPerfisSelecionados;
        this.projetoId= projetoId;
        
        
        projetoDeEstimativaPresenter.setView(new ManterProjetoDeEstimativaView());
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesProjetoCommand(projetoDeEstimativaPresenter, idPerfisSelecionados, projetoId).execute();
        
    }
    
    @Override
    public void salvar(){
        new SalvarProjetoDeEstimativaCommand(projetoDeEstimativaPresenter,idPerfisSelecionados, projetoId).execute();
        //CODIGO PARA APARECER MENSAGEM DE SALVO COM SUCESSO
        //projetoDeEstimativaPresenter.getView().getFrame().dispose();
    }
    
    @Override
    public void voltar(){
        projetoDeEstimativaPresenter.getView().getFrame().dispose();
    }

    //VERIFICAR DEMETER
    private void configuraTela() {
        projetoDeEstimativaPresenter.getView().getFrame().setVisible(false);
        projetoDeEstimativaPresenter.getView().getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               salvar();
            }
        });
        
        projetoDeEstimativaPresenter.getView().getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                voltar();
            }
        });    
        projetoDeEstimativaPresenter.getView().getFrame().setVisible(true);
    }
}
