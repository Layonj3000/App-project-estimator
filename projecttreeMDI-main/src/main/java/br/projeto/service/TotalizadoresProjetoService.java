/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.view.DetalheProjetoView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Potentini
 */
public class TotalizadoresProjetoService {
    private static TotalizadoresProjetoService service = null;
    
    private TotalizadoresProjetoService(){}
    
    public static TotalizadoresProjetoService getInstance(){
        if(service == null){
            service = new TotalizadoresProjetoService();
        }
        return service;
    }
    
    public Double getImposto(ProjetoDeEstimativaModel projeto, Double valorTotal){
        return ((projeto.getPercentualComImpostos()/100) * valorTotal);
    }
    
    public Double getLucroCalculado(ProjetoDeEstimativaModel projeto, Double valorTotal){
        return ((projeto.getPercentualLucroDesejado()/100) * (valorTotal + (getImposto(projeto, valorTotal))));
    }
    
    public Integer getTotalDias(ProjetoDeEstimativaModel projeto,DetalheProjetoView view){
        DefaultTableModel modeloTabela = view.getModeloTabela();
        
        int totalDias = 0;
        int colunaDias = 1; 

        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            Object valorCelula = modeloTabela.getValueAt(i, colunaDias);

            if (valorCelula != null) {
                String valorTexto = valorCelula.toString();

                if (!valorTexto.isEmpty()) {
                totalDias += Integer.parseInt(valorTexto);
                }
            }
        }
        return totalDias;
    }
    
    public Double getTotalMeses(ProjetoDeEstimativaModel projeto, DetalheProjetoView view){
        return (getTotalDias(projeto, view)/30.0);
    }
    
    public Double getMediaMes(ProjetoDeEstimativaModel projeto,DetalheProjetoView view, Double valorTotal){
        if(getTotalMeses(projeto, view)>1){
            return (valorTotal/getTotalMeses(projeto, view));
        }
            return valorTotal;
    }
    
    
}
