package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.presenter.DetalheProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.view.DetalheProjetoView;

import javax.swing.*;

public class AbrirDetalhesProjetoCommand implements Command {
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final JDesktopPane desktop;
    private Integer projetoId;
    private String projetoNome;

    public AbrirDetalhesProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository,PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, JDesktopPane desktop) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;  
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.desktop = desktop;
    }

    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    public Integer getProjetoId() {
        return projetoId;
    }

    @Override
    public void execute() {
        if (projetoId == null) {
            throw new IllegalStateException("O nome do projeto não foi definido para este comando.");
        }

        String tituloJanela = "Detalhes do Projeto: " + projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa();
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DetalheProjetoView detalheView = new DetalheProjetoView();
            detalheView.setTitle(tituloJanela);
            new DetalheProjetoPresenter(detalheView, projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, projetoId, projetoNome);
            desktop.add(detalheView);
            detalheView.setVisible(true);
            try {
                detalheView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
