/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.command.PreencherTabelaFuncionalidadesPerfilParaUpdateCommand;
import br.projeto.command.SalvarPerfilProjetoDeEstimativaCommand;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class AtualizacaoEscolhaFuncionalidadesPerfilState extends AEscolhaFuncionalidadesPerfilState{
    private Integer idPerfil;
    
    public AtualizacaoEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        super(escolhaFuncionalidadesPerfilPresenter);
        
        this.idPerfil = idPerfil;
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesPerfilParaUpdateCommand(escolhaFuncionalidadesPerfilPresenter, idPerfil).execute();
    }
    
        @Override
    public void salvar(){
        new SalvarPerfilProjetoDeEstimativaCommand(escolhaFuncionalidadesPerfilPresenter, idPerfil).execute();
        
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
