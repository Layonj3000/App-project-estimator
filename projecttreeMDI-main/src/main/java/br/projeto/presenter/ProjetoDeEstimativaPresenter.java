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
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.state.EscolherPlataformaState;
import br.projeto.state.AProjetoDeEstimativaPresenterState;
import br.projeto.view.EscolhaPlataformaView;
import br.projeto.view.IProjetoDeEstimativaView;
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
    private final UsuarioModel usuarioModel;
  
    private IProjetoDeEstimativaView view;
    
    private AProjetoDeEstimativaPresenterState estado; 

    //AVALIAR POSSÍVEIS RETIRADAS DE REPOSOTORYS DO CONSTRUTOR
    public ProjetoDeEstimativaPresenter(IProjetoDeEstimativaView view, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;    
        this.usuarioModel = usuarioModel;
        
        this.view = view;//new EscolhaPlataformaView();
        this.estado = new EscolherPlataformaState(this);
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
