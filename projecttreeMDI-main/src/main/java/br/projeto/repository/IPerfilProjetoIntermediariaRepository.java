package br.projeto.repository;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;

import java.util.List;

public interface IPerfilProjetoIntermediariaRepository {
    List<PerfilProjetoIntermediariaModel> findAll();
    PerfilProjetoIntermediariaModel findById(Integer idProjeto, Integer idPerfil);
    void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    void insertMutiple(ProjetoDeEstimativaModel projetoDeEstimativaModel, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList);
    void deleteById(Integer idProjeto, Integer idPerfil);

}
