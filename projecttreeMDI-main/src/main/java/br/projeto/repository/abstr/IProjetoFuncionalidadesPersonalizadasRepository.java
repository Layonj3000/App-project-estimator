/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.repository.abstr;

import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import java.util.List;

/**
 *
 * @author David Potentini
 */
public interface IProjetoFuncionalidadesPersonalizadasRepository {
    List<ProjetosFuncionalidadesPersonalizadasModel> findAll();
    ProjetosFuncionalidadesPersonalizadasModel findById(Integer id);
    List<ProjetosFuncionalidadesPersonalizadasModel> findByProjetoEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    void insert(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel);
    void update(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel);
    void updateByProjetoDeEstimativa(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel, ProjetoDeEstimativaModel projetoDeEstimativaModel);
    boolean deleteById(Integer id);
    boolean deleteByProjetoDeEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel);
}
