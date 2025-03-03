/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.PerfilProjetoDeEstimativaPresenter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author USER
 */
public class SalvarPerfilProjetoDeEstimativaCommand implements Command{
    private PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter;

    public SalvarPerfilProjetoDeEstimativaCommand(PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter) {
        this.perfilProjetoDeEstimativaPresenter = perfilProjetoDeEstimativaPresenter;
    }

    @Override
    public void execute() {
        JTable tabela = perfilProjetoDeEstimativaPresenter.getView().getTable();
        Map<String, Integer> mapPerfil = new LinkedHashMap<>();
        
        
        int qtdLinhas = tabela.getRowCount();
        //int qtdColunas = tabela.getColumnCount();
        
        //VERIFICAR VALORES INCONSISTENTES
        for(int i = 0; i < qtdLinhas; i++){
            try{
                Integer valor = (Integer) tabela.getValueAt(i, 2);
            }catch(ClassCastException e){
                 JOptionPane.showMessageDialog(null, "O valor de" + tabela.getValueAt(i, 1) + " não é um Double!", "Erro de tipo", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        for(int i = 0; i < qtdLinhas; i++){
                mapPerfil.put((String)tabela.getValueAt(i, 1), (Integer)tabela.getValueAt(i, 2));
        }
    
    }
    
    
}
