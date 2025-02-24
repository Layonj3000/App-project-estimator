/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.state.ProjetoDeEstimativaPresenterState;

/**
 *
 * @author USER
 */
public class ProjetoDeEstimativaPresenter {
    
//    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
//    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
//    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
//    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
      
    
    
    private ProjetoDeEstimativaPresenterState estado; 

    public ProjetoDeEstimativaPresenter() {
        this.estado = new ProjetoDeEstimativaPresenterState(this);
    }
    
    public void salvar(){
        estado.salvar();
    }
    
    public void escolherPlataforma(){
        estado.escolherPlataforma();
    }
    
    public void setState(ProjetoDeEstimativaPresenterState estado){
        this.estado = estado;
    }


    
    
    
}
