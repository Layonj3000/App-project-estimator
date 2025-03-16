package br.projeto.service;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import java.util.List;

import java.util.Map;
import java.util.Optional;

public class EstimaProjetoService {


    public double calcularValorUnitario(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, String nomeFuncionalidade, int dias, int diasTamanhoProjeto) {//LEMBRAR DE ADICIONAR A LOGICA PARA O TAMANHO DO PROJETO(MVP, Básico, Profissional)
        double ValorUnitarioDesenvolvimento = retornaValorTotalDia(projeto,perfilProjetoDeEstimativaModelList);
        int resultadoDiasNivelUI;
        
            //Verifica nível  de UI
        if(nomeFuncionalidade.equals("MVP") || nomeFuncionalidade.equals("Básico") || nomeFuncionalidade.equals("Profissional")){
                //Nesse contexto dias é, na verdade, porcentagem
                resultadoDiasNivelUI = calculaResultadoDiasNivelUI(dias, diasTamanhoProjeto);
                
                return ValorUnitarioDesenvolvimento * resultadoDiasNivelUI;
        }
        
        return ValorUnitarioDesenvolvimento * dias;
    }
   
    public Double retornaValorTotalDia(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList){
        double valorUnitarioDesenvolvimento = 0;
        for (PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel : perfilProjetoDeEstimativaModelList) {
            if (verificaGerenteProjeto(projeto.getGerenteDeProjetos(), perfilProjetoDeEstimativaModel)) {
                valorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getTaxaDiariaGerenciaProjeto();
            }
            switch (perfilProjetoDeEstimativaModel.getNomePerfil()) {
                case "Web e Back-end":
                    valorUnitarioDesenvolvimento =+ perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento();
                    break;
                case "Android"://PODIA DEIXAR SOMENTE O DEFAULT, MAS OPTEI POR DEIXAR O ANDROID E IOS PARA MELHOR ENTENDIMENTO
                case "iOS":
                default:    
                    valorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento();
                    valorUnitarioDesenvolvimento += perfilProjetoDeEstimativaModel.getTaxaDiariaDesign();
                    break;
            }
        }
        return valorUnitarioDesenvolvimento;
    }
    public Integer calculaResultadoDiasNivelUI(int dias, int diasTamanhoProjeto){
        return (int)((dias/100.0) * diasTamanhoProjeto);
    }

    private boolean verificaGerenteProjeto(SimNao gerenteProjeto, PerfilProjetoDeEstimativaModel perfilModel) {
        Optional<Integer> valor = Optional.ofNullable(perfilModel.getGerenteDeProjetos());
        
        if (gerenteProjeto == SimNao.SIM && valor.isPresent()) {
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
