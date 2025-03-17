package br.projeto.presenter;

import br.projeto.command.ExportarCommand;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.view.DetalheProjetoView;
import br.projeto.view.TelaExportacaoView;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public final class ExportarPresenter {
    private final TelaExportacaoView telaExportacaoView;
    private Integer idProjeto;
    private String nomeProjeto;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final DetalheProjetoPresenter detalhesProjetoPresenter;
    
    public ExportarPresenter(ProjetoDeEstimativaRepository projetoDeEstimativaRepository,PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository,PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, Integer idProjeto, String nomeProjeto) {
        this.telaExportacaoView = new TelaExportacaoView();
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;  
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        
        this.detalhesProjetoPresenter = new DetalheProjetoPresenter(new DetalheProjetoView(), projetoDeEstimativaRepository, perfilProjetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository, perfilFuncionalidadesPersonalizadasRepository,perfilProjetoIntermediariaRepository, idProjeto, nomeProjeto);

        configurarTela();
    }

    public void configurarTela() {
        telaExportacaoView.setVisible(false);
        telaExportacaoView.getBtnConfirmar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoSelecionado = (String) getFormato().getSelectedItem();
                try {
                    new ExportarCommand(formatoSelecionado, detalhesProjetoPresenter).execute();
                    JOptionPane.showMessageDialog(telaExportacaoView, "Exportação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(telaExportacaoView, "Erro ao exportar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                telaExportacaoView.dispose();
            }
        });
        telaExportacaoView.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaExportacaoView.dispose();
            }
        });
        telaExportacaoView.setVisible(true);
    }

    public JComboBox<String> getFormato() {
        return telaExportacaoView.getCmEscolherFormato();
    }

    public TelaExportacaoView getView() {
        return telaExportacaoView;
    }
}