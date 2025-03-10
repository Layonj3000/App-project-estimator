/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PreencherTabelaFuncionalidadesPerfilParaUpdateCommand implements Command{
    
    private EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;
    private Integer idPerfil;

    public PreencherTabelaFuncionalidadesPerfilParaUpdateCommand(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
        this.idPerfil = idPerfil;
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
        
        PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel;
        
        perfilProjetoDeEstimativaModel = escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().findById(idPerfil);
        Map<String, Integer> mapPerfil = perfilProjetoDeEstimativaModel.getFuncionalidadesDisponiveis();
        
        for(Map.Entry<String, Integer> entrySet:mapPerfil.entrySet()){
            String nomeFuncionalidade = entrySet.getKey();
            Integer valorFuncionalidade = entrySet.getValue();
            
            if(valorFuncionalidade!=null && valorFuncionalidade!=0){
                modelo.addRow(new Object[]{nomeFuncionalidade, valorFuncionalidade});
            }
        }
        
        //LOGICA PARA FUNCIONALIDADES PERSONALIZADAS
        List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList;
        
        perfilFuncionalidadesPersonalizadasModelList = escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().findByPerfilProjetoEstimativa(perfilProjetoDeEstimativaModel);
        
        for(PerfilFuncionalidadesPersonalizadasModel funcionalidadePersonalizada: perfilFuncionalidadesPersonalizadasModelList){
            String nomeFuncionalidade = funcionalidadePersonalizada.getNome();
            Integer valorFuncionalidade = funcionalidadePersonalizada.getValor();
            
            if(valorFuncionalidade!=null && valorFuncionalidade!=0){
                modelo.addRow(new Object[]{nomeFuncionalidade, valorFuncionalidade});
            }
            
        }
        
        //INFORMAÇÕES DOS TEXT FIELDS
        escolhaFuncionalidadesPerfilPresenter.getView().getTxtNomePerfil().setText(perfilProjetoDeEstimativaModel.getNomePerfil());
        escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesenvolvimento().setText(String.valueOf(perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento()));
        escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesign().setText(String.valueOf(perfilProjetoDeEstimativaModel.getTaxaDiariaDesign()));
        escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaGerenciaProjeto().setText(String.valueOf(perfilProjetoDeEstimativaModel.getTaxaDiariaGerenciaProjeto()));
        
        
        tabela.setModel(modelo);
    }
    
}
