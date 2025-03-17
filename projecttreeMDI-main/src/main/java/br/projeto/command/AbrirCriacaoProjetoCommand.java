package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.observer.LogNotifier;
import br.projeto.presenter.EscolhaPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import com.log.model.LogRegister;

public class AbrirCriacaoProjetoCommand extends ProjetoLogCommand {
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository; 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;

    private final UsuarioModel usuarioModel;

    public AbrirCriacaoProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,/* JDesktopPane desktop,*/PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, LogNotifier logNotifier) {
        super(logNotifier, usuarioModel.getFormatoLOG());
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository =perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
    }

    
    @Override
    public void execute() {
        EscolhaPerfilPresenter escolhaPerfilPresenter = new EscolhaPerfilPresenter(projetoDeEstimativaRepository,
                perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository,
                perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,usuarioModel);
        escolhaPerfilPresenter.setIdProjeto(null);
        escolhaPerfilPresenter.setEstadoInicial();
        
        logRegister();
    }
    
    private void logRegister(){
        LogRegister logRegister = new LogRegister("Criação de Projeto", usuarioModel.getNome(),
                usuarioModel.getEmail(), true, "Sucesso");

        super.setLogRegister(logRegister);
        super.execute();
    }

}
