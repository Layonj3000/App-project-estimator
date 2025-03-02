package br.projeto.repository_factory;

import br.projeto.db.DB;
import br.projeto.repository.ProjetoDeEstimativaRepository;

public class ProjetoDeEstimativaRepositoryFactory extends RepositoryFactory {
    @Override
    public <T> T createRepository() {
        return (T) new ProjetoDeEstimativaRepository(DB.getConnection());
    }
}
