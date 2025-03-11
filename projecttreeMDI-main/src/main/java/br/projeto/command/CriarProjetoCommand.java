package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.EscolhaPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import com.log.adaptador.LogConfig;
import com.log.model.LogRegister;

public class CriarProjetoCommand implements Command {
    /*private final ProjetoRepositoryMock repository;*/
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    
    /*private final JDesktopPane desktop;*/
    private final UsuarioModel usuarioModel;
    //private final CriarProjetoMock criarProjetoMock;

    public CriarProjetoCommand(/*ProjetoRepositoryMock repository,*/ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,/* JDesktopPane desktop,*/PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        /*this.repository = repository;*/
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository =perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        /*this.desktop = desktop;*/
        this.usuarioModel = usuarioModel;
        //this.criarProjetoMock = new CriarProjetoMock(repository);//LEMBRAR DE EXCLUIR CLASSE
    }

    
    @Override
    public void execute() {
        //IMPLEMENTAR NOVA LOGICA DE CRIACAO
        EscolhaPerfilPresenter escolhaPerfilPresenter = new EscolhaPerfilPresenter(projetoDeEstimativaRepository,
                perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository,
                perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,usuarioModel);
        escolhaPerfilPresenter.setIdProjeto(null);
        escolhaPerfilPresenter.setEstadoInicial();

        // Registro Log
        LogConfig.getInstance().setLogFormat(usuarioModel.getFormatoLOG());
        LogConfig.getInstance().novoRegistro(new LogRegister("Criação de Projeto", usuarioModel.getNome(), usuarioModel.getEmail(), true, "Sucesso"));
        // Arrumar a parte de tratamento de exceção para mensagem do erro
        // LogConfig.getInstance().novoRegistro(new LogRegister("Criação de Projeto", usuarioModel.getNome(), usuarioModel.getEmail(), false, "Mensagem do Erro (tratamento de exceção)"));



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
