/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.service.RetornaPerfilModelService;
import br.projeto.service.AuxiliarTelaPerfilService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author USER
 */
public class SalvarPerfilProjetoDeEstimativaCommand implements Command{
    private final EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;
    private final AuxiliarTelaPerfilService auxiliarService;
    private final Integer idPerfil;
    
    Double taxaDiariaDesenvolvimento = null;
    Double taxaDiariaGerenciaProjeto = null;
    Double taxaDiariaDesign = null;

    public SalvarPerfilProjetoDeEstimativaCommand(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
        this.auxiliarService = AuxiliarTelaPerfilService.getInstance();
        
        this.idPerfil = idPerfil;
    }
    
        @Override
        public void execute() {
           JTable tabela = escolhaFuncionalidadesPerfilPresenter.getView().getTable();
           String nomePerfil = escolhaFuncionalidadesPerfilPresenter.getView().getTxtNomePerfil().getText();
           auxiliarService.encerrarEdicaoCelula(tabela);

           if (!auxiliarService.verificarValoresInconsistentes(tabela)) {
               return;
           }

           Map<String, Integer> mapPerfil = auxiliarService.criarMapPerfil(tabela);
           RetornaPerfilModelService retornaPerfilModelService = new RetornaPerfilModelService(mapPerfil);
           PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = retornaPerfilModelService.getPerfil();

        Double taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign;
            try {
                              
            if (auxiliarService.verificaPreenchimentoTaxaDev(escolhaFuncionalidadesPerfilPresenter) || auxiliarService.verificaPreenchimentoNome(nomePerfil) ||
                auxiliarService.verificaPreenchimentoTaxaGerProjetos(escolhaFuncionalidadesPerfilPresenter, perfilProjetoDeEstimativaModel)){
                    return;
                } else {
                    taxaDiariaDesenvolvimento = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesenvolvimento().getText());
                    taxaDiariaGerenciaProjeto = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaGerenciaProjeto().getText());
                    taxaDiariaDesign = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesign().getText());
                }
            } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Digite um número válido para as taxas diárias!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
               return;
            }

            setExtrasPerfil(perfilProjetoDeEstimativaModel, nomePerfil, taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign);
            
            if(idPerfil == null){
                escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().insert(perfilProjetoDeEstimativaModel);
            }else{
            /*LOGICA PARA UPDATE*/
                perfilProjetoDeEstimativaModel.setId(idPerfil);
                if(!escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().findByPerfilProjetoEstimativa(perfilProjetoDeEstimativaModel).isEmpty()){
                    escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().deleteByPerfilProjetoDeEstimativa(perfilProjetoDeEstimativaModel);
                }
                
                escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().update(perfilProjetoDeEstimativaModel);
            /*LOGICA PARA UPDATE*/
            }
            
            salvarFuncionalidadesPersonalizadas(retornaPerfilModelService);

            
            if(idPerfil == null){
                JOptionPane.showMessageDialog(null, "PERFIL CRIADO COM SUCESSO!!");
            }else{
                JOptionPane.showMessageDialog(null, "PERFIL ATUALIZADO COM SUCESSO!!");
            }
            escolhaFuncionalidadesPerfilPresenter.getView().dispose();
        }

        //Tudo o que não é funcionalidade
        private void setExtrasPerfil(PerfilProjetoDeEstimativaModel perfil, String nomePerfil, Double taxaDev, Double taxaGer, Double taxaDes) {
           perfil.setTaxaDiariaDesenvolvimento(taxaDev);
           perfil.setTaxaDiariaDesign(taxaGer);
           perfil.setTaxaDiariaGerenciaProjeto(taxaDes);
           perfil.setNomePerfil(nomePerfil);
           perfil.setUsuarioModel(escolhaFuncionalidadesPerfilPresenter.getUsuarioModel());
           perfil.setDataCriacao(new Date(System.currentTimeMillis()));
        }

        private void salvarFuncionalidadesPersonalizadas(RetornaPerfilModelService service) {
           List<PerfilFuncionalidadesPersonalizadasModel> lista = service.getFuncionalidadesPersonalizadas();
           if (lista != null) {
               for (PerfilFuncionalidadesPersonalizadasModel model : lista) {
                  escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(model);
               }
           }
        }
        
        
        
        
        
        
        
        
    /*@Override
    public void execute() {

        
        JTable tabela = escolhaFuncionalidadesPerfilPresenter.getView().getTable();
        Map<String, Integer> mapPerfil = new LinkedHashMap<>();
        String nomePerfil = escolhaFuncionalidadesPerfilPresenter.getView().getTxtNomePerfil().getText();
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
            String taxaDiariaDesenvolvimentoText = escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesenvolvimento().getText();
            String taxaDiariaGerenciaProjetoText = escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaGerenciaProjeto().getText();
            String taxaDiariaDesignText = escolhaFuncionalidadesPerfilPresenter.getView().getTxtTaxaDiariaDesign().getText();

            
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
        perfilProjetoDeEstimativaModel.setUsuarioModel(escolhaFuncionalidadesPerfilPresenter.getUsuarioModel());
        perfilProjetoDeEstimativaModel.setDataCriacao(new Date(System.currentTimeMillis()));
        
        
        escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().insert(perfilProjetoDeEstimativaModel);
        
        List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
        perfilFuncionalidadesPersonalizadasModelList = retornaPerfilModelService.getFuncionalidadesPersonalizadas();
        
        if(perfilFuncionalidadesPersonalizadasModelList != null){
        for(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel:perfilFuncionalidadesPersonalizadasModelList){
            
                escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(perfilFuncionalidadesPersonalizadasModel);
            }
        }
        
        JOptionPane.showMessageDialog(null, "PERFIL CRIADO COM SUCESSO!!");
        escolhaFuncionalidadesPerfilPresenter.getView().dispose();*/
    }
    
    
}
