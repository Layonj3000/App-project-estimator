package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.observer.LogNotifier;
import br.projeto.presenter.EscolhaPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import com.log.adaptador.LogConfig;
import com.log.model.LogRegister;

public class AbrirCriacaoProjetoCommand extends ProjetoLogCommand {
    /*private final ProjetoRepositoryMock repository;*/
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
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
        //IMPLEMENTAR NOVA LOGICA DE CRIACAO
        EscolhaPerfilPresenter escolhaPerfilPresenter = new EscolhaPerfilPresenter(projetoDeEstimativaRepository,
                perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository,
                perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,usuarioModel);
        escolhaPerfilPresenter.setIdProjeto(null);
        escolhaPerfilPresenter.setEstadoInicial();

        LogRegister logRegister = new LogRegister("Criação de Projeto", usuarioModel.getNome(),
                usuarioModel.getEmail(), true, "Sucesso");

        super.setLogRegister(logRegister);
        super.execute();
    }


/*    @Override
    public void execute() {
        Optional<Projeto> projetoCriado = criarProjetoMock.criarProjetoAleatorio();

        projetoCriado.ifPresentOrElse(
                projeto -> {
                    repository.adicionarProjeto(
                            projeto.getNome(),
                            projeto.getCriador(),
                            projeto.getDataCriacao(),
                            projeto.getStatus(),
                            projeto.isCompartilhado(),
                            projeto.getCompartilhadoPor(),
                            projeto.getPerfis(),
                            projeto.getFuncionalidadesEscolhidas()
                    );
                    new MostrarMensagemProjetoCommand("Projeto \"" + projeto.getNome() + "\" criado com sucesso!").execute();
                },
                () -> new MostrarMensagemProjetoCommand("Falha ao criar o projeto.").execute());
    }*/
    
    /*public void executeTeste(){
        Optional<ProjetoDeEstimativaModel> = projetoDeEstimativaRepository.findAll();
    }*/


}
