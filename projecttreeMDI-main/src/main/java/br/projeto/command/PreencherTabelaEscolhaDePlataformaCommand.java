/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PreencherTabelaEscolhaDePlataformaCommand implements Command{
    private final ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;

    public PreencherTabelaEscolhaDePlataformaCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
    }

    @Override
    public void execute() {
        JTable tabela = projetoDeEstimativaPresenter.getView().getTable();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Selecionar", "Plataforma", "Id"}, 0){
            @Override   
            public Class<?> getColumnClass(int columnIndex){
                return (columnIndex == 0) ? Boolean.class:String.class;//DEFINE A PRIMEIRA COLUNA COMO BOOLEAN, QUE NO CONTEXTO DE UM JTABLE É A MESMA COISA QUE UM CHECKBOX
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
               return column == 0; //SOMENTE A PRIMEIRA COLUNA É EDITAVEL
            }
        };
        
        //O PROXIMO PASSO É PERCORRER OS PERFIL E ADICIONÁ-LOS AO JTABLE DE ESCOLHA DE PLATAFORMAS
        //POSSIVELMENTE DEPOIS IRA MUDAR PARA UM FindByUser COMO EM OUTROS CONTEXTOS DA APLICAÇÃO
        
        List<PerfilProjetoDeEstimativaModel> perfilList = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findAll();
                
        for(PerfilProjetoDeEstimativaModel perfil: perfilList){
            String nomePlataforma = perfil.getNomePerfil();
            Integer idPlataforma = perfil.getId();
            modelo.addRow(new Object[]{false, nomePlataforma, idPlataforma});
        }
        
        tabela.setModel(modelo);
    }
    
}
