/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author USER
 */
public class VerificacoesTelaProjetoService {
    private static VerificacoesTelaProjetoService instancia;

    private VerificacoesTelaProjetoService() {}

    public static VerificacoesTelaProjetoService getInstancia() {
        if (instancia == null) {
            instancia = new VerificacoesTelaProjetoService();
        }
        return instancia;
    }

    public boolean verificarCamposObrigatorios(String nomeProjeto, String percentualComImpostos, String percentualDeLucroDesejado) {
        return nomeProjeto != null && !nomeProjeto.trim().isEmpty() &&
               percentualComImpostos != null && !percentualComImpostos.trim().isEmpty() &&
               percentualDeLucroDesejado != null && !percentualDeLucroDesejado.trim().isEmpty();
    }
    
    public boolean verificarCamposPorcentagem(String percentualComImpostos, String percentualDeLucroDesejado){
        Double percentualComImp = Double.valueOf(percentualComImpostos);
        Double percentualLucroDesejado = Double.valueOf(percentualDeLucroDesejado);
        
        return percentualComImp>100 || percentualLucroDesejado>100;
    }

    public boolean verificarCustosEPercentuais(String custoHardwareEInstalacoesFisicas, String custoSoftware,
                                               String custoRiscos, String custoGarantia, String fundoDeReserva,
                                               String outrosCustos, String percentualComImpostos,
                                               String percentualDeLucroDesejado) {
        try {
            if (!custoHardwareEInstalacoesFisicas.isEmpty()) {Double.parseDouble(custoHardwareEInstalacoesFisicas);}
            if (!custoSoftware.isEmpty()) {Double.parseDouble(custoSoftware);}
            if (!custoRiscos.isEmpty()) {Double.parseDouble(custoRiscos);}
            if (!custoGarantia.isEmpty()) {Double.parseDouble(custoGarantia);}
            if (!fundoDeReserva.isEmpty()) {Double.parseDouble(fundoDeReserva);}
            if (!outrosCustos.isEmpty()) {Double.parseDouble(outrosCustos);}
            if (!percentualComImpostos.isEmpty()) {Double.parseDouble(percentualComImpostos);}
            if (!percentualDeLucroDesejado.isEmpty()) {Double.parseDouble(percentualDeLucroDesejado);}
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Map<String, Integer> verificarProjetosSelecionados(JTable tabela) {
        Map<String, Integer> mapProjetos = new LinkedHashMap<>();
        int qtdLinhas = tabela.getRowCount();
        
        for(int i = 0; i < qtdLinhas; i++){
            if((Boolean)tabela.getValueAt(i, 0)){
                Integer trueOrFalse=(Boolean) tabela.getValueAt(i, 0).equals(true)? 1 : 0;
                mapProjetos.put((String)tabela.getValueAt(i, 1), trueOrFalse);
            }
        }
        return mapProjetos;
    }
}
