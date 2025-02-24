/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.repository.abstr.IPerfilFuncionalidadesPersonalizadasRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class PerfilFuncionalidadesPersonalizadasRepository implements Subject, IPerfilFuncionalidadesPersonalizadasRepository{

    private Connection conn;
    private List<Observer> observers;
    private List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList;
    
    public PerfilFuncionalidadesPersonalizadasRepository(Connection conn) {
        this.conn = conn;
        observers = new ArrayList<>();
        perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();    
    }
    
    

    @Override
    public List<PerfilFuncionalidadesPersonalizadasModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                        "FROM perfil_funcionalidades_personalizadas " +
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "    
                                      );

            Map<Integer, PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                PerfilProjetoDeEstimativaModel perfilDeEstimativa = perfilProjetoDeEstimativaModelMap.get(rs.getInt("perfil_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (perfilDeEstimativa == null) {
                    perfilDeEstimativa = instantiatePerfilProjetoDeEstimativaModel(rs, usuario);
                    perfilProjetoDeEstimativaModelMap.put(perfilDeEstimativa.getId(), perfilDeEstimativa);
                }
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilDeEstimativa);
                perfilFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return perfilFuncionalidadesPersonalizadasModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public PerfilFuncionalidadesPersonalizadasModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                       "FROM perfil_funcionalidades_personalizadas "+ 
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "+  
                                        "WHERE perfil_funcionalidades_personalizadas.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = instantiatePerfilProjetoDeEstimativaModel(rs, usuarioModel);
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilProjetoDeEstimativaModel);
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
    public List<PerfilFuncionalidadesPersonalizadasModel> findByPerfilProjetoEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email " +
                                        "FROM perfil_funcionalidades_personalizadas " +
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "+
                                        "WHERE perfil_id = ?"    
                                      );
            
            
            ps.setInt(1, perfilProjetoDeEstimativaModel.getId());

            Map<Integer, PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                PerfilProjetoDeEstimativaModel perfilDeEstimativa = perfilProjetoDeEstimativaModelMap.get(rs.getInt("perfil_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (perfilDeEstimativa == null) {
                    perfilDeEstimativa = instantiatePerfilProjetoDeEstimativaModel(rs, usuario);
                    perfilProjetoDeEstimativaModelMap.put(perfilDeEstimativa.getId(), perfilDeEstimativa);
                }
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilDeEstimativa);
                perfilFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return perfilFuncionalidadesPersonalizadasModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }   
    }

    @Override
    public void insert(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel) {
                PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO perfil_funcionalidades_personalizadas (nome, valor, perfil_id) "+
                                       "VALUES (?,?,?)"
                                       , PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilFuncionalidadesPersonalizadasModel.getIdPerfilProjetoDeEstimativaModel());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    perfilFuncionalidadesPersonalizadasModel.setId(rs.getInt(1));
                    perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
                    notifyObservers();
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
    }

    @Override
    public void update(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE perfil_funcionalidades_personalizadas "+
                    "SET nome = ?, valor = ?"+
                    "WHERE id = ?"        
            );
            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilFuncionalidadesPersonalizadasModel.getId());
            
            ps.executeUpdate();
            
        perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public void updateByPerfilProjetoDeEstimativa(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE perfil_funcionalidades_personalizadas "+
                    "SET nome = ?, valor = ?"+
                    "WHERE perfil_id  = ?"        
            );
            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilProjetoDeEstimativaModel.getId());
            
            ps.executeUpdate();
            
        perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
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
            ps = conn.prepareStatement("DELETE FROM perfil_funcionalidades_personalizadas WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                perfilFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getId().equals(id));
                notifyObservers();
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
    public boolean deleteByPerfilProjetoDeEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM perfil_funcionalidades_personalizadas WHERE perfil_id=?");
            ps.setInt(1, perfilProjetoDeEstimativaModel.getId());
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                perfilFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getIdPerfilProjetoDeEstimativaModel().equals(perfilProjetoDeEstimativaModel.getId()));
                notifyObservers();
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
    
    
    
    private PerfilFuncionalidadesPersonalizadasModel instantiatePerfilFuncionalidadesPersonalizadasModel(ResultSet rs, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) throws SQLException {
        PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new PerfilFuncionalidadesPersonalizadasModel();
        projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt("id"));
        projetosFuncionalidadesPersonalizadasModel.setNome(rs.getString("nome"));
        projetosFuncionalidadesPersonalizadasModel.setValor(rs.getInt("valor"));
        projetosFuncionalidadesPersonalizadasModel.setPerfilProjetoDeEstimativaModel(perfilProjetoDeEstimativaModel);
        
        return projetosFuncionalidadesPersonalizadasModel;
    }

    private PerfilProjetoDeEstimativaModel instantiatePerfilProjetoDeEstimativaModel(ResultSet rs,  UsuarioModel usuarioModel) throws SQLException {
        PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel();
        perfilProjetoDeEstimativaModel.setId(rs.getInt("id"));
        perfilProjetoDeEstimativaModel.setNomePerfil(rs.getString("nome_perfil"));
        perfilProjetoDeEstimativaModel.setUsuarioModel(usuarioModel);
        perfilProjetoDeEstimativaModel.setPequeno(rs.getInt("pequeno"));
        perfilProjetoDeEstimativaModel.setMedio(rs.getInt("medio"));
        perfilProjetoDeEstimativaModel.setGrande(rs.getInt("grande"));
        perfilProjetoDeEstimativaModel.setMvp(rs.getInt("mvp"));
        perfilProjetoDeEstimativaModel.setBasico(rs.getInt("basico"));
        perfilProjetoDeEstimativaModel.setProfissional(rs.getInt("profissional"));
        perfilProjetoDeEstimativaModel.setCadastroPorEmailSenha(rs.getInt("cadastro_por_email_senha"));
        perfilProjetoDeEstimativaModel.setCadastroPorFacebook(rs.getInt("cadastro_por_facebook"));
        perfilProjetoDeEstimativaModel.setCadastroPorTwitter(rs.getInt("cadastro_por_twitter"));
        perfilProjetoDeEstimativaModel.setCadastroPorGoogle(rs.getInt("cadastro_por_google"));
        perfilProjetoDeEstimativaModel.setCadastroPorLinkedin(rs.getInt("cadastro_por_linkedin"));
        perfilProjetoDeEstimativaModel.setCadastroPorGithub(rs.getInt("cadastro_por_github"));
        perfilProjetoDeEstimativaModel.setCadastroPorConviteUsuario(rs.getInt("cadastro_por_convite_usuario"));
        perfilProjetoDeEstimativaModel.setCadastroPorContasMultitenant(rs.getInt("cadastro_por_contas_multitenant"));
        perfilProjetoDeEstimativaModel.setCadastroPorSubdominios(rs.getInt("cadastro_por_subdominios"));
        perfilProjetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(rs.getInt("cadastro_por_dominios_personalizados"));
        perfilProjetoDeEstimativaModel.setPainel(rs.getInt("painel"));
        perfilProjetoDeEstimativaModel.setFeedDeAtividades(rs.getInt("feed_de_atividades"));
        perfilProjetoDeEstimativaModel.setUploadDeArquivos(rs.getInt("upload_de_arquivos"));
        perfilProjetoDeEstimativaModel.setUploadDeMidia(rs.getInt("upload_de_midia"));
        perfilProjetoDeEstimativaModel.setPerfisDeUsuario(rs.getInt("perfis_de_usuario"));
        perfilProjetoDeEstimativaModel.setEmailsTransacionais(rs.getInt("emails_transacionais"));
        perfilProjetoDeEstimativaModel.setTags(rs.getInt("tags"));
        perfilProjetoDeEstimativaModel.setAvaliacoesOuComentarios(rs.getInt("avaliacoes_ou_comentarios"));
        perfilProjetoDeEstimativaModel.setProcessamentoAudioVideo(rs.getInt("processamento_audio_video"));
        perfilProjetoDeEstimativaModel.setPesquisaTextoLivre(rs.getInt("pesquisa_texto_livre"));
        perfilProjetoDeEstimativaModel.setPesquisa(rs.getInt("pesquisa"));
        perfilProjetoDeEstimativaModel.setCalendario(rs.getInt("calendario"));
        perfilProjetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(rs.getInt("exibicao_dados_mapa_geolocalizacao"));
        perfilProjetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(rs.getInt("exibicao_marcadores_regioes_mapa_personalizados"));
        perfilProjetoDeEstimativaModel.setReservas(rs.getInt("reservas"));
        perfilProjetoDeEstimativaModel.setMensagens(rs.getInt("mensagens"));
        perfilProjetoDeEstimativaModel.setForunsOuComentarios(rs.getInt("foruns_ou_comentarios"));
        perfilProjetoDeEstimativaModel.setCompartilhamentoSocial(rs.getInt("compartilhamento_social"));
        perfilProjetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(rs.getInt("integracao_facebook_open_graph"));
        perfilProjetoDeEstimativaModel.setNotificacaoPush(rs.getInt("notificacao_push"));
        perfilProjetoDeEstimativaModel.setPlanosDeAssinatura(rs.getInt("planos_de_assinatura"));
        perfilProjetoDeEstimativaModel.setProcessamentoDePagamento(rs.getInt("processamento_de_pagamento"));
        perfilProjetoDeEstimativaModel.setCarrinhoDeCompras(rs.getInt("carrinho_de_compras"));
        perfilProjetoDeEstimativaModel.setMarketplaceDeUsuarios(rs.getInt("marketplace_de_usuarios"));
        perfilProjetoDeEstimativaModel.setGerenciamentoDeProdutos(rs.getInt("gerenciamento_de_produtos"));
        perfilProjetoDeEstimativaModel.setComprasDentroDoAplicativo(rs.getInt("compras_dentro_do_aplicativo"));
        perfilProjetoDeEstimativaModel.setColetaInformacaoPagamento(rs.getInt("coleta_informacao_pagamento"));
        perfilProjetoDeEstimativaModel.setIntegracaoCms((rs.getInt("integracao_cms")));
        perfilProjetoDeEstimativaModel.setPaginasAdministracaoUsuarios(rs.getInt("paginas_administracao_usuarios"));
        perfilProjetoDeEstimativaModel.setModeracaoAprovacaoConteudo(rs.getInt("moderacao_aprovacao_conteudo"));
        perfilProjetoDeEstimativaModel.setIntercom(rs.getInt("intercom"));
        perfilProjetoDeEstimativaModel.setAnalisesUso(rs.getInt("analises_uso"));
        perfilProjetoDeEstimativaModel.setRelatoriosErro(rs.getInt("relatorios_erro"));
        perfilProjetoDeEstimativaModel.setMonitoramentoPerformance(rs.getInt("monitoramento_performance"));
        perfilProjetoDeEstimativaModel.setSuporteMultilingue(rs.getInt("suporte_multilingue"));
        perfilProjetoDeEstimativaModel.setConectarServicosDeTerceiros(rs.getInt("conectar_servicos_de_terceiros"));
        perfilProjetoDeEstimativaModel.setApiParaTerceiros(rs.getInt("api_para_terceiros"));
        perfilProjetoDeEstimativaModel.setEnvioSms(rs.getInt("envio_sms"));
        perfilProjetoDeEstimativaModel.setMascaramentoNumeroTelefone(rs.getInt("mascaramento_numero_telefone"));
        perfilProjetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(rs.getInt("seguranca_baseada_certificado_ssl"));
        perfilProjetoDeEstimativaModel.setProtecaoContraDos(rs.getInt("protecao_contra_dos"));
        perfilProjetoDeEstimativaModel.setAutenticacaoDuasEtapas(rs.getInt("autenticacao_duas_etapas"));
        perfilProjetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(rs.getInt("desenvolvimento_especifico_app"));
        perfilProjetoDeEstimativaModel.setDesignIconeApp(rs.getInt("design_icone_app"));
        perfilProjetoDeEstimativaModel.setSincronizacaoNuvem(rs.getInt("sincronizacao_nuvem"));
        perfilProjetoDeEstimativaModel.setDadosSensoresDispositivo(rs.getInt("dados_sensores_dispositivo"));
        perfilProjetoDeEstimativaModel.setCodigoBarraQrCode(rs.getInt("codigo_barra_qr_code"));
        perfilProjetoDeEstimativaModel.setDadosSaude(rs.getInt("dados_saude"));
        perfilProjetoDeEstimativaModel.setAppleWatch(rs.getInt("apple_watch"));
        perfilProjetoDeEstimativaModel.setGerenteDeProjetos(rs.getInt("gerente_de_projetos"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesign(rs.getDouble("taxa_diaria_design"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaGerenciaProjeto(rs.getDouble("taxa_diaria_gerencia_projeto"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesenvolvimento(rs.getDouble("taxa_diaria_desenvolvimento"));

        return perfilProjetoDeEstimativaModel;
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
            observer.updatePerfilFuncionalidadesPersonalizadasModel(perfilFuncionalidadesPersonalizadasModelList);
        }    
    }
    
}
