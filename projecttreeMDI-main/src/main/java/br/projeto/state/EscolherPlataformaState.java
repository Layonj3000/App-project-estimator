/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.command.ObterPerfisSelecionadosCommand;
import br.projeto.command.PreencherTabelaEscolhaDePlataformaCommand;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import br.projeto.command.Command;
/**
 *
 * @author USER
 */
public class EscolherPlataformaState extends ProjetoDeEstimativaPresenterState{
    //private EscolhaPlataformaView view;
    
    public EscolherPlataformaState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        super(projetoDeEstimativaPresenter);
        //view = projetoDeEstimativaPresenter.getView();
        configuraTela();
        new PreencherTabelaEscolhaDePlataformaCommand(projetoDeEstimativaPresenter).execute();
    }
    
    @Override
    public void escolherPlataforma(){
        ObterPerfisSelecionadosCommand command  = new ObterPerfisSelecionadosCommand(projetoDeEstimativaPresenter);
        command.execute();
        projetoDeEstimativaPresenter.setState(new ManterProjetoDeEstimativaState(projetoDeEstimativaPresenter, command.getIdPerfisSelecionados()));
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
                escolherPlataforma();
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
