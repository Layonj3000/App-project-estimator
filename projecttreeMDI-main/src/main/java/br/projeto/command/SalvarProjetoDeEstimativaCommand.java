/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class SalvarProjetoDeEstimativaCommand implements Command{
    private ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private ProjetoDeEstimativaModel projetoDeEstimativaModel;
    
    public SalvarProjetoDeEstimativaCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter){
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        this.projetoDeEstimativaModel = new ProjetoDeEstimativaModel();
    }

    @Override
    public void execute() {
        JTable tabela = projetoDeEstimativaPresenter.getView().getTable();
        
        int qtdLinhas = tabela.getRowCount();
        int qtdColunas = tabela.getColumnCount();
        
        for(int i = 0; i < qtdLinhas; i++){
            if(tabela.getValueAt(0, i).equals(true)){
                //ANOTAÇÕES PARA FAZER DEPOIS
                //CRIAR UMA FUNÇÃO NO REPOSITORY DE PROJETO DE ESTIMATIVA DE NOME insertByMap(mapa, nome,custosExtras como hardware e software)
                //ELIMINAR OS CAMPOS QUE PODEM SER FEITOS COM CALCULO DO BANCO DE DADOS
                //MODIFICAR A VIEW DE MANTER PROJETO PARA ACEITAR OS CUSTOS EXTRAS+
            }
        }
    }
    
}
