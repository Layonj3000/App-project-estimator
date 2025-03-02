/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class ObterPerfisSelecionadosCommand implements Command{
    private ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private List<Integer> idPerfisSelecionados;

    public ObterPerfisSelecionadosCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        idPerfisSelecionados = new ArrayList<>();
    }
    
    

    @Override
    public void execute() {
        JTable tabela = projetoDeEstimativaPresenter.getView().getTable();
        int qtdLinhas = tabela.getRowCount();
        int qtdColunas  = tabela.getColumnCount();
        
        for(int i = 0; i < qtdLinhas; i++){
            //for(int j = 0; j < qtdColunas; j++){
                if(tabela.getValueAt(i, 0).equals(true)){
                    Integer idPerfil = (Integer)tabela.getValueAt(i, 2);
                    idPerfisSelecionados.add(idPerfil);
                }
            //}
        } 
    }
    
    public List<Integer> getIdPerfisSelecionados(){
        return idPerfisSelecionados;
    }
    
}
