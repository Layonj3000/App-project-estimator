/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.service.MapValoresPadraoPerfilService;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand implements Command{
    
    private EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;

    public PreencherTabelaFuncionalidadesPerfilParaInsercaoCommand(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
    }

    @Override
    public void execute() {
        JTable tabela = escolhaFuncionalidadesPerfilPresenter.getView().getTable();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Funcionalidades", "Dias"},0){
            
            @Override
            public boolean isCellEditable(int row, int column){
            return column == 0 || column == 1;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex){
                if (columnIndex == 1) {
                    return Integer.class; 
                }
              return String.class;  
            }
                 
        };
        
       PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel();
        
        Map<String, Integer> mapPerfilBase = MapValoresPadraoPerfilService.getInstance().getMap();
        
        
        
        for(Map.Entry<String, Integer> entrySet: mapPerfilBase.entrySet()){
            String nomeFuncionalidade = entrySet.getKey();
            Integer valorFuncionalidade = entrySet.getValue();
        
            modelo.addRow(new Object[]{nomeFuncionalidade, valorFuncionalidade});
        }
        
        tabela.setModel(modelo);
    }
    
    
    
}
