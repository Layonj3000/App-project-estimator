package br.projeto.repository_factory;

import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.UsuarioRepository;

public abstract class RepositoryFactory {

    public static <T> T escolherClasseFabricada(Object objDao) {
        if(objDao == PerfilProjetoDeEstimativaRepository.class) {
            return (T) new PerfilProjetoDeEstimativaRepositoryFactory();
        }else if(objDao == ProjetoDeEstimativaRepository.class) {
            return (T) new ProjetoDeEstimativaRepositoryFactory();
        }else if(objDao == PerfilProjetoIntermediariaRepository.class) {
            return (T) new PerfilProjetoIntermediariaRepositoryFactory();
        }else if(objDao == UsuarioRepository.class){
            return (T) new UsuarioRepositoryFactory();
        }else{
            throw new IllegalArgumentException();
        }
    }
     public abstract <T> T createRepository();

}
