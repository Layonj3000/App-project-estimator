/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidade_projeto;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.command.preencher.PreencherTabelaFuncionalidadesProjetoCommand;
import br.projeto.command.salvar.SalvarProjetoDeEstimativaCommand;
import br.projeto.db.DbException;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author David Potentini
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
            new SalvarProjetoDeEstimativaCommand(escolhaFuncionalidadesProjetoPresenter,idPerfisSelecionados, null).execute();
            
            new MostrarMensagemCommand("PROJETO CRIADO COM SUCESSO!").execute();
            escolhaFuncionalidadesProjetoPresenter.getView().dispose();
        } catch (IllegalArgumentException e) {
            new MostrarMensagemCommand("Erro de validação: " + e.getMessage()).execute();
        } catch (DbException e) {
            new MostrarMensagemCommand("Erro no banco de dados: " + e.getMessage()).execute();
        } catch (Exception e) { 
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
