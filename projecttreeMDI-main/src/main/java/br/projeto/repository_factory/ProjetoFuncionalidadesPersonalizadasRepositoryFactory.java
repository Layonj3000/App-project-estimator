/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;

/**
 *
 * @author USER
 */
public class ProjetoFuncionalidadesPersonalizadasRepositoryFactory extends RepositoryFactory{

    @Override
    public <T> T createRepository() {
        return (T) new ProjetoFuncionalidadesPersonalizadasRepository(DB.getConnection());
    }
    
}
