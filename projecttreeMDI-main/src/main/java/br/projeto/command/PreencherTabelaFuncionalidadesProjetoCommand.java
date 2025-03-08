/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.presenter.ProjetoDeEstimativaPresenter;
import br.projeto.service.AdicionaListenerCheckBoxProjeto;
import br.projeto.view.IProjetoDeEstimativaView;
import br.projeto.view.ManterProjetoDeEstimativaView;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class PreencherTabelaFuncionalidadesProjetoCommand implements Command{
    private ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;
    private List<Integer> idPerfisSelecionados;
    private Integer projetoId;
    
    public PreencherTabelaFuncionalidadesProjetoCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter, List<Integer> idPerfisSelecionados, Integer projetoId){
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
        this.idPerfisSelecionados = idPerfisSelecionados;
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {
        JTable tabela = projetoDeEstimativaPresenter.getView().getTable();
        ManterProjetoDeEstimativaView view = (ManterProjetoDeEstimativaView) projetoDeEstimativaPresenter.getView();
        
        
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
                
            }
            
            Map<String, Integer> funcionalidadesSomadasMap = new LinkedHashMap<>();
            for(PerfilProjetoDeEstimativaModel model: perfilProjetoDeEstimativaModelList){
                //ADICIONAR VERIFICAÇÃO PARA VER SE O CAMPO NAO É NULL(CASO FOIR QUER DIZER QUE FOI REMOVIDO DO PERFIL)
                //SE funcionalidadesDisponiveis.chave != null, pega o valor e chave para inserir na tabela
                for(Map.Entry<String, Integer> mapPerfil: model.getFuncionalidadesDisponiveis().entrySet()){
                    if(mapPerfil.getValue() != null && mapPerfil.getValue()!= 0){
                        String nomeFuncionalidade = mapPerfil.getKey();
                        Integer valorFuncionalidade = mapPerfil.getValue();
                        
                        criarMapaFucionalidadesSomadas(funcionalidadesSomadasMap, nomeFuncionalidade, valorFuncionalidade);
                    }
                }
                perfilFuncionalidadesPersonalizadasModelList.addAll(projetoDeEstimativaPresenter.getPerfilFuncionalidadesPersonalizadasRepository().findByPerfilProjetoEstimativa(model));
                 
                for(PerfilFuncionalidadesPersonalizadasModel funcionalidadePersonalizada: perfilFuncionalidadesPersonalizadasModelList){
                    
                    if(funcionalidadePersonalizada.getValor() != null && funcionalidadePersonalizada.getValor() != 0){
                        String nomeFuncionalidade = funcionalidadePersonalizada.getNome();
                        Integer valorFuncionalidade = funcionalidadePersonalizada.getValor();
                        
                        criarMapaFucionalidadesSomadas(funcionalidadesSomadasMap, nomeFuncionalidade, valorFuncionalidade);
                    }
                }
                perfilFuncionalidadesPersonalizadasModelList.clear();
            }
            
            /*ALTERAÇÃO PARA SUPORTAR UPDATE*/
            
            
            /*if(projetoId!=null){
                ProjetoDeEstimativaModel projetoDeEstimativaModel = projetoDeEstimativaPresenter.getProjetoDeEstimativaRepository().findById(projetoId);
                //List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModel = projetoDeEstimativaPresenter.getProjetoFuncionalidadesPersonalizadasRepository().findByProjetoEstimativa(projetoDeEstimativaModel);
                
                for(Map.Entry<String, Integer> entrySet: funcionalidadesSomadasMap.entrySet()){//ANTES
                        //modelo.addRow(new Object[]{false, entrySet.getKey(), entrySet.getValue()});//ANTES
                }//ANTES*/
            if (projetoId != null) {
                ProjetoDeEstimativaModel projetoDeEstimativaModel = projetoDeEstimativaPresenter
                    .getProjetoDeEstimativaRepository().findById(projetoId);

                
                List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModel = 
                    projetoDeEstimativaPresenter.getProjetoFuncionalidadesPersonalizadasRepository()
                    .findByProjetoEstimativa(projetoDeEstimativaModel);
                
                preencherTxtFields(view, projetoDeEstimativaModel);

                for (Map.Entry<String, Integer> entrySet : funcionalidadesSomadasMap.entrySet()) {

                    boolean selecionado = false;

                    if (projetoDeEstimativaModel.getFuncionalidadesDisponiveis() != null &&
                        projetoDeEstimativaModel.getFuncionalidadesDisponiveis().get(entrySet.getKey()) != null &&
                        projetoDeEstimativaModel.getFuncionalidadesDisponiveis().get(entrySet.getKey()).equals(SimNao.SIM)) {
                        selecionado = true;
                    }

                    
                    for (ProjetosFuncionalidadesPersonalizadasModel personalizada : projetosFuncionalidadesPersonalizadasModel) {
                        if (personalizada.getNome().equals(entrySet.getKey())) {
                            selecionado = true; 
                            break;
                        }
                    }

                   
                    modelo.addRow(new Object[]{selecionado, entrySet.getKey(), entrySet.getValue()});
                }
            }else{
                for(Map.Entry<String, Integer> entrySet: funcionalidadesSomadasMap.entrySet()){//ANTES
                        modelo.addRow(new Object[]{false, entrySet.getKey(), entrySet.getValue()});//ANTES
                }
            }

                
            /*ALTERAÇÃO PARA SUPORTAR UPDATE*/    
            
            tabela.setModel(modelo); 
            
            
            AdicionaListenerCheckBoxProjeto adicionaListenerCheckBoxProjeto = AdicionaListenerCheckBoxProjeto.getInstance();
            
            adicionaListenerCheckBoxProjeto.adicionaListenerTamanhoApp(tabela, modelo);
            adicionaListenerCheckBoxProjeto.adicionaListenerModeloApp(tabela,modelo); 
            
            if(projetoId!=null){
                
            }
    }
    
    private void criarMapaFucionalidadesSomadas(Map<String, Integer> funcionalidadesSomadasMap, String nomeFuncionalidade, Integer valorFuncionalidade){
                    if(funcionalidadesSomadasMap.containsKey(nomeFuncionalidade)){
                        Integer valorAtual = funcionalidadesSomadasMap.get(nomeFuncionalidade);
                        funcionalidadesSomadasMap.put(nomeFuncionalidade, valorAtual + valorFuncionalidade);
                    }else{
                        funcionalidadesSomadasMap.put(nomeFuncionalidade, valorFuncionalidade);
                    }                
    }
    
    private void adicionaListenerTamanhoApp(JTable tabela, DefaultTableModel modelo){
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
   
    private void adicionaListenerModeloApp(JTable tabela, DefaultTableModel modelo){
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

    private void preencherTxtFields(ManterProjetoDeEstimativaView view, ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        view.getTxtNomeProjetoEstimativa().setText(projetoDeEstimativaModel.getNomeProjetoDeEstimativa());
        view.getTxtCustoGarantia().setText(String.valueOf(projetoDeEstimativaModel.getCustoGarantia()));
        view.getTxtCustoHardwareEInstalacoesFisicas().setText(String.valueOf(projetoDeEstimativaModel.getCustoHardware()));
        view.getTxtCustoRiscos().setText(String.valueOf(projetoDeEstimativaModel.getCustoRiscos()));
        view.getTxtCustoSoftware().setText(String.valueOf(projetoDeEstimativaModel.getCustoSoftware()));
        view.getTxtFundoReserva().setText(String.valueOf(projetoDeEstimativaModel.getFundoDeReserva()));
        view.getTxtOutrosCustos().setText(String.valueOf(projetoDeEstimativaModel.getOutrosCustos()));
        view.getTxtPercentualComImpostos().setText(String.valueOf(projetoDeEstimativaModel.getPercentualLucroDesejado()));
        view.getTxtPercentualLucroDesejado().setText(String.valueOf(projetoDeEstimativaModel.getPercentualLucroDesejado()));
    }
    
   

    
}
