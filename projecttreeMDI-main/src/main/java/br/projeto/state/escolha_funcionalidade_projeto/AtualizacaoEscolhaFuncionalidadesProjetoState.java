/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidade_projeto;

import br.projeto.command.PreencherTabelaFuncionalidadesProjetoCommand;
import br.projeto.command.SalvarProjetoDeEstimativaCommand;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author USER
 */
public class AtualizacaoEscolhaFuncionalidadesProjetoState extends AEscolhaFuncionalidadeProjetoState{
    private Integer projetoId;
    private List<Integer> idPerfisSelecionados;
    
    public AtualizacaoEscolhaFuncionalidadesProjetoState(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadeProjetoPresenter,List<Integer> idPerfisSelecionados, Integer projetoId) {
        super(escolhaFuncionalidadeProjetoPresenter);
        this.idPerfisSelecionados = idPerfisSelecionados;
        this.projetoId = projetoId;
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesProjetoCommand(escolhaFuncionalidadeProjetoPresenter, idPerfisSelecionados, projetoId).execute();
    }
    
        @Override
    public void confirmar(){
        new SalvarProjetoDeEstimativaCommand(escolhaFuncionalidadeProjetoPresenter,idPerfisSelecionados, projetoId).execute();
        //CODIGO PARA APARECER MENSAGEM DE SALVO COM SUCESSO
        //projetoDeEstimativaPresenter.getView().getFrame().dispose();
    }
    
    @Override
    public void voltar(){
        escolhaFuncionalidadeProjetoPresenter.getView().dispose();
    }

    //VERIFICAR DEMETER
    private void configuraTela() {
        escolhaFuncionalidadeProjetoPresenter.getView().setVisible(false);
        escolhaFuncionalidadeProjetoPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               confirmar();
            }
        });
        
        escolhaFuncionalidadeProjetoPresenter.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                voltar();
            }
        });    
        escolhaFuncionalidadeProjetoPresenter.getView().setVisible(true);
    }
}
