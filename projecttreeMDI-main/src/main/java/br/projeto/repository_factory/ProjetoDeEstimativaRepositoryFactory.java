package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.ProjetoDeEstimativaRepository;

public class ProjetoDeEstimativaRepositoryFactory extends RepositoryFactory {
    @Override
    public <T> T createRepositoryJDBC() {
        return (T) new ProjetoDeEstimativaRepository(DB.getConnection());
    }
}
