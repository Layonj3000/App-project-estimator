/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;


/**
 *
 * @author layon
 */
public class InstanciaRepositoryService {

    private static InstanciaRepositoryService service = null;
    
    private InstanciaRepositoryService() {
        
    }
    public static InstanciaRepositoryService getInstancia(){
        if(service == null){
            service = new InstanciaRepositoryService();
        }
        return service;
    }
    public UsuarioRepository getUsuarioRepository(){
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory.createRepository();  
        return usuarioRepository;
    }

    public ProjetoDeEstimativaRepository getProjetoDeEstimativaRepository() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(ProjetoDeEstimativaRepository.class);
        ProjetoDeEstimativaRepository projetoDeEstimativaRepository = factory.createRepository();        
        return projetoDeEstimativaRepository;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(PerfilProjetoDeEstimativaRepository.class);
        PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository = factory.createRepository();        
        return perfilProjetoDeEstimativaRepository;
    }

    public ProjetoFuncionalidadesPersonalizadasRepository getProjetoFuncionalidadesPersonalizadasRepository() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(ProjetoFuncionalidadesPersonalizadasRepository.class);
        ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository = factory.createRepository();  
        return projetoFuncionalidadesPersonalizadasRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(PerfilFuncionalidadesPersonalizadasRepository.class);
        PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository = factory.createRepository();
        return perfilFuncionalidadesPersonalizadasRepository;
    }
    public PerfilProjetoIntermediariaRepository getPerfilPerfilProjetoIntermediariaRepository() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(PerfilProjetoIntermediariaRepository.class);
        PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository = factory.createRepository();
        return perfilProjetoIntermediariaRepository;
    }
    public ProjetoRepositoryMock getRepositoryMock(){
        ProjetoRepositoryMock repositoryMock = new ProjetoRepositoryMock();
        return repositoryMock;
    }
}
