package br.projeto.repository_factory;

import br.projeto.repository.PerfilProjetoDeEstimativaRepositoryJDBC;
import br.projeto.repository.PerfilProjetoIntermediariaRepositoryJDBC;
import br.projeto.repository.ProjetoDeEstimativaRepositoryJDBC;
import br.projeto.repository.UsuarioRepositoryJDBC;

public abstract class RepositoryJDBCFactory {

    public static <T> T escolherClasseFabricada(Object objDao) {
        if(objDao == PerfilProjetoDeEstimativaRepositoryJDBC.class) {
            return (T) new PerfilProjetoDeEstimativaRepositoryJDBCFactory();
        }else if(objDao == ProjetoDeEstimativaRepositoryJDBC.class) {
            return (T) new ProjetoDeEstimativaRepositoryJDBCFactory();
        }else if(objDao == PerfilProjetoIntermediariaRepositoryJDBC.class) {
            return (T) new PerfilProjetoIntermediariaRepositoryJDBCFactory();
        }else if(objDao == UsuarioRepositoryJDBC.class){
            return (T) new UsuarioRepositoryJDBCFactory();
        }else{
            throw new IllegalArgumentException();
        }
    }
     public abstract <T> T createRepositoryJDBC();

}
