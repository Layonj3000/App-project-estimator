package br.projeto.repository.abstr;

import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;

import java.util.List;

public interface IProjetoDeEstimativaRepository {
    List<ProjetoDeEstimativaModel> findAll();
    List<ProjetoDeEstimativaModel>findByUser(UsuarioModel usuarioModel);
    ProjetoDeEstimativaModel findById(Integer id);
    void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    void update(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    boolean deleteById(Integer id);

}
