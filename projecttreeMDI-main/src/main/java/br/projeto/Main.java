package br.projeto;

import br.projeto.db.DB;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.repository_factory.RepositoryFactory;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        RepositoryJDBCFactory factory = RepositoryJDBCFactory.escolherClasseFabricada(ProjetoDeEstimativaRepositoryJDBC.class);
//        ProjetoDeEstimativaRepositoryJDBC projetoDeEstimativaDaoJDBC = factory.createRepositoryJDBC();
//        System.out.println(projetoDeEstimativaDaoJDBC.findAll());
        
        SwingUtilities.invokeLater(() -> {
            PrincipalPresenter presenter = new PrincipalPresenter(new ProjetoRepositoryMock());
            WindowManager.getInstance().initialize(presenter);
        });
    }
}


