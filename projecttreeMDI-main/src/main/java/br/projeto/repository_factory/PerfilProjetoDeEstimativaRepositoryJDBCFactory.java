package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.PerfilProjetoDeEstimativaRepositoryJDBC;

public class PerfilProjetoDeEstimativaRepositoryJDBCFactory extends RepositoryJDBCFactory {
    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new PerfilProjetoDeEstimativaRepositoryJDBC(DB.getConnection());
    }
}
