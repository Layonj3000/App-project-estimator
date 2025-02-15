package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.PerfilProjetoIntermediariaRepositoryJDBC;

public class PerfilProjetoIntermediariaRepositoryJDBCFactory extends RepositoryJDBCFactory {

    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new PerfilProjetoIntermediariaRepositoryJDBC(DB.getConnection());
    }
}
