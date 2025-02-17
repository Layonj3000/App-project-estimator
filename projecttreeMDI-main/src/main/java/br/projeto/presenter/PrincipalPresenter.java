package br.projeto.presenter;

import br.projeto.command.*;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.presenter.window_command.*;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
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
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;//NOVO    
    private final ConstrutorDeArvoreNavegacaoService construtorDeArvoreNavegacaoService;
    private final Map<String, ProjetoCommand> comandos;
    private final List<WindowCommand> windowCommands = new ArrayList<>();

    public PrincipalPresenter(ProjetoRepositoryMock repository, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository) {
        this.view = new PrincipalView();
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;//NOVO
        this.projetoDeEstimativaRepository.addObserver(this);//NOVO
        
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;//NOVO
        this.perfilProjetoDeEstimativaRepository.addObserver(this);//NOVO
        
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
//        this.perfilProjetoIntermediariaRepository.addObserver(this);
        
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


    private Map<String, ProjetoCommand> inicializarComandos() {
        Map<String, ProjetoCommand> comandos = new HashMap<>();
        comandos.put("Principal", new AbrirDashboardProjetoCommand(view.getDesktop(), repository));
        comandos.put("Usuário", new AbrirInternalFrameGenericoProjetoCommand(view.getDesktop(), "Usuário"));
        comandos.put("Ver perfis de projeto", new AbrirInternalFrameGenericoProjetoCommand(view.getDesktop(), "Ver Perfis de Projetos"));
        comandos.put("Elaborar estimativa", new MostrarMensagemProjetoCommand("Elaborar estimativa ainda não implementada"));
        comandos.put("Visualizar estimativa", new MostrarMensagemProjetoCommand("Visualizar estimativa ainda não implementada"));
        comandos.put("Compartilhar projeto de estimativa", new MostrarMensagemProjetoCommand("Compartilhar ainda não implementado"));
        comandos.put("Exportar projeto de estimativa", new MostrarMensagemProjetoCommand("Exportar ainda não implementado"));
        comandos.put("Novo projeto", new CriarProjetoProjetoCommand(repository, view.getDesktop()));
        comandos.put("Excluir projeto", new ExcluirProjetoProjetoCommand(projetoDeEstimativaRepository, perfilProjetoIntermediariaRepository));
        comandos.put("Abrir detalhes", new AbrirDetalhesProjetoProjetoCommand(repository,projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository, view.getDesktop()));
        return comandos;
    }

    public void configurarArvore() {
        NoArvoreComposite raiz = construtorDeArvoreNavegacaoService.criarNo("Principal", "principal", comandos.get("Principal"));
        NoArvoreComposite noUsuario = construtorDeArvoreNavegacaoService.criarNo("Usuário", "usuario", comandos.get("Usuário"));
        NoArvoreComposite noPerfis = construtorDeArvoreNavegacaoService.criarNo("Ver perfis de projeto", "perfil", comandos.get("Ver perfis de projeto"));
        NoArvoreComposite noProjetos = construtorDeArvoreNavegacaoService.criarNo("Projetos", "projeto", null);

        noProjetos.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem novoProjetoItem = new JMenuItem("Novo Projeto");
            novoProjetoItem.addActionListener(e -> {
                ProjetoCommand cmd = comandos.get("Novo projeto");
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
            AbrirDetalhesProjetoProjetoCommand cmdDetalhes = new AbrirDetalhesProjetoProjetoCommand(repository,projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository, view.getDesktop()) {
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
    }

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
    
        private void adicionarMenuContextual(ProjetoDeEstimativaModel projeto, NoArvoreComposite noProjeto) {//NOVO
        noProjeto.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirProjetoItem = new JMenuItem("Excluir Projeto");
            excluirProjetoItem.addActionListener(e -> {
                ProjetoCommand cmdExcluir = new ExcluirProjetoProjetoCommand(projetoDeEstimativaRepository, projeto.getId());
                cmdExcluir.execute();
            });
            menu.add(excluirProjetoItem);
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


    public Map<String, ProjetoCommand> getComandos() {
        return comandos;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }

    public PrincipalView getView() {
        return view;
    }

    @Override
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel) {
            SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetoDeEstimativaModel);
            fecharJanelasCommand.execute();
            configurarArvore();
        });
    }

    @Override
    public void update(List<Projeto> projetos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
