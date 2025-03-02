package br.projeto.repository_factory;

import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
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
        }else if(objDao == ProjetoFuncionalidadesPersonalizadasRepository.class){
            return (T) new ProjetoFuncionalidadesPersonalizadasRepositoryFactory();
        }else if(objDao == PerfilFuncionalidadesPersonalizadasRepository.class){
            return (T) new PerfilFuncionalidadesPersonalizadasRepositoryFactory();
        }else{
            throw new IllegalArgumentException();
        }
    }
     public abstract <T> T createRepository();

}
