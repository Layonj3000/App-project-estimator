/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_perfis_projeto;

import br.projeto.command.ObterPerfisSelecionadosCommand;
import br.projeto.command.PreencherTabelaEscolhaDePlataformaParaInsercaoCommand;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import br.projeto.presenter.EscolhaPerfilPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author David Potentini
 */
public class InclusaoEscolhaPerfilState extends AEscolhaPerfilState{

    public InclusaoEscolhaPerfilState(EscolhaPerfilPresenter escolhaPerfilPresenter) {
        super(escolhaPerfilPresenter);
        
        configuraTela();
        new PreencherTabelaEscolhaDePlataformaParaInsercaoCommand(escolhaPerfilPresenter).execute();
    }
    
    @Override
    public void confirmar(){
        ObterPerfisSelecionadosCommand perfisSelecionados  = new ObterPerfisSelecionadosCommand(escolhaPerfilPresenter);
        perfisSelecionados.execute();
        escolhaPerfilPresenter.getView().dispose();
        
        EscolhaFuncionalidadesProjetoPresenter projetoPresenter = new EscolhaFuncionalidadesProjetoPresenter(escolhaPerfilPresenter.getProjetoDeEstimativaRepository(), escolhaPerfilPresenter.getPerfilProjetoDeEstimativaRepository(), escolhaPerfilPresenter.getProjetoFuncionalidadesPersonalizadasRepository(), 
                                                                                                             escolhaPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository(), escolhaPerfilPresenter.getPerfilProjetoIntermediariaRepository(), escolhaPerfilPresenter.getUsuarioModel());
        projetoPresenter.setProjetoId(null);
        projetoPresenter.setPerfisSelecionados(perfisSelecionados.getIdPerfisSelecionados());
        projetoPresenter.setEstadoInicial();
    }
    
    @Override
    public void voltar(){
        escolhaPerfilPresenter.getView().dispose();
    }

    //VERIFICAR DEMETER
    private void configuraTela() {
        escolhaPerfilPresenter.getView().setVisible(false);
        
        escolhaPerfilPresenter.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                confirmar();
            }
        });
        
        escolhaPerfilPresenter.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                voltar();
            }
        });
        
        escolhaPerfilPresenter.getView().setVisible(true);
    }
    
}
