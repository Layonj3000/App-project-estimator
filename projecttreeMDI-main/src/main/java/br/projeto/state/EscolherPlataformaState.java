/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.command.ObterPerfisSelecionadosCommand;
import br.projeto.command.PreencherTabelaEscolhaDePlataformaParaInsercaoCommand;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import br.projeto.command.Command;
import br.projeto.command.PreencherTabelaEscolhaDePlataformaParaUpdateCommand;
/**
 *
 * @author USER
 */
public class EscolherPlataformaState extends AProjetoDeEstimativaPresenterState{
    //private EscolhaPlataformaView view;
    private Integer projetoId;
    //CONSTRUTOR PARA UPDATE
    public EscolherPlataformaState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter, Integer projetoId) {
        super(projetoDeEstimativaPresenter);
        this.projetoId = projetoId;
        //view = projetoDeEstimativaPresenter.getView();
        configuraTela();
        new PreencherTabelaEscolhaDePlataformaParaUpdateCommand(projetoDeEstimativaPresenter, projetoId).execute();
    }
    
    //CONSTRUTOR PARA INSERÇÃO
    public EscolherPlataformaState(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        super(projetoDeEstimativaPresenter);
        //view = projetoDeEstimativaPresenter.getView();
        configuraTela();
        new PreencherTabelaEscolhaDePlataformaParaInsercaoCommand(projetoDeEstimativaPresenter).execute();
    }
    
    @Override
    public void escolherPlataforma(){
        ObterPerfisSelecionadosCommand command  = new ObterPerfisSelecionadosCommand(projetoDeEstimativaPresenter);
        command.execute();
        projetoDeEstimativaPresenter.getView().getFrame().dispose();
        projetoDeEstimativaPresenter.setState(new ManterProjetoDeEstimativaState(projetoDeEstimativaPresenter, command.getIdPerfisSelecionados(), projetoId));
        
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
