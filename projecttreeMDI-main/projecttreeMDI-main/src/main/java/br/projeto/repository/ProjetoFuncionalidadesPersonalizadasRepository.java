/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.enums.SimNao;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.repository.abstr.IProjetoFuncionalidadesPersonalizadasRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ProjetoFuncionalidadesPersonalizadasRepository implements Subject, IProjetoFuncionalidadesPersonalizadasRepository{
    private Connection conn;
    private List<Observer> observers;
    private List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelList;
    
    public ProjetoFuncionalidadesPersonalizadasRepository(Connection conn) {
        this.conn = conn;
        observers = new ArrayList<>();
        projetosFuncionalidadesPersonalizadasModelList = new ArrayList<>();    
    }
    
    

    @Override
    public List<ProjetosFuncionalidadesPersonalizadasModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                        "FROM projeto_funcionalidades_personalizadas " +
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "    
                                      );

            Map<Integer, ProjetoDeEstimativaModel> projetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelListMetodo = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoDeEstimativaModel projetoDeEstimativa = projetoDeEstimativaModelMap.get(rs.getInt("projeto_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (projetoDeEstimativa == null) {
                    projetoDeEstimativa = instantiateProjetoDeEstimativaModel(rs, usuario);
                    projetoDeEstimativaModelMap.put(projetoDeEstimativa.getId(), projetoDeEstimativa);
                }
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativa);
                projetosFuncionalidadesPersonalizadasModelListMetodo.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return projetosFuncionalidadesPersonalizadasModelListMetodo;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public ProjetosFuncionalidadesPersonalizadasModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                       "FROM projeto_funcionalidades_personalizadas "+ 
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "+  
                                        "WHERE projeto_funcionalidades_personalizadas.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
                ProjetoDeEstimativaModel projetoDeEstimativaModel = instantiateProjetoDeEstimativaModel(rs, usuarioModel);
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativaModel);
                return projetosFuncionalidadesPersonalizadasModel;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<ProjetosFuncionalidadesPersonalizadasModel> findByProjetoEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                        "FROM projeto_funcionalidades_personalizadas " +
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "+
                                        "WHERE projeto_id = ?"    
                                      );
            
            ps.setInt(1, projetoDeEstimativaModel.getId());

            Map<Integer, ProjetoDeEstimativaModel> projetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelListMetodo = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoDeEstimativaModel projetoDeEstimativa = projetoDeEstimativaModelMap.get(rs.getInt("projeto_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (projetoDeEstimativa == null) {
                    projetoDeEstimativa = instantiateProjetoDeEstimativaModel(rs, usuario);
                    projetoDeEstimativaModelMap.put(projetoDeEstimativa.getId(), projetoDeEstimativa);
                }
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadas = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativa);
                projetosFuncionalidadesPersonalizadasModelListMetodo.add(projetosFuncionalidadesPersonalizadas);
            }
            return projetosFuncionalidadesPersonalizadasModelListMetodo;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }        
    }

    @Override
    public void insert(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel) {
                PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO projeto_funcionalidades_personalizadas (nome, selecionado, projeto_id) "+
                                       "VALUES (?,?,?)"
                                       , PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetosFuncionalidadesPersonalizadasModel.getIdProjetoDeEstimativa());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt(1));
                    projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
                    notifyObservers();
                    //return rs.getInt(1);
                } else {
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        //return null;
    }

    @Override
    public void update(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE projeto_funcionalidades_personalizadas "+
                    "SET nome = ?, selecionado = ?"+
                    "WHERE id = ?"        
            );
            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetosFuncionalidadesPersonalizadasModel.getId());
            
            ps.executeUpdate();
            
        projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public void updateByProjetoDeEstimativa(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel,ProjetoDeEstimativaModel projetoDeEstimativaModel){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE projeto_funcionalidades_personalizadas "+
                    "SET nome = ?, selecionado = ?"+
                    "WHERE projeto_id = ?"        
            );
            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetoDeEstimativaModel.getId());
            
            ps.executeUpdate();
            
        projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM projeto_funcionalidades_personalizadas WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                //projetosFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getId().equals(id));
                //notifyObservers();
                return true;
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public boolean deleteByProjetoDeEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM projeto_funcionalidades_personalizadas WHERE projeto_id=?");
            ps.setInt(1, projetoDeEstimativaModel.getId());
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                //projetosFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getIdProjetoDeEstimativa().equals(projetoDeEstimativaModel.getId()));
                //notifyObservers();
                return true;
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    
    
    private ProjetosFuncionalidadesPersonalizadasModel instantiateProjetosFuncionalidadesPersonalizadasModel(ResultSet rs, ProjetoDeEstimativaModel projetoDeEstimativaModel) throws SQLException {
        ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new ProjetosFuncionalidadesPersonalizadasModel();
        projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt("id"));
        projetosFuncionalidadesPersonalizadasModel.setNome(rs.getString("nome"));
        projetosFuncionalidadesPersonalizadasModel.setSelecionado((rs.getInt("selecionado")));
        projetosFuncionalidadesPersonalizadasModel.setProjetoDeEstimativaModel(projetoDeEstimativaModel);
        
        return projetosFuncionalidadesPersonalizadasModel;
    }

    private ProjetoDeEstimativaModel instantiateProjetoDeEstimativaModel(ResultSet rs,  UsuarioModel usuarioModel) throws SQLException {
        ProjetoDeEstimativaModel projetoDeEstimativaModel = new ProjetoDeEstimativaModel();
        projetoDeEstimativaModel.setId(rs.getInt("id"));
        projetoDeEstimativaModel.setUsuarioModel(usuarioModel);
        projetoDeEstimativaModel.setCompartilhado(rs.getInt("compartilhado"));
        projetoDeEstimativaModel.setCompartilhadoPor(rs.getInt("compartilhado_por"));
        long timestamp = rs.getLong("data_criacao");
        Date data = new Date(timestamp);
        projetoDeEstimativaModel.setDataCriacao(data);
        projetoDeEstimativaModel.setNomeProjetoDeEstimativa(rs.getString("nome_projeto_estimativa"));
        projetoDeEstimativaModel.setStatus(rs.getInt("status"));
        projetoDeEstimativaModel.setPequeno(rs.getInt("pequeno"));
        projetoDeEstimativaModel.setMedio(rs.getInt("medio"));
        projetoDeEstimativaModel.setGrande(rs.getInt("grande"));
        projetoDeEstimativaModel.setMvp(rs.getInt("mvp"));
        projetoDeEstimativaModel.setBasico(rs.getInt("basico"));
        projetoDeEstimativaModel.setProfissional(rs.getInt("profissional"));
        projetoDeEstimativaModel.setCadastroPorEmailSenha(rs.getInt("cadastro_por_email_senha"));
        projetoDeEstimativaModel.setCadastroPorFacebook(rs.getInt("cadastro_por_facebook"));
        projetoDeEstimativaModel.setCadastroPorTwitter(rs.getInt("cadastro_por_twitter"));
        projetoDeEstimativaModel.setCadastroPorGoogle(rs.getInt("cadastro_por_google"));
        projetoDeEstimativaModel.setCadastroPorLinkedin(rs.getInt("cadastro_por_linkedin"));
        projetoDeEstimativaModel.setCadastroPorGithub(rs.getInt("cadastro_por_github"));
        projetoDeEstimativaModel.setCadastroPorConviteUsuario(rs.getInt("cadastro_por_convite_usuario"));
        projetoDeEstimativaModel.setCadastroPorContasMultitenant(rs.getInt("cadastro_por_contas_multitenant"));
        projetoDeEstimativaModel.setCadastroPorSubdominios(rs.getInt("cadastro_por_subdominios"));
        projetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(rs.getInt("cadastro_por_dominios_personalizados"));
        projetoDeEstimativaModel.setPainel(rs.getInt("painel"));
        projetoDeEstimativaModel.setFeedDeAtividades(rs.getInt("feed_de_atividades"));
        projetoDeEstimativaModel.setUploadDeArquivos(rs.getInt("upload_de_arquivos"));
        projetoDeEstimativaModel.setUploadDeMidia(rs.getInt("upload_de_midia"));
        projetoDeEstimativaModel.setPerfisDeUsuario(rs.getInt("perfis_de_usuario"));
        projetoDeEstimativaModel.setEmailsTransacionais(rs.getInt("emails_transacionais"));
        projetoDeEstimativaModel.setTags(rs.getInt("tags"));
        projetoDeEstimativaModel.setAvaliacoesOuComentarios(rs.getInt("avaliacoes_ou_comentarios"));
        projetoDeEstimativaModel.setProcessamentoAudioVideo(rs.getInt("processamento_audio_video"));
        projetoDeEstimativaModel.setPesquisaTextoLivre(rs.getInt("pesquisa_texto_livre"));
        projetoDeEstimativaModel.setPesquisa(rs.getInt("pesquisa"));
        projetoDeEstimativaModel.setCalendario(rs.getInt("calendario"));
        projetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(rs.getInt("exibicao_dados_mapa_geolocalizacao"));
        projetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(rs.getInt("exibicao_marcadores_regioes_mapa_personalizados"));
        projetoDeEstimativaModel.setReservas(rs.getInt("reservas"));
        projetoDeEstimativaModel.setMensagens(rs.getInt("mensagens"));
        projetoDeEstimativaModel.setForunsOuComentarios(rs.getInt("foruns_ou_comentarios"));
        projetoDeEstimativaModel.setCompartilhamentoSocial(rs.getInt("compartilhamento_social"));
        projetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(rs.getInt("integracao_facebook_open_graph"));
        projetoDeEstimativaModel.setNotificacaoPush(rs.getInt("notificacao_push"));
        projetoDeEstimativaModel.setPlanosDeAssinatura(rs.getInt("planos_de_assinatura"));
        projetoDeEstimativaModel.setProcessamentoDePagamento(rs.getInt("processamento_de_pagamento"));
        projetoDeEstimativaModel.setCarrinhoDeCompras(rs.getInt("carrinho_de_compras"));
        projetoDeEstimativaModel.setMarketplaceDeUsuarios(rs.getInt("marketplace_de_usuarios"));
        projetoDeEstimativaModel.setGerenciamentoDeProdutos(rs.getInt("gerenciamento_de_produtos"));
        projetoDeEstimativaModel.setComprasDentroDoAplicativo(rs.getInt("compras_dentro_do_aplicativo"));
        projetoDeEstimativaModel.setColetaInformacaoPagamento(rs.getInt("coleta_informacao_pagamento"));
        projetoDeEstimativaModel.setIntegracaoCms((rs.getInt("integracao_cms")));
        projetoDeEstimativaModel.setPaginasAdministracaoUsuarios(rs.getInt("paginas_administracao_usuarios"));
        projetoDeEstimativaModel.setModeracaoAprovacaoConteudo(rs.getInt("moderacao_aprovacao_conteudo"));
        projetoDeEstimativaModel.setIntercom(rs.getInt("intercom"));
        projetoDeEstimativaModel.setAnalisesUso(rs.getInt("analises_uso"));
        projetoDeEstimativaModel.setRelatoriosErro(rs.getInt("relatorios_erro"));
        projetoDeEstimativaModel.setMonitoramentoPerformance(rs.getInt("monitoramento_performance"));
        projetoDeEstimativaModel.setSuporteMultilingue(rs.getInt("suporte_multilingue"));
        projetoDeEstimativaModel.setConectarServicosDeTerceiros(rs.getInt("conectar_servicos_de_terceiros"));
        projetoDeEstimativaModel.setApiParaTerceiros(rs.getInt("api_para_terceiros"));
        projetoDeEstimativaModel.setEnvioSms(rs.getInt("envio_sms"));
        projetoDeEstimativaModel.setMascaramentoNumeroTelefone(rs.getInt("mascaramento_numero_telefone"));
        projetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(rs.getInt("seguranca_baseada_certificado_ssl"));
        projetoDeEstimativaModel.setProtecaoContraDos(rs.getInt("protecao_contra_dos"));
        projetoDeEstimativaModel.setAutenticacaoDuasEtapas(rs.getInt("autenticacao_duas_etapas"));
        projetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(rs.getInt("desenvolvimento_especifico_app"));
        projetoDeEstimativaModel.setDesignIconeApp(rs.getInt("design_icone_app"));
        projetoDeEstimativaModel.setSincronizacaoNuvem(rs.getInt("sincronizacao_nuvem"));
        projetoDeEstimativaModel.setDadosSensoresDispositivo(rs.getInt("dados_sensores_dispositivo"));
        projetoDeEstimativaModel.setCodigoBarraQrCode(rs.getInt("codigo_barra_qr_code"));
        projetoDeEstimativaModel.setDadosSaude(rs.getInt("dados_saude"));
        projetoDeEstimativaModel.setAppleWatch(rs.getInt("apple_watch"));
        projetoDeEstimativaModel.setGerenteDeProjetos(rs.getInt("gerente_de_projetos"));
        projetoDeEstimativaModel.setCustoHardware(rs.getDouble("custo_hardware"));
        projetoDeEstimativaModel.setCustoSoftware(rs.getDouble("custo_software"));
        projetoDeEstimativaModel.setCustoRiscos(rs.getDouble("custo_riscos"));
        projetoDeEstimativaModel.setCustoGarantia(rs.getDouble("custo_garantia"));
        projetoDeEstimativaModel.setFundoDeReserva(rs.getDouble("fundo_de_reserva"));
        projetoDeEstimativaModel.setOutrosCustos(rs.getDouble("outros_custos"));
        /*projetoDeEstimativaModel.setSubTotal(rs.getDouble("sub_total"));*/
        projetoDeEstimativaModel.setPercentualComImpostos(rs.getDouble("percentual_com_impostos"));
        /*projetoDeEstimativaModel.setTotalComImposto(rs.getDouble("total_com_imposto"));*/
        projetoDeEstimativaModel.setPercentualLucroDesejado(rs.getDouble("percentual_lucro_desejado"));
        /*projetoDeEstimativaModel.setLucroCalculado(rs.getDouble("lucro_calculado"));
        projetoDeEstimativaModel.setDias(rs.getInt("dias"));
        projetoDeEstimativaModel.setMeses(rs.getDouble("meses"));
        projetoDeEstimativaModel.setPrecoFinalCliente(rs.getDouble("preco_final_cliente"));
        projetoDeEstimativaModel.setMediaPorMes(rs.getDouble("media_por_mes"));*/

        return projetoDeEstimativaModel;
    }

    private UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("user_id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
        return usuarioModel;    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);   
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            ///observer.update(projetos);
            observer.updateProjetoFuncionalidadesPersonalizadasModel(projetosFuncionalidadesPersonalizadasModelList);
        }    
    }


    
}
