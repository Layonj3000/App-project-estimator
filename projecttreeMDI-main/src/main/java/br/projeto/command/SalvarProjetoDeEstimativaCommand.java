/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import br.projeto.service.RetornaProjetoModelService;
import br.projeto.service.VerificacoesTelaProjetoService;
import br.projeto.view.ManterProjetoDeEstimativaView;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class SalvarProjetoDeEstimativaCommand implements Command{
    private final EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadesProjetoPresenter;
    private final List<Integer> idPerfisSelecionados;
    private final Integer projetoId;
    
    public SalvarProjetoDeEstimativaCommand(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadesProjetoPresenter, List<Integer> idPerfisSelecionados, Integer projetoId) {
        this.escolhaFuncionalidadesProjetoPresenter = escolhaFuncionalidadesProjetoPresenter;
        this.idPerfisSelecionados = idPerfisSelecionados;
        this.projetoId = projetoId;
    }
    
    
    
     @Override
    public void execute() {
        ManterProjetoDeEstimativaView view = escolhaFuncionalidadesProjetoPresenter.getView();
        JTable tabela = view.getTable();

        // Usando o Singleton para as verificações
        VerificacoesTelaProjetoService verificacoesService = VerificacoesTelaProjetoService.getInstancia();

        // Recuperando os dados da tela
        String nomeProjeto = view.getTxtNomeProjetoEstimativa().getText();
        String percentualComImpostosText = view.getTxtPercentualComImpostos().getText();
        String percentualDeLucroDesejadoText = view.getTxtPercentualLucroDesejado().getText();

        // Verificação de campos obrigatórios
        if (!verificacoesService.verificarCamposObrigatorios(nomeProjeto, percentualComImpostosText, percentualDeLucroDesejadoText)) {
            JOptionPane.showMessageDialog(null, "O percentual com imposto, de lucro desejado e o nome do projeto devem ser obrigatoriamente informados.", "Campos Obrigatórios!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação dos custos e percentuais
        String custoHardwareEInstalacoesFisicasText = view.getTxtCustoHardwareEInstalacoesFisicas().getText();
        String custoSoftwareText = view.getTxtCustoSoftware().getText();
        String custoRiscosText = view.getTxtCustoRiscos().getText();
        String custoGarantiaText = view.getTxtCustoGarantia().getText();
        String fundoDeReservaText = view.getTxtFundoReserva().getText();
        String outrosCustosText = view.getTxtOutrosCustos().getText();

        if (!verificacoesService.verificarCustosEPercentuais(custoHardwareEInstalacoesFisicasText, custoSoftwareText,
                                                             custoRiscosText, custoGarantiaText, fundoDeReservaText,
                                                             outrosCustosText, percentualComImpostosText, percentualDeLucroDesejadoText)) {
            JOptionPane.showMessageDialog(null, "Digite um número válido para os custos e percentuais!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação dos projetos selecionados na tabela
        Map<String, Integer> mapProjetos = verificacoesService.verificarProjetosSelecionados(tabela);

        // Atribuindo valores aos campos
        Double custoHardwareEInstalacoesFisicas = custoHardwareEInstalacoesFisicasText.isEmpty() ? 0.0 : Double.parseDouble(custoHardwareEInstalacoesFisicasText);
        Double custoSoftware = custoSoftwareText.isEmpty() ? 0.0 : Double.parseDouble(custoSoftwareText);
        Double custoRiscos = custoRiscosText.isEmpty() ? 0.0 : Double.parseDouble(custoRiscosText);
        Double custoGarantia = custoGarantiaText.isEmpty() ? 0.0 : Double.parseDouble(custoGarantiaText);
        Double fundoDeReserva = fundoDeReservaText.isEmpty() ? 0.0 : Double.parseDouble(fundoDeReservaText);
        Double outrosCustos = outrosCustosText.isEmpty() ? 0.0 : Double.parseDouble(outrosCustosText);
        Double percentualComImpostos = percentualComImpostosText.isEmpty() ? 0.0 : Double.parseDouble(percentualComImpostosText);
        Double percentualDeLucroDesejado = percentualDeLucroDesejadoText.isEmpty() ? 0.0 : Double.parseDouble(percentualDeLucroDesejadoText);

        // Criando o modelo do projeto
        RetornaProjetoModelService retornaProjetoModelService = new RetornaProjetoModelService(mapProjetos);
        ProjetoDeEstimativaModel projetoDeEstimativaModel = retornaProjetoModelService.getProjeto();

        projetoDeEstimativaModel.setNomeProjetoDeEstimativa(nomeProjeto);
        projetoDeEstimativaModel.setCustoHardware(custoHardwareEInstalacoesFisicas);
        projetoDeEstimativaModel.setCustoSoftware(custoSoftware);
        projetoDeEstimativaModel.setCustoRiscos(custoRiscos);
        projetoDeEstimativaModel.setCustoGarantia(custoGarantia);
        projetoDeEstimativaModel.setFundoDeReserva(fundoDeReserva);
        projetoDeEstimativaModel.setOutrosCustos(outrosCustos);
        projetoDeEstimativaModel.setPercentualComImpostos(percentualComImpostos);
        projetoDeEstimativaModel.setPercentualLucroDesejado(percentualDeLucroDesejado);
        projetoDeEstimativaModel.setUsuarioModel(escolhaFuncionalidadesProjetoPresenter.getUsuarioModel());
        projetoDeEstimativaModel.setDataCriacao(new Date(System.currentTimeMillis()));
        
        /*LOGICA PARA UPDATE*/
        if(projetoId != null){
            projetoDeEstimativaModel.setId(projetoId);
        
            escolhaFuncionalidadesProjetoPresenter.getProjetoDeEstimativaRepository().update(projetoDeEstimativaModel);
            
            if(!escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoIntermediariaRepository().findByProjeto(projetoId).isEmpty()){
                escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoIntermediariaRepository().deleteByProjeto(projetoId);
            }
            
            if(!escolhaFuncionalidadesProjetoPresenter.getProjetoFuncionalidadesPersonalizadasRepository().findByProjetoEstimativa(projetoDeEstimativaModel).isEmpty()){
                escolhaFuncionalidadesProjetoPresenter.getProjetoFuncionalidadesPersonalizadasRepository().deleteByProjetoDeEstimativa(projetoDeEstimativaModel);
            }
        }else{
            escolhaFuncionalidadesProjetoPresenter.getProjetoDeEstimativaRepository().insert(projetoDeEstimativaModel);
        }
        /*LOGICA PARA UPDATE*/

        // Inserindo relacao com os perfis selecionados
        for (Integer idPerfil : idPerfisSelecionados) {
            PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoDeEstimativaRepository().findById(idPerfil);
            escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoIntermediariaRepository().insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
        }

        // Inserindo as funcionalidades personalizadas
        List<ProjetosFuncionalidadesPersonalizadasModel> funcionalidadesPersonalizadasList = retornaProjetoModelService.getFuncionalidadesPersonalizadas();
        if (funcionalidadesPersonalizadasList != null) {
            for (ProjetosFuncionalidadesPersonalizadasModel model : funcionalidadesPersonalizadasList) {
                escolhaFuncionalidadesProjetoPresenter.getProjetoFuncionalidadesPersonalizadasRepository().insert(model);
            }
        }



        // Exibindo a mensagem de sucesso
        if(projetoId == null){
            JOptionPane.showMessageDialog(null, "PROJETO CRIADO COM SUCESSO!!");
        }else{
            JOptionPane.showMessageDialog(null, "PROJETO ATUALIZADO COM SUCESSO!!");
        }
        escolhaFuncionalidadesProjetoPresenter.getView().dispose();
    }
    
    
/*     @Override
    public void execute() {
        ManterProjetoDeEstimativaView view = (ManterProjetoDeEstimativaView) projetoDeEstimativaPresenter.getView();
        JTable tabela = view.getTable();

        // Usando o Singleton para as verificações
        VerificacoesTelaProjetoService verificacoesService = VerificacoesTelaProjetoService.getInstancia();

        // Recuperando os dados da tela
        String nomeProjeto = view.getTxtNomeProjetoEstimativa().getText();
        String percentualComImpostosText = view.getTxtPercentualComImpostos().getText();
        String percentualDeLucroDesejadoText = view.getTxtPercentualLucroDesejado().getText();

        // Verificação de campos obrigatórios
        if (!verificacoesService.verificarCamposObrigatorios(nomeProjeto, percentualComImpostosText, percentualDeLucroDesejadoText)) {
            JOptionPane.showMessageDialog(null, "O percentual com imposto, de lucro desejado e o nome do projeto devem ser obrigatoriamente informados.", "Campos Obrigatórios!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação dos custos e percentuais
        String custoHardwareEInstalacoesFisicasText = view.getTxtCustoHardwareEInstalacoesFisicas().getText();
        String custoSoftwareText = view.getTxtCustoSoftware().getText();
        String custoRiscosText = view.getTxtCustoRiscos().getText();
        String custoGarantiaText = view.getTxtCustoGarantia().getText();
        String fundoDeReservaText = view.getTxtFundoReserva().getText();
        String outrosCustosText = view.getTxtOutrosCustos().getText();

        if (!verificacoesService.verificarCustosEPercentuais(custoHardwareEInstalacoesFisicasText, custoSoftwareText, 
                                                             custoRiscosText, custoGarantiaText, fundoDeReservaText, 
                                                             outrosCustosText, percentualComImpostosText, percentualDeLucroDesejadoText)) {
            JOptionPane.showMessageDialog(null, "Digite um número válido para os custos e percentuais!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação dos projetos selecionados na tabela
        Map<String, Integer> mapProjetos = verificacoesService.verificarProjetosSelecionados(tabela);

        // Atribuindo valores aos campos
        //String nomeProjeto = view.getTxtNomeProjetoEstimativa().getText();
        Double custoHardwareEInstalacoesFisicas = custoHardwareEInstalacoesFisicasText.isEmpty() ? 0.0 : Double.parseDouble(custoHardwareEInstalacoesFisicasText);
        Double custoSoftware = custoSoftwareText.isEmpty() ? 0.0 : Double.parseDouble(custoSoftwareText);
        Double custoRiscos = custoRiscosText.isEmpty() ? 0.0 : Double.parseDouble(custoRiscosText);
        Double custoGarantia = custoGarantiaText.isEmpty() ? 0.0 : Double.parseDouble(custoGarantiaText);
        Double fundoDeReserva = fundoDeReservaText.isEmpty() ? 0.0 : Double.parseDouble(fundoDeReservaText);
        Double outrosCustos = outrosCustosText.isEmpty() ? 0.0 : Double.parseDouble(outrosCustosText);
        Double percentualComImpostos = percentualComImpostosText.isEmpty() ? 0.0 : Double.parseDouble(percentualComImpostosText);
        Double percentualDeLucroDesejado = percentualDeLucroDesejadoText.isEmpty() ? 0.0 : Double.parseDouble(percentualDeLucroDesejadoText);

        // Criando o modelo do projeto
        RetornaProjetoModelService retornaProjetoModelService = new RetornaProjetoModelService(mapProjetos);
        ProjetoDeEstimativaModel projetoDeEstimativaModel = retornaProjetoModelService.getProjeto();

        projetoDeEstimativaModel.setNomeProjetoDeEstimativa(nomeProjeto);
        projetoDeEstimativaModel.setCustoHardware(custoHardwareEInstalacoesFisicas);
        projetoDeEstimativaModel.setCustoSoftware(custoSoftware);
        projetoDeEstimativaModel.setCustoRiscos(custoRiscos);
        projetoDeEstimativaModel.setCustoGarantia(custoGarantia);
        projetoDeEstimativaModel.setFundoDeReserva(fundoDeReserva);
        projetoDeEstimativaModel.setOutrosCustos(outrosCustos);
        projetoDeEstimativaModel.setCompartilhado(null);
        projetoDeEstimativaModel.setCompartilhadoPor(null);
        projetoDeEstimativaModel.setPercentualComImpostos(percentualComImpostos);
        projetoDeEstimativaModel.setPercentualLucroDesejado(percentualDeLucroDesejado);
        projetoDeEstimativaModel.setUsuarioModel(projetoDeEstimativaPresenter.getUsuarioModel());
        projetoDeEstimativaModel.setDataCriacao(new Date(System.currentTimeMillis()));
        projetoDeEstimativaModel.setStatus(0);

        // Inserindo o projeto no repositório
        projetoDeEstimativaPresenter.getProjetoDeEstimativaRepository().insert(projetoDeEstimativaModel);

        // Inserindo as funcionalidades personalizadas
        List<ProjetosFuncionalidadesPersonalizadasModel> funcionalidadesPersonalizadasList = retornaProjetoModelService.getFuncionalidadesPersonalizadas();
        if (funcionalidadesPersonalizadasList != null) {
            for (ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel : funcionalidadesPersonalizadasList) {
                projetoDeEstimativaPresenter.getProjetoFuncionalidadesPersonalizadasRepository().insert(projetosFuncionalidadesPersonalizadasModel);
            }
        }

        // Inserindo os perfis selecionados
        for (Integer idPerfil : idPerfisSelecionados) {
            PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findById(idPerfil);
            projetoDeEstimativaPresenter.getPerfilProjetoIntermediariaRepository().insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
        }

        // Exibindo a mensagem de sucesso
        JOptionPane.showMessageDialog(null, "PROJETO CRIADO COM SUCESSO!!");
        projetoDeEstimativaPresenter.getView().getFrame().dispose();
    }*/

 /*   @Override
    public void execute() {
        ManterProjetoDeEstimativaView view = (ManterProjetoDeEstimativaView)projetoDeEstimativaPresenter.getView();
        JTable tabela = view.getTable();
        
        Map<String, Integer> mapProjetos = new LinkedHashMap<>();
        String nomeProjeto = view.getTxtNomeProjetoEstimativa().getText();
        Double custoHardwareEInstalacoesFisicas = null;//Double.parseDouble(view.getTxtCustoHardwareEInstalacoesFisicas().getText());
        Double custoSoftware = null;
        Double custoRiscos = null;
        Double custoGarantia = null;
        Double fundoDeReserva = null;
        Double outrosCustos = null;
        Double percentualComImpostos = null;
        Double percentualDeLucroDesejado = null;
        
        
        int qtdLinhas = tabela.getRowCount();
        int qtdColunas = tabela.getColumnCount();
        
        for(int i = 0; i < qtdLinhas; i++){
            if((Boolean)tabela.getValueAt(i, 0)){
                //COLOCAR VERIFFICAÇÕES EM OUTROS METODOS
                Integer trueOrFalse=(Boolean) tabela.getValueAt(i, 0).equals(true)? 1 : 0;
                mapProjetos.put((String)tabela.getValueAt(i, 1), trueOrFalse);
            }
        }
        
        try{
            String custoHardwareEInstalacoesFisicasText = view.getTxtCustoHardwareEInstalacoesFisicas().getText();
            String custoSoftwareText = view.getTxtCustoSoftware().getText();
            String custoRiscosText = view.getTxtCustoRiscos().getText();
            String custoGarantiaText = view.getTxtCustoGarantia().getText();
            String fundoDeReservaText = view.getTxtFundoReserva().getText();
            String outrosCustosText = view.getTxtOutrosCustos().getText();
            String percentualComImpostosText = view.getTxtPercentualComImpostos().getText();
            String percentualDeLucroDesejadoText = view.getTxtPercentualLucroDesejado().getText();
            
            if(nomeProjeto.trim().isEmpty() || percentualComImpostosText.trim().isEmpty() || percentualDeLucroDesejadoText.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "O percentual com imposto, de lucro desejado e o nome do projeto devem ser obrigatoriamente informados.", "Campos Obrigatórios!", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                custoHardwareEInstalacoesFisicas = custoHardwareEInstalacoesFisicasText.isEmpty()?0.0:Double.parseDouble(custoHardwareEInstalacoesFisicasText);//Double.parseDouble(view.getTxtCustoHardwareEInstalacoesFisicas().getText());
                custoSoftware = custoSoftwareText.isEmpty()?0.0:Double.parseDouble(custoSoftwareText);
                custoRiscos = custoRiscosText.isEmpty()?0.0:Double.parseDouble(custoRiscosText);
                custoGarantia = custoGarantiaText.isEmpty()?0.0:Double.parseDouble(custoGarantiaText);
                fundoDeReserva = fundoDeReservaText.isEmpty()?0.0:Double.parseDouble(fundoDeReservaText);
                outrosCustos = outrosCustosText.isEmpty()?0.0:Double.parseDouble(outrosCustosText);
                percentualComImpostos = percentualComImpostosText.isEmpty()?0.0:Double.parseDouble(percentualComImpostosText);
                percentualDeLucroDesejado = percentualDeLucroDesejadoText.isEmpty()?0.0:Double.parseDouble(percentualDeLucroDesejadoText);
            }
        }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(null, "Digite um número válido para os custos e percentuais!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
            return;
       }
        
        RetornaProjetoModelService retornaProjetoModelService = new RetornaProjetoModelService(mapProjetos);
        ProjetoDeEstimativaModel projetoDeEstimativaModel  = retornaProjetoModelService.getProjeto();
        
        projetoDeEstimativaModel.setNomeProjetoDeEstimativa(nomeProjeto);
        projetoDeEstimativaModel.setCustoHardware(custoHardwareEInstalacoesFisicas);
        projetoDeEstimativaModel.setCustoSoftware(custoSoftware);
        projetoDeEstimativaModel.setCustoRiscos(custoRiscos);
        projetoDeEstimativaModel.setCustoGarantia(custoGarantia);
        projetoDeEstimativaModel.setFundoDeReserva(fundoDeReserva);
        projetoDeEstimativaModel.setOutrosCustos(outrosCustos);
        projetoDeEstimativaModel.setCompartilhado(null);
        projetoDeEstimativaModel.setCompartilhadoPor(null);
        projetoDeEstimativaModel.setPercentualComImpostos(percentualComImpostos);
        projetoDeEstimativaModel.setPercentualLucroDesejado(percentualDeLucroDesejado);
        projetoDeEstimativaModel.setUsuarioModel(projetoDeEstimativaPresenter.getUsuarioModel());
        projetoDeEstimativaModel.setDataCriacao(new Date(System.currentTimeMillis()));
        projetoDeEstimativaModel.setStatus(0);
        
        projetoDeEstimativaPresenter.getProjetoDeEstimativaRepository().insert(projetoDeEstimativaModel);
        
        List<ProjetosFuncionalidadesPersonalizadasModel> funcionalidadesPersonalizadasList = retornaProjetoModelService.getFuncionalidadesPersonalizadas();
        
        if(funcionalidadesPersonalizadasList != null){
            for(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel: funcionalidadesPersonalizadasList){
                
                    projetoDeEstimativaPresenter.getProjetoFuncionalidadesPersonalizadasRepository().insert(projetosFuncionalidadesPersonalizadasModel);
                
            }
        }
        
        for(Integer idPerfil: idPerfisSelecionados){
            PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = projetoDeEstimativaPresenter.getPerfilProjetoDeEstimativaRepository().findById(idPerfil);
            projetoDeEstimativaPresenter.getPerfilProjetoIntermediariaRepository().insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
        }
        
        JOptionPane.showMessageDialog(null, "PROJETO CRIADO COM SUCESSO!!");
        projetoDeEstimativaPresenter.getView().getFrame().dispose();
    }*/
}
   

