/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.command.AdicionarOpcaoPerfilCommand;
import br.projeto.command.PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand;
import br.projeto.command.SalvarPerfilProjetoDeEstimativaCommand;
import br.projeto.presenter.PerfilProjetoDeEstimativaPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ManterPerfilProjetoDeEstimativaState extends APerfilProjetoDeEstimativaState{
    
    public ManterPerfilProjetoDeEstimativaState(PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter) {
        super(perfilProjetoDeEstimativaPresenter);
        
        configuraTela();
        
        new PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand(perfilProjetoDeEstimativaPresenter).execute();
    }
    
    @Override
    public void salvar(){
        new SalvarPerfilProjetoDeEstimativaCommand(perfilProjetoDeEstimativaPresenter).execute();
        
        //perfilProjetoDeEstimativaPresenter.getView().dispose();        
    }
    
    @Override
    public void voltar(){
        perfilProjetoDeEstimativaPresenter.getView().dispose();
    }
    
    
        //VERIFICAR DEMETER
    private void configuraTela() {
        perfilProjetoDeEstimativaPresenter.getView().setVisible(false);
        perfilProjetoDeEstimativaPresenter.getView().getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               salvar();
            }
        });
        
        perfilProjetoDeEstimativaPresenter.getView().getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                voltar();
            }
        });
        
       perfilProjetoDeEstimativaPresenter.configurarAdicaoFuncionalidades();
       perfilProjetoDeEstimativaPresenter.configurarRemocaoFuncionalidades();
        
        perfilProjetoDeEstimativaPresenter.getView().setVisible(true);
    }
    
    
}
