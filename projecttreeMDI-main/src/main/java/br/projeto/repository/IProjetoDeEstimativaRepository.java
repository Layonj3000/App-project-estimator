package br.projeto.repository;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;

import java.util.List;

public interface IProjetoDeEstimativaRepository {
    List<ProjetoDeEstimativaModel> findAll();
    List<ProjetoDeEstimativaModel>findByUser(UsuarioModel usuarioModel);
    ProjetoDeEstimativaModel findById(Integer id);
    void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    void update(ProjetoDeEstimativaModel projetoDeEstimativaModel);
    void deleteById(Integer id);

}
