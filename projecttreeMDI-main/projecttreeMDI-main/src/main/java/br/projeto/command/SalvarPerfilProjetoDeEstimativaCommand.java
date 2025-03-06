/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.PerfilProjetoDeEstimativaPresenter;
import br.projeto.service.RetornaPerfilModelService;
import br.projeto.service.VerificacoesTelaPerfilService;
import java.sql.Date;
import java.util.ArrayList;
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
public class SalvarPerfilProjetoDeEstimativaCommand implements Command{
    private final PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter;
    private final VerificacoesTelaPerfilService verificacoesService;
    Double taxaDiariaDesenvolvimento = null;
    Double taxaDiariaGerenciaProjeto = null;
    Double taxaDiariaDesign = null;

    public SalvarPerfilProjetoDeEstimativaCommand(PerfilProjetoDeEstimativaPresenter perfilProjetoDeEstimativaPresenter) {
        this.perfilProjetoDeEstimativaPresenter = perfilProjetoDeEstimativaPresenter;
        this.verificacoesService = VerificacoesTelaPerfilService.getInstance();
    }

    
    
        @Override
        public void execute() {
           JTable tabela = perfilProjetoDeEstimativaPresenter.getView().getTable();
           String nomePerfil = perfilProjetoDeEstimativaPresenter.getView().getTxtNomePerfil().getText();
           encerrarEdicaoCelula(tabela);

           if (!verificarValoresInconsistentes(tabela)) {
               return;
           }

           Map<String, Integer> mapPerfil = criarMapPerfil(tabela);
          RetornaPerfilModelService retornaPerfilModelService = new RetornaPerfilModelService(mapPerfil);
           PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = retornaPerfilModelService.getPerfil();

          Double taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign;
           try {
               taxaDiariaDesenvolvimento = obterTaxa(perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaDesenvolvimento().getText());
               taxaDiariaGerenciaProjeto = obterTaxa(perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaGerenciaProjeto().getText());
               taxaDiariaDesign = obterTaxa(perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaDesign().getText());
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Digite um número válido para as taxas diárias!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
               return;
          }

           salvarPerfil(perfilProjetoDeEstimativaModel, nomePerfil, taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign);
           salvarFuncionalidadesPersonalizadas(retornaPerfilModelService);

           JOptionPane.showMessageDialog(null, "PERFIL CRIADO COM SUCESSO!!");
           perfilProjetoDeEstimativaPresenter.getView().dispose();
        }

        private void encerrarEdicaoCelula(JTable tabela) {
           int linha = tabela.getSelectedRow();
           int coluna = tabela.getSelectedColumn();
          if (linha != -1) {
               TableCellEditor editor = tabela.getCellEditor(linha, coluna);
               editor.stopCellEditing();
           }
        }

