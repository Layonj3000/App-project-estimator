package br.projeto.presenter;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.service.EstimaProjetoService;
import br.projeto.view.DetalheProjetoView;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DetalheProjetoPresenter implements Observer {

    private final DetalheProjetoView view;
    private final EstimaProjetoService estimaService;
    //private final ProjetoRepositoryMock repository;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final String projetoNome;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO
    private final Integer projetoId;//NOVO
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO

    public DetalheProjetoPresenter(DetalheProjetoView view/*/, ProjetoRepositoryMock repository*/, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, Integer projetoId, String projetoNome) {//NOME E REPOSITORY SERÃO RETIRADOS
        this.view = view;
        //this.repository = repository;
        
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;//NOVO
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;//NOVO
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;//NOVO
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;//NOVO  
        this.projetoId = projetoId;//NOVO
        
        this.perfilProjetoDeEstimativaRepository.addObserver(this);
        this.projetoDeEstimativaRepository.addObserver(this);
        this.projetoFuncionalidadesPersonalizadasRepository.addObserver(this);
        this.perfilFuncionalidadesPersonalizadasRepository.addObserver(this);
        
        this.projetoNome = projetoNome;
        this.estimaService = new EstimaProjetoService();

        //this.repository.addObserver(this);
        carregarDetalhesProjeto();
    }

    private void carregarDetalhesProjeto() {
        //Projeto projeto = repository.getProjetoPorNome(projetoNome);//ANTIGO
        ProjetoDeEstimativaModel projeto = projetoDeEstimativaRepository.findById(projetoId);
        List<PerfilProjetoDeEstimativaModel> perfilList = perfilProjetoDeEstimativaRepository.findByProjetoEstimativa(projeto);
        List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasList= projetoFuncionalidadesPersonalizadasRepository.findByProjetoEstimativa(projeto);
        List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList = new ArrayList<>();
        for(PerfilProjetoDeEstimativaModel perfil: perfilList){
            perfilFuncionalidadesPersonalizadasList.addAll(perfilFuncionalidadesPersonalizadasRepository.findByPerfilProjetoEstimativa(perfil));
        }
        
        if (projeto != null) {
            carregarCabecalho(projeto, perfilList);
            carregarDetalhes(projeto, perfilList, projetosFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList);
        }
    }

    private void carregarCabecalho(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList) {//PARAMETRO ERA DO TIPO PROJETO
//        String tiposConcatenados = projeto.getPerfis().stream()
//                .collect(Collectors.joining(", "));//ANTIGO
        String tiposConcatenados = perfilProjetoDeEstimativaModelList.stream()//NOVO
                                   .map(PerfilProjetoDeEstimativaModel :: getNomePerfil)
                                   .collect(Collectors.joining(", ")); 
        

        view.atualizarCabecalho(
                projeto.getNomeProjetoDeEstimativa(),
                projeto.getNomeUsuario(),
                projeto.getDataCriacao(),
                tiposConcatenados,
                projeto.getStatus()
        );
    }

//    private void carregarDetalhes(Projeto projeto) {//ANTIGO
//        Object[][] dadosTabela = projeto.getFuncionalidadesEscolhidas()
//                .entrySet()
//                .stream()
//                .map(entry -> {
//                    String nomeFuncionalidade = entry.getKey();
//                    int dias = entry.getValue();
//                    double valor = estimaService.calcularValorUnitario(projeto.getPerfis().get(0), dias);
//                    return new Object[]{nomeFuncionalidade, dias, String.format("R$ %.2f", valor)};
//                })
//                .toArray(Object[][]::new);
//
//        double valorTotal = calcularValorTotal(projeto);
//        view.atualizarTabela(dadosTabela, valorTotal);
//    }
    private void carregarDetalhes(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList,List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {//NOVO

        Object[][] dadosTabela = funcionalidadesEscolhidasPerfil(projeto, perfilProjetoDeEstimativaModelList, projetoFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList)
                .entrySet()
                .stream()
                .map(entry -> {
                    String nomeFuncionalidade = entry.getKey();
                    int dias = entry.getValue();
                    double valor = estimaService.calcularValorUnitario(projeto, perfilProjetoDeEstimativaModelList, dias);
                    return new Object[]{nomeFuncionalidade, dias, String.format("R$ %.2f", valor)};
                })
                .toArray(Object[][]::new);

        double valorTotal = calcularValorTotal(projeto, perfilProjetoDeEstimativaModelList, projetoFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList);
        view.atualizarTabela(dadosTabela, valorTotal);
    }

//    private double calcularValorTotal(Projeto projeto) {//ANTIGO
//        return projeto.getFuncionalidadesEscolhidas()
//                .entrySet()
//                .stream()
//                .mapToDouble(entry -> {
//                    int dias = entry.getValue();
//                    return estimaService.calcularValorUnitario(projeto.getPerfis().get(0), dias);
//                })
//                .sum();
//    }
    private double calcularValorTotal(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList,List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {//NOVO
        return funcionalidadesEscolhidasPerfil(projeto, perfilProjetoDeEstimativaModelList, projetoFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList)
                .entrySet()
                .stream()
                .mapToDouble(entry -> {
                    int dias = entry.getValue();
                    return estimaService.calcularValorUnitario(projeto, perfilProjetoDeEstimativaModelList, dias);
                })
                .sum();
    }

    private Map<String, SimNao> funcionalidadesEscolhidasProjeto(ProjetoDeEstimativaModel projeto,  List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList) {
        Map<String, SimNao> funcionalidadesDisponiveis = projeto.getFuncionalidadesDisponiveis();
        Map<String, SimNao> funcionalidadesEscolhidas = new HashMap<>();

        for (Map.Entry<String, SimNao> entrySet : funcionalidadesDisponiveis.entrySet()) {
            if (entrySet.getValue() == SimNao.SIM) {
                funcionalidadesEscolhidas.put(entrySet.getKey(), entrySet.getValue());
            }
        }
        
        for (ProjetosFuncionalidadesPersonalizadasModel funcionalidadesPersonalizadas: projetoFuncionalidadesPersonalizadasList){
            if(funcionalidadesPersonalizadas.getSelecionado() == SimNao.SIM){
                funcionalidadesEscolhidas.put(funcionalidadesPersonalizadas.getNome(), SimNao.SIM);
            }
        } 
        
        
        return funcionalidadesEscolhidas;
    }

    private Map<String, Integer> funcionalidadesEscolhidasPerfil(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList,List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {
        Map<String, Integer> funcionalidadesEscolhidasPerfil = new HashMap<>();
        Map<String, Integer> funcionalidadesDisponiveis = new HashMap<>();
        for (Map.Entry<String, SimNao> entrySet : funcionalidadesEscolhidasProjeto(projeto, projetoFuncionalidadesPersonalizadasList).entrySet()) {
            for (PerfilProjetoDeEstimativaModel model : perfilProjetoDeEstimativaModelList) {
                funcionalidadesDisponiveis = model.getFuncionalidadesDisponiveis();
            }
            if(funcionalidadesDisponiveis.containsKey(entrySet.getKey())){
                Integer valor = funcionalidadesEscolhidasPerfil.getOrDefault(entrySet.getKey(), 0) + funcionalidadesDisponiveis.getOrDefault(entrySet.getKey(), 0);
                funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
            }else{
                for(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel: perfilFuncionalidadesPersonalizadasList){
                    if(entrySet.getKey().equals(perfilFuncionalidadesPersonalizadasModel.getNome())){
                        Integer valor = funcionalidadesEscolhidasPerfil.getOrDefault(entrySet.getKey(), 0) + perfilFuncionalidadesPersonalizadasModel.getValor();
                        funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
                    }
                }
            }
        }
        return funcionalidadesEscolhidasPerfil;
    }

    //verificar lançamento de excessoes em updates nao utilizados
    @Override
    public void update(List<Projeto> projetos) {
        carregarDetalhesProjeto();
    }

    @Override
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel) {
        carregarDetalhesProjeto();
    }

    @Override
    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel) {
        carregarDetalhesProjeto();
    }

    @Override
    public void updateProjetoFuncionalidadesPersonalizadasModel(List<ProjetosFuncionalidadesPersonalizadasModel> listaProjetosFuncionalidadesPersonalizadasModel) {
        carregarDetalhesProjeto();
    }

    @Override
    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel) {
        carregarDetalhesProjeto();
    }
}
