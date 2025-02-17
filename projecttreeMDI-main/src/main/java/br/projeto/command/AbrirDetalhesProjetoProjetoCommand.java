package br.projeto.command;

import br.projeto.presenter.DetalheProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.DetalheProjetoView;

import javax.swing.*;

public class AbrirDetalhesProjetoProjetoCommand implements ProjetoCommand {
    private final ProjetoRepositoryMock repository;//ANTIGO
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO
    private final JDesktopPane desktop;
    private Integer projetoId;
    private String projetoNome;

    public AbrirDetalhesProjetoProjetoCommand(ProjetoRepositoryMock repository,ProjetoDeEstimativaRepository projetoDeEstimativaRepository,PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, JDesktopPane desktop) {
        this.repository = repository;
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.desktop = desktop;
    }

    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {
        if (projetoId == null) {
            throw new IllegalStateException("O nome do projeto não foi definido para este comando.");
        }

        String tituloJanela = "Detalhes do Projeto: " + projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa();//VERIFICAR MELHORES FORMAS DE IMPLEMENTAR DEPOIS TALVEZ FIRA DEMETER
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DetalheProjetoView detalheView = new DetalheProjetoView();
            detalheView.setTitle(tituloJanela);
            new DetalheProjetoPresenter(detalheView, repository, projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository,projetoId, projetoNome);
            desktop.add(detalheView);
            detalheView.setVisible(true);
            try {
                detalheView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
