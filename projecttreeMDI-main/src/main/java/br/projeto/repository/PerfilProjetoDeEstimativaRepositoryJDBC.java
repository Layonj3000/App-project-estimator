package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfilProjetoDeEstimativaRepositoryJDBC implements IPerfilProjetoDeEstimativaRepository {
    private Connection conn;

    public PerfilProjetoDeEstimativaRepositoryJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<PerfilProjetoDeEstimativaModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_projeto_estimativa.*, usuario.nome, usuario.senha, usuario.email " +
                    "FROM perfil_projeto_estimativa " +
                    "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id"
            );

            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = instantiatePerfilProjetoDeEstimativaModel(rs, usuario);
                perfilProjetoDeEstimativaModelList.add(perfilProjetoDeEstimativaModel);
            }
            return perfilProjetoDeEstimativaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<PerfilProjetoDeEstimativaModel> findByUser(UsuarioModel usuarioModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_projeto_estimativa.*, usuario.nome, usuario.senha, usuario.email " +
                    "FROM perfil_projeto_estimativa " +
                    "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id " +
                    "WHERE usuario.id=?");
            ps.setInt(1, usuarioModel.getId());

            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = instantiatePerfilProjetoDeEstimativaModel(rs, usuario);
                perfilProjetoDeEstimativaModelList.add(perfilProjetoDeEstimativaModel);
            }
            return perfilProjetoDeEstimativaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<PerfilProjetoDeEstimativaModel> findByProjetoEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = conn.prepareStatement("SELECT perfil_projeto_estimativa.*, usuario.nome, usuario.senha, usuario.email FROM perfil_projeto_estimativa "+
                    "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "+
                    "INNER JOIN perfil_projeto_intermediaria ON perfil_projeto_estimativa.id = perfil_projeto_intermediaria.perfil_id "+
                    "WHERE perfil_projeto_intermediaria.projeto_id = ?");
            ps.setInt(1, projetoDeEstimativaModel.getId());

            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = instantiatePerfilProjetoDeEstimativaModel(rs, usuario);
                perfilProjetoDeEstimativaModelList.add(perfilProjetoDeEstimativaModel);
            }
            return perfilProjetoDeEstimativaModelList;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public PerfilProjetoDeEstimativaModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM perfil_projeto_estimativa " +
                    "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id " +
                    "WHERE perfil_projeto_estimativa.id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = instantiatePerfilProjetoDeEstimativaModel(rs, usuarioModel);
                return perfilProjetoDeEstimativaModel;
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
    public void insert(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO perfil_projeto_estimativa(user_id, nome_perfil, pequeno, medio, grande, " +
                    "mvp, basico, profissional, cadastro_por_email_senha, " +
                    "cadastro_por_facebook, cadastro_por_twitter, cadastro_por_google, " +
                    "cadastro_por_linkedin, cadastro_por_github, cadastro_por_convite_usuario, " +
                    "cadastro_por_contas_multitenant, cadastro_por_subdominios, " +
                    "cadastro_por_dominios_personalizados, painel, feed_de_atividades, " +
                    "upload_de_arquivos, upload_de_midia, perfis_de_usuario, " +
                    "emails_transacionais, tags, avaliacoes_ou_comentarios, " +
                    "processamento_audio_video, pesquisa_texto_livre, pesquisa, " +
                    "calendario, exibicao_dados_mapa_geolocalizacao, " +
                    "exibicao_marcadores_regioes_mapa_personalizados, reservas, " +
                    "mensagens, foruns_ou_comentarios, compartilhamento_social, " +
                    "integracao_facebook_open_graph, notificacao_push, planos_de_assinatura, " +
                    "processamento_de_pagamento, carrinho_de_compras, marketplace_de_usuarios, " +
                    "gerenciamento_de_produtos, compras_dentro_do_aplicativo, " +
                    "coleta_informacao_pagamento, integracao_cms, paginas_administracao_usuarios, " +
                    "moderacao_aprovacao_conteudo, intercom, analises_uso, " +
                    "relatorios_erro, monitoramento_performance, suporte_multilingue, " +
                    "conectar_servicos_de_terceiros, api_para_terceiros, envio_sms, " +
                    "mascaramento_numero_telefone, seguranca_baseada_certificado_ssl, " +
                    "protecao_contra_dos, autenticacao_duas_etapas, desenvolvimento_especifico_app, " +
                    "design_icone_app, sincronizacao_nuvem, dados_sensores_dispositivo, " +
                    "codigo_barra_qr_code, dados_saude, apple_watch, gerente_de_projetos, " +
                    "taxa_diaria_design, taxa_diaria_gerencia_projeto, taxa_diaria_desenvolvimento, " +
                    "custo_hardware, custo_software, custo_riscos, custo_garantia, " +
                    "fundo_de_reserva, outros_custos) " +
                    "VALUES( ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?, " +
                    "?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, perfilProjetoDeEstimativaModel.getUsuarioModel().getId());
            ps.setString(2, perfilProjetoDeEstimativaModel.getNomePerfil());
            ps.setInt(3, perfilProjetoDeEstimativaModel.getPequeno());
            ps.setInt(4, perfilProjetoDeEstimativaModel.getMedio());
            ps.setInt(5, perfilProjetoDeEstimativaModel.getGrande());
            ps.setInt(6, perfilProjetoDeEstimativaModel.getMvp());
            ps.setInt(7, perfilProjetoDeEstimativaModel.getBasico());
            ps.setInt(8, perfilProjetoDeEstimativaModel.getProfissional());
            ps.setInt(9, perfilProjetoDeEstimativaModel.getCadastroPorEmailSenha());
            ps.setInt(10, perfilProjetoDeEstimativaModel.getCadastroPorFacebook());
            ps.setInt(11, perfilProjetoDeEstimativaModel.getCadastroPorTwitter());
            ps.setInt(12, perfilProjetoDeEstimativaModel.getCadastroPorGoogle());
            ps.setInt(13, perfilProjetoDeEstimativaModel.getCadastroPorLinkedin());
            ps.setInt(14, perfilProjetoDeEstimativaModel.getCadastroPorGithub());
            ps.setInt(15, perfilProjetoDeEstimativaModel.getCadastroPorConviteUsuario());
            ps.setInt(16, perfilProjetoDeEstimativaModel.getCadastroPorContasMultitenant());
            ps.setInt(17, perfilProjetoDeEstimativaModel.getCadastroPorSubdominios());
            ps.setInt(18, perfilProjetoDeEstimativaModel.getCadastroPorDominiosPersonalizados());
            ps.setInt(19, perfilProjetoDeEstimativaModel.getPainel());
            ps.setInt(20, perfilProjetoDeEstimativaModel.getFeedDeAtividades());
            ps.setInt(21, perfilProjetoDeEstimativaModel.getUploadDeArquivos());
            ps.setInt(22, perfilProjetoDeEstimativaModel.getUploadDeMidia());
            ps.setInt(23, perfilProjetoDeEstimativaModel.getPerfisDeUsuario());
            ps.setInt(24, perfilProjetoDeEstimativaModel.getEmailsTransacionais());
            ps.setInt(25, perfilProjetoDeEstimativaModel.getTags());
            ps.setInt(26, perfilProjetoDeEstimativaModel.getAvaliacoesOuComentarios());
            ps.setInt(27, perfilProjetoDeEstimativaModel.getProcessamentoAudioVideo());
            ps.setInt(28, perfilProjetoDeEstimativaModel.getPesquisaTextoLivre());
            ps.setInt(29, perfilProjetoDeEstimativaModel.getPesquisa());
            ps.setInt(30, perfilProjetoDeEstimativaModel.getCalendario());
            ps.setInt(31, perfilProjetoDeEstimativaModel.getExibicaoDadosMapaGeolocalizacao());
            ps.setInt(32, perfilProjetoDeEstimativaModel.getExibicaoMarcadoresRegioesMapaPersonalizados());
            ps.setInt(33, perfilProjetoDeEstimativaModel.getReservas());
            ps.setInt(34, perfilProjetoDeEstimativaModel.getMensagens());
            ps.setInt(35, perfilProjetoDeEstimativaModel.getForunsOuComentarios());
            ps.setInt(36, perfilProjetoDeEstimativaModel.getCompartilhamentoSocial());
            ps.setInt(37, perfilProjetoDeEstimativaModel.getIntegracaoFacebookOpenGraph());
            ps.setInt(38, perfilProjetoDeEstimativaModel.getNotificacaoPush());
            ps.setInt(39, perfilProjetoDeEstimativaModel.getPlanosDeAssinatura());
            ps.setInt(40, perfilProjetoDeEstimativaModel.getProcessamentoDePagamento());
            ps.setInt(41, perfilProjetoDeEstimativaModel.getCarrinhoDeCompras());
            ps.setInt(42, perfilProjetoDeEstimativaModel.getMarketplaceDeUsuarios());
            ps.setInt(43, perfilProjetoDeEstimativaModel.getGerenciamentoDeProdutos());
            ps.setInt(44, perfilProjetoDeEstimativaModel.getComprasDentroDoAplicativo());
            ps.setInt(45, perfilProjetoDeEstimativaModel.getColetaInformacaoPagamento());
            ps.setInt(46, perfilProjetoDeEstimativaModel.getIntegracaoCms());
            ps.setInt(47, perfilProjetoDeEstimativaModel.getPaginasAdministracaoUsuarios());
            ps.setInt(48, perfilProjetoDeEstimativaModel.getModeracaoAprovacaoConteudo());
            ps.setInt(49, perfilProjetoDeEstimativaModel.getIntercom());
            ps.setInt(50, perfilProjetoDeEstimativaModel.getAnalisesUso());
            ps.setInt(51, perfilProjetoDeEstimativaModel.getRelatoriosErro());
            ps.setInt(52, perfilProjetoDeEstimativaModel.getMonitoramentoPerformance());
            ps.setInt(53, perfilProjetoDeEstimativaModel.getSuporteMultilingue());
            ps.setInt(54, perfilProjetoDeEstimativaModel.getConectarServicosDeTerceiros());
            ps.setInt(55, perfilProjetoDeEstimativaModel.getApiParaTerceiros());
            ps.setInt(56, perfilProjetoDeEstimativaModel.getEnvioSms());
            ps.setInt(57, perfilProjetoDeEstimativaModel.getMascaramentoNumeroTelefone());
            ps.setInt(58, perfilProjetoDeEstimativaModel.getSegurancaBaseadaCertificadoSsl());
            ps.setInt(59, perfilProjetoDeEstimativaModel.getProtecaoContraDos());
            ps.setInt(60, perfilProjetoDeEstimativaModel.getAutenticacaoDuasEtapas());
            ps.setInt(61, perfilProjetoDeEstimativaModel.getDesenvolvimentoEspecificoApp());
            ps.setInt(62, perfilProjetoDeEstimativaModel.getDesignIconeApp());
            ps.setInt(63, perfilProjetoDeEstimativaModel.getSincronizacaoNuvem());
            ps.setInt(64, perfilProjetoDeEstimativaModel.getDadosSensoresDispositivo());
            ps.setInt(65, perfilProjetoDeEstimativaModel.getCodigoBarraQrCode());
            ps.setInt(66, perfilProjetoDeEstimativaModel.getDadosSaude());
            ps.setInt(67, perfilProjetoDeEstimativaModel.getAppleWatch());
            ps.setInt(68, perfilProjetoDeEstimativaModel.getGerenteDeProjetos());
            ps.setDouble(69, perfilProjetoDeEstimativaModel.getTaxaDiariaDesign());
            ps.setDouble(70, perfilProjetoDeEstimativaModel.getTaxaDiariaGerenciaProjeto());
            ps.setDouble(71, perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento());
            ps.setDouble(72, perfilProjetoDeEstimativaModel.getCustoHardware());
            ps.setDouble(73, perfilProjetoDeEstimativaModel.getCustoSoftware());
            ps.setDouble(74, perfilProjetoDeEstimativaModel.getCustoRiscos());
            ps.setDouble(75, perfilProjetoDeEstimativaModel.getCustoGarantia());
            ps.setDouble(76, perfilProjetoDeEstimativaModel.getFundoDeReserva());
            ps.setDouble(77, perfilProjetoDeEstimativaModel.getOutrosCustos());


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    perfilProjetoDeEstimativaModel.setId(rs.getInt(1));
                } else {
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("UPDATE perfil_projeto_estimativa SET user_id = ?, nome_perfil = ?, pequeno = ?, medio = ?, grande = ?, " +
                            "mvp = ?, basico = ?, profissional = ?, cadastro_por_email_senha = ?, " +
                            "cadastro_por_facebook = ?, cadastro_por_twitter = ?, cadastro_por_google = ?, " +
                            "cadastro_por_linkedin = ?, cadastro_por_github = ?, cadastro_por_convite_usuario = ?, " +
                            "cadastro_por_contas_multitenant = ?, cadastro_por_subdominios = ?, " +
                            "cadastro_por_dominios_personalizados = ?, painel = ?, feed_de_atividades = ?, " +
                            "upload_de_arquivos = ?, upload_de_midia = ?, perfis_de_usuario = ?, " +
                            "emails_transacionais = ?, tags = ?, avaliacoes_ou_comentarios = ?, " +
                            "processamento_audio_video = ?, pesquisa_texto_livre = ?, pesquisa = ?, " +
                            "calendario = ?, exibicao_dados_mapa_geolocalizacao = ?, " +
                            "exibicao_marcadores_regioes_mapa_personalizados = ?, reservas = ?, " +
                            "mensagens = ?, foruns_ou_comentarios = ?, compartilhamento_social = ?, " +
                            "integracao_facebook_open_graph = ?, notificacao_push = ?, planos_de_assinatura = ?, " +
                            "processamento_de_pagamento = ?, carrinho_de_compras = ?, marketplace_de_usuarios = ?, " +
                            "gerenciamento_de_produtos = ?, compras_dentro_do_aplicativo = ?, " +
                            "coleta_informacao_pagamento = ?, integracao_cms = ?, paginas_administracao_usuarios = ?, " +
                            "moderacao_aprovacao_conteudo = ?, intercom = ?, analises_uso = ?, " +
                            "relatorios_erro = ?, monitoramento_performance = ?, suporte_multilingue = ?, " +
                            "conectar_servicos_de_terceiros = ?, api_para_terceiros = ?, envio_sms = ?, " +
                            "mascaramento_numero_telefone = ?, seguranca_baseada_certificado_ssl = ?, " +
                            "protecao_contra_dos = ?, autenticacao_duas_etapas = ?, desenvolvimento_especifico_app = ?, " +
                            "design_icone_app = ?, sincronizacao_nuvem = ?, dados_sensores_dispositivo = ?, " +
                            "codigo_barra_qr_code = ?, dados_saude = ?, apple_watch = ?, gerente_de_projetos = ?, " +
                            "taxa_diaria_design = ?, taxa_diaria_gerencia_projeto = ?, taxa_diaria_desenvolvimento = ?, " +
                            "custo_hardware = ?, custo_software = ?, custo_riscos = ?, custo_garantia = ?, " +
                            "fundo_de_reserva = ?, outros_custos = ? " +
                            "WHERE id = ?"
                    , PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, perfilProjetoDeEstimativaModel.getUsuarioModel().getId());
            ps.setString(2, perfilProjetoDeEstimativaModel.getNomePerfil());
            ps.setInt(3, perfilProjetoDeEstimativaModel.getPequeno());
            ps.setInt(4, perfilProjetoDeEstimativaModel.getMedio());
            ps.setInt(5, perfilProjetoDeEstimativaModel.getGrande());
            ps.setInt(6, perfilProjetoDeEstimativaModel.getMvp());
            ps.setInt(7, perfilProjetoDeEstimativaModel.getBasico());
            ps.setInt(8, perfilProjetoDeEstimativaModel.getProfissional());
            ps.setInt(9, perfilProjetoDeEstimativaModel.getCadastroPorEmailSenha());
            ps.setInt(10, perfilProjetoDeEstimativaModel.getCadastroPorFacebook());
            ps.setInt(11, perfilProjetoDeEstimativaModel.getCadastroPorTwitter());
            ps.setInt(12, perfilProjetoDeEstimativaModel.getCadastroPorGoogle());
            ps.setInt(13, perfilProjetoDeEstimativaModel.getCadastroPorLinkedin());
            ps.setInt(14, perfilProjetoDeEstimativaModel.getCadastroPorGithub());
            ps.setInt(15, perfilProjetoDeEstimativaModel.getCadastroPorConviteUsuario());
            ps.setInt(16, perfilProjetoDeEstimativaModel.getCadastroPorContasMultitenant());
            ps.setInt(17, perfilProjetoDeEstimativaModel.getCadastroPorSubdominios());
            ps.setInt(18, perfilProjetoDeEstimativaModel.getCadastroPorDominiosPersonalizados());
            ps.setInt(19, perfilProjetoDeEstimativaModel.getPainel());
            ps.setInt(20, perfilProjetoDeEstimativaModel.getFeedDeAtividades());
            ps.setInt(21, perfilProjetoDeEstimativaModel.getUploadDeArquivos());
            ps.setInt(22, perfilProjetoDeEstimativaModel.getUploadDeMidia());
            ps.setInt(23, perfilProjetoDeEstimativaModel.getPerfisDeUsuario());
            ps.setInt(24, perfilProjetoDeEstimativaModel.getEmailsTransacionais());
            ps.setInt(25, perfilProjetoDeEstimativaModel.getTags());
            ps.setInt(26, perfilProjetoDeEstimativaModel.getAvaliacoesOuComentarios());
            ps.setInt(27, perfilProjetoDeEstimativaModel.getProcessamentoAudioVideo());
            ps.setInt(28, perfilProjetoDeEstimativaModel.getPesquisaTextoLivre());
            ps.setInt(29, perfilProjetoDeEstimativaModel.getPesquisa());
            ps.setInt(30, perfilProjetoDeEstimativaModel.getCalendario());
            ps.setInt(31, perfilProjetoDeEstimativaModel.getExibicaoDadosMapaGeolocalizacao());
            ps.setInt(32, perfilProjetoDeEstimativaModel.getExibicaoMarcadoresRegioesMapaPersonalizados());
            ps.setInt(33, perfilProjetoDeEstimativaModel.getReservas());
            ps.setInt(34, perfilProjetoDeEstimativaModel.getMensagens());
            ps.setInt(35, perfilProjetoDeEstimativaModel.getForunsOuComentarios());
            ps.setInt(36, perfilProjetoDeEstimativaModel.getCompartilhamentoSocial());
            ps.setInt(37, perfilProjetoDeEstimativaModel.getIntegracaoFacebookOpenGraph());
            ps.setInt(38, perfilProjetoDeEstimativaModel.getNotificacaoPush());
            ps.setInt(39, perfilProjetoDeEstimativaModel.getPlanosDeAssinatura());
            ps.setInt(40, perfilProjetoDeEstimativaModel.getProcessamentoDePagamento());
            ps.setInt(41, perfilProjetoDeEstimativaModel.getCarrinhoDeCompras());
            ps.setInt(42, perfilProjetoDeEstimativaModel.getMarketplaceDeUsuarios());
            ps.setInt(43, perfilProjetoDeEstimativaModel.getGerenciamentoDeProdutos());
            ps.setInt(44, perfilProjetoDeEstimativaModel.getComprasDentroDoAplicativo());
            ps.setInt(45, perfilProjetoDeEstimativaModel.getColetaInformacaoPagamento());
            ps.setInt(46, perfilProjetoDeEstimativaModel.getIntegracaoCms());
            ps.setInt(47, perfilProjetoDeEstimativaModel.getPaginasAdministracaoUsuarios());
            ps.setInt(48, perfilProjetoDeEstimativaModel.getModeracaoAprovacaoConteudo());
            ps.setInt(49, perfilProjetoDeEstimativaModel.getIntercom());
            ps.setInt(50, perfilProjetoDeEstimativaModel.getAnalisesUso());
            ps.setInt(51, perfilProjetoDeEstimativaModel.getRelatoriosErro());
            ps.setInt(52, perfilProjetoDeEstimativaModel.getMonitoramentoPerformance());
            ps.setInt(53, perfilProjetoDeEstimativaModel.getSuporteMultilingue());
            ps.setInt(54, perfilProjetoDeEstimativaModel.getConectarServicosDeTerceiros());
            ps.setInt(55, perfilProjetoDeEstimativaModel.getApiParaTerceiros());
            ps.setInt(56, perfilProjetoDeEstimativaModel.getEnvioSms());
            ps.setInt(57, perfilProjetoDeEstimativaModel.getMascaramentoNumeroTelefone());
            ps.setInt(58, perfilProjetoDeEstimativaModel.getSegurancaBaseadaCertificadoSsl());
            ps.setInt(59, perfilProjetoDeEstimativaModel.getProtecaoContraDos());
            ps.setInt(60, perfilProjetoDeEstimativaModel.getAutenticacaoDuasEtapas());
            ps.setInt(61, perfilProjetoDeEstimativaModel.getDesenvolvimentoEspecificoApp());
            ps.setInt(62, perfilProjetoDeEstimativaModel.getDesignIconeApp());
            ps.setInt(63, perfilProjetoDeEstimativaModel.getSincronizacaoNuvem());
            ps.setInt(64, perfilProjetoDeEstimativaModel.getDadosSensoresDispositivo());
            ps.setInt(65, perfilProjetoDeEstimativaModel.getCodigoBarraQrCode());
            ps.setInt(66, perfilProjetoDeEstimativaModel.getDadosSaude());
            ps.setInt(67, perfilProjetoDeEstimativaModel.getAppleWatch());
            ps.setInt(68, perfilProjetoDeEstimativaModel.getGerenteDeProjetos());
            ps.setDouble(69, perfilProjetoDeEstimativaModel.getTaxaDiariaDesign());
            ps.setDouble(70, perfilProjetoDeEstimativaModel.getTaxaDiariaGerenciaProjeto());
            ps.setDouble(71, perfilProjetoDeEstimativaModel.getTaxaDiariaDesenvolvimento());
            ps.setDouble(72, perfilProjetoDeEstimativaModel.getCustoHardware());
            ps.setDouble(73, perfilProjetoDeEstimativaModel.getCustoSoftware());
            ps.setDouble(74, perfilProjetoDeEstimativaModel.getCustoRiscos());
            ps.setDouble(75, perfilProjetoDeEstimativaModel.getCustoGarantia());
            ps.setDouble(76, perfilProjetoDeEstimativaModel.getFundoDeReserva());
            ps.setDouble(77, perfilProjetoDeEstimativaModel.getOutrosCustos());
            ps.setInt(78, perfilProjetoDeEstimativaModel.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM perfil_projeto_estimativa WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    private UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("user_id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
        return usuarioModel;
    }

    private PerfilProjetoDeEstimativaModel instantiatePerfilProjetoDeEstimativaModel(ResultSet rs, UsuarioModel usuarioModel) throws SQLException {
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
        perfilProjetoDeEstimativaModel.setCustoHardware(rs.getDouble("custo_hardware"));
        perfilProjetoDeEstimativaModel.setCustoSoftware(rs.getDouble("custo_software"));
        perfilProjetoDeEstimativaModel.setCustoRiscos(rs.getDouble("custo_riscos"));
        perfilProjetoDeEstimativaModel.setCustoGarantia(rs.getDouble("custo_garantia"));
        perfilProjetoDeEstimativaModel.setFundoDeReserva(rs.getDouble("fundo_de_reserva"));
        perfilProjetoDeEstimativaModel.setOutrosCustos(rs.getDouble("outros_custos"));

        return perfilProjetoDeEstimativaModel;

    }
}
