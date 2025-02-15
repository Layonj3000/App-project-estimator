package br.projeto.repository;

import br.projeto.model.UsuarioModel;

import java.util.List;

public interface IUsuarioRepository {
    List<UsuarioModel> findAll();
    UsuarioModel findById(Integer id);
    void insert(UsuarioModel usuarioModel);
    void update(UsuarioModel usuarioModel);

    void deleteById(Integer id);

}
