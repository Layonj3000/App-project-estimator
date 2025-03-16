/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaPerfilPresenter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class PreencherTabelaEscolhaDePlataformaParaInsercaoCommand implements Command{
    private final EscolhaPerfilPresenter escolhaPerfilPresenter;

    public PreencherTabelaEscolhaDePlataformaParaInsercaoCommand(EscolhaPerfilPresenter escolhaPerfilPresenter) {
        this.escolhaPerfilPresenter = escolhaPerfilPresenter;
    }

    @Override
    public void execute() {
        JTable tabela = escolhaPerfilPresenter.getTable();
        
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
        
        List<PerfilProjetoDeEstimativaModel> perfilList = escolhaPerfilPresenter.getPerfilProjetoDeEstimativaRepository().findByUser(escolhaPerfilPresenter.getUsuarioModel());
                
        for(PerfilProjetoDeEstimativaModel perfil: perfilList){
            String nomePlataforma = perfil.getNomePerfil();
            Integer idPlataforma = perfil.getId();
            modelo.addRow(new Object[]{false, nomePlataforma, idPlataforma});
        }
        
        tabela.setModel(modelo);
    }
    
}
