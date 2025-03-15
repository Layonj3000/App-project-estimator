/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidade_projeto;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.command.PreencherTabelaFuncionalidadesProjetoCommand;
import br.projeto.command.SalvarProjetoDeEstimativaCommand;
import br.projeto.db.DbException;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author USER
 */
public class InsercaoEscolhaFuncionalidadesProjetoState  extends AEscolhaFuncionalidadeProjetoState{
    private List<Integer> idPerfisSelecionados;
    
    public InsercaoEscolhaFuncionalidadesProjetoState(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadeProjetoPresenter,List<Integer> idPerfisSelecionados) {
        super(escolhaFuncionalidadeProjetoPresenter);
        this.idPerfisSelecionados = idPerfisSelecionados;
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesProjetoCommand(escolhaFuncionalidadeProjetoPresenter, idPerfisSelecionados, null).execute();
    }
    
    @Override
    public void confirmar(){
        try{
            new SalvarProjetoDeEstimativaCommand(escolhaFuncionalidadeProjetoPresenter,idPerfisSelecionados, null).execute();
        } catch (IllegalArgumentException e) {
            new MostrarMensagemCommand("Erro de validação: " + e.getMessage()).execute();
        } catch (DbException e) {
            new MostrarMensagemCommand("Erro no banco de dados: " + e.getMessage()).execute();
        } catch (Exception e) { 
            new MostrarMensagemCommand("Ocorreu um erro inesperado: " + e.getMessage()).execute();
        } 
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
