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
                if (isUpdating[0]) return; // Evita loop infinito
                isUpdating[0] = true; // Marca que a atualização está ocorrendo

                //int colunaSelecionado = 0;
                //int colunaNome = 1;

                if (e.getColumn() == 0) {
                    for (int i = 0; i < tabela.getRowCount(); i++) {
                        Boolean selecionado = (Boolean) tabela.getValueAt(i, 0);
                        String nomeFuncionalidade = (String) tabela.getValueAt(i, 1);

                        if (selecionado && (nomeFuncionalidade.equals("Pequeno") || nomeFuncionalidade.equals("Médio") || nomeFuncionalidade.equals("Grande"))) {
                            for (int j = 0; j < tabela.getRowCount(); j++) {
                                if (j != i) {
                                    String outraFuncionalidade = (String) tabela.getValueAt(j, 1);
                                    if (outraFuncionalidade.equals("Pequeno") || outraFuncionalidade.equals("Médio") || outraFuncionalidade.equals("Grande")) {
                                        modelo.setValueAt(false, j, 0);
                                    }
                                }
                            }
                        }
                    }
                }
                isUpdating[0] = false; // Libera a atualização
            });    
    }
   
    public void adicionaListenerModeloApp(JTable tabela, DefaultTableModel modelo){
        final boolean[] isUpdating = {false};
            tabela.getModel().addTableModelListener(e -> {
                if (isUpdating[0]) return; // Evita loop infinito
                isUpdating[0] = true; // Marca que a atualização está ocorrendo

                //int colunaSelecionado = 0;
                //int colunaNome = 1;

                if (e.getColumn() == 0) {
                    for (int i = 0; i < tabela.getRowCount(); i++) {
                        Boolean selecionado = (Boolean) tabela.getValueAt(i, 0);
                        String nomeFuncionalidade = (String) tabela.getValueAt(i, 1);

                        if (selecionado && (nomeFuncionalidade.equals("MVP") || nomeFuncionalidade.equals("Básico") || nomeFuncionalidade.equals("Profissional"))) {
                            for (int j = 0; j < tabela.getRowCount(); j++) {
                                if (j != i) {
                                    String outraFuncionalidade = (String) tabela.getValueAt(j, 1);
                                    if (outraFuncionalidade.equals("MVP") || outraFuncionalidade.equals("Básico") || outraFuncionalidade.equals("Profissional")) {
                                        modelo.setValueAt(false, j, 0);
                                    }
                                }
                            }
                        }
                    }
                }
                isUpdating[0] = false; // Libera a atualização
            });
    }
}
