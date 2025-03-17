package br.projeto.presenter;

import br.projeto.command.excluir.ExcluirProjetoCommand;
import br.projeto.command.excluir.ExcluirPerfilCommand;
import br.projeto.command.abrir.AbrirInternalFrameGenericoProjetoCommand;
import br.projeto.command.abrir.AbrirDetalhesUsuarioCommand;
import br.projeto.command.abrir.AbrirTelaCompartilharCommand;
import br.projeto.command.abrir.AbrirTelaInicialAplicacao;
import br.projeto.command.abrir.AbrirCriacaoPerfilCommand;
import br.projeto.command.abrir.AbrirTelaExportacaoCommand;
import br.projeto.command.abrir.AbrirAtualizacaoPerfilCommand;
import br.projeto.command.abrir.AbrirCriacaoProjetoCommand;
import br.projeto.command.abrir.AbrirAtualizacaoProjetoCommand;
import br.projeto.command.abrir.AbrirDetalhesProjetoCommand;
import br.projeto.command.abrir.AbrirDetalhesPerfilCommand;
import br.projeto.command.*;
import br.projeto.command.abrir.AbrirTelaConfiguracaoCommand;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.presenter.window_command.*;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.service.ConstrutorDeArvoreNavegacaoService;
import br.projeto.service.InstanciaRepositoryService;
import br.projeto.service.NoArvoreComposite;
import br.projeto.view.GlobalWindowManager;
import br.projeto.view.PrincipalView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public final class PrincipalPresenter extends Observer {
    private final PrincipalView view;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository; 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    private final ConstrutorDeArvoreNavegacaoService construtorDeArvoreNavegacaoService;
    private final Map<String, Command> comandos;
    private final List<WindowCommand> windowCommands = new ArrayList<>();
    private InstanciaRepositoryService repositoryService = InstanciaRepositoryService.getInstancia();
    
    
    public PrincipalPresenter(UsuarioModel usuarioModel) {
        this.view = new PrincipalView();
        this.projetoDeEstimativaRepository = repositoryService.getProjetoDeEstimativaRepository();
        this.projetoDeEstimativaRepository.addObserver(this);
        
        this.perfilProjetoDeEstimativaRepository = repositoryService.getPerfilProjetoDeEstimativaRepository();
        this.perfilProjetoDeEstimativaRepository.addObserver(this);
        
        this.projetoFuncionalidadesPersonalizadasRepository = repositoryService.getProjetoFuncionalidadesPersonalizadasRepository();
        
        this.perfilFuncionalidadesPersonalizadasRepository = repositoryService.getPerfilFuncionalidadesPersonalizadasRepository();
        
        this.perfilProjetoIntermediariaRepository = repositoryService.getPerfilPerfilProjetoIntermediariaRepository();
        
        this.usuarioModel = usuarioModel;

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
        comandos.put("Principal", new AbrirTelaInicialAplicacao(view.getDesktop()));
        comandos.put("Usuário", new AbrirDetalhesUsuarioCommand(view.getDesktop(), "Usuário", usuarioModel));
        comandos.put("Perfis", new AbrirInternalFrameGenericoProjetoCommand(view.getDesktop(), "Perfis"));
        comandos.put("Configuração", new AbrirTelaConfiguracaoCommand(usuarioModel));
        comandos.put("Compartilhar projeto de estimativa",new AbrirTelaCompartilharCommand(perfilProjetoDeEstimativaRepository,projetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel));
        comandos.put("Exportar projeto de estimativa", new AbrirTelaExportacaoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel));
        comandos.put("Atualizar projeto",new AbrirAtualizacaoProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel));
        comandos.put("Novo projeto", new AbrirCriacaoProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel));
        comandos.put("Novo perfil", new AbrirCriacaoPerfilCommand(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel));
        comandos.put("Atualizar perfil", new AbrirAtualizacaoPerfilCommand(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel));
        comandos.put("Excluir projeto", new ExcluirProjetoCommand(projetoDeEstimativaRepository,usuarioModel));
        comandos.put("Excluir Perfil", new ExcluirPerfilCommand(perfilProjetoDeEstimativaRepository));
        comandos.put("Abrir detalhes", new AbrirDetalhesProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, view.getDesktop()));
        return comandos;
    }
    
        public void configurarArvore() {
        NoArvoreComposite raiz = construtorDeArvoreNavegacaoService.criarNo("Principal", "principal", comandos.get("Principal"));
        NoArvoreComposite noUsuario = construtorDeArvoreNavegacaoService.criarNo("Usuário", "usuario", comandos.get("Usuário"));
        NoArvoreComposite noPerfis = construtorDeArvoreNavegacaoService.criarNo("Perfis", "perfil", null);
        NoArvoreComposite noProjetos = construtorDeArvoreNavegacaoService.criarNo("Projetos", "projeto", null);
        NoArvoreComposite noConfiguracao = construtorDeArvoreNavegacaoService.criarNo("Configuração", "configuracao", comandos.get("Configuração"));

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
        raiz.adicionarFilho(noConfiguracao);
        /*CONSTRUINDO ARVORE HIERARQUICA*/

        
        /*PROJETO PARTE 2*/
        List<ProjetoDeEstimativaModel> listaProjetos = projetoDeEstimativaRepository.findByUser(usuarioModel);
        for (ProjetoDeEstimativaModel projeto : listaProjetos) {
            AbrirDetalhesProjetoCommand cmdDetalhes = new AbrirDetalhesProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository, projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository, perfilProjetoIntermediariaRepository, view.getDesktop()) {
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
            cmdDetalhes.setProjetoNome(projeto.getNomeProjetoDeEstimativa());
            NoArvoreComposite noProjeto = construtorDeArvoreNavegacaoService.criarNo(projeto.getNomeProjetoDeEstimativa(), "projeto", cmdDetalhes);

            adicionarMenuContextual(projeto, noProjeto);
            
            AbrirAtualizacaoProjetoCommand atualizarProjetoCommand = new AbrirAtualizacaoProjetoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel);
            atualizarProjetoCommand.setProjetoId(projeto.getId());
            
            AbrirTelaCompartilharCommand compartilharCommand = new AbrirTelaCompartilharCommand(perfilProjetoDeEstimativaRepository,projetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, usuarioModel);
            compartilharCommand.setProjetoId(projeto.getId());
            
            AbrirTelaExportacaoCommand exportarCommand = new AbrirTelaExportacaoCommand(projetoDeEstimativaRepository,perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository,perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository,usuarioModel);
            exportarCommand.setIdProjeto(projeto.getId());
            exportarCommand.setNomeProjeto(projeto.getNomeProjetoDeEstimativa());
            
            if(projeto.getCompartilhadoValor() == 0){
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Atualizar projeto de estimativa", "action", atualizarProjetoCommand));
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Compartilhar projeto de estimativa", "action", compartilharCommand));
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Exportar projeto de estimativa", "action", exportarCommand));
            }

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
            cmdDetalhes.setPerfilNome(perfil.getNomePerfil());
            NoArvoreComposite noPerfil = construtorDeArvoreNavegacaoService.criarNo(perfil.getNomePerfil(), "perfil", cmdDetalhes);

            adicionarMenuContextual(perfil, noPerfil);
            
            AbrirAtualizacaoPerfilCommand atualizarPerfilCommand = new AbrirAtualizacaoPerfilCommand(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel);
            atualizarPerfilCommand.setIdPerfil(perfil.getId());
            noPerfil.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Atualizar perfil", "action", atualizarPerfilCommand));
                
            noPerfis.adicionarFilho(noPerfil);
        }
        /*PERFIL PARTE 2*/

        DefaultMutableTreeNode modeloArvore = construtorDeArvoreNavegacaoService.converterParaNoMutavel(raiz);
        JTree arvore = construtorDeArvoreNavegacaoService.criarJTreeDoModelo(modeloArvore);
        view.setTree(arvore);
    }
        
        //PROJETO DE ESTIMATIVA
        private void adicionarMenuContextual(ProjetoDeEstimativaModel projeto, NoArvoreComposite noProjeto) {
        noProjeto.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirProjetoItem = new JMenuItem("Excluir Projeto");
            excluirProjetoItem.addActionListener(e -> {
                Command cmdExcluir = new ExcluirProjetoCommand(projetoDeEstimativaRepository, projeto.getId(), usuarioModel);
                try{
                cmdExcluir.execute();
                }catch(IllegalArgumentException ex){
                    new MostrarMensagemCommand("Id do projeto não definido: " + ex.getMessage()).execute();
                }
            });
            menu.add(excluirProjetoItem);
            return menu;
        });
      }
        
      //PERFIL DE ESTIMATIVA
        private void adicionarMenuContextual(PerfilProjetoDeEstimativaModel perfil, NoArvoreComposite noPerfil) {
        noPerfil.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirPerfilItem = new JMenuItem("Excluir Perfil");
            excluirPerfilItem.addActionListener(e -> {
                Command cmdExcluir = new ExcluirPerfilCommand(perfilProjetoDeEstimativaRepository, perfil.getId());
                try{
                cmdExcluir.execute();
                }catch(IllegalArgumentException ex){
                    new MostrarMensagemCommand("Id do perfil não definido: " + ex.getMessage()).execute();
                }
            });
            menu.add(excluirPerfilItem);
            return menu;
        });
      }


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
            new MostrarMensagemCommand("Nenhum estado anterior salvo para restaurar.").execute();
        }
    }


    public Map<String, Command> getComandos() {
        return comandos;
    }


    public PrincipalView getView() {
        return view;
    }

    
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

}
