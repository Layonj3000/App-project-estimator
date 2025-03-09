package br.projeto.service;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import java.util.List;

import java.util.Map;

public class EstimaProjetoService {

    private static final double VALOR_DIARIA_DESENVOLVIMENTO = 450.0;
    private static final double VALOR_DIARIA_GERENCIA = 300.0;
    private static final double VALOR_DIARIA_UI_UX = 550.0;

    public int calcularDiasTotais(Projeto projeto) {//ANTIGO
        return calcularDiasFuncionalidades(projeto.getFuncionalidadesEscolhidas());
    }

   public double calcularCusto(Projeto projeto) {//ANTIGO
        int diasTotais = calcularDiasTotais(projeto);
        return diasTotais * VALOR_DIARIA_DESENVOLVIMENTO;
    }

    /*public double calcularValorUnitario(String tipoProjeto, int dias) {
        switch (tipoProjeto) {
            case "Web/Back-end":
            case "Android":
                return dias * VALOR_DIARIA_DESENVOLVIMENTO;
            case "iOS":
                return dias * VALOR_DIARIA_UI_UX;
            default:
                throw new IllegalArgumentException("Tipo de projeto desconhecido: " + tipoProjeto);
        }
    }*/

    public double calcularValorUnitario(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, String nomeFuncionalidade, int dias, int diasTamanhoProjeto) {//LEMBRAR DE ADICIONAR A LOGICA PARA O TAMANHO DO PROJETO(MVP, Básico, Profissional)
        double ValorUnitarioDesenvolvimento = 0;
        int resultadoDiasNivelUI;
        for (PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel : perfilProjetoDeEstimativaModelList) {
            if (verificaGerenteProjeto(projeto.getGerenteDeProjetos())) {
                ValorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getGerenteDeProjetos();
            }
            switch (perfilProjetoDeEstimativaModel.getNomePerfil()) {
                case "Web/Back-end":
                    ValorUnitarioDesenvolvimento =+ perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento();
                    break;
                case "Android"://PODIA DEIXAR SOMENTE O DEFAULT, MAS OPTEI POR DEIXAR O ANDROID E IOS PARA MELHOR ENTENDIMENTO
                case "iOS":
                default:    
                    ValorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento();
                    ValorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getTaxaDiariaDesign();
                    break;
            }
        }
            //Verifica nível  de UI
        if(nomeFuncionalidade.equals("MVP") || nomeFuncionalidade.equals("Básico") || nomeFuncionalidade.equals("Profissional")){
                //Nesse contexto dias é, na verdade, porcentagem
                resultadoDiasNivelUI = (int)((dias/100.0) * diasTamanhoProjeto);
                
                return ValorUnitarioDesenvolvimento * resultadoDiasNivelUI;
        }
        
        return ValorUnitarioDesenvolvimento * dias;
    }

    private boolean verificaGerenteProjeto(SimNao gerenteProjeto) {
        if (gerenteProjeto == SimNao.SIM) {
            return true;
        } else {
            return false;
        }
    }

    public int calcularDiasFuncionalidades(Map<String, Integer> funcionalidadesEscolhidas) {//ANTIGO
        int totalDias = 0;
        for (Integer dias : funcionalidadesEscolhidas.values()) {
            totalDias += dias;
        }
        return totalDias;
    }
    

    public double calcularCustosAdicionais(double custoHardware, double custoSoftware, double custoRiscos, double custoGarantia, double fundoReserva, double outrosCustos) {
        return custoHardware + custoSoftware + custoRiscos + custoGarantia + fundoReserva + outrosCustos;
    }

    public double calcularImpostos(double subtotal, double percentualImpostos) {
        return subtotal * (percentualImpostos / 100);
    }

    public double calcularLucro(double subtotalComImpostos, double percentualLucro) {
        return subtotalComImpostos * (percentualLucro / 100);
    }

    public double calcularPrecoFinal(double subtotalComImpostos, double lucro) {
        return subtotalComImpostos + lucro;
    }

    public double calcularMediaPorMes(double precoFinal, double meses) {
        return precoFinal / meses;
    }


}
