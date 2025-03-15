/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class AdicionarOpcaoPerfilCommand implements Command{
    private EscolhaFuncionalidadesPerfilPresenter perfilProjetoDeEstimativaPresenter;

    public AdicionarOpcaoPerfilCommand(EscolhaFuncionalidadesPerfilPresenter perfilProjetoDeEstimativaPresenter) {
        this.perfilProjetoDeEstimativaPresenter = perfilProjetoDeEstimativaPresenter;
    }
    
    @Override
    public void execute() {
        JTable tabela = perfilProjetoDeEstimativaPresenter.getView().getTable();
         
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        
        modelo.addRow(new Object[]{null, null});
        
        /*FOCA NA LINHA ADICIONADA*/
        int novaLinha = modelo.getRowCount() - 1; 
        tabela.setRowSelectionInterval(novaLinha, novaLinha);
    
        tabela.editCellAt(novaLinha, 0);
        
        
    
        /*SCROLL ACOMPANHA*/
        Rectangle rect = tabela.getCellRect(novaLinha, 0, true);  
        tabela.scrollRectToVisible(rect);
    }
    
    
    
}