       private boolean verificarValoresInconsistentes(JTable tabela) {
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
        private Map<String, Integer> criarMapPerfil(JTable tabela) {
           Map<String, Integer> mapPerfil = new LinkedHashMap<>();
           int qtdLinhas = tabela.getRowCount();
           for (int i = 0; i < qtdLinhas; i++) {
               mapPerfil.put((String) tabela.getValueAt(i, 0), (Integer) tabela.getValueAt(i, 1));
           }
           return mapPerfil;
       }

        private Double obterTaxa(String taxaText) throws NumberFormatException {
           return taxaText.trim().isEmpty() ? 0.0 : Double.parseDouble(taxaText);
       }

        private void salvarPerfil(PerfilProjetoDeEstimativaModel perfil, String nomePerfil, Double taxaDev, Double taxaGer, Double taxaDes) {
           perfil.setTaxaDiariaDesenvolvimento(taxaDev);
           perfil.setTaxaDiariaDesign(taxaGer);
           perfil.setTaxaDiariaGerenciaProjeto(taxaDes);
           perfil.setNomePerfil(nomePerfil);
           perfil.setUsuarioModel(perfilProjetoDeEstimativaPresenter.getUsuarioModel());
           perfil.setDataCriacao(new Date(System.currentTimeMillis()));
           perfilProjetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().insert(perfil);
       }

        private void salvarFuncionalidadesPersonalizadas(RetornaPerfilModelService service) {
           List<PerfilFuncionalidadesPersonalizadasModel> lista = service.getFuncionalidadesPersonalizadas();
           if (lista != null) {
               for (PerfilFuncionalidadesPersonalizadasModel model : lista) {
                  perfilProjetoDeEstimativaPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(model);
               }
           }
      }
        
    /*@Override
    public void execute() {

        
        JTable tabela = perfilProjetoDeEstimativaPresenter.getView().getTable();
        Map<String, Integer> mapPerfil = new LinkedHashMap<>();
        String nomePerfil = perfilProjetoDeEstimativaPresenter.getView().getTxtNomePerfil().getText();
        Double taxaDiariaDesenvolvimento = null;
        Double taxaDiariaGerenciaProjeto = null;
        Double taxaDiariaDesign = null;
        
        //PARA A EDIÇÃO DE CELULA
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        
        if(linha != -1){
            TableCellEditor editor = tabela.getCellEditor(linha, coluna);
            editor.stopCellEditing();
        }
        

        int qtdLinhas = tabela.getRowCount();
        //VERIFICAR VALORES INCONSISTENTES(ENCONTRAR FORMA DE VERIFICAR SE É SOMENTE INTEGER)
        for (int i = 0; i < qtdLinhas; i++) {
            try {
                Object valor = tabela.getValueAt(i, 1);
                
                int valorInteiro = Integer.parseInt(valor.toString());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O valor '" + tabela.getValueAt(i, 1) + "' não é um número inteiro válido!", "Erro de tipo", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "O valor da linha " + i + " não pode ser lido pela celula, pois a mesma só aceita inteiros.", "Erro de tipo", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        
        for(int i = 0; i < qtdLinhas; i++){
                mapPerfil.put((String)tabela.getValueAt(i, 0), (Integer)tabela.getValueAt(i, 1));
        }
        
        
        RetornaPerfilModelService retornaPerfilModelService = new RetornaPerfilModelService(mapPerfil);
        
        PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = retornaPerfilModelService.getPerfil();
        
        try {
            String taxaDiariaDesenvolvimentoText = perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaDesenvolvimento().getText();
            String taxaDiariaGerenciaProjetoText = perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaGerenciaProjeto().getText();
            String taxaDiariaDesignText = perfilProjetoDeEstimativaPresenter.getView().getTxtTaxaDiariaDesign().getText();

            
        if (taxaDiariaDesenvolvimentoText.trim().isEmpty() ||
                (taxaDiariaGerenciaProjetoText.trim().isEmpty() && (perfilProjetoDeEstimativaModel.getGerenteDeProjetos() != null && perfilProjetoDeEstimativaModel.getGerenteDeProjetos()!=0)))
                /*taxaDiariaDesignText.trim().isEmpty() || nomePerfil.trim().isEmpty())*/ {
                /*JOptionPane.showMessageDialog(null, "As taxas diárias/nome do perfil não podem ser vazios!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                taxaDiariaDesenvolvimento = Double.parseDouble(taxaDiariaDesenvolvimentoText);
                taxaDiariaGerenciaProjeto = taxaDiariaGerenciaProjetoText.trim().isEmpty()?0.0:Double.parseDouble(taxaDiariaGerenciaProjetoText);
                taxaDiariaDesign = taxaDiariaDesignText.trim().isEmpty()?0.0:Double.parseDouble(taxaDiariaDesignText);
            }//NAO ENTRA NO CATCH VERIFICAR
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido para as taxas diárias!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesenvolvimento(taxaDiariaDesenvolvimento);
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesign(taxaDiariaGerenciaProjeto);
        perfilProjetoDeEstimativaModel.setTaxaDiariaGerenciaProjeto(taxaDiariaDesign);
        perfilProjetoDeEstimativaModel.setNomePerfil(nomePerfil);
        perfilProjetoDeEstimativaModel.setUsuarioModel(perfilProjetoDeEstimativaPresenter.getUsuarioModel());
        perfilProjetoDeEstimativaModel.setDataCriacao(new Date(System.currentTimeMillis()));
        
        
        perfilProjetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().insert(perfilProjetoDeEstimativaModel);
        
        List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
        perfilFuncionalidadesPersonalizadasModelList = retornaPerfilModelService.getFuncionalidadesPersonalizadas();
        
        if(perfilFuncionalidadesPersonalizadasModelList != null){
        for(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel:perfilFuncionalidadesPersonalizadasModelList){
            
                perfilProjetoDeEstimativaPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(perfilFuncionalidadesPersonalizadasModel);
            }
        }
        
        JOptionPane.showMessageDialog(null, "PERFIL CRIADO COM SUCESSO!!");
        perfilProjetoDeEstimativaPresenter.getView().dispose();*/
    }
    
    
}
