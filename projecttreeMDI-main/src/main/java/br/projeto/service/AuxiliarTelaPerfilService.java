/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author USER
 */
public class AuxiliarTelaPerfilService {
    
    private static AuxiliarTelaPerfilService instance;

    private AuxiliarTelaPerfilService() {}

    public static AuxiliarTelaPerfilService getInstance() {
        if (instance == null) {
            instance = new AuxiliarTelaPerfilService();
        }
        return instance;
    }

    
        public void encerrarEdicaoCelula(JTable tabela) {
           int linha = tabela.getSelectedRow();
           int coluna = tabela.getSelectedColumn();
          if (linha != -1) {
               TableCellEditor editor = tabela.getCellEditor(linha, coluna);
               editor.stopCellEditing();
           }
        }

        public boolean verificarValoresInconsistentes(JTable tabela) {
           int qtdLinhas = tabela.getRowCount();
           for (int i = 0; i < qtdLinhas; i++) {
               try {
                   Object valor = tabela.getValueAt(i, 1);
                   Integer.parseInt(valor.toString());
               } catch (NumberFormatException | NullPointerException e) {
                   JOptionPane.showMessageDialog(null, "O valor da linha " + i + " não é um número inteiro válido!", "Erro de tipo", JOptionPane.ERROR_MESSAGE);
                   return false;
              }
           }
           return true;
       }
        public Map<String, Integer> criarMapPerfil(JTable tabela) {
           Map<String, Integer> mapPerfil = new LinkedHashMap<>();
           int qtdLinhas = tabela.getRowCount();
           for (int i = 0; i < qtdLinhas; i++) {
               mapPerfil.put((String) tabela.getValueAt(i, 0), (Integer) tabela.getValueAt(i, 1));
           }
           return mapPerfil;
       }

        public Double obterTaxa(String taxaText) throws NumberFormatException {
           return taxaText.trim().isEmpty() ? 0.0 : Double.parseDouble(taxaText);
       }


}

