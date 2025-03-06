package br.projeto.repository.abstr;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;

import java.util.List;

public interface IPerfilProjetoIntermediariaRepository {
    List<PerfilProjetoIntermediariaModel> findAll();
    PerfilProjetoIntermediariaModel findById(Integer idProjeto, Integer idPerfil);
    List<PerfilProjetoIntermediariaModel> findByProjeto(Integer idProjeto);
    void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    void insertMutiple(ProjetoDeEstimativaModel projetoDeEstimativaModel, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList);
    void deleteById(Integer idProjeto, Integer idPerfil);
    void deleteByProjeto(Integer idProjeto);
}
