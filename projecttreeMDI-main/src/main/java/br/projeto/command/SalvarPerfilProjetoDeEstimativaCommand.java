/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.service.RetornaPerfilModelService;
import br.projeto.service.AuxiliarTelaPerfilService;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author USER
 */
public class SalvarPerfilProjetoDeEstimativaCommand implements Command{
    private final EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;
    private final AuxiliarTelaPerfilService auxiliarService;
    private final Integer idPerfil;
    
    Double taxaDiariaDesenvolvimento = null;
    Double taxaDiariaGerenciaProjeto = null;
    Double taxaDiariaDesign = null;

    public SalvarPerfilProjetoDeEstimativaCommand(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter, Integer idPerfil) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
        this.auxiliarService = AuxiliarTelaPerfilService.getInstance();
        
        this.idPerfil = idPerfil;
    }
    
        @Override
        public void execute() {
           JTable tabela = escolhaFuncionalidadesPerfilPresenter.getTable();
           String nomePerfil = escolhaFuncionalidadesPerfilPresenter.getTxtNomePerfil();
           auxiliarService.encerrarEdicaoCelula(tabela);

           if (!auxiliarService.verificarValoresInconsistentes(tabela)) {
               return;
           }

           Map<String, Integer> mapPerfil = auxiliarService.criarMapPerfil(tabela);
           RetornaPerfilModelService retornaPerfilModelService = new RetornaPerfilModelService(mapPerfil);
           PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = retornaPerfilModelService.getPerfil();

        Double taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign;
            try {
                              
            if (auxiliarService.verificaPreenchimentoTaxaDev(escolhaFuncionalidadesPerfilPresenter) || auxiliarService.verificaPreenchimentoNome(nomePerfil) ||
                auxiliarService.verificaPreenchimentoTaxaGerProjetos(escolhaFuncionalidadesPerfilPresenter, perfilProjetoDeEstimativaModel)){
                    return;
                } else {
                    taxaDiariaDesenvolvimento = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaDesenvolvimento());
                    taxaDiariaGerenciaProjeto = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaGerenciaProjeto());
                    taxaDiariaDesign = auxiliarService.obterTaxa(escolhaFuncionalidadesPerfilPresenter.getTxtTaxaDiariaDesign());
                }
            } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Digite um número válido para as taxas diárias!", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
               return;
            }

            setExtrasPerfil(perfilProjetoDeEstimativaModel, nomePerfil, taxaDiariaDesenvolvimento, taxaDiariaGerenciaProjeto, taxaDiariaDesign);
            
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
            
            salvarFuncionalidadesPersonalizadas(retornaPerfilModelService);

            
            if(idPerfil == null){
                JOptionPane.showMessageDialog(null, "PERFIL CRIADO COM SUCESSO!!");
            }else{
                JOptionPane.showMessageDialog(null, "PERFIL ATUALIZADO COM SUCESSO!!");
            }
            escolhaFuncionalidadesPerfilPresenter.getView().dispose();
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

        private void salvarFuncionalidadesPersonalizadas(RetornaPerfilModelService service) {
           List<PerfilFuncionalidadesPersonalizadasModel> lista = service.getFuncionalidadesPersonalizadas();
           if (lista != null) {
               for (PerfilFuncionalidadesPersonalizadasModel model : lista) {
                  escolhaFuncionalidadesPerfilPresenter.getPerfilFuncionalidadesPersonalizadasRepository().insert(model);
               }
           }
        }
          
        
   
    }
    
    

