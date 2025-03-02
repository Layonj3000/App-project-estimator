package br.projeto.repository;

import br.projeto.repository.abstr.IProjetoDeEstimativaRepository;
import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjetoDeEstimativaRepository implements Subject, IProjetoDeEstimativaRepository {//TESTAR MUDANÇA NA TIPAGEM DE OUTROS CUSTOS
    //CONSIDERAR COLOCAR TODOS OS METODOS DE INSTANTIATE EM UMA SERVICE
    private Connection conn;
    private List<Projeto> projetos;//VERIFICAR RETIRADA
    private List<Observer> observers;
    private List<ProjetoDeEstimativaModel> projetosDeEstimativaModel;
    
    public ProjetoDeEstimativaRepository(Connection conn) {    
        this.conn = conn;
        observers = new ArrayList<>();
        projetos = new ArrayList<>();//VERIFICAR RETIRADA
        projetosDeEstimativaModel = new ArrayList<>();
    }

    @Override
    public List<ProjetoDeEstimativaModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projetos_estimativa.*, usuario.nome, usuario.senha, usuario.email " +
                                           "FROM projetos_estimativa " +
                                           "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id"
                                      );

            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetoDeEstimativaModel> projetoDeEstimativaModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }
                ProjetoDeEstimativaModel projetoDeEstimativaModel = instantiateProjetoDeEstimativaModel(rs, usuario);
                projetoDeEstimativaModelList.add(projetoDeEstimativaModel);
            }
            return projetoDeEstimativaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    
    @Override
    public List<ProjetoDeEstimativaModel> findByUser(UsuarioModel usuarioModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projetos_estimativa.*, usuario.nome, usuario.senha, usuario.email "+
                                           "FROM projetos_estimativa " +
                                           "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id " +
                                           "WHERE usuario.id=?");
            ps.setInt(1, usuarioModel.getId());
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetoDeEstimativaModel> projetoDeEstimativaModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }
                ProjetoDeEstimativaModel projetoDeEstimativaModel = instantiateProjetoDeEstimativaModel(rs, usuario);
                projetoDeEstimativaModelList.add(projetoDeEstimativaModel);
            }
            return projetoDeEstimativaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    
    @Override
    public ProjetoDeEstimativaModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projetos_estimativa.*, usuario.nome, usuario.senha, usuario.email FROM projetos_estimativa " +
                                           "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id " +
                                           "WHERE projetos_estimativa.id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
                ProjetoDeEstimativaModel projetoDeEstimativaModel = instantiateProjetoDeEstimativaModel(rs, usuarioModel);
                //projetosDeEstimativaModel.add(projetoDeEstimativaModel);
                //notifyObservers();//VERIFICAR SEM FUNCIONA SEM
                return projetoDeEstimativaModel;
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
    public void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO projetos_estimativa(user_id, compartilhado, compartilhado_por, data_criacao, "+
                    "nome_projeto_estimativa, status, pequeno, medio, grande, " +
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
                    "custo_hardware, custo_software, custo_riscos, custo_garantia, " +
                    "fundo_de_reserva, outros_custos, sub_total, percentual_com_impostos, " +
                    "total_com_imposto, percentual_lucro_desejado, lucro_calculado, " +
                    "dias, meses, preco_final_cliente, media_por_mes) " +
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
                            "?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, "+
                            "?, ?, ?, ?, ?, ?, "+
                            "?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, projetoDeEstimativaModel.getUsuarioModel().getId());
            ps.setInt(2, projetoDeEstimativaModel.getCompartilhadoValor());
            if (projetoDeEstimativaModel.getCompartilhadoPor() == null) {
                ps.setNull(3, java.sql.Types.INTEGER); 
            } else {
                ps.setInt(3, projetoDeEstimativaModel.getCompartilhadoPor());
            }
            ps.setDate(4, projetoDeEstimativaModel.getDataCriacao());
            ps.setString(5, projetoDeEstimativaModel.getNomeProjetoDeEstimativa());
            ps.setInt(6, projetoDeEstimativaModel.getStatusValor());
            ps.setInt(7, projetoDeEstimativaModel.getPequenoValor());
            ps.setInt(8, projetoDeEstimativaModel.getMedioValor());
            ps.setInt(9, projetoDeEstimativaModel.getGrandeValor());
            ps.setInt(10, projetoDeEstimativaModel.getMvpValor());
            ps.setInt(11, projetoDeEstimativaModel.getBasicoValor());
            ps.setInt(12, projetoDeEstimativaModel.getProfissionalValor());
            ps.setInt(13, projetoDeEstimativaModel.getCadastroPorEmailSenhaValor());
            ps.setInt(14, projetoDeEstimativaModel.getCadastroPorFacebookValor());
            ps.setInt(15, projetoDeEstimativaModel.getCadastroPorTwitterValor());
            ps.setInt(16, projetoDeEstimativaModel.getCadastroPorGoogleValor());
            ps.setInt(17, projetoDeEstimativaModel.getCadastroPorLinkedinValor());
            ps.setInt(18, projetoDeEstimativaModel.getCadastroPorGithubValor());
            ps.setInt(19, projetoDeEstimativaModel.getCadastroPorConviteUsuarioValor());
            ps.setInt(20, projetoDeEstimativaModel.getCadastroPorContasMultitenantValor());
            ps.setInt(21, projetoDeEstimativaModel.getCadastroPorSubdominiosValor());
            ps.setInt(22, projetoDeEstimativaModel.getCadastroPorDominiosPersonalizadosValor());
            ps.setInt(23, projetoDeEstimativaModel.getPainelValor());
            ps.setInt(24, projetoDeEstimativaModel.getFeedDeAtividadesValor());
            ps.setInt(25, projetoDeEstimativaModel.getUploadDeArquivosValor());
            ps.setInt(26, projetoDeEstimativaModel.getUploadDeMidiaValor());
            ps.setInt(27, projetoDeEstimativaModel.getPerfisDeUsuarioValor());
            ps.setInt(28, projetoDeEstimativaModel.getEmailsTransacionaisValor());
            ps.setInt(29, projetoDeEstimativaModel.getTagsValor());
            ps.setInt(30, projetoDeEstimativaModel.getAvaliacoesOuComentariosValor());
            ps.setInt(31, projetoDeEstimativaModel.getProcessamentoAudioVideoValor());
            ps.setInt(32, projetoDeEstimativaModel.getPesquisaTextoLivreValor());
            ps.setInt(33, projetoDeEstimativaModel.getPesquisaValor());
            ps.setInt(34, projetoDeEstimativaModel.getCalendarioValor());
            ps.setInt(35, projetoDeEstimativaModel.getExibicaoDadosMapaGeolocalizacaoValor());
            ps.setInt(36, projetoDeEstimativaModel.getExibicaoMarcadoresRegioesMapaPersonalizadosValor());
            ps.setInt(37, projetoDeEstimativaModel.getReservasValor());
            ps.setInt(38, projetoDeEstimativaModel.getMensagensValor());
            ps.setInt(39, projetoDeEstimativaModel.getForunsOuComentariosValor());
            ps.setInt(40, projetoDeEstimativaModel.getCompartilhamentoSocialValor());
            ps.setInt(41, projetoDeEstimativaModel.getIntegracaoFacebookOpenGraphValor());
            ps.setInt(42, projetoDeEstimativaModel.getNotificacaoPushValor());
            ps.setInt(43, projetoDeEstimativaModel.getPlanosDeAssinaturaValor());
            ps.setInt(44, projetoDeEstimativaModel.getProcessamentoDePagamentoValor());
            ps.setInt(45, projetoDeEstimativaModel.getCarrinhoDeComprasValor());
            ps.setInt(46, projetoDeEstimativaModel.getMarketplaceDeUsuariosValor());
            ps.setInt(47, projetoDeEstimativaModel.getGerenciamentoDeProdutosValor());
            ps.setInt(48, projetoDeEstimativaModel.getComprasDentroDoAplicativoValor());
            ps.setInt(49, projetoDeEstimativaModel.getColetaInformacaoPagamentoValor());
            ps.setInt(50, projetoDeEstimativaModel.getIntegracaoCmsValor());
            ps.setInt(51, projetoDeEstimativaModel.getPaginasAdministracaoUsuariosValor());
            ps.setInt(52, projetoDeEstimativaModel.getModeracaoAprovacaoConteudoValor());
            ps.setInt(53, projetoDeEstimativaModel.getIntercomValor());
            ps.setInt(54, projetoDeEstimativaModel.getAnalisesUsoValor());
            ps.setInt(55, projetoDeEstimativaModel.getRelatoriosErroValor());
            ps.setInt(56, projetoDeEstimativaModel.getMonitoramentoPerformanceValor());
            ps.setInt(57, projetoDeEstimativaModel.getSuporteMultilingueValor());
            ps.setInt(58, projetoDeEstimativaModel.getConectarServicosDeTerceirosValor());
            ps.setInt(59, projetoDeEstimativaModel.getApiParaTerceirosValor());
            ps.setInt(60, projetoDeEstimativaModel.getEnvioSmsValor());
            ps.setInt(61, projetoDeEstimativaModel.getMascaramentoNumeroTelefoneValor());
            ps.setInt(62, projetoDeEstimativaModel.getSegurancaBaseadaCertificadoSslValor());
            ps.setInt(63, projetoDeEstimativaModel.getProtecaoContraDosValor());
            ps.setInt(64, projetoDeEstimativaModel.getAutenticacaoDuasEtapasValor());
            ps.setInt(65, projetoDeEstimativaModel.getDesenvolvimentoEspecificoAppValor());
            ps.setInt(66, projetoDeEstimativaModel.getDesignIconeAppValor());
            ps.setInt(67, projetoDeEstimativaModel.getSincronizacaoNuvemValor());
            ps.setInt(68, projetoDeEstimativaModel.getDadosSensoresDispositivoValor());
            ps.setInt(69, projetoDeEstimativaModel.getCodigoBarraQrCodeValor());
            ps.setInt(70, projetoDeEstimativaModel.getDadosSaudeValor());
            ps.setInt(71, projetoDeEstimativaModel.getAppleWatchValor());
            ps.setInt(72, projetoDeEstimativaModel.getGerenteDeProjetosValor());
            ps.setDouble(73, projetoDeEstimativaModel.getCustoHardware());
            ps.setDouble(74, projetoDeEstimativaModel.getCustoSoftware());
            ps.setDouble(75, projetoDeEstimativaModel.getCustoRiscos());
            ps.setDouble(76, projetoDeEstimativaModel.getCustoGarantia());
            ps.setDouble(77, projetoDeEstimativaModel.getFundoDeReserva());
            ps.setDouble(78, projetoDeEstimativaModel.getOutrosCustos());
            ps.setDouble(79, projetoDeEstimativaModel.getSubTotal());
            ps.setDouble(80, projetoDeEstimativaModel.getPercentualComImpostos());
            ps.setDouble(81, projetoDeEstimativaModel.getTotalComImposto());
            ps.setDouble(82, projetoDeEstimativaModel.getPercentualLucroDesejado());
            ps.setDouble(83, projetoDeEstimativaModel.getLucroCalculado());
            ps.setInt(84, projetoDeEstimativaModel.getDias());
            ps.setDouble(85, projetoDeEstimativaModel.getMeses());
            ps.setDouble(86, projetoDeEstimativaModel.getPrecoFinalCliente());
            ps.setDouble(87, projetoDeEstimativaModel.getMediaPorMes());


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    projetoDeEstimativaModel.setId(rs.getInt(1));
                    projetosDeEstimativaModel.add(projetoDeEstimativaModel);
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
    
        
//    public void insertByMap(Map<String, Integer> mapFuncionalidadesProjeto, String nomeProjeto) {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement("INSERT INTO projetos_estimativa(user_id, compartilhado, compartilhado_por, data_criacao, "+
//                    "nome_projeto_estimativa, status, pequeno, medio, grande, " +
//                    "mvp, basico, profissional, cadastro_por_email_senha, " +
//                    "cadastro_por_facebook, cadastro_por_twitter, cadastro_por_google, " +
//                    "cadastro_por_linkedin, cadastro_por_github, cadastro_por_convite_usuario, " +
//                    "cadastro_por_contas_multitenant, cadastro_por_subdominios, " +
//                    "cadastro_por_dominios_personalizados, painel, feed_de_atividades, " +
//                    "upload_de_arquivos, upload_de_midia, perfis_de_usuario, " +
//                    "emails_transacionais, tags, avaliacoes_ou_comentarios, " +
//                    "processamento_audio_video, pesquisa_texto_livre, pesquisa, " +
//                    "calendario, exibicao_dados_mapa_geolocalizacao, " +
//                    "exibicao_marcadores_regioes_mapa_personalizados, reservas, " +
//                    "mensagens, foruns_ou_comentarios, compartilhamento_social, " +
//                    "integracao_facebook_open_graph, notificacao_push, planos_de_assinatura, " +
//                    "processamento_de_pagamento, carrinho_de_compras, marketplace_de_usuarios, " +
//                    "gerenciamento_de_produtos, compras_dentro_do_aplicativo, " +
//                    "coleta_informacao_pagamento, integracao_cms, paginas_administracao_usuarios, " +
//                    "moderacao_aprovacao_conteudo, intercom, analises_uso, " +
//                    "relatorios_erro, monitoramento_performance, suporte_multilingue, " +
//                    "conectar_servicos_de_terceiros, api_para_terceiros, envio_sms, " +
//                    "mascaramento_numero_telefone, seguranca_baseada_certificado_ssl, " +
//                    "protecao_contra_dos, autenticacao_duas_etapas, desenvolvimento_especifico_app, " +
//                    "design_icone_app, sincronizacao_nuvem, dados_sensores_dispositivo, " +
//                    "codigo_barra_qr_code, dados_saude, apple_watch, gerente_de_projetos, " +
//                    "custo_hardware, custo_software, custo_riscos, custo_garantia, " +
//                    "fundo_de_reserva, outros_custos, sub_total, percentual_com_impostos, " +
//                    "total_com_imposto, percentual_lucro_desejado, lucro_calculado, " +
//                    "dias, meses, preco_final_cliente, media_por_mes) " +
//                    "VALUES( ?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, " +
//                            "?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, ?, " +
//                            "?, ?, ?, ?, "+
//                            "?, ?, ?, ?, ?, ?, "+
//                            "?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, projetoDeEstimativaModel.getUsuarioModel().getId());
//            ps.setInt(2, projetoDeEstimativaModel.getCompartilhadoValor());
//            if (projetoDeEstimativaModel.getCompartilhadoPor() == null) {
//                ps.setNull(3, java.sql.Types.INTEGER); 
//            } else {
//                ps.setInt(3, projetoDeEstimativaModel.getCompartilhadoPor());
//            }
//            ps.setDate(4, projetoDeEstimativaModel.getDataCriacao());
//            ps.setString(5, projetoDeEstimativaModel.getNomeProjetoDeEstimativa());
//            ps.setInt(6, projetoDeEstimativaModel.getStatusValor());
//            ps.setInt(7, projetoDeEstimativaModel.getPequenoValor());
//            ps.setInt(8, projetoDeEstimativaModel.getMedioValor());
//            ps.setInt(9, projetoDeEstimativaModel.getGrandeValor());
//            ps.setInt(10, projetoDeEstimativaModel.getMvpValor());
//            ps.setInt(11, projetoDeEstimativaModel.getBasicoValor());
//            ps.setInt(12, projetoDeEstimativaModel.getProfissionalValor());
//            ps.setInt(13, projetoDeEstimativaModel.getCadastroPorEmailSenhaValor());
//            ps.setInt(14, projetoDeEstimativaModel.getCadastroPorFacebookValor());
//            ps.setInt(15, projetoDeEstimativaModel.getCadastroPorTwitterValor());
//            ps.setInt(16, projetoDeEstimativaModel.getCadastroPorGoogleValor());
//            ps.setInt(17, projetoDeEstimativaModel.getCadastroPorLinkedinValor());
//            ps.setInt(18, projetoDeEstimativaModel.getCadastroPorGithubValor());
//            ps.setInt(19, projetoDeEstimativaModel.getCadastroPorConviteUsuarioValor());
//            ps.setInt(20, projetoDeEstimativaModel.getCadastroPorContasMultitenantValor());
//            ps.setInt(21, projetoDeEstimativaModel.getCadastroPorSubdominiosValor());
//            ps.setInt(22, projetoDeEstimativaModel.getCadastroPorDominiosPersonalizadosValor());
//            ps.setInt(23, projetoDeEstimativaModel.getPainelValor());
//            ps.setInt(24, projetoDeEstimativaModel.getFeedDeAtividadesValor());
//            ps.setInt(25, projetoDeEstimativaModel.getUploadDeArquivosValor());
//            ps.setInt(26, projetoDeEstimativaModel.getUploadDeMidiaValor());
//            ps.setInt(27, projetoDeEstimativaModel.getPerfisDeUsuarioValor());
//            ps.setInt(28, projetoDeEstimativaModel.getEmailsTransacionaisValor());
//            ps.setInt(29, projetoDeEstimativaModel.getTagsValor());
//            ps.setInt(30, projetoDeEstimativaModel.getAvaliacoesOuComentariosValor());
//            ps.setInt(31, projetoDeEstimativaModel.getProcessamentoAudioVideoValor());
//            ps.setInt(32, projetoDeEstimativaModel.getPesquisaTextoLivreValor());
//            ps.setInt(33, projetoDeEstimativaModel.getPesquisaValor());
//            ps.setInt(34, projetoDeEstimativaModel.getCalendarioValor());
//            ps.setInt(35, projetoDeEstimativaModel.getExibicaoDadosMapaGeolocalizacaoValor());
//            ps.setInt(36, projetoDeEstimativaModel.getExibicaoMarcadoresRegioesMapaPersonalizadosValor());
//            ps.setInt(37, projetoDeEstimativaModel.getReservasValor());
//            ps.setInt(38, projetoDeEstimativaModel.getMensagensValor());
//            ps.setInt(39, projetoDeEstimativaModel.getForunsOuComentariosValor());
//            ps.setInt(40, projetoDeEstimativaModel.getCompartilhamentoSocialValor());
//            ps.setInt(41, projetoDeEstimativaModel.getIntegracaoFacebookOpenGraphValor());
//            ps.setInt(42, projetoDeEstimativaModel.getNotificacaoPushValor());
//            ps.setInt(43, projetoDeEstimativaModel.getPlanosDeAssinaturaValor());
//            ps.setInt(44, projetoDeEstimativaModel.getProcessamentoDePagamentoValor());
//            ps.setInt(45, projetoDeEstimativaModel.getCarrinhoDeComprasValor());
//            ps.setInt(46, projetoDeEstimativaModel.getMarketplaceDeUsuariosValor());
//            ps.setInt(47, projetoDeEstimativaModel.getGerenciamentoDeProdutosValor());
//            ps.setInt(48, projetoDeEstimativaModel.getComprasDentroDoAplicativoValor());
//            ps.setInt(49, projetoDeEstimativaModel.getColetaInformacaoPagamentoValor());
//            ps.setInt(50, projetoDeEstimativaModel.getIntegracaoCmsValor());
//            ps.setInt(51, projetoDeEstimativaModel.getPaginasAdministracaoUsuariosValor());
//            ps.setInt(52, projetoDeEstimativaModel.getModeracaoAprovacaoConteudoValor());
//            ps.setInt(53, projetoDeEstimativaModel.getIntercomValor());
//            ps.setInt(54, projetoDeEstimativaModel.getAnalisesUsoValor());
//            ps.setInt(55, projetoDeEstimativaModel.getRelatoriosErroValor());
//            ps.setInt(56, projetoDeEstimativaModel.getMonitoramentoPerformanceValor());
//            ps.setInt(57, projetoDeEstimativaModel.getSuporteMultilingueValor());
//            ps.setInt(58, projetoDeEstimativaModel.getConectarServicosDeTerceirosValor());
//            ps.setInt(59, projetoDeEstimativaModel.getApiParaTerceirosValor());
//            ps.setInt(60, projetoDeEstimativaModel.getEnvioSmsValor());
//            ps.setInt(61, projetoDeEstimativaModel.getMascaramentoNumeroTelefoneValor());
//            ps.setInt(62, projetoDeEstimativaModel.getSegurancaBaseadaCertificadoSslValor());
//            ps.setInt(63, projetoDeEstimativaModel.getProtecaoContraDosValor());
//            ps.setInt(64, projetoDeEstimativaModel.getAutenticacaoDuasEtapasValor());
//            ps.setInt(65, projetoDeEstimativaModel.getDesenvolvimentoEspecificoAppValor());
//            ps.setInt(66, projetoDeEstimativaModel.getDesignIconeAppValor());
//            ps.setInt(67, projetoDeEstimativaModel.getSincronizacaoNuvemValor());
//            ps.setInt(68, projetoDeEstimativaModel.getDadosSensoresDispositivoValor());
//            ps.setInt(69, projetoDeEstimativaModel.getCodigoBarraQrCodeValor());
//            ps.setInt(70, projetoDeEstimativaModel.getDadosSaudeValor());
//            ps.setInt(71, projetoDeEstimativaModel.getAppleWatchValor());
//            ps.setInt(72, projetoDeEstimativaModel.getGerenteDeProjetosValor());
//            ps.setDouble(73, projetoDeEstimativaModel.getCustoHardware());
//            ps.setDouble(74, projetoDeEstimativaModel.getCustoSoftware());
//            ps.setDouble(75, projetoDeEstimativaModel.getCustoRiscos());
//            ps.setDouble(76, projetoDeEstimativaModel.getCustoGarantia());
//            ps.setDouble(77, projetoDeEstimativaModel.getFundoDeReserva());
//            ps.setDouble(78, projetoDeEstimativaModel.getOutrosCustos());
//            ps.setDouble(79, projetoDeEstimativaModel.getSubTotal());
//            ps.setDouble(80, projetoDeEstimativaModel.getPercentualComImpostos());
//            ps.setDouble(81, projetoDeEstimativaModel.getTotalComImposto());
//            ps.setDouble(82, projetoDeEstimativaModel.getPercentualLucroDesejado());
//            ps.setDouble(83, projetoDeEstimativaModel.getLucroCalculado());
//            ps.setInt(84, projetoDeEstimativaModel.getDias());
//            ps.setDouble(85, projetoDeEstimativaModel.getMeses());
//            ps.setDouble(86, projetoDeEstimativaModel.getPrecoFinalCliente());
//            ps.setDouble(87, projetoDeEstimativaModel.getMediaPorMes());
//
//
//            int rowsAffected = ps.executeUpdate();
//
//            if (rowsAffected > 0) {
//                rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    projetoDeEstimativaModel.setId(rs.getInt(1));
//                    projetosDeEstimativaModel.add(projetoDeEstimativaModel);
//                    notifyObservers();
//                } else {
//                    throw new DbException("Unexpected error! No rows affected!");
//                }
//            }
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        }finally {
//            DB.closeStatement(ps);
//            DB.closeResultSet(rs);
//        }
//    }
    
    

    @Override
    public void update(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE projetos_estimativa SET " +
                            "user_id = ?, compartilhado = ?, compartilhado_por = ?, data_criacao = ?, nome_projeto_estimativa = ?, status = ?, " +
                            "pequeno = ?, medio = ?, grande = ?, mvp = ?, basico = ?, profissional = ?, cadastro_por_email_senha = ?, " +
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
                            "custo_hardware = ?, custo_software = ?, custo_riscos = ?, custo_garantia = ?, " +
                            "fundo_de_reserva = ?, outros_custos = ?, sub_total = ?, percentual_com_impostos = ?, " +
                            "total_com_imposto = ?, percentual_lucro_desejado = ?, lucro_calculado = ?, " +
                            "dias = ?, meses = ?, preco_final_cliente = ?, media_por_mes = ? " +
                            "WHERE id = ?"
            );
            ps.setInt(1, projetoDeEstimativaModel.getUsuarioModel().getId());
            ps.setInt(2, projetoDeEstimativaModel.getCompartilhadoValor());
            if (projetoDeEstimativaModel.getCompartilhadoPor() == null) {
                ps.setNull(3, java.sql.Types.INTEGER); 
            } else {
                ps.setInt(3, projetoDeEstimativaModel.getCompartilhadoPor());
            }
            ps.setDate(4, projetoDeEstimativaModel.getDataCriacao());
            ps.setString(5, projetoDeEstimativaModel.getNomeProjetoDeEstimativa());
            ps.setInt(6, projetoDeEstimativaModel.getStatusValor());
            ps.setInt(7, projetoDeEstimativaModel.getPequenoValor());
            ps.setInt(8, projetoDeEstimativaModel.getMedioValor());
            ps.setInt(9, projetoDeEstimativaModel.getGrandeValor());
            ps.setInt(10, projetoDeEstimativaModel.getMvpValor());
            ps.setInt(11, projetoDeEstimativaModel.getBasicoValor());
            ps.setInt(12, projetoDeEstimativaModel.getProfissionalValor());
            ps.setInt(13, projetoDeEstimativaModel.getCadastroPorEmailSenhaValor());
            ps.setInt(14, projetoDeEstimativaModel.getCadastroPorFacebookValor());
            ps.setInt(15, projetoDeEstimativaModel.getCadastroPorTwitterValor());
            ps.setInt(16, projetoDeEstimativaModel.getCadastroPorGoogleValor());
            ps.setInt(17, projetoDeEstimativaModel.getCadastroPorLinkedinValor());
            ps.setInt(18, projetoDeEstimativaModel.getCadastroPorGithubValor());
            ps.setInt(19, projetoDeEstimativaModel.getCadastroPorConviteUsuarioValor());
            ps.setInt(20, projetoDeEstimativaModel.getCadastroPorContasMultitenantValor());
            ps.setInt(21, projetoDeEstimativaModel.getCadastroPorSubdominiosValor());
            ps.setInt(22, projetoDeEstimativaModel.getCadastroPorDominiosPersonalizadosValor());
            ps.setInt(23, projetoDeEstimativaModel.getPainelValor());
            ps.setInt(24, projetoDeEstimativaModel.getFeedDeAtividadesValor());
            ps.setInt(25, projetoDeEstimativaModel.getUploadDeArquivosValor());
            ps.setInt(26, projetoDeEstimativaModel.getUploadDeMidiaValor());
            ps.setInt(27, projetoDeEstimativaModel.getPerfisDeUsuarioValor());
            ps.setInt(28, projetoDeEstimativaModel.getEmailsTransacionaisValor());
            ps.setInt(29, projetoDeEstimativaModel.getTagsValor());
            ps.setInt(30, projetoDeEstimativaModel.getAvaliacoesOuComentariosValor());
            ps.setInt(31, projetoDeEstimativaModel.getProcessamentoAudioVideoValor());
            ps.setInt(32, projetoDeEstimativaModel.getPesquisaTextoLivreValor());
            ps.setInt(33, projetoDeEstimativaModel.getPesquisaValor());
            ps.setInt(34, projetoDeEstimativaModel.getCalendarioValor());
            ps.setInt(35, projetoDeEstimativaModel.getExibicaoDadosMapaGeolocalizacaoValor());
            ps.setInt(36, projetoDeEstimativaModel.getExibicaoMarcadoresRegioesMapaPersonalizadosValor());
            ps.setInt(37, projetoDeEstimativaModel.getReservasValor());
            ps.setInt(38, projetoDeEstimativaModel.getMensagensValor());
            ps.setInt(39, projetoDeEstimativaModel.getForunsOuComentariosValor());
            ps.setInt(40, projetoDeEstimativaModel.getCompartilhamentoSocialValor());
            ps.setInt(41, projetoDeEstimativaModel.getIntegracaoFacebookOpenGraphValor());
            ps.setInt(42, projetoDeEstimativaModel.getNotificacaoPushValor());
            ps.setInt(43, projetoDeEstimativaModel.getPlanosDeAssinaturaValor());
            ps.setInt(44, projetoDeEstimativaModel.getProcessamentoDePagamentoValor());
            ps.setInt(45, projetoDeEstimativaModel.getCarrinhoDeComprasValor());
            ps.setInt(46, projetoDeEstimativaModel.getMarketplaceDeUsuariosValor());
            ps.setInt(47, projetoDeEstimativaModel.getGerenciamentoDeProdutosValor());
            ps.setInt(48, projetoDeEstimativaModel.getComprasDentroDoAplicativoValor());
            ps.setInt(49, projetoDeEstimativaModel.getColetaInformacaoPagamentoValor());
            ps.setInt(50, projetoDeEstimativaModel.getIntegracaoCmsValor());
            ps.setInt(51, projetoDeEstimativaModel.getPaginasAdministracaoUsuariosValor());
            ps.setInt(52, projetoDeEstimativaModel.getModeracaoAprovacaoConteudoValor());
            ps.setInt(53, projetoDeEstimativaModel.getIntercomValor());
            ps.setInt(54, projetoDeEstimativaModel.getAnalisesUsoValor());
            ps.setInt(55, projetoDeEstimativaModel.getRelatoriosErroValor());
            ps.setInt(56, projetoDeEstimativaModel.getMonitoramentoPerformanceValor());
            ps.setInt(57, projetoDeEstimativaModel.getSuporteMultilingueValor());
            ps.setInt(58, projetoDeEstimativaModel.getConectarServicosDeTerceirosValor());
            ps.setInt(59, projetoDeEstimativaModel.getApiParaTerceirosValor());
            ps.setInt(60, projetoDeEstimativaModel.getEnvioSmsValor());
            ps.setInt(61, projetoDeEstimativaModel.getMascaramentoNumeroTelefoneValor());
            ps.setInt(62, projetoDeEstimativaModel.getSegurancaBaseadaCertificadoSslValor());
            ps.setInt(63, projetoDeEstimativaModel.getProtecaoContraDosValor());
            ps.setInt(64, projetoDeEstimativaModel.getAutenticacaoDuasEtapasValor());
            ps.setInt(65, projetoDeEstimativaModel.getDesenvolvimentoEspecificoAppValor());
            ps.setInt(66, projetoDeEstimativaModel.getDesignIconeAppValor());
            ps.setInt(67, projetoDeEstimativaModel.getSincronizacaoNuvemValor());
            ps.setInt(68, projetoDeEstimativaModel.getDadosSensoresDispositivoValor());
            ps.setInt(69, projetoDeEstimativaModel.getCodigoBarraQrCodeValor());
            ps.setInt(70, projetoDeEstimativaModel.getDadosSaudeValor());
            ps.setInt(71, projetoDeEstimativaModel.getAppleWatchValor());
            ps.setInt(72, projetoDeEstimativaModel.getGerenteDeProjetosValor());
            ps.setDouble(73, projetoDeEstimativaModel.getCustoHardware());
            ps.setDouble(74, projetoDeEstimativaModel.getCustoSoftware());
            ps.setDouble(75, projetoDeEstimativaModel.getCustoRiscos());
            ps.setDouble(76, projetoDeEstimativaModel.getCustoGarantia());
            ps.setDouble(77, projetoDeEstimativaModel.getFundoDeReserva());
            ps.setDouble(78, projetoDeEstimativaModel.getOutrosCustos());
            ps.setDouble(79, projetoDeEstimativaModel.getSubTotal());
            ps.setDouble(80, projetoDeEstimativaModel.getPercentualComImpostos());
            ps.setDouble(81, projetoDeEstimativaModel.getTotalComImposto());
            ps.setDouble(82, projetoDeEstimativaModel.getPercentualLucroDesejado());
            ps.setDouble(83, projetoDeEstimativaModel.getLucroCalculado());
            ps.setInt(84, projetoDeEstimativaModel.getDias());
            ps.setDouble(85, projetoDeEstimativaModel.getMeses());
            ps.setDouble(86, projetoDeEstimativaModel.getPrecoFinalCliente());
            ps.setDouble(87, projetoDeEstimativaModel.getMediaPorMes());
            ps.setInt(88, projetoDeEstimativaModel.getId());

            ps.executeUpdate();
            projetosDeEstimativaModel.add(projetoDeEstimativaModel);
            notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
    

    
    @Override
    public boolean deleteById(Integer id) {
        PreparedStatement stfkON = null;
        PreparedStatement ps = null;
        try {
            stfkON = conn.prepareStatement("PRAGMA foreign_keys = ON;");
            stfkON.execute();
            ps = conn.prepareStatement("DELETE FROM projetos_estimativa WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){ 
                projetosDeEstimativaModel.removeIf(item -> item.getId().equals(id));
                notifyObservers();
                return true;
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeStatement(stfkON);
        }
    }

    private UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("user_id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"));
        return usuarioModel;
    }

    private ProjetoDeEstimativaModel instantiateProjetoDeEstimativaModel(ResultSet rs, UsuarioModel usuarioModel) throws SQLException {
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
        projetoDeEstimativaModel.setSubTotal(rs.getDouble("sub_total"));
        projetoDeEstimativaModel.setPercentualComImpostos(rs.getDouble("percentual_com_impostos"));
        projetoDeEstimativaModel.setTotalComImposto(rs.getDouble("total_com_imposto"));
        projetoDeEstimativaModel.setPercentualLucroDesejado(rs.getDouble("percentual_lucro_desejado"));
        projetoDeEstimativaModel.setLucroCalculado(rs.getDouble("lucro_calculado"));
        projetoDeEstimativaModel.setDias(rs.getInt("dias"));
        projetoDeEstimativaModel.setMeses(rs.getDouble("meses"));
        projetoDeEstimativaModel.setPrecoFinalCliente(rs.getDouble("preco_final_cliente"));
        projetoDeEstimativaModel.setMediaPorMes(rs.getDouble("media_por_mes"));

        return projetoDeEstimativaModel;
    }

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
            observer.updateProjetoModel(projetosDeEstimativaModel);
        }
    }


}
