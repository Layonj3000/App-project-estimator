/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.command.PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand;
import br.projeto.command.SalvarPerfilProjetoDeEstimativaCommand;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class InclusaoEscolhaFuncionalidadesPerfilState extends AEscolhaFuncionalidadesPerfilState{
    
    public InclusaoEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter) {
        super(escolhaFuncionalidadesPerfilPresenter);
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand(escolhaFuncionalidadesPerfilPresenter).execute();
    }
    
    @Override
    public void salvar(){
        new SalvarPerfilProjetoDeEstimativaCommand(escolhaFuncionalidadesPerfilPresenter, null).execute();
        
        //escolhaFuncionalidadesPerfilPresenter.getView().dispose();        
    }
    
    @Override
    public void voltar(){
        escolhaFuncionalidadesPerfilPresenter.getView().dispose();
    }
    
    
        //VERIFICAR DEMETER
    private void configuraTela() {
        escolhaFuncionalidadesPerfilPresenter.getView().setVisible(false);
        escolhaFuncionalidadesPerfilPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               salvar();
            }
        });
        
        escolhaFuncionalidadesPerfilPresenter.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                voltar();
            }
        });
        
       escolhaFuncionalidadesPerfilPresenter.configurarAdicaoFuncionalidades();
       escolhaFuncionalidadesPerfilPresenter.configurarRemocaoFuncionalidades();
        
       escolhaFuncionalidadesPerfilPresenter.getView().setVisible(true);
    }
    
    
}
