/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.state.escolha_funcionalidade_projeto.AEscolhaFuncionalidadeProjetoState;
import br.projeto.state.escolha_funcionalidade_projeto.AtualizacaoEscolhaFuncionalidadesProjetoState;
import br.projeto.state.escolha_funcionalidade_projeto.InsercaoEscolhaFuncionalidadesProjetoState;
import br.projeto.view.ManterProjetoDeEstimativaView;
import com.log.model.LogRegister;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class EscolhaFuncionalidadesProjetoPresenter {
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository; 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    
    private Integer projetoId;
    
    private List<Integer> perfisSelecionados;
    
    private final ManterProjetoDeEstimativaView view;
    
    private AEscolhaFuncionalidadeProjetoState estado;

    public EscolhaFuncionalidadesProjetoPresenter(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        
        this.view = new ManterProjetoDeEstimativaView();
    }
    
    public void setEstadoInicial(){
        if(projetoId == null){
            this.estado = new InsercaoEscolhaFuncionalidadesProjetoState(this, perfisSelecionados);
        }else{
            this.estado = new AtualizacaoEscolhaFuncionalidadesProjetoState(this, perfisSelecionados, projetoId);
        }
    }
    
    public void setPerfisSelecionados(List<Integer> perfisSelecionados) {
        this.perfisSelecionados = perfisSelecionados;
    }
    
    public void setProjetoId(Integer projetoId){
        this.projetoId = projetoId;
    }
    
    public void confirmar(){
        estado.confirmar();
    }
    

    public ProjetoDeEstimativaRepository getProjetoDeEstimativaRepository() {
        return projetoDeEstimativaRepository;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public ProjetoFuncionalidadesPersonalizadasRepository getProjetoFuncionalidadesPersonalizadasRepository() {
        return projetoFuncionalidadesPersonalizadasRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }

    public PerfilProjetoIntermediariaRepository getPerfilProjetoIntermediariaRepository() {
        return perfilProjetoIntermediariaRepository;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public ManterProjetoDeEstimativaView getView() {
        return view;
    }
    
    public String getTxtNomeProjetoEstimativa(){
        return view.getTxtNomeProjetoEstimativa().getText();
    }
    
    public String getTxtCustoGarantia(){
        return view.getTxtCustoGarantia().getText().trim();
    }
    
    public String getTxtCustoHardwareEInstalacoesFisicas(){
        return view.getTxtCustoHardwareEInstalacoesFisicas().getText().trim();
    }
    
    public String getTxtCustoRiscos(){
        return view.getTxtCustoRiscos().getText().trim();
    }
    
    public String getTxtCustoSoftware(){
        return view.getTxtCustoSoftware().getText().trim();
    }
    
    public String getTxtFundoReserva(){
        return view.getTxtFundoReserva().getText().trim();
    }
    
    public String getTxtOutrosCustos(){
        return view.getTxtOutrosCustos().getText().trim();
    }
    
    public String getTxtPercentualComImpostos(){
        return view.getTxtPercentualComImpostos().getText().trim();
    }
    
    public String getTxtPercentualLucroDesejado(){
        return view.getTxtPercentualLucroDesejado().getText().trim();
    }
    
    public JTable getTable(){
        return view.getTable();
    }
    
    public JButton getBtnConfirmar(){
        return view.getBtnConfirmar();
    }
    
}
