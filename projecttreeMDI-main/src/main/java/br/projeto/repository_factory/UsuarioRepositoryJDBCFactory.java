package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.UsuarioRepositoryJDBC;

public class UsuarioRepositoryJDBCFactory extends RepositoryJDBCFactory {
    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new UsuarioRepositoryJDBC(DB.getConnection());
    }
}
