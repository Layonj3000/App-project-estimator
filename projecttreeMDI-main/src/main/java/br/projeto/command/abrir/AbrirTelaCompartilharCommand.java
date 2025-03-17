package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.CompartilharPresenter;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;

public class AbrirTelaCompartilharCommand implements Command{
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final UsuarioModel usuarioModel;
    private Integer projetoId;
    
    public AbrirTelaCompartilharCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoDeEstimativaRepository projetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }
    
    @Override
    public void execute() {
        new CompartilharPresenter(perfilProjetoDeEstimativaRepository, projetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilProjetoIntermediariaRepository, usuarioModel, projetoId);
    }
    
}
