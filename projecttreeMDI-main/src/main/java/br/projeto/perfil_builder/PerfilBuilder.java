/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.perfil_builder;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;

/**
 *
 * @author David Potentini
 */
public abstract class PerfilBuilder {
    protected PerfilProjetoDeEstimativaModel perfilModel;
    
    public PerfilBuilder(UsuarioModel usuarioModel){
        this.perfilModel = new PerfilProjetoDeEstimativaModel();
        perfilModel.setUsuarioModel(usuarioModel);
    }
    public abstract void setNomePerfil();
    
    public abstract void setFuncionalidades();
    
    public abstract void setTaxas();
    
    public abstract void setDataCriacao();
    
    public PerfilProjetoDeEstimativaModel getPerfil(){
        return perfilModel;
    }
}
