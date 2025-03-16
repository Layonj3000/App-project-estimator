/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.perfil_builder;

import br.projeto.model.PerfilProjetoDeEstimativaModel;

/**
 *
 * @author David Potentini
 */
public class Diretor {
    
    
    public PerfilProjetoDeEstimativaModel criar(PerfilBuilder perfilBuilder){
        perfilBuilder.setNomePerfil();
        perfilBuilder.setFuncionalidades();
        perfilBuilder.setTaxas();
        perfilBuilder.setDataCriacao();
        
        return perfilBuilder.getPerfil();
    }
}
