package br.projeto.presenter;

import br.projeto.command.*;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.presenter.window_command.*;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.service.ConstrutorDeArvoreNavegacaoService;
import br.projeto.service.NoArvoreComposite;
import br.projeto.view.GlobalWindowManager;
import br.projeto.view.PrincipalView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public final class PrincipalPresenter implements Observer {
    private final PrincipalView view;
    private final ProjetoRepositoryMock repository;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    private final ConstrutorDeArvoreNavegacaoService construtorDeArvoreNavegacaoService;
    private final Map<String, Command> comandos;
    private final List<WindowCommand> windowCommands = new ArrayList<>();
    
    /*ATUALIZAR*/
    private AtualizarProjetoCommand atualizarProjetoCommand;
    /*ATUALIZAR*/
    
    public PrincipalPresenter(ProjetoRepositoryMock repository, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        this.view = new PrincipalView();
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;//NOVO
        this.projetoDeEstimativaRepository.addObserver(this);//NOVO
        
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;//NOVO
        this.perfilProjetoDeEstimativaRepository.addObserver(this);//NOVO
        
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;//NOVO
        //this.projetoFuncionalidadesPersonalizadasRepository.addObserver(this);//NOVO
        
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;//NOVO
        /*this.perfilFuncionalidadesPersonalizadasRepository.addObserver(this);//NOVO*/
        
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        
        this.usuarioModel = usuarioModel;
        
        this.repository = repository;
        this.repository.addObserver(this);

        this.construtorDeArvoreNavegacaoService = new ConstrutorDeArvoreNavegacaoService();

        GlobalWindowManager.initialize(view);

        
        
        this.comandos = inicializarComandos();

        inicializarEExecutarWindowCommands();
        view.setVisible(true);
    }

    private void inicializarEExecutarWindowCommands() {
        Arrays.asList(
                new ConfigurarViewCommand(this),
                new ConfigurarMenuJanelaCommand(this),
                new SetLookAndFeelCommand()
        ).forEach(WindowCommand::execute);
    }


    private Map<String, Command> inicializarComandos() {
        Map<String, Command> comandos = new HashMap<>();
        comandos.put("Principal", new AbrirDashboardProjetoCommand(view.getDesktop(), repository));
        comandos.put("Usuário", new AbrirInternalFrameGenericoProjetoCommand(view.getDesktop(), "Usuário"));
        comandos.put("Ver perfis de projeto", new AbrirInternalFrameGenericoProjetoCommand(view.getDesktop(), "Ver Perfis de Projetos"));
        //comandos.put("Elaborar estimativa", new MostrarMensagemProjetoCommand("Elaborar estimativa ainda não implementada"));
        //comandos.put("Visualizar estimativa", new MostrarMensagemProjetoCommand("Visualizar estimativa ainda não implementada"));
        comandos.put("Compartilhar projeto de estimativa", new MostrarMensagemProjetoCommand("Compartilhar ainda não implementado"));
        comandos.put("Exportar projeto de estimativa", new MostrarMensagemProjetoCommand("Exportar ainda não implementado"));
        /*novo comando "Atualizar Projeto"*/
        //comandos.put("Atualizar projeto",atualizarProjetoCommand);//new AtualizarProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel));//atualizarProjetoCommand /*new AtualizarProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel)*/);
        /*novo comando "Atualizar Projeto"*/
        comandos.put("Novo projeto", new CriarProjetoProjetoCommand(/*repository,*/ projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository/*,view.getDesktop()*/,perfilProjetoIntermediariaRepository, usuarioModel));
        /*novo comando "Novo Perfil"*/
        comandos.put("Novo perfil", new CriarPerfilCommand(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository/*, view.getDesktop()*/, usuarioModel));
        /*novo comando "Novo Perfil"*/
        comandos.put("Excluir projeto", new ExcluirProjetoProjetoCommand(projetoDeEstimativaRepository));
        comandos.put("Abrir detalhes", new AbrirDetalhesProjetoProjetoCommand(/*repository,*/projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository, view.getDesktop()));
        return comandos;
    }
    
        public void configurarArvore() {
        NoArvoreComposite raiz = construtorDeArvoreNavegacaoService.criarNo("Principal", "principal", comandos.get("Principal"));
        NoArvoreComposite noUsuario = construtorDeArvoreNavegacaoService.criarNo("Usuário", "usuario", comandos.get("Usuário"));
        NoArvoreComposite noPerfis = construtorDeArvoreNavegacaoService.criarNo("Ver perfis de projeto", "perfil", null);
        NoArvoreComposite noProjetos = construtorDeArvoreNavegacaoService.criarNo("Projetos", "projeto", null);
        
        //PROJETO PARTE 1(ADICIONANDO MENU "Novo Projeto" ao clicar com botao direto)
        noProjetos.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem novoProjetoItem = new JMenuItem("Novo Projeto");
            novoProjetoItem.addActionListener(e -> {
                Command cmd = comandos.get("Novo projeto");
                if (cmd != null) {
                    cmd.execute();
                }
            });
            menu.add(novoProjetoItem);
            return menu;
        });
        //PROJETO PARTE 1
        
        //PERFIL PARTE 1
        noPerfis.setMenuContextual(()-> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem novoPerfilItem = new JMenuItem("Novo Perfil");
            novoPerfilItem.addActionListener(e -> {
                Command cmd = comandos.get("Novo perfil");
                if(cmd != null){
                    cmd.execute();
                }
            });
            menu.add(novoPerfilItem);
            return menu;
        });
        //PERFIL PARTE 1 
        
        /*CONSTRUINDO ESTRUTURA HIERARQUICA*/
        raiz.adicionarFilho(noUsuario);
        raiz.adicionarFilho(noPerfis);
        raiz.adicionarFilho(noProjetos);
        /*CONSTRUINDO ARVORE HIERARQUICA*/

        
        /*PROJETO PARTE 2*/
        //List<Projeto> listaProjetos = repository.getProjetos();//ANTIGO
        List<ProjetoDeEstimativaModel> listaProjetos = projetoDeEstimativaRepository.findByUser(usuarioModel);
        //for (final Projeto projeto : listaProjetos) {//ANTIGO
        for (ProjetoDeEstimativaModel projeto : listaProjetos) {
            AbrirDetalhesProjetoProjetoCommand cmdDetalhes = new AbrirDetalhesProjetoProjetoCommand(/*repository,*/projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository, view.getDesktop()) {
                @Override
                public void execute() {
                    String tituloJanela = "Detalhes do Projeto: " + projeto.getNomeProjetoDeEstimativa();
                    WindowManager windowManager = WindowManager.getInstance();

                    if (!windowManager.isFrameAberto(tituloJanela)) {
                        super.execute();
                        bloquearMinimizacao(tituloJanela);
                    } else {
                        windowManager.bringToFront(tituloJanela);
                    }
                }
            };
            cmdDetalhes.setProjetoId(projeto.getId());
            cmdDetalhes.setProjetoNome(projeto.getNomeProjetoDeEstimativa());//VERIFICAR POSSIBLIDADE DE EXCLUSAO DA LINHA
            NoArvoreComposite noProjeto = construtorDeArvoreNavegacaoService.criarNo(projeto.getNomeProjetoDeEstimativa(), "projeto", cmdDetalhes);

            adicionarMenuContextual(projeto, noProjeto);
            //noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Elaborar estimativa", "action", comandos.get("Elaborar estimativa")));
            //noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Visualizar estimativa", "action", comandos.get("Visualizar estimativa")));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Atualizar projeto de estimativa", "action", new AtualizarProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel, cmdDetalhes)));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Compartilhar projeto de estimativa", "action", comandos.get("Compartilhar projeto de estimativa")));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Exportar projeto de estimativa", "action", comandos.get("Exportar projeto de estimativa")));
            

            noProjetos.adicionarFilho(noProjeto);
        }
        /*PROJETO PARTE 2*/
        
        /*PERFIL PARTE 2*/
        List<PerfilProjetoDeEstimativaModel> listaPerfis = perfilProjetoDeEstimativaRepository.findByUser(usuarioModel);
        for(PerfilProjetoDeEstimativaModel perfil: listaPerfis){
            AbrirDetalhesPerfilCommand cmdDetalhes = new AbrirDetalhesPerfilCommand(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, view.getDesktop()){
                @Override
                public void execute(){
                    String tituloJanela = "Detalhes do Perfil: " + perfil.getNomePerfil();
                    WindowManager windowManager = WindowManager.getInstance();

                    if (!windowManager.isFrameAberto(tituloJanela)) {
                        super.execute();
                        bloquearMinimizacao(tituloJanela);
                    } else {
                        windowManager.bringToFront(tituloJanela);
                    }
                }
            };
            cmdDetalhes.setPerfilId(perfil.getId());
            cmdDetalhes.setPerfilNome(perfil.getNomePerfil());//VERIFICAR POSSIBLIDADE DE EXCLUSAO DA LINHA
            NoArvoreComposite noPerfil = construtorDeArvoreNavegacaoService.criarNo(perfil.getNomePerfil(), "perfil", cmdDetalhes);

            adicionarMenuContextual(perfil, noPerfil);

//            noPerfil.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Elaborar estimativa", "action", comandos.get("Elaborar estimativa")));
//            noPerfil.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Visualizar estimativa", "action", comandos.get("Visualizar estimativa")));
//            noPerfil.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Compartilhar projeto de estimativa", "action", comandos.get("Compartilhar projeto de estimativa")));
            noPerfil.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Atualizar perfil", "action", comandos.get("Atualizar perfil")));
                
            noPerfis.adicionarFilho(noPerfil);
        }
        /*PERFIL PARTE 2*/

        DefaultMutableTreeNode modeloArvore = construtorDeArvoreNavegacaoService.converterParaNoMutavel(raiz);
        JTree arvore = construtorDeArvoreNavegacaoService.criarJTreeDoModelo(modeloArvore);
        view.setTree(arvore);
    }

    /*public void configurarArvore() {
        NoArvoreComposite raiz = construtorDeArvoreNavegacaoService.criarNo("Principal", "principal", comandos.get("Principal"));
        NoArvoreComposite noUsuario = construtorDeArvoreNavegacaoService.criarNo("Usuário", "usuario", comandos.get("Usuário"));
        NoArvoreComposite noPerfis = construtorDeArvoreNavegacaoService.criarNo("Ver perfis de projeto", "perfil", comandos.get("Ver perfis de projeto"));
        NoArvoreComposite noProjetos = construtorDeArvoreNavegacaoService.criarNo("Projetos", "projeto", null);

        noProjetos.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem novoProjetoItem = new JMenuItem("Novo Projeto");
            novoProjetoItem.addActionListener(e -> {
                Command cmd = comandos.get("Novo projeto");
                if (cmd != null) {
                    cmd.execute();
                }
            });
            menu.add(novoProjetoItem);
            return menu;
        });

        raiz.adicionarFilho(noUsuario);
        raiz.adicionarFilho(noPerfis);
        raiz.adicionarFilho(noProjetos);

        //List<Projeto> listaProjetos = repository.getProjetos();//ANTIGO
        List<ProjetoDeEstimativaModel> listaProjetos = projetoDeEstimativaRepository.findAll();
        //for (final Projeto projeto : listaProjetos) {//ANTIGO
        for (ProjetoDeEstimativaModel projeto : listaProjetos) {
            AbrirDetalhesProjetoProjetoCommand cmdDetalhes = new AbrirDetalhesProjetoProjetoCommand(/*repository,*//*projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository, view.getDesktop()) {
                @Override
                public void execute() {
                    String tituloJanela = "Detalhes do Projeto: " + projeto.getNomeProjetoDeEstimativa();
                    WindowManager windowManager = WindowManager.getInstance();

                    if (!windowManager.isFrameAberto(tituloJanela)) {
                        super.execute();
                        bloquearMinimizacao(tituloJanela);
                    } else {
                        windowManager.bringToFront(tituloJanela);
                    }
                }
            };
            cmdDetalhes.setProjetoId(projeto.getId());
            cmdDetalhes.setProjetoNome(projeto.getNomeProjetoDeEstimativa());//VERIFICAR POSSIBLIDADE DE EXCLUSAO DA LINHA
            NoArvoreComposite noProjeto = construtorDeArvoreNavegacaoService.criarNo(projeto.getNomeProjetoDeEstimativa(), "projeto", cmdDetalhes);

            adicionarMenuContextual(projeto, noProjeto);

            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Elaborar estimativa", "action", comandos.get("Elaborar estimativa")));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Visualizar estimativa", "action", comandos.get("Visualizar estimativa")));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Compartilhar projeto de estimativa", "action", comandos.get("Compartilhar projeto de estimativa")));
            noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Exportar projeto de estimativa", "action", comandos.get("Exportar projeto de estimativa")));
            noProjetos.adicionarFilho(noProjeto);
        }

        DefaultMutableTreeNode modeloArvore = construtorDeArvoreNavegacaoService.converterParaNoMutavel(raiz);
        JTree arvore = construtorDeArvoreNavegacaoService.criarJTreeDoModelo(modeloArvore);
        view.setTree(arvore);
    }*/

