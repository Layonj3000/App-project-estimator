/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service.retornar;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.UsuarioModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Potentini
 */
public class RetornaPerfilModelService {
    private Map<String, Integer> mapPerfil;
    private PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel;
    
    
    public RetornaPerfilModelService(Map<String, Integer> mapPerfil) {
        this.mapPerfil = mapPerfil;
        perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel();
    }

    public RetornaPerfilModelService(){}
    
    public PerfilProjetoDeEstimativaModel instantiatePerfilComMap(){
        perfilProjetoDeEstimativaModel.setPequeno(mapPerfil.get("Pequeno"));
        perfilProjetoDeEstimativaModel.setMedio(mapPerfil.get("Médio"));
        perfilProjetoDeEstimativaModel.setGrande(mapPerfil.get("Grande"));
        perfilProjetoDeEstimativaModel.setMvp(mapPerfil.get("MVP"));
        perfilProjetoDeEstimativaModel.setBasico(mapPerfil.get("Básico"));
        perfilProjetoDeEstimativaModel.setProfissional(mapPerfil.get("Profissional"));
        perfilProjetoDeEstimativaModel.setCadastroPorEmailSenha(mapPerfil.get("Cadastro por Email/Senha"));
        perfilProjetoDeEstimativaModel.setCadastroPorFacebook(mapPerfil.get("Cadastro por Facebook"));
        perfilProjetoDeEstimativaModel.setCadastroPorTwitter(mapPerfil.get("Cadastro por Twitter"));
        perfilProjetoDeEstimativaModel.setCadastroPorGoogle(mapPerfil.get("Cadastro por Google"));
        perfilProjetoDeEstimativaModel.setCadastroPorLinkedin(mapPerfil.get("Cadastro por LinkedIn"));
        perfilProjetoDeEstimativaModel.setCadastroPorGithub(mapPerfil.get("Cadastro por GitHub"));
        perfilProjetoDeEstimativaModel.setCadastroPorConviteUsuario(mapPerfil.get("Cadastro por Convite de Usuário"));
        perfilProjetoDeEstimativaModel.setCadastroPorContasMultitenant(mapPerfil.get("Cadastro por Contas Multi-tenant"));
        perfilProjetoDeEstimativaModel.setCadastroPorSubdominios(mapPerfil.get("Cadastro por Subdomínios"));
        perfilProjetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(mapPerfil.get("Cadastro por Domínios Personalizados"));
        perfilProjetoDeEstimativaModel.setPainel(mapPerfil.get("Painel"));
        perfilProjetoDeEstimativaModel.setFeedDeAtividades(mapPerfil.get("Feed de Atividades"));
        perfilProjetoDeEstimativaModel.setUploadDeArquivos(mapPerfil.get("Upload de Arquivos"));
        perfilProjetoDeEstimativaModel.setUploadDeMidia(mapPerfil.get("Upload de Mídia"));
        perfilProjetoDeEstimativaModel.setPerfisDeUsuario(mapPerfil.get("Perfis de Usuário"));
        perfilProjetoDeEstimativaModel.setEmailsTransacionais(mapPerfil.get("Emails Transacionais"));
        perfilProjetoDeEstimativaModel.setTags(mapPerfil.get("Tags"));
        perfilProjetoDeEstimativaModel.setAvaliacoesOuComentarios(mapPerfil.get("Avaliações ou Comentários"));
        perfilProjetoDeEstimativaModel.setProcessamentoAudioVideo(mapPerfil.get("Processamento de Áudio e Vídeo"));
        perfilProjetoDeEstimativaModel.setPesquisaTextoLivre(mapPerfil.get("Pesquisa Texto Livre"));
        perfilProjetoDeEstimativaModel.setPesquisa(mapPerfil.get("Pesquisa"));
        perfilProjetoDeEstimativaModel.setCalendario(mapPerfil.get("Calendário"));
        perfilProjetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(mapPerfil.get("Exibição de Dados no Mapa"));
        perfilProjetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(mapPerfil.get("Exibição de Marcadores no Mapa"));
        perfilProjetoDeEstimativaModel.setReservas(mapPerfil.get("Reservas"));
        perfilProjetoDeEstimativaModel.setMensagens(mapPerfil.get("Mensagens"));
        perfilProjetoDeEstimativaModel.setForunsOuComentarios(mapPerfil.get("Fóruns ou Comentários"));
        perfilProjetoDeEstimativaModel.setCompartilhamentoSocial(mapPerfil.get("Compartilhamento Social"));
        perfilProjetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(mapPerfil.get("Integração com Facebook Open Graph"));
        perfilProjetoDeEstimativaModel.setNotificacaoPush(mapPerfil.get("Notificação Push"));
        perfilProjetoDeEstimativaModel.setPlanosDeAssinatura(mapPerfil.get("Planos de Assinatura"));
        perfilProjetoDeEstimativaModel.setProcessamentoDePagamento(mapPerfil.get("Processamento de Pagamento"));
        perfilProjetoDeEstimativaModel.setCarrinhoDeCompras(mapPerfil.get("Carrinho de Compras"));
        perfilProjetoDeEstimativaModel.setMarketplaceDeUsuarios(mapPerfil.get("Marketplace de Usuários"));
        perfilProjetoDeEstimativaModel.setGerenciamentoDeProdutos(mapPerfil.get("Gerenciamento de Produtos"));
        perfilProjetoDeEstimativaModel.setComprasDentroDoAplicativo(mapPerfil.get("Compras dentro do Aplicativo"));
        perfilProjetoDeEstimativaModel.setColetaInformacaoPagamento(mapPerfil.get("Coleta de Informação de Pagamento"));
        perfilProjetoDeEstimativaModel.setIntegracaoCms(mapPerfil.get("Integração com CMS"));
        perfilProjetoDeEstimativaModel.setPaginasAdministracaoUsuarios(mapPerfil.get("Páginas de Administração de Usuários"));
        perfilProjetoDeEstimativaModel.setModeracaoAprovacaoConteudo(mapPerfil.get("Moderação e Aprovação de Conteúdo"));
        perfilProjetoDeEstimativaModel.setIntercom(mapPerfil.get("Intercom"));
        perfilProjetoDeEstimativaModel.setAnalisesUso(mapPerfil.get("Análises de Uso"));
        perfilProjetoDeEstimativaModel.setRelatoriosErro(mapPerfil.get("Relatórios de Erro"));
        perfilProjetoDeEstimativaModel.setMonitoramentoPerformance(mapPerfil.get("Monitoramento de Performance"));
        perfilProjetoDeEstimativaModel.setSuporteMultilingue(mapPerfil.get("Suporte Multilíngue"));
        perfilProjetoDeEstimativaModel.setConectarServicosDeTerceiros(mapPerfil.get("Conectar com Serviços de Terceiros"));
        perfilProjetoDeEstimativaModel.setApiParaTerceiros(mapPerfil.get("API para Terceiros"));
        perfilProjetoDeEstimativaModel.setEnvioSms(mapPerfil.get("Envio de SMS"));
        perfilProjetoDeEstimativaModel.setMascaramentoNumeroTelefone(mapPerfil.get("Mascaramento de Número de Telefone"));
        perfilProjetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(mapPerfil.get("Segurança Baseada em Certificado SSL"));
        perfilProjetoDeEstimativaModel.setProtecaoContraDos(mapPerfil.get("Proteção Contra DoS"));
        perfilProjetoDeEstimativaModel.setAutenticacaoDuasEtapas(mapPerfil.get("Autenticação em Duas Etapas"));
        perfilProjetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(mapPerfil.get("Desenvolvimento Específico de App"));
        perfilProjetoDeEstimativaModel.setDesignIconeApp(mapPerfil.get("Design de Ícone para App"));
        perfilProjetoDeEstimativaModel.setSincronizacaoNuvem(mapPerfil.get("Sincronização com a Nuvem"));
        perfilProjetoDeEstimativaModel.setDadosSensoresDispositivo(mapPerfil.get("Dados de Sensores do Dispositivo"));
        perfilProjetoDeEstimativaModel.setCodigoBarraQrCode(mapPerfil.get("Código de Barras ou QR Code"));
        perfilProjetoDeEstimativaModel.setDadosSaude(mapPerfil.get("Dados de Saúde"));
        perfilProjetoDeEstimativaModel.setAppleWatch(mapPerfil.get("Apple Watch"));
        perfilProjetoDeEstimativaModel.setGerenteDeProjetos(mapPerfil.get("Gerente de Projetos"));

        return perfilProjetoDeEstimativaModel;
    }
    
