/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.repository.abstr;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import java.util.List;

/**
 *
 * @author David Potentini
 */
public interface IPerfilFuncionalidadesPersonalizadasRepository {
    List<PerfilFuncionalidadesPersonalizadasModel> findAll();
    PerfilFuncionalidadesPersonalizadasModel findById(Integer id);
    List<PerfilFuncionalidadesPersonalizadasModel> findByPerfilProjetoEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    void insert(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel);
    void update(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel);
    void updateByPerfilProjetoDeEstimativa(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel,PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    boolean deleteById(Integer id);
    boolean deleteByPerfilProjetoDeEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
}