//    private void adicionarMenuContextual(Projeto projeto, NoArvoreComposite noProjeto) { //ANTIGO
//        noProjeto.setMenuContextual(() -> {
//            JPopupMenu menu = new JPopupMenu();
//            JMenuItem excluirProjetoItem = new JMenuItem("Excluir Projeto");
//            excluirProjetoItem.addActionListener(e -> {
//                ProjetoCommand cmdExcluir = new ExcluirProjetoProjetoCommand(repository, projeto.getNome());
//                cmdExcluir.execute();
//            });
//            menu.add(excluirProjetoItem);
//            return menu;
//        });
//    }
        
        //PROJETO DE ESTIMATIVA
        private void adicionarMenuContextual(ProjetoDeEstimativaModel projeto, NoArvoreComposite noProjeto) {//NOVO
        noProjeto.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirProjetoItem = new JMenuItem("Excluir Projeto");
            excluirProjetoItem.addActionListener(e -> {
                Command cmdExcluir = new ExcluirProjetoProjetoCommand(projetoDeEstimativaRepository, projeto.getId());
                cmdExcluir.execute();
            });
            menu.add(excluirProjetoItem);
            return menu;
        });
      }
        
      //PERFIL DE ESTIMATIVA
        private void adicionarMenuContextual(PerfilProjetoDeEstimativaModel perfil, NoArvoreComposite noPerfil) {//NOVO
        noPerfil.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirPerfilItem = new JMenuItem("Excluir Perfil");
            excluirPerfilItem.addActionListener(e -> {
                Command cmdExcluir = new ExcluirPerfilCommand(perfilProjetoDeEstimativaRepository, perfil.getId());
                cmdExcluir.execute();
            });
            menu.add(excluirPerfilItem);
            return menu;
        });
      }


