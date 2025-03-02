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
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class PreencherTabelaFuncionalidadesProjetoCommand implements Command{
    private ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private List<Integer> idPerfisSelecionados;
    
    public PreencherTabelaFuncionalidadesProjetoCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter, List<Integer> idPerfisSelecionados){
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        this.idPerfisSelecionados = idPerfisSelecionados;
    }

    @Override
    public void execute() {
        JTable tabela = projetoDeEstimativaPresenter.getView().getTable();
        
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Selecionar", "Funcionalidades", "Valores"},0){
            @Override
            public Class<?> getColumnClass(int columnIndex){
                return (columnIndex == 0) ? Boolean.class: String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
            return column == 0;
            }
        };      
            
            //ABAIXO: PERCORRER AS TABELAS RELACIONADAS AOS PERFIS ESCOLHIDOS E PREENCHER A TABELA
            List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList = new ArrayList<>();
            List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
            
            
            for(Integer idList: idPerfisSelecionados){
                //PEGA O ID DOS PERFIS SELECIONADOS E ADICIONA A LISTA DE PERFIS CRIADA ANTERIORMENTE
                PerfilProjetoDeEstimativaModel modelPerfil = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findById(idList);
                perfilProjetoDeEstimativaModelList.add(modelPerfil);
                
                //PEGA O ID DOS PERFIS SELECIONADOS, FAZ A BUSCA PELO ID DO PERFIL E ADICIONA A LISTA DE FUNCIONALIDADES PERSONALIZADAS(TOMAR CUIDADADE POIS ADICIONA TODAS AS FUNCIONALIDADES PERSONALIZADAS DE TODOS OS PERFIL NA MESMA LISTA)
                perfilFuncionalidadesPersonalizadasModelList.addAll(projetoDeEstimativaPresenter.getPerfilFuncionalidadesPersonalizadasRepository().findByPerfilProjetoEstimativa(modelPerfil));
                //perfilFuncionalidadesPersonalizadasModelList.add(modelPerfilPers);
            }
            
            for(PerfilProjetoDeEstimativaModel model: perfilProjetoDeEstimativaModelList){
                //ADICIONAR VERIFICAÇÃO PARA VER SE O CAMPO NAO É NULL(CASO FOIR QUER DIZER QUE FOI REMOVIDO DO PERFIL)
                //SE funcionalidadesDisponiveis.chave != null, pega o valor e chave para inserir na tabela
                for(Map.Entry<String, Integer> mapPerfil: model.getFuncionalidadesDisponiveis().entrySet()){
                    if(mapPerfil.getValue() != null){
                        String nomeFuncionalidade = mapPerfil.getKey();
                        Integer valorFuncionalidade = mapPerfil.getValue();
                        
                        modelo.addRow(new Object[]{false, nomeFuncionalidade, valorFuncionalidade});
                    }
                }
                
                for(PerfilFuncionalidadesPersonalizadasModel funcionalidadePersonalizada: perfilFuncionalidadesPersonalizadasModelList){
                    if(funcionalidadePersonalizada.getValor() != null){
                        String nomeFuncionalidade = funcionalidadePersonalizada.getNome();
                        Integer valorFuncionalidade = funcionalidadePersonalizada.getValor();
                    
                        modelo.addRow(new Object[]{false, nomeFuncionalidade, valorFuncionalidade});
                    }
                }
            }
            
           tabela.setModel(modelo); 
    }
}
