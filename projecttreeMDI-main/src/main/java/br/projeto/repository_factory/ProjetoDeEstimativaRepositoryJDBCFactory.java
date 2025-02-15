package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.ProjetoDeEstimativaRepositoryJDBC;

public class ProjetoDeEstimativaRepositoryJDBCFactory extends RepositoryJDBCFactory {
    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new ProjetoDeEstimativaRepositoryJDBC(DB.getConnection());
    }
}
