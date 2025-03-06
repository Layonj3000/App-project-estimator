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
import br.projeto.view.TelaLogin;
import javax.swing.SwingUtilities;

/**
 *
 * @author layon
 */
public class LoginService {

    private ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private UsuarioRepository usuarioRepository;
    private PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;

    public LoginService() {
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(ProjetoDeEstimativaRepository.class);
        projetoDeEstimativaRepository = factory1.createRepository();

        RepositoryFactory factory2 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        usuarioRepository = factory2.createRepository();

        RepositoryFactory factory3 = RepositoryFactory.escolherClasseFabricada(PerfilProjetoDeEstimativaRepository.class);
        perfilProjetoDeEstimativaRepository = factory3.createRepository();

        RepositoryFactory factory4 = RepositoryFactory.escolherClasseFabricada(ProjetoFuncionalidadesPersonalizadasRepository.class);
        projetoFuncionalidadesPersonalizadasRepository = factory4.createRepository();

        RepositoryFactory factory5 = RepositoryFactory.escolherClasseFabricada(PerfilFuncionalidadesPersonalizadasRepository.class);
        perfilFuncionalidadesPersonalizadasRepository = factory5.createRepository();
        
        RepositoryFactory factory6 = RepositoryFactory.escolherClasseFabricada(PerfilProjetoIntermediariaRepository.class);
        perfilProjetoIntermediariaRepository = factory6.createRepository();
    }

    public boolean verificarLogin(String email, String senha) {
        return usuarioRepository.verificarLogin(email, senha);
    }

    public UsuarioModel obterUsuario(String email, String senha) {
        return usuarioRepository.findByEmailandPassword(email, senha);
    }

    public ProjetoDeEstimativaRepository getProjetoDeEstimativaRepository() {
        return projetoDeEstimativaRepository;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public ProjetoFuncionalidadesPersonalizadasRepository getProjetoFuncionalidadesPersonalizadasRepository() {
        return projetoFuncionalidadesPersonalizadasRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }
    public PerfilProjetoIntermediariaRepository getPerfilPerfilProjetoIntermediariaRepository() {
        return perfilProjetoIntermediariaRepository;
    }
}
