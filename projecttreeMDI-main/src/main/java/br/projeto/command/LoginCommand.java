/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author layon
 */
public class LoginCommand implements Command{
    private String email;
    private String senha;


    public LoginCommand(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    public void execute() {
        RepositoryFactory factory = RepositoryFactory.escolherClasseFabricada(ProjetoDeEstimativaRepository.class);
        ProjetoDeEstimativaRepository projetoDeEstimativaRepository = factory.createRepository();

        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();
        
        RepositoryFactory factory2 = RepositoryFactory.escolherClasseFabricada(PerfilProjetoDeEstimativaRepository.class);
        PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository = factory2.createRepository();
        RepositoryFactory factory4 = RepositoryFactory.escolherClasseFabricada(ProjetoFuncionalidadesPersonalizadasRepository.class);
        ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository = factory4.createRepository();
        
        RepositoryFactory factory5 = RepositoryFactory.escolherClasseFabricada(PerfilFuncionalidadesPersonalizadasRepository.class);
        PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository = factory5.createRepository();
        if (usuarioRepository.verificarLogin(email, senha) == true) {    
            UsuarioModel usuarioModel = usuarioRepository.findByEmailandPassword(email,senha);
            SwingUtilities.invokeLater(() -> {
                PrincipalPresenter presenter = new PrincipalPresenter(new ProjetoRepositoryMock(), projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel);
                WindowManager.getInstance().initialize(presenter);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
