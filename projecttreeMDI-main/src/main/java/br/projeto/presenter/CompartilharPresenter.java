package br.projeto.presenter;

import br.projeto.command.CompartilharCommand;
import br.projeto.view.TelaCompartilharView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.projeto.model.UsuarioModel;
import br.projeto.observer.LogNotifier;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.service.ProjetoLogService;
import com.log.model.LogRegister;
import javax.swing.JOptionPane;

public final class CompartilharPresenter {
    private final TelaCompartilharView telaCompartilhar;
    private final Integer projetoID;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final UsuarioModel usuarioModel;
    
    private final ProjetoLogService projetoLogService;
    
    public CompartilharPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, Integer projetoID, LogNotifier logNotifier) {
        this.projetoLogService = new ProjetoLogService(logNotifier, usuarioModel.getFormatoLOG());
        this.telaCompartilhar = new TelaCompartilharView();
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
        this.projetoID = projetoID;
        configurarTela();
    }

    public void configurarTela(){
        telaCompartilhar.setVisible(false);
        telaCompartilhar.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    CompartilharCommand compartilharCommand = new CompartilharCommand(CompartilharPresenter.this,perfilProjetoDeEstimativaRepository, projetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository, perfilProjetoIntermediariaRepository, usuarioModel, projetoID);
                    compartilharCommand.execute();   
                    JOptionPane.showMessageDialog(null, "Projeto compartilhado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    
                    LogRegister logRegister = new LogRegister("Compartilhar Projeto", usuarioModel.getNome(),usuarioModel.getEmail(), true, "Sucesso");
                    projetoLogService.setLogRegister(logRegister);
                    projetoLogService.notificar();
                    
                }catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, 
                        ex.getMessage(), 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    
                    LogRegister logRegister = new LogRegister("Compartilhar Projeto", usuarioModel.getNome(),usuarioModel.getEmail(), false, ex.getMessage());
                    projetoLogService.setLogRegister(logRegister);
                    projetoLogService.notificar();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Ocorreu um erro inesperado. Tente novamente.", 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    
                    LogRegister logRegister = new LogRegister("Compartilhar Projeto", usuarioModel.getNome(),usuarioModel.getEmail(), false, "Ocorreu um erro inesperado. Tente novamente.");
                    projetoLogService.setLogRegister(logRegister);
                    projetoLogService.notificar();  
                    
                }
            }      
         });
        telaCompartilhar.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                telaCompartilhar.dispose();
            }       
        });       
        telaCompartilhar.setVisible(true);
    }    
    public String getEmail(){
        return telaCompartilhar.getTxtEmail().getText().trim();
    }   
  
    public TelaCompartilharView getView(){
        return telaCompartilhar;
    }  
}
    

