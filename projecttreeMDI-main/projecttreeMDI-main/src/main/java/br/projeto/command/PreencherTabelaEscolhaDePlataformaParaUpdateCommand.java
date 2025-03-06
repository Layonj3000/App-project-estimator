/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PreencherTabelaEscolhaDePlataformaParaUpdateCommand implements Command{
    private final ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private final Integer projetoId;
    
    
    public PreencherTabelaEscolhaDePlataformaParaUpdateCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter, Integer projetoId) {
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        this.projetoId = projetoId;
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

            List<PerfilProjetoDeEstimativaModel> perfilUsuarioList = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findByUser(projetoDeEstimativaPresenter.getUsuarioModel());

            List<PerfilProjetoIntermediariaModel> perfilProjetoList = projetoDeEstimativaPresenter.getPerfilProjetoIntermediariaRepository().findByProjeto(projetoId);

            for (PerfilProjetoDeEstimativaModel perfil : perfilUsuarioList) {
                String nomePlataforma = perfil.getNomePerfil();
                Integer idPlataforma = perfil.getId();

                boolean encontrado = false; 
                for (PerfilProjetoIntermediariaModel perfilProjetoIntermediariaModel : perfilProjetoList) {
                    if (idPlataforma.equals(perfilProjetoIntermediariaModel.getIdPerfilProjetoDeEstimativaModel())) {
                        modelo.addRow(new Object[]{true, nomePlataforma, idPlataforma});
                        encontrado = true;
                        break; 
                    }
                }

                if (!encontrado) {
                    modelo.addRow(new Object[]{false, nomePlataforma, idPlataforma});
                }
            }
    
//        List<PerfilProjetoDeEstimativaModel> perfilUsuarioList = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findByUser(projetoDeEstimativaPresenter.getUsuarioModel());
//
//        List<PerfilProjetoIntermediariaModel> perfilProjetoList = projetoDeEstimativaPresenter.getPerfilProjetoIntermediariaRepository().findByProjeto(projetoId);
//
//        // Criar um Set com os IDs que existem na tabela de relacionamento (para evitar duplicação)
//        Set<Integer> idsRelacionados = perfilProjetoList.stream()
//                .map(PerfilProjetoIntermediariaModel::getIdPerfilProjetoDeEstimativaModel)
//                .collect(Collectors.toSet());
//
//        for (PerfilProjetoDeEstimativaModel perfil : perfilUsuarioList) {
//            String nomePlataforma = perfil.getNomePerfil();
//            Integer idPlataforma = perfil.getId();
//
//        // Se o IDPlataforma está no conjunto, adiciona com true; senão, com false
//        boolean estaRelacionada = idsRelacionados.contains(idPlataforma);
//        modelo.addRow(new Object[]{estaRelacionada, nomePlataforma, idPlataforma});

        tabela.setModel(modelo);
        
    }
    }
    

