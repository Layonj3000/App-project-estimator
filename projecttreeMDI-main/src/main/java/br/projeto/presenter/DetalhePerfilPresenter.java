/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.view.DetalhePerfilView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Potentini
 */
public class DetalhePerfilPresenter extends Observer{
    private final DetalhePerfilView view;
    // private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO
    //private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    
    
    private Integer perfilId;
    private String perfilNome;
    
    public DetalhePerfilPresenter(DetalhePerfilView view, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,String perfilNome, Integer perfilId){
        this.view = view;
        
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilNome = perfilNome;
        this.perfilId = perfilId;
        
        this.perfilProjetoDeEstimativaRepository.addObserver(this);
        this.perfilFuncionalidadesPersonalizadasRepository.addObserver(this);
        
        carregarDetalhesPerfil();
    }
    
    private void carregarDetalhesPerfil() {
        PerfilProjetoDeEstimativaModel perfil = perfilProjetoDeEstimativaRepository.findById(perfilId);
        List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList = perfilFuncionalidadesPersonalizadasRepository.findByPerfilProjetoEstimativa(perfil);
        
        if(perfil != null){
            carregarCabecalho(perfil);
            carregarDetalhes(perfil, perfilFuncionalidadesPersonalizadasList);
        }
    }
    
    private void carregarCabecalho(PerfilProjetoDeEstimativaModel perfil) {
        String taxaDev = Double.toString(perfil.getTaxaDiariaDesenvolvimento());
        String taxaDes = Double.toString(perfil.getTaxaDiariaDesign());
        String taxaGer = Double.toString(perfil.getTaxaDiariaGerenciaProjeto());
        
        view.atualizarCabecalho(
                perfil.getNomePerfil(), 
                perfil.getNomeUsuario(), 
                perfil.getDataCriacao(),
                taxaDev,
                taxaDes,
                taxaGer
                );
    }
    
    private void carregarDetalhes(PerfilProjetoDeEstimativaModel perfil, List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {
        Object[][] dadosTabela = funcionalidadesPerfil(perfil, perfilFuncionalidadesPersonalizadasList)
                                 .entrySet()
                                 .stream()
                                 .map(entry -> {
                                 String nomeFuncionalidade = entry.getKey();
                                 int dias = entry.getValue();
                                 
                                 return new Object[]{nomeFuncionalidade, dias};
                                 })
                                 .toArray(Object[][]::new);
        view.atualizarTabela(dadosTabela);
    }
    
    private Map<String, Integer> funcionalidadesPerfil(PerfilProjetoDeEstimativaModel perfil, List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasList) {
            Map<String, Integer> funcionalidadesPerfil = perfil.getFuncionalidadesDisponiveis();
            Map<String, Integer> funcionalidadesEscolhidas = new LinkedHashMap<>();
            
            for(Map.Entry<String, Integer> entrySet: funcionalidadesPerfil.entrySet()){
                if(entrySet.getValue() != null /*&& entrySet.getValue()!= 0*/){
                    funcionalidadesEscolhidas.put(entrySet.getKey(), entrySet.getValue());
                }
            }
            
            for(PerfilFuncionalidadesPersonalizadasModel funcionalidadesPersonalizadas: perfilFuncionalidadesPersonalizadasList){
                funcionalidadesEscolhidas.put(funcionalidadesPersonalizadas.getNome(), funcionalidadesPersonalizadas.getValor());
            }
            
            return funcionalidadesEscolhidas;
    }


    @Override
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel) {
        if(listaPerfilProjetoDeEstimativaModel!=null && !listaPerfilProjetoDeEstimativaModel.isEmpty()){
            carregarDetalhesPerfil();
        }
    }

    @Override
    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel) {
        if(listaPerfilFuncionalidadesPersonalizadasModel!=null && !listaPerfilFuncionalidadesPersonalizadasModel.isEmpty()){
        carregarDetalhesPerfil();
        }
    }

}
