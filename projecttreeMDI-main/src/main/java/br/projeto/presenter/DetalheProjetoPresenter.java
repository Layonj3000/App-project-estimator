package br.projeto.presenter;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.service.EstimaProjetoService;
import br.projeto.view.DetalheProjetoView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DetalheProjetoPresenter extends Observer {

    private final DetalheProjetoView view;
    private final EstimaProjetoService estimaService;
    //private final ProjetoRepositoryMock repository;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final String projetoNome;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO
    private final Integer projetoId;//NOVO
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;

    private Map<String, Integer> funcionalidades;
    public DetalheProjetoPresenter(DetalheProjetoView view, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, Integer projetoId, String projetoNome) {//NOME E REPOSITORY SERÃO RETIRADOS
        this.view = view;
        //this.repository = repository;
        
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;//NOVO
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;//NOVO
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;//NOVO
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;//NOVO  
        this.perfilProjetoIntermediariaRepository= perfilProjetoIntermediariaRepository;//NOVO
        this.projetoId = projetoId;//NOVO
        
        this.perfilProjetoDeEstimativaRepository.addObserver(this);
        this.projetoDeEstimativaRepository.addObserver(this);
        this.projetoFuncionalidadesPersonalizadasRepository.addObserver(this);
        this.perfilFuncionalidadesPersonalizadasRepository.addObserver(this);
        this.perfilProjetoIntermediariaRepository.addObserver(this);
        
        this.projetoNome = projetoNome;
        this.estimaService = new EstimaProjetoService();

        carregarDetalhesProjeto();
    }

    private void carregarDetalhesProjeto() {
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

    private void carregarCabecalho(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList) {
        String tiposConcatenados = perfilProjetoDeEstimativaModelList.stream()//NOVO
                                   .map(PerfilProjetoDeEstimativaModel :: getNomePerfil)
                                   .collect(Collectors.joining(", ")); 
        

        view.atualizarCabecalho(
                projeto.getNomeProjetoDeEstimativa(),
                projeto.getNomeUsuario(),
                projeto.getDataCriacao(),
                tiposConcatenados
        );
    }
    
    private void carregarDetalhes(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList,List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {//NOVO
        funcionalidades = funcionalidadesEscolhidasPerfil(projeto, perfilProjetoDeEstimativaModelList, projetoFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList);
        
        final int diasTamanhoProjeto = funcionalidades.entrySet()
            .stream()
            .filter(entry -> entry.getKey().equals("Pequeno") || entry.getKey().equals("Médio") || entry.getKey().equals("Grande"))
            .mapToInt(Map.Entry::getValue)
            .findFirst()
            .orElse(0);
        
        Object[][] dadosTabela = funcionalidades
                .entrySet()
                .stream()
                .map(entry -> {
                    String nomeFuncionalidade = entry.getKey();
                    int dias = entry.getValue();
                    double valor = estimaService.calcularValorUnitario(projeto, perfilProjetoDeEstimativaModelList, nomeFuncionalidade, dias, diasTamanhoProjeto);
                    if(nomeFuncionalidade.equals("MVP") || nomeFuncionalidade.equals("Básico") || nomeFuncionalidade.equals("Profissional")){
                        return new Object[]{nomeFuncionalidade, dias + "%", String.format("R$ %.2f", valor)};
                    }
                        return new Object[]{nomeFuncionalidade, dias, String.format("R$ %.2f", valor)};
                })
                .toArray(Object[][]::new);

        double valorTotal = calcularValorTotal(projeto, perfilProjetoDeEstimativaModelList, projetoFuncionalidadesPersonalizadasList, perfilFuncionalidadesPersonalizadasList);
        view.atualizarTabela(dadosTabela, valorTotal);
    }

    private double calcularValorTotal(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList, List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList,List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {//NOVO
                
        final int diasTamanhoProjeto = funcionalidades
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey().equals("Pequeno") || entry.getKey().equals("Médio") || entry.getKey().equals("Grande"))
            .mapToInt(Map.Entry::getValue)
            .findFirst()
            .orElse(0);
        
        Double somaFuncionalidades =funcionalidades
                .entrySet()
                .stream()
                .mapToDouble(entry -> {
                    String nomeFuncionalidade = entry.getKey();
                    int dias = entry.getValue();
                    return estimaService.calcularValorUnitario(projeto, perfilProjetoDeEstimativaModelList, nomeFuncionalidade, dias, diasTamanhoProjeto);
                })
                .sum();
        
        return somaFuncionalidades;
    }

    private Map<String, SimNao> funcionalidadesEscolhidasProjeto(ProjetoDeEstimativaModel projeto,  List<ProjetosFuncionalidadesPersonalizadasModel> projetoFuncionalidadesPersonalizadasList) {
        Map<String, SimNao> funcionalidadesDisponiveis = projeto.getFuncionalidadesDisponiveis();
        Map<String, SimNao> funcionalidadesEscolhidas = new LinkedHashMap<>();

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
        Map<String, Integer> funcionalidadesEscolhidasPerfil = new LinkedHashMap<>();
        Map<String, Integer> funcionalidadesDisponiveis = new LinkedHashMap<>();
        for (Map.Entry<String, SimNao> entrySet : funcionalidadesEscolhidasProjeto(projeto, projetoFuncionalidadesPersonalizadasList).entrySet()) {
            for (PerfilProjetoDeEstimativaModel model : perfilProjetoDeEstimativaModelList) {
                funcionalidadesDisponiveis = model.getFuncionalidadesDisponiveis();
                
            if(funcionalidadesDisponiveis.containsKey(entrySet.getKey())){
                Integer valor = funcionalidadesEscolhidasPerfil.getOrDefault(entrySet.getKey(), 0) + funcionalidadesDisponiveis.getOrDefault(entrySet.getKey(), 0);
                funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
            }else{
                 Iterator<PerfilFuncionalidadesPersonalizadasModel> iterator = perfilFuncionalidadesPersonalizadasList.iterator();
                 while (iterator.hasNext()) {
                    PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel = iterator.next();
                    if (entrySet.getKey().equals(perfilFuncionalidadesPersonalizadasModel.getNome())) {
                        Integer valor = funcionalidadesEscolhidasPerfil.getOrDefault(entrySet.getKey(), 0) + perfilFuncionalidadesPersonalizadasModel.getValor();
                        funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
                        iterator.remove(); // Remoção segura
                    }
                }
   
            }
            }
        }
        return funcionalidadesEscolhidasPerfil;
    }

    //verificar lançamento de excessoes em updates nao utilizados

    @Override
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel) {
        if(listaPerfilProjetoDeEstimativaModel != null && !listaPerfilProjetoDeEstimativaModel.isEmpty()){
            carregarDetalhesProjeto();
        }
    }

    @Override
    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel) {
        if(listaProjetoDeEstimativaModel!=null && !listaProjetoDeEstimativaModel.isEmpty()){
            carregarDetalhesProjeto();
        }
    }

    @Override
    public void updateProjetoFuncionalidadesPersonalizadasModel(List<ProjetosFuncionalidadesPersonalizadasModel> listaProjetosFuncionalidadesPersonalizadasModel) {
        if(listaProjetosFuncionalidadesPersonalizadasModel!=null && !listaProjetosFuncionalidadesPersonalizadasModel.isEmpty()){
            carregarDetalhesProjeto();
        }
    }

    @Override
    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel) {
        if(listaPerfilFuncionalidadesPersonalizadasModel != null && !listaPerfilFuncionalidadesPersonalizadasModel.isEmpty()){
            carregarDetalhesProjeto();
        }
    }

    
    //IMPORTANTE PARA ATUALIZAR FUNCIONALIDADES PERSONALIZADAS
    @Override
    public void updatePerfilProjetoIntermediariaModel(List<PerfilProjetoIntermediariaModel> listaPerfilProjetoIntermediariaModel) {
            carregarDetalhesProjeto();
    }
}
