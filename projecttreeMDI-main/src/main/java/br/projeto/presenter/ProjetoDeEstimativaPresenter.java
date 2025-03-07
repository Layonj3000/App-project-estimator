/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.state.PlataformaEscolhidaState;
import br.projeto.state.AProjetoDeEstimativaPresenterState;
import br.projeto.view.EscolhaPlataformaView;
import br.projeto.view.IProjetoDeEstimativaView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class ProjetoDeEstimativaPresenter {
    
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    
    //teste
    private Integer idProjeto;
    // private List<Integer> perfisIds;
  
    private IProjetoDeEstimativaView view;
    
    private AProjetoDeEstimativaPresenterState estado; 

    
    //CONSTRUTOR PARA UUPDATE
    public ProjetoDeEstimativaPresenter(IProjetoDeEstimativaView view, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, Integer idProjeto) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        //this.perfisIds = new ArrayList<>();
        this.idProjeto = idProjeto;
        this.view = view;//new EscolhaPlataformaView();
        this.estado = new PlataformaEscolhidaState(this, idProjeto);
    }
    
    
    //AVALIAR POSSÍVEIS RETIRADAS DE REPOSOTORYS DO CONSTRUTOR
    //CONSTRUTOR PARA INSERCAO
    public ProjetoDeEstimativaPresenter(IProjetoDeEstimativaView view, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        //this.perfisIds = new ArrayList<>();
        
        this.view = view;//new EscolhaPlataformaView();
        this.estado = new PlataformaEscolhidaState(this);
    }

    public PerfilProjetoIntermediariaRepository getPerfilProjetoIntermediariaRepository() {
        return perfilProjetoIntermediariaRepository;
    }
    
    public void salvar(){
        estado.salvar();
    }
    
    public void escolherPlataforma(){
        estado.escolherPlataforma();
    }
    
    public void setState(AProjetoDeEstimativaPresenterState estado){
        this.estado = estado;
    }

    public IProjetoDeEstimativaView getView() {
        return view;
    }

    public void setView(IProjetoDeEstimativaView view) {
        this.view = view;
    }

    /*IDS PERFIL PROJETO*/
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    /*public List<Integer> getIdPerfil() {
        return perfisIds;
    }

    public void addIdPerfil(Integer idPerfil) {
        perfisIds.add(idPerfil);
    }*/

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }
    /*IDS PERFIL PROJETO*/
    
    public ProjetoDeEstimativaRepository getProjetoDeEstimativaRepository() {
        return projetoDeEstimativaRepository;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public ProjetoFuncionalidadesPersonalizadasRepository getProjetoFuncionalidadesPersonalizadasRepository() {
        return projetoFuncionalidadesPersonalizadasRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }
    
    

}