    public List<PerfilFuncionalidadesPersonalizadasModel> getFuncionalidadesPersonalizadas(){
        List<PerfilFuncionalidadesPersonalizadasModel> funconalidadesPersonalizadasList = new ArrayList<>();
        int contador = 0;
        for(Map.Entry<String, Integer> entrySet: mapPerfil.entrySet()){
            if(!perfilProjetoDeEstimativaModel.getFuncionalidadesDisponiveis().containsKey(entrySet.getKey())){
                contador++;
                PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel = new PerfilFuncionalidadesPersonalizadasModel();
                perfilFuncionalidadesPersonalizadasModel.setNome(entrySet.getKey());
                perfilFuncionalidadesPersonalizadasModel.setPerfilProjetoDeEstimativaModel(perfilProjetoDeEstimativaModel);
                perfilFuncionalidadesPersonalizadasModel.setValor(entrySet.getValue());
                
                funconalidadesPersonalizadasList.add(perfilFuncionalidadesPersonalizadasModel);
            }
        }
        if(contador>0){
            return funconalidadesPersonalizadasList;
        }
        return null;
    }
    
    public PerfilProjetoDeEstimativaModel instantiatePerfilComResultSet(ResultSet rs, UsuarioModel usuarioModel) throws SQLException {
        PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel();
        perfilProjetoDeEstimativaModel.setId(getIntegerOrNull(rs, "id"));
        perfilProjetoDeEstimativaModel.setNomePerfil(rs.getObject("nome_perfil", String.class));
        perfilProjetoDeEstimativaModel.setUsuarioModel(usuarioModel);
        perfilProjetoDeEstimativaModel.setPequeno(getIntegerOrNull(rs, "pequeno"));
        perfilProjetoDeEstimativaModel.setMedio(getIntegerOrNull(rs, "medio"));
        perfilProjetoDeEstimativaModel.setGrande(getIntegerOrNull(rs, "grande"));
        perfilProjetoDeEstimativaModel.setMvp(getIntegerOrNull(rs, "mvp"));
        perfilProjetoDeEstimativaModel.setBasico(getIntegerOrNull(rs, "basico"));
        perfilProjetoDeEstimativaModel.setProfissional(getIntegerOrNull(rs, "profissional"));
        perfilProjetoDeEstimativaModel.setCadastroPorEmailSenha(getIntegerOrNull(rs, "cadastro_por_email_senha"));
        perfilProjetoDeEstimativaModel.setCadastroPorFacebook(getIntegerOrNull(rs, "cadastro_por_facebook"));
        perfilProjetoDeEstimativaModel.setCadastroPorTwitter(getIntegerOrNull(rs, "cadastro_por_twitter"));
        perfilProjetoDeEstimativaModel.setCadastroPorGoogle(getIntegerOrNull(rs, "cadastro_por_google"));
        perfilProjetoDeEstimativaModel.setCadastroPorLinkedin(getIntegerOrNull(rs, "cadastro_por_linkedin"));
        perfilProjetoDeEstimativaModel.setCadastroPorGithub(getIntegerOrNull(rs, "cadastro_por_github"));
        perfilProjetoDeEstimativaModel.setCadastroPorConviteUsuario(getIntegerOrNull(rs, "cadastro_por_convite_usuario"));
        perfilProjetoDeEstimativaModel.setCadastroPorContasMultitenant(getIntegerOrNull(rs, "cadastro_por_contas_multitenant"));
        perfilProjetoDeEstimativaModel.setCadastroPorSubdominios(getIntegerOrNull(rs, "cadastro_por_subdominios"));
        perfilProjetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(getIntegerOrNull(rs, "cadastro_por_dominios_personalizados"));
        perfilProjetoDeEstimativaModel.setPainel(getIntegerOrNull(rs, "painel"));
        perfilProjetoDeEstimativaModel.setFeedDeAtividades(getIntegerOrNull(rs, "feed_de_atividades"));
        perfilProjetoDeEstimativaModel.setUploadDeArquivos(getIntegerOrNull(rs, "upload_de_arquivos"));
        perfilProjetoDeEstimativaModel.setUploadDeMidia(getIntegerOrNull(rs, "upload_de_midia"));
        perfilProjetoDeEstimativaModel.setPerfisDeUsuario(getIntegerOrNull(rs, "perfis_de_usuario"));
        perfilProjetoDeEstimativaModel.setEmailsTransacionais(getIntegerOrNull(rs, "emails_transacionais"));
        perfilProjetoDeEstimativaModel.setTags(getIntegerOrNull(rs, "tags"));
        perfilProjetoDeEstimativaModel.setAvaliacoesOuComentarios(getIntegerOrNull(rs, "avaliacoes_ou_comentarios"));
        perfilProjetoDeEstimativaModel.setProcessamentoAudioVideo(getIntegerOrNull(rs, "processamento_audio_video"));
        perfilProjetoDeEstimativaModel.setPesquisaTextoLivre(getIntegerOrNull(rs, "pesquisa_texto_livre"));
        perfilProjetoDeEstimativaModel.setPesquisa(getIntegerOrNull(rs, "pesquisa"));
        perfilProjetoDeEstimativaModel.setCalendario(getIntegerOrNull(rs, "calendario"));
        perfilProjetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(getIntegerOrNull(rs, "exibicao_dados_mapa_geolocalizacao"));
        perfilProjetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(getIntegerOrNull(rs, "exibicao_marcadores_regioes_mapa_personalizados"));
        perfilProjetoDeEstimativaModel.setReservas(getIntegerOrNull(rs, "reservas"));
        perfilProjetoDeEstimativaModel.setMensagens(getIntegerOrNull(rs, "mensagens"));
        perfilProjetoDeEstimativaModel.setForunsOuComentarios(getIntegerOrNull(rs, "foruns_ou_comentarios"));
        perfilProjetoDeEstimativaModel.setCompartilhamentoSocial(getIntegerOrNull(rs, "compartilhamento_social"));
        perfilProjetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(getIntegerOrNull(rs, "integracao_facebook_open_graph"));
        perfilProjetoDeEstimativaModel.setNotificacaoPush(getIntegerOrNull(rs, "notificacao_push"));
        perfilProjetoDeEstimativaModel.setPlanosDeAssinatura(getIntegerOrNull(rs, "planos_de_assinatura"));
        perfilProjetoDeEstimativaModel.setProcessamentoDePagamento(getIntegerOrNull(rs, "processamento_de_pagamento"));
        perfilProjetoDeEstimativaModel.setCarrinhoDeCompras(getIntegerOrNull(rs, "carrinho_de_compras"));
        perfilProjetoDeEstimativaModel.setMarketplaceDeUsuarios(getIntegerOrNull(rs, "marketplace_de_usuarios"));
        perfilProjetoDeEstimativaModel.setGerenciamentoDeProdutos(getIntegerOrNull(rs, "gerenciamento_de_produtos"));
        perfilProjetoDeEstimativaModel.setComprasDentroDoAplicativo(getIntegerOrNull(rs, "compras_dentro_do_aplicativo"));
        perfilProjetoDeEstimativaModel.setColetaInformacaoPagamento(getIntegerOrNull(rs, "coleta_informacao_pagamento"));
        perfilProjetoDeEstimativaModel.setIntegracaoCms(getIntegerOrNull(rs, "integracao_cms"));
        perfilProjetoDeEstimativaModel.setPaginasAdministracaoUsuarios(getIntegerOrNull(rs, "paginas_administracao_usuarios"));
        perfilProjetoDeEstimativaModel.setModeracaoAprovacaoConteudo(getIntegerOrNull(rs, "moderacao_aprovacao_conteudo"));
        perfilProjetoDeEstimativaModel.setIntercom(getIntegerOrNull(rs, "intercom"));
        perfilProjetoDeEstimativaModel.setAnalisesUso(getIntegerOrNull(rs, "analises_uso"));
        perfilProjetoDeEstimativaModel.setRelatoriosErro(getIntegerOrNull(rs, "relatorios_erro"));
        perfilProjetoDeEstimativaModel.setMonitoramentoPerformance(getIntegerOrNull(rs, "monitoramento_performance"));
        perfilProjetoDeEstimativaModel.setSuporteMultilingue(getIntegerOrNull(rs, "suporte_multilingue"));
        perfilProjetoDeEstimativaModel.setConectarServicosDeTerceiros(getIntegerOrNull(rs, "conectar_servicos_de_terceiros"));
        perfilProjetoDeEstimativaModel.setApiParaTerceiros(getIntegerOrNull(rs, "api_para_terceiros"));
        perfilProjetoDeEstimativaModel.setEnvioSms(getIntegerOrNull(rs, "envio_sms"));
        perfilProjetoDeEstimativaModel.setMascaramentoNumeroTelefone(getIntegerOrNull(rs, "mascaramento_numero_telefone"));
        perfilProjetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(getIntegerOrNull(rs, "seguranca_baseada_certificado_ssl"));
        perfilProjetoDeEstimativaModel.setProtecaoContraDos(getIntegerOrNull(rs, "protecao_contra_dos"));
        perfilProjetoDeEstimativaModel.setAutenticacaoDuasEtapas(getIntegerOrNull(rs, "autenticacao_duas_etapas"));
        perfilProjetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(getIntegerOrNull(rs, "desenvolvimento_especifico_app"));
        perfilProjetoDeEstimativaModel.setDesignIconeApp(getIntegerOrNull(rs, "design_icone_app"));
        perfilProjetoDeEstimativaModel.setSincronizacaoNuvem(getIntegerOrNull(rs, "sincronizacao_nuvem"));
        perfilProjetoDeEstimativaModel.setDadosSensoresDispositivo(getIntegerOrNull(rs, "dados_sensores_dispositivo"));
        perfilProjetoDeEstimativaModel.setCodigoBarraQrCode(getIntegerOrNull(rs, "codigo_barra_qr_code"));
        perfilProjetoDeEstimativaModel.setDadosSaude(getIntegerOrNull(rs, "dados_saude"));
        perfilProjetoDeEstimativaModel.setAppleWatch(getIntegerOrNull(rs, "apple_watch"));
        perfilProjetoDeEstimativaModel.setGerenteDeProjetos(getIntegerOrNull(rs, "gerente_de_projetos"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesign(getDoubleOrNull(rs, "taxa_diaria_design"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaGerenciaProjeto(getDoubleOrNull(rs, "taxa_diaria_gerencia_projeto"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesenvolvimento(getDoubleOrNull(rs, "taxa_diaria_desenvolvimento"));

        Long timestamp = rs.getLong("data_criacao");
        if (timestamp != null) {
            perfilProjetoDeEstimativaModel.setDataCriacao(new Date(timestamp));
        }

        return perfilProjetoDeEstimativaModel;
    }
    
    private Integer getIntegerOrNull(ResultSet rs, String column) throws SQLException {
        int value = rs.getInt(column);
        return rs.wasNull() ? null : value;
    }

    private Double getDoubleOrNull(ResultSet rs, String column) throws SQLException {
        double value = rs.getDouble(column);
        return rs.wasNull() ? null : value;
    }
    
}
