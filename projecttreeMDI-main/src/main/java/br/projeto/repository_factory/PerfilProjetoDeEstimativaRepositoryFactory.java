package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;

public class PerfilProjetoDeEstimativaRepositoryFactory extends RepositoryFactory {
    @Override
    public <T> T createRepository() {
        return (T) new PerfilProjetoDeEstimativaRepository(DB.getConnection());
    }
}
