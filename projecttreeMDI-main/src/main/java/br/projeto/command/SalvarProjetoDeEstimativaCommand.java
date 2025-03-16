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
    
    VerificacoesTelaProjetoService verificacoesService;
    
    public SalvarProjetoDeEstimativaCommand(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadesProjetoPresenter, List<Integer> idPerfisSelecionados, Integer projetoId) {
        this.escolhaFuncionalidadesProjetoPresenter = escolhaFuncionalidadesProjetoPresenter;
        this.idPerfisSelecionados = idPerfisSelecionados;
        this.projetoId = projetoId;
        
        this.verificacoesService = VerificacoesTelaProjetoService.getInstancia();
    }
    
    
    
     @Override
    public void execute() {
        ManterProjetoDeEstimativaView view = escolhaFuncionalidadesProjetoPresenter.getView();
        JTable tabela = view.getTable();

        String nomeProjeto = escolhaFuncionalidadesProjetoPresenter.getTxtNomeProjetoEstimativa();
        String percentualComImpostosText = escolhaFuncionalidadesProjetoPresenter.getTxtPercentualComImpostos();
        String percentualDeLucroDesejadoText = escolhaFuncionalidadesProjetoPresenter.getTxtPercentualLucroDesejado();

        verificaCamposObrigatoriosEPorcentagem(nomeProjeto, percentualComImpostosText, percentualDeLucroDesejadoText);

        String custoHardwareEInstalacoesFisicasText = escolhaFuncionalidadesProjetoPresenter.getTxtCustoHardwareEInstalacoesFisicas();
        String custoSoftwareText = escolhaFuncionalidadesProjetoPresenter.getTxtCustoSoftware();
        String custoRiscosText = escolhaFuncionalidadesProjetoPresenter.getTxtCustoRiscos();
        String custoGarantiaText = escolhaFuncionalidadesProjetoPresenter.getTxtCustoGarantia();
        String fundoDeReservaText = escolhaFuncionalidadesProjetoPresenter.getTxtFundoReserva();
        String outrosCustosText = escolhaFuncionalidadesProjetoPresenter.getTxtOutrosCustos();

        verificaValoresInválidos(custoHardwareEInstalacoesFisicasText, custoSoftwareText, custoGarantiaText, custoRiscosText, fundoDeReservaText, outrosCustosText, percentualComImpostosText, percentualDeLucroDesejadoText);
 
        Map<String, Integer> mapProjetos = verificacoesService.verificarProjetosSelecionados(tabela);

        RetornaProjetoModelService retornaProjetoModelService = new RetornaProjetoModelService(mapProjetos);
        ProjetoDeEstimativaModel projetoDeEstimativaModel = retornaProjetoModelService.getProjeto();

        setExtrasProjeto(projetoDeEstimativaModel, custoHardwareEInstalacoesFisicasText, custoSoftwareText, custoRiscosText, custoGarantiaText, fundoDeReservaText, outrosCustosText, nomeProjeto, percentualComImpostosText, percentualDeLucroDesejadoText);    
        
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

        
        for (Integer idPerfil : idPerfisSelecionados) {
            PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoDeEstimativaRepository().findById(idPerfil);
            escolhaFuncionalidadesProjetoPresenter.getPerfilProjetoIntermediariaRepository().insert(projetoDeEstimativaModel, perfilProjetoDeEstimativaModel);
        }

        
        List<ProjetosFuncionalidadesPersonalizadasModel> funcionalidadesPersonalizadasList = retornaProjetoModelService.getFuncionalidadesPersonalizadas();
        if (funcionalidadesPersonalizadasList != null) {
            for (ProjetosFuncionalidadesPersonalizadasModel model : funcionalidadesPersonalizadasList) {
                escolhaFuncionalidadesProjetoPresenter.getProjetoFuncionalidadesPersonalizadasRepository().insert(model);
            }
        }

    }
    
    //TUDO QUE NÃO É FUNCIONALIDADE
    private void setExtrasProjeto(ProjetoDeEstimativaModel projetoDeEstimativaModel, String custoHardwareEInstalacoesFisicasText, String custoSoftwareText, String custoRiscosText, String custoGarantiaText, String fundoDeReservaText, String outrosCustosText, String nomeProjeto, String percentualComImpostosText, String percentualDeLucroDesejadoText){
        Double custoHardwareEInstalacoesFisicas = custoHardwareEInstalacoesFisicasText.isEmpty() ? 0.0 : Double.parseDouble(custoHardwareEInstalacoesFisicasText);
        Double custoSoftware = custoSoftwareText.isEmpty() ? 0.0 : Double.parseDouble(custoSoftwareText);
        Double custoRiscos = custoRiscosText.isEmpty() ? 0.0 : Double.parseDouble(custoRiscosText);
        Double custoGarantia = custoGarantiaText.isEmpty() ? 0.0 : Double.parseDouble(custoGarantiaText);
        Double fundoDeReserva = fundoDeReservaText.isEmpty() ? 0.0 : Double.parseDouble(fundoDeReservaText);
        Double outrosCustos = outrosCustosText.isEmpty() ? 0.0 : Double.parseDouble(outrosCustosText);
        Double percentualComImpostos = percentualComImpostosText.isEmpty() ? 0.0 : Double.parseDouble(percentualComImpostosText);
        Double percentualDeLucroDesejado = percentualDeLucroDesejadoText.isEmpty() ? 0.0 : Double.parseDouble(percentualDeLucroDesejadoText);
        
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
    }
    
    private void verificaCamposObrigatoriosEPorcentagem(String nomeProjeto, String percentualComImpostosText, String percentualDeLucroDesejadoText){
        if (!verificacoesService.verificarCamposObrigatorios(nomeProjeto, percentualComImpostosText, percentualDeLucroDesejadoText)) {
            throw new IllegalArgumentException("O percentual com imposto, de lucro desejado e o nome do projeto devem ser obrigatoriamente informados.");
        }
        
        if(verificacoesService.verificarCamposPorcentagem(percentualComImpostosText, percentualDeLucroDesejadoText)){
            throw new IllegalArgumentException("Os campos de porcentagem não devem exceder 100%");
        }
    }
    
    private void verificaValoresInválidos(String custoHardwareEInstalacoesFisicasText, String custoSoftwareText,String custoGarantiaText, String custoRiscosText, String fundoDeReservaText, String outrosCustosText, String percentualComImpostosText, String percentualDeLucroDesejadoText){
        try{
            verificacoesService.verificarCustosEPercentuais(custoHardwareEInstalacoesFisicasText, custoSoftwareText,
                                                                 custoRiscosText, custoGarantiaText, fundoDeReservaText,
                                                                 outrosCustosText, percentualComImpostosText, percentualDeLucroDesejadoText);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Um ou mais campos com valores inválidos: "+e.getMessage());
        }
    }
    }
   

