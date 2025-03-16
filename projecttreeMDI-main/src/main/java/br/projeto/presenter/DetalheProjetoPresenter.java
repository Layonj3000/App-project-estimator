package br.projeto.presenter;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.EstimaProjetoService;
import br.projeto.service.InstanciaRepositoryService;
import br.projeto.service.TotalizadoresProjetoService;
import br.projeto.view.DetalheProjetoView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetalheProjetoPresenter extends Observer {

    private final DetalheProjetoView view;
    private final EstimaProjetoService estimaService;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final String projetoNome;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final Integer projetoId;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private TotalizadoresProjetoService totalizadoresService =  TotalizadoresProjetoService.getInstance();
    private UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();
    
    
    private Map<String, Integer> funcionalidades;
    public DetalheProjetoPresenter(DetalheProjetoView view, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, Integer projetoId, String projetoNome) {//NOME E REPOSITORY SERÃO RETIRADOS
        this.view = view;
        
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository; 
        this.perfilProjetoIntermediariaRepository= perfilProjetoIntermediariaRepository;
        this.projetoId = projetoId;
        
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
        String tiposConcatenados = perfilProjetoDeEstimativaModelList.stream()
                                   .map(PerfilProjetoDeEstimativaModel :: getNomePerfil)
                                   .collect(Collectors.joining(", ")); 
        
        String percentualLucro = Double.toString(projeto.getPercentualLucroDesejado());
        String percentualImpostos = Double.toString(projeto.getPercentualComImpostos());
        String totalDevDiario = Double.toString(estimaService.retornaValorTotalDia(projeto, perfilProjetoDeEstimativaModelList));
        
        String nomeCompartilhador = "";
        String nomeCriador = projeto.getNomeUsuario();
        if(projeto.getCompartilhadoValor() == 1){
            UsuarioModel compartilhador = usuarioRepository.findById(projeto.getCompartilhadoPor());
            nomeCompartilhador = compartilhador.getNome();
            nomeCriador = "";
        }
        
        
        
        view.atualizarCabecalho(
                projeto.getNomeProjetoDeEstimativa(),
                nomeCriador,
                projeto.getDataCriacao(),
                tiposConcatenados,
                percentualLucro,
                percentualImpostos,
                totalDevDiario,
                nomeCompartilhador
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
                        return new Object[]{nomeFuncionalidade, estimaService.calculaResultadoDiasNivelUI(dias, diasTamanhoProjeto), String.format("R$ %.2f", valor)};
                    }
                        return new Object[]{nomeFuncionalidade, dias, String.format("R$ %.2f", valor)};
                })
                .toArray(Object[][]::new);

        List<Object[]> listaDados = new ArrayList<>(Arrays.asList(dadosTabela));
        /*Custos Adicionais*/
        addCustosAdicionais(listaDados, projeto);
        /*Custos Adicionais*/
                
        dadosTabela = listaDados.toArray(Object[][]::new);        
        double valorTotal = calcularValorTotal(projeto, perfilProjetoDeEstimativaModelList);
        
        view.atualizarTabela(dadosTabela);
        
        double valorTotalBase = calcularValorTotalBase(projeto, perfilProjetoDeEstimativaModelList);
        double imposto = totalizadoresService.getImposto(projeto, valorTotalBase);
        double lucroCalculado = totalizadoresService.getLucroCalculado(projeto, valorTotalBase);
        double mediaMes = totalizadoresService.getMediaMes(projeto, view, valorTotal);
        double totalMes = totalizadoresService.getTotalMeses(projeto, view);
        int totalDias = totalizadoresService.getTotalDias(projeto, view);
    
        view.atualizarRodape(totalDias, totalMes,imposto,lucroCalculado,mediaMes, valorTotal, valorTotalBase);
    }
    
    private void addCustosAdicionais(List<Object[]> listaDados, ProjetoDeEstimativaModel projeto){
        if(projeto.getCustoHardware()>0){
            listaDados.add(new Object[]{"Custo Com Hardware e Instalações Físicas", "", String.format("R$ %.2f", projeto.getCustoHardware())});
        }
        if(projeto.getCustoSoftware()>0){
            listaDados.add(new Object[]{"Custo Com Software", "", String.format("R$ %.2f", projeto.getCustoSoftware())});
        }
        if(projeto.getCustoRiscos()>0){
            listaDados.add(new Object[]{"Custo Com Riscos", "", String.format("R$ %.2f", projeto.getCustoRiscos())});
        }
        if(projeto.getCustoGarantia()>0){
            listaDados.add(new Object[]{"Custos Com Garantia", "", String.format("R$ %.2f", projeto.getCustoGarantia())});
        }
        if(projeto.getFundoDeReserva()>0){
            listaDados.add(new Object[]{"Fundo De Reserva", "", String.format("R$ %.2f", projeto.getFundoDeReserva())});
        }
        if(projeto.getOutrosCustos()>0){
            listaDados.add(new Object[]{"Outros Custos", "", String.format("R$ %.2f", projeto.getOutrosCustos())});
        }
    }
    
    private double calcularTotalCustosAdicionais(ProjetoDeEstimativaModel projeto){
        Double totalCustosAdicionais = 0.0;
        totalCustosAdicionais += projeto.getCustoHardware();
        totalCustosAdicionais += projeto.getCustoSoftware();
        totalCustosAdicionais += projeto.getCustoRiscos();
        totalCustosAdicionais += projeto.getCustoGarantia();
        totalCustosAdicionais += projeto.getFundoDeReserva();
        totalCustosAdicionais += projeto.getOutrosCustos();
        return totalCustosAdicionais;
    }
    
    private double calcularValorTotal(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList){

        double valorTotalBase = calcularValorTotalBase(projeto, perfilProjetoDeEstimativaModelList);
        double imposto = totalizadoresService.getImposto(projeto, valorTotalBase);
        double lucroCalculado = totalizadoresService.getLucroCalculado(projeto, valorTotalBase);
        
        double valorTotal = valorTotalBase +  imposto + lucroCalculado;
        
        return valorTotal;
    }
    
    private double calcularValorTotalBase(ProjetoDeEstimativaModel projeto, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList) {
                
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
        
        return (somaFuncionalidades + calcularTotalCustosAdicionais(projeto));
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
                Optional<Integer> funcionalidadesEscolhidasOptional = Optional.ofNullable(funcionalidadesEscolhidasPerfil.get(entrySet.getKey()));
                Optional<Integer> funcionalidadesDisponiveisOptional = Optional.ofNullable(funcionalidadesDisponiveis.get(entrySet.getKey()));
                
                Integer valor = 0;
                if(funcionalidadesEscolhidasOptional.isPresent()){
                    valor += funcionalidadesEscolhidasPerfil.get(entrySet.getKey());
                }
                if(funcionalidadesDisponiveisOptional.isPresent()){
                    valor += funcionalidadesDisponiveis.get(entrySet.getKey());
                }
                
                funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
            }else{
                 Iterator<PerfilFuncionalidadesPersonalizadasModel> iterator = perfilFuncionalidadesPersonalizadasList.iterator();
                 while (iterator.hasNext()) {
                    PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel = iterator.next();
                    if (entrySet.getKey().equals(perfilFuncionalidadesPersonalizadasModel.getNome())) {
                        Integer valor = funcionalidadesEscolhidasPerfil.getOrDefault(entrySet.getKey(), 0) + perfilFuncionalidadesPersonalizadasModel.getValor();
                        funcionalidadesEscolhidasPerfil.put(entrySet.getKey(), valor);
                        iterator.remove(); 
                    }
                }
   
            }
            }
        }
        return funcionalidadesEscolhidasPerfil;
    }


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
