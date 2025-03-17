package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.EscolhaPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.service.LogStrategyService;

public class AbrirCriacaoProjetoCommand implements Command{
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository; 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;

    private final UsuarioModel usuarioModel;
    
    private final LogStrategyService logStrategyService;


    public AbrirCriacaoProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
                
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository =perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        
        logStrategyService = new LogStrategyService(usuarioModel); 
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
        logStrategyService.gerarLOG("Criação");
    }

}
