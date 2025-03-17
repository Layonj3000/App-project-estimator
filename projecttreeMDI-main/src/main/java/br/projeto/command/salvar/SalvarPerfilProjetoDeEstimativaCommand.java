/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command.salvar;

import br.projeto.command.Command;
import br.projeto.db.DbException;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.service.retornar.RetornaPerfilModelService;
import br.projeto.service.AuxiliarTelaPerfilService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class SalvarPerfilProjetoDeEstimativaCommand implements Command{
    private final EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;
    private final AuxiliarTelaPerfilService auxiliarService;
    private final Integer idPerfil;
    
    Double taxaDiariaDesenvolvimento = null;
    Double taxaDiariaGerenciaProjeto = null;
    Double taxaDiariaDesign = null;
    
    JTable tabela;
    String nomePerfil; 

    public SalvarPerfilProjetoDeEstimativaCommand(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
        this.auxiliarService = AuxiliarTelaPerfilService.getInstance();
        this.idPerfil = idPerfil;
        
        this.tabela = escolhaFuncionalidadesPerfilPresenter.getTable();
        this.nomePerfil = escolhaFuncionalidadesPerfilPresenter.getTxtNomePerfil();
    }
    
        @Override
        public void execute(){

            verificaDadosTabela();

            Map<String, Integer> mapPerfil = auxiliarService.criarMapPerfil(tabela);
            RetornaPerfilModelService retornaPerfilModelService = new RetornaPerfilModelService(mapPerfil);
            PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = retornaPerfilModelService.instantiatePerfilComMap();

            verificaDadosTxtFields(perfilProjetoDeEstimativaModel);

            setExtrasPerfil(perfilProjetoDeEstimativaModel, nomePerfil, taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign);
            
            try{
                if(idPerfil == null){
                    escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().insert(perfilProjetoDeEstimativaModel);
                }else{
                /*LOGICA PARA UPDATE*/
                    perfilProjetoDeEstimativaModel.setId(idPerfil);
                    if(!escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().findByPerfilProjetoEstimativa(perfilProjetoDeEstimativaModel).isEmpty()){
                        escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().deleteByPerfilProjetoDeEstimativa(perfilProjetoDeEstimativaModel);
                    }

                    escolhaFuncionalidadesPerfilPresenter.getPerfilProjetoDeEstimativaRepository().update(perfilProjetoDeEstimativaModel);
                /*LOGICA PARA UPDATE*/
                }
            }catch(DbException e){
                throw new DbException(e.getMessage());
            }    
            
            inserirFuncionalidadesPersonalizadas(retornaPerfilModelService);

        }

        //TUDO QUE NÃO É FUNCIONALIDADE
        private void setExtrasPerfil(PerfilProjetoDeEstimativaModel perfil, String nomePerfil, Double taxaDev, Double taxaGer, Double taxaDes) {
           perfil.setTaxaDiariaDesenvolvimento(taxaDev);
           perfil.setTaxaDiariaDesign(taxaDes);
           perfil.setTaxaDiariaGerenciaProjeto(taxaGer);
           perfil.setNomePerfil(nomePerfil);
           perfil.setUsuarioModel(escolhaFuncionalidadesPerfilPresenter.getUsuarioModel());
           perfil.setDataCriacao(new Date(System.currentTimeMillis()));
        }

        private void inserirFuncionalidadesPersonalizadas(RetornaPerfilModelService service) {
           List<PerfilFuncionalidadesPersonalizadasModel> lista = service.getFuncionalidadesPersonalizadas();
           try{
           if (lista != null) {
               for (PerfilFuncionalidadesPersonalizadasModel model : lista) {
                  escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(model);
               }
           }}catch(DbException e){
                throw new DbException(e.getMessage());
            }
        }
        
        private void verificaDadosTabela(){
            try {
                auxiliarService.encerrarEdicaoCelula(tabela);
                auxiliarService.verificarValoresInconsistentesTabela(tabela);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }
        
        private void verificaDadosTxtFields(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel){
            try {
                              
            if (auxiliarService.verificaPreenchimentoTaxaDev(escolhaFuncionalidadesPerfilPresenter) || auxiliarService.verificaPreenchimentoNome(nomePerfil) ||
                auxiliarService.verificaPreenchimentoTaxaGerProjetos(escolhaFuncionalidadesPerfilPresenter, perfilProjetoDeEstimativaModel)){
                    throw new IllegalArgumentException("Preenchimento obrigatório não realizado!");
                } else {
                    taxaDiariaDesenvolvimento = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaDesenvolvimento());
                    taxaDiariaGerenciaProjeto = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaGerenciaProjeto());
                    taxaDiariaDesign = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaDesign());
                }
            } catch (NumberFormatException e) {
                    throw new NumberFormatException(e.getMessage());
            }
        }
        
        

    }
    
    

