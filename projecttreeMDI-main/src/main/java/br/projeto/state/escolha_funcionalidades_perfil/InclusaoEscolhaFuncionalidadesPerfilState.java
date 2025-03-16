/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.command.PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand;
import br.projeto.command.SalvarPerfilProjetoDeEstimativaCommand;
import br.projeto.db.DbException;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author David Potentini
 */
public class InclusaoEscolhaFuncionalidadesPerfilState extends AEscolhaFuncionalidadesPerfilState{
    
    public InclusaoEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter) {
        super(escolhaFuncionalidadesPerfilPresenter);
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand(escolhaFuncionalidadesPerfilPresenter).execute();
    }
    
    @Override
    public void salvar(){
        try{
            new SalvarPerfilProjetoDeEstimativaCommand(escolhaFuncionalidadesPerfilPresenter, null).execute();
            new MostrarMensagemCommand("PERFIL CRIADO COM SUCESSO!").execute();
            
            escolhaFuncionalidadesPerfilPresenter.getView().dispose();
        } catch (NumberFormatException e) {
            new MostrarMensagemCommand("Número inválido inserido: " + e.getMessage()).execute();
        } catch (IllegalArgumentException e) {
            new MostrarMensagemCommand("Erro de validação: " + e.getMessage()).execute();
        } catch (DbException e) {
            new MostrarMensagemCommand("Erro no banco de dados: " + e.getMessage()).execute();
        } catch (ArrayIndexOutOfBoundsException e){
            new MostrarMensagemCommand("Preencha o conteúdo da linha adicionada: " + e.getMessage()).execute();
        } catch (Exception e) { 
            new MostrarMensagemCommand("Ocorreu um erro inesperado: " + e.getMessage()).execute();
        }        
    }
    
    private void configuraTela() {
        escolhaFuncionalidadesPerfilPresenter.getView().setVisible(false);
        escolhaFuncionalidadesPerfilPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               salvar();
            }
        });
        
        
       escolhaFuncionalidadesPerfilPresenter.configurarAdicaoFuncionalidades();
       escolhaFuncionalidadesPerfilPresenter.configurarRemocaoFuncionalidades();
        
       escolhaFuncionalidadesPerfilPresenter.getView().setVisible(true);
    }
    
    
}
