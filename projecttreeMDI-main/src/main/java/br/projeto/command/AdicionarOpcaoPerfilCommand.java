/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
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
        
        /*SCROLL ACOMPANHA A LINHA ADICIONADA*/
        /*JScrollPane scrollPane = (JScrollPane) tabela.getParent();
       // Rola até a nova linha
        Rectangle retangulo = tabela.getCellRect(novaLinha, 0, true);
        scrollPane.getViewport().scrollRectToVisible(retangulo);
        //tabela.setModel(modelo);*/
    
        //ver maneiras do scroll acompanhar
    }
    
    
    
}
