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
 * @author David Potentini
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
        try{
            new SalvarProjetoDeEstimativaCommand(escolhaFuncionalidadesProjetoPresenter,idPerfisSelecionados, projetoId).execute();
            
            new MostrarMensagemCommand("PROJETO ATUALIZADO COM SUCESSO!").execute();
            escolhaFuncionalidadesProjetoPresenter.getView().dispose();
        } catch (IllegalArgumentException e) {
            new MostrarMensagemCommand("Erro de validação: " + e.getMessage()).execute();
        } catch (DbException e) {
            new MostrarMensagemCommand("Erro no banco de dados: " + e.getMessage()).execute();
        }catch (Exception e) { 
            new MostrarMensagemCommand("Ocorreu um erro inesperado: " + e.getMessage()).execute();
        } 
    }
    

    
    private void configuraTela() {
        escolhaFuncionalidadesProjetoPresenter.getView().setVisible(false);
        escolhaFuncionalidadesProjetoPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               confirmar();
            }
        });
   
        escolhaFuncionalidadesProjetoPresenter.getView().setVisible(true);
    }
}
