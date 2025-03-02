package br.projeto.repository.abstr;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;

import java.util.List;

public interface IPerfilProjetoDeEstimativaRepository {
    List<PerfilProjetoDeEstimativaModel> findAll();
    List<PerfilProjetoDeEstimativaModel>findByUser(UsuarioModel usuarioModel);
    List<PerfilProjetoDeEstimativaModel> findByProjetoEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    PerfilProjetoDeEstimativaModel findById(Integer id);
    void insert(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    void update(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel);
    boolean deleteById(Integer id);
}
