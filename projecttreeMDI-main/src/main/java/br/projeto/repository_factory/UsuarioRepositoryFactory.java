package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.UsuarioRepository;

public class UsuarioRepositoryFactory extends RepositoryFactory {
    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new UsuarioRepository(DB.getConnection());
    }
}