/*    @Override
    public void update(final List<Projeto> listaProjetos) {
        SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetos);
            fecharJanelasCommand.execute();
            configurarArvore();
        });
    }*/

    private void bloquearMinimizacao(String titulo) {
        JInternalFrame[] frames = view.getDesktop().getAllFrames();
        for (JInternalFrame frame : frames) {
            if (frame.getTitle().equals(titulo)) {
                frame.setIconifiable(false);
            }
        }
    }

    public void restaurarJanelas() {
        DesktopMemento memento = Zelador.getInstance().restaurarEstado();
        if (memento != null) {
            memento.restore(getView().getDesktop());
            getView().revalidate();
            getView().repaint();
        } else {
            new MostrarMensagemProjetoCommand("Nenhum estado anterior salvo para restaurar.").execute();
        }
    }


    public Map<String, Command> getComandos() {
        return comandos;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }

    public PrincipalView getView() {
        return view;
    }

    
    //VERIFICAR LOGICA DE ATUALIZAÇÃO 03/03/2025
    @Override
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel) {
        SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasPerfilRelacionadasCommand(view.getDesktop(), listaPerfilProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();
            configurarArvore();
        });    }

    @Override
    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel) {
            SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasProjetoRelacionadasCommand(view.getDesktop(), listaProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();
            configurarArvore();
        });
    }

    @Override
    public void update(List<Projeto> projetos) {
        SwingUtilities.invokeLater(() -> {
            /*WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();*/
            configurarArvore();
        });    }

    /*@Override
    public void updateProjetoFuncionalidadesPersonalizadasModel(List<ProjetosFuncionalidadesPersonalizadasModel> listaProjetosFuncionalidadesPersonalizadasModel) {
        SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();
            configurarArvore();
        });    }*/

    /*@Override
    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel) {
        SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();
            configurarArvore();
        });    }*/
}
