/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class AdicionaListenerCheckBoxProjeto {
    private static AdicionaListenerCheckBoxProjeto adicionaListenerCheckBoxProjeto = null;
    
    private AdicionaListenerCheckBoxProjeto(){}
    
    public static AdicionaListenerCheckBoxProjeto getInstance(){
        if(adicionaListenerCheckBoxProjeto == null){
           adicionaListenerCheckBoxProjeto = new AdicionaListenerCheckBoxProjeto();
        }
        return adicionaListenerCheckBoxProjeto;
    }
    
        public void adicionaListenerTamanhoApp(JTable tabela, DefaultTableModel modelo){
            final boolean[] isUpdating = {false};
    
        tabela.getModel().addTableModelListener(e -> {
        if (isUpdating[0]) return; 
        isUpdating[0] = true; 

        int colunaSelecionada = 0; 
        int colunaNome = 1; 

        if (e.getColumn() == colunaSelecionada) {
            int linhaAlterada = e.getFirstRow();
            Boolean selecionado = (Boolean) tabela.getValueAt(linhaAlterada, colunaSelecionada);
            String nomeFuncionalidade = (String) tabela.getValueAt(linhaAlterada, colunaNome);

            if (selecionado && (nomeFuncionalidade.equals("Pequeno") || 
                                nomeFuncionalidade.equals("Médio") || 
                                nomeFuncionalidade.equals("Grande"))) {
                
                
                for (int i = 0; i < tabela.getRowCount(); i++) {
                    if (i != linhaAlterada) {
                        String outraFuncionalidade = (String) tabela.getValueAt(i, colunaNome);
                        if (outraFuncionalidade.equals("Pequeno") || 
                            outraFuncionalidade.equals("Médio") || 
                            outraFuncionalidade.equals("Grande")) {
                            modelo.setValueAt(false, i, colunaSelecionada);
                        }
                    }
                }
            }
        }
        
        isUpdating[0] = false; 
    });

    }
   
    public void adicionaListenerModeloApp(JTable tabela, DefaultTableModel modelo){
        final boolean[] isUpdating = {false};

        tabela.getModel().addTableModelListener(e -> {
            if (isUpdating[0]) return; 
            isUpdating[0] = true; 

            int colunaSelecionada = 0; 
            int colunaNome = 1; 

            if (e.getColumn() == colunaSelecionada) {
                int linhaAlterada = e.getFirstRow();
                Boolean selecionado = (Boolean) tabela.getValueAt(linhaAlterada, colunaSelecionada);
                String nomeFuncionalidade = (String) tabela.getValueAt(linhaAlterada, colunaNome);

                if (selecionado && (nomeFuncionalidade.equals("MVP") || 
                                    nomeFuncionalidade.equals("Básico") || 
                                    nomeFuncionalidade.equals("Profissional"))) {

                    
                    for (int i = 0; i < tabela.getRowCount(); i++) {
                        if (i != linhaAlterada) {
                            String outraFuncionalidade = (String) tabela.getValueAt(i, colunaNome);
                            if (outraFuncionalidade.equals("MVP") || 
                                outraFuncionalidade.equals("Básico") || 
                                outraFuncionalidade.equals("Profissional")) {
                                modelo.setValueAt(false, i, colunaSelecionada);
                            }
                        }
                    }
                }
            }

            isUpdating[0] = false; 
        });
    }
}
