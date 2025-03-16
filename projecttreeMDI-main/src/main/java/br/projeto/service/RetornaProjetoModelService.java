/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
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
public class RetornaProjetoModelService {
    private ProjetoDeEstimativaModel projetoDeEstimativaModel;
    
    
    private Map<String, Integer> mapProjetos;
    
    public RetornaProjetoModelService(Map<String, Integer> mapProjetos){
        this.mapProjetos = mapProjetos;
        this.projetoDeEstimativaModel = new ProjetoDeEstimativaModel();
    }
    
    public RetornaProjetoModelService(){}
    
    public ProjetoDeEstimativaModel getProjeto(){
        projetoDeEstimativaModel.setPequeno(mapProjetos.get("Pequeno"));
        projetoDeEstimativaModel.setMedio(mapProjetos.get("Médio"));
        projetoDeEstimativaModel.setGrande(mapProjetos.get("Grande"));
        projetoDeEstimativaModel.setMvp(mapProjetos.get("MVP"));
        projetoDeEstimativaModel.setBasico(mapProjetos.get("Básico"));
        projetoDeEstimativaModel.setProfissional(mapProjetos.get("Profissional"));
        projetoDeEstimativaModel.setCadastroPorEmailSenha(mapProjetos.get("Cadastro por Email/Senha"));
        projetoDeEstimativaModel.setCadastroPorFacebook(mapProjetos.get("Cadastro por Facebook"));
        projetoDeEstimativaModel.setCadastroPorTwitter(mapProjetos.get("Cadastro por Twitter"));
        projetoDeEstimativaModel.setCadastroPorGoogle(mapProjetos.get("Cadastro por Google"));
        projetoDeEstimativaModel.setCadastroPorLinkedin(mapProjetos.get("Cadastro por LinkedIn"));
        projetoDeEstimativaModel.setCadastroPorGithub(mapProjetos.get("Cadastro por GitHub"));
        projetoDeEstimativaModel.setCadastroPorConviteUsuario(mapProjetos.get("Cadastro por Convite de Usuário"));
        projetoDeEstimativaModel.setCadastroPorContasMultitenant(mapProjetos.get("Cadastro por Contas Multi-tenant"));
        projetoDeEstimativaModel.setCadastroPorSubdominios(mapProjetos.get("Cadastro por Subdomínios"));
        projetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(mapProjetos.get("Cadastro por Domínios Personalizados"));
        projetoDeEstimativaModel.setPainel(mapProjetos.get("Painel"));
        projetoDeEstimativaModel.setFeedDeAtividades(mapProjetos.get("Feed de Atividades"));
        projetoDeEstimativaModel.setUploadDeArquivos(mapProjetos.get("Upload de Arquivos"));
        projetoDeEstimativaModel.setUploadDeMidia(mapProjetos.get("Upload de Mídia"));
        projetoDeEstimativaModel.setPerfisDeUsuario(mapProjetos.get("Perfis de Usuário"));
        projetoDeEstimativaModel.setEmailsTransacionais(mapProjetos.get("Emails Transacionais"));
        projetoDeEstimativaModel.setTags(mapProjetos.get("Tags"));
        projetoDeEstimativaModel.setAvaliacoesOuComentarios(mapProjetos.get("Avaliações ou Comentários"));
        projetoDeEstimativaModel.setProcessamentoAudioVideo(mapProjetos.get("Processamento de Áudio e Vídeo"));
        projetoDeEstimativaModel.setPesquisaTextoLivre(mapProjetos.get("Pesquisa Texto Livre"));
        projetoDeEstimativaModel.setPesquisa(mapProjetos.get("Pesquisa"));
        projetoDeEstimativaModel.setCalendario(mapProjetos.get("Calendário"));
        projetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(mapProjetos.get("Exibição de Dados no Mapa"));
        projetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(mapProjetos.get("Exibição de Marcadores no Mapa"));
        projetoDeEstimativaModel.setReservas(mapProjetos.get("Reservas"));
        projetoDeEstimativaModel.setMensagens(mapProjetos.get("Mensagens"));
        projetoDeEstimativaModel.setForunsOuComentarios(mapProjetos.get("Fóruns ou Comentários"));
        projetoDeEstimativaModel.setCompartilhamentoSocial(mapProjetos.get("Compartilhamento Social"));
        projetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(mapProjetos.get("Integração com Facebook Open Graph"));
        projetoDeEstimativaModel.setNotificacaoPush(mapProjetos.get("Notificação Push"));
        projetoDeEstimativaModel.setPlanosDeAssinatura(mapProjetos.get("Planos de Assinatura"));
        projetoDeEstimativaModel.setProcessamentoDePagamento(mapProjetos.get("Processamento de Pagamento"));
        projetoDeEstimativaModel.setCarrinhoDeCompras(mapProjetos.get("Carrinho de Compras"));
        projetoDeEstimativaModel.setMarketplaceDeUsuarios(mapProjetos.get("Marketplace de Usuários"));
        projetoDeEstimativaModel.setGerenciamentoDeProdutos(mapProjetos.get("Gerenciamento de Produtos"));
        projetoDeEstimativaModel.setComprasDentroDoAplicativo(mapProjetos.get("Compras dentro do Aplicativo"));
        projetoDeEstimativaModel.setColetaInformacaoPagamento(mapProjetos.get("Coleta de Informação de Pagamento"));
        projetoDeEstimativaModel.setIntegracaoCms(mapProjetos.get("Integração com CMS"));
        projetoDeEstimativaModel.setPaginasAdministracaoUsuarios(mapProjetos.get("Páginas de Administração de Usuários"));
        projetoDeEstimativaModel.setModeracaoAprovacaoConteudo(mapProjetos.get("Moderação e Aprovação de Conteúdo"));
        projetoDeEstimativaModel.setIntercom(mapProjetos.get("Intercom"));
        projetoDeEstimativaModel.setAnalisesUso(mapProjetos.get("Análises de Uso"));
        projetoDeEstimativaModel.setRelatoriosErro(mapProjetos.get("Relatórios de Erro"));
        projetoDeEstimativaModel.setMonitoramentoPerformance(mapProjetos.get("Monitoramento de Performance"));
        projetoDeEstimativaModel.setSuporteMultilingue(mapProjetos.get("Suporte Multilíngue"));
        projetoDeEstimativaModel.setConectarServicosDeTerceiros(mapProjetos.get("Conectar com Serviços de Terceiros"));
        projetoDeEstimativaModel.setApiParaTerceiros(mapProjetos.get("API para Terceiros"));
        projetoDeEstimativaModel.setEnvioSms(mapProjetos.get("Envio de SMS"));
        projetoDeEstimativaModel.setMascaramentoNumeroTelefone(mapProjetos.get("Mascaramento de Número de Telefone"));
        projetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(mapProjetos.get("Segurança Baseada em Certificado SSL"));
        projetoDeEstimativaModel.setProtecaoContraDos(mapProjetos.get("Proteção Contra DoS"));
        projetoDeEstimativaModel.setAutenticacaoDuasEtapas(mapProjetos.get("Autenticação em Duas Etapas"));
        projetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(mapProjetos.get("Desenvolvimento Específico de App"));
        projetoDeEstimativaModel.setDesignIconeApp(mapProjetos.get("Design de Ícone para App"));
        projetoDeEstimativaModel.setSincronizacaoNuvem(mapProjetos.get("Sincronização com a Nuvem"));
        projetoDeEstimativaModel.setDadosSensoresDispositivo(mapProjetos.get("Dados de Sensores do Dispositivo"));
        projetoDeEstimativaModel.setCodigoBarraQrCode(mapProjetos.get("Código de Barras ou QR Code"));
        projetoDeEstimativaModel.setDadosSaude(mapProjetos.get("Dados de Saúde"));
        projetoDeEstimativaModel.setAppleWatch(mapProjetos.get("Apple Watch"));
        projetoDeEstimativaModel.setGerenteDeProjetos(mapProjetos.get("Gerente de Projetos"));
        
        return projetoDeEstimativaModel;
    }
    
    public List<ProjetosFuncionalidadesPersonalizadasModel> getFuncionalidadesPersonalizadas(){
        List<ProjetosFuncionalidadesPersonalizadasModel> funconalidadesPersonalizadasList = new ArrayList<>();
        int contador = 0;
        for(Map.Entry<String, Integer> entrySet: mapProjetos.entrySet()){
            ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new ProjetosFuncionalidadesPersonalizadasModel();
            if(!projetoDeEstimativaModel.getFuncionalidadesDisponiveis().containsKey(entrySet.getKey())){
                contador++;
                projetosFuncionalidadesPersonalizadasModel.setNome(entrySet.getKey());
                projetosFuncionalidadesPersonalizadasModel.setProjetoDeEstimativaModel(projetoDeEstimativaModel);
                projetosFuncionalidadesPersonalizadasModel.setSelecionado(entrySet.getValue());
                
                funconalidadesPersonalizadasList.add(projetosFuncionalidadesPersonalizadasModel);
            }
        }
        if(contador>0){
            return funconalidadesPersonalizadasList;
        }
        return null;
    }
    
    public ProjetoDeEstimativaModel instantiateProjetoDeEstimativaModel(ResultSet rs, UsuarioModel usuarioModel) throws SQLException {
        ProjetoDeEstimativaModel projetoDeEstimativaModel = new ProjetoDeEstimativaModel();

        projetoDeEstimativaModel.setId(rs.getInt("id"));
        projetoDeEstimativaModel.setUsuarioModel(usuarioModel);
        projetoDeEstimativaModel.setCompartilhado(getIntegerOrNull(rs, "compartilhado"));
        projetoDeEstimativaModel.setCompartilhadoPor(getIntegerOrNull(rs, "compartilhado_por"));

        long timestamp = rs.getLong("data_criacao");
        Date data = new Date(timestamp);
        projetoDeEstimativaModel.setDataCriacao(data);
        
        projetoDeEstimativaModel.setNomeProjetoDeEstimativa(rs.getString("nome_projeto_estimativa"));
        projetoDeEstimativaModel.setPequeno(getIntegerOrNull(rs, "pequeno"));
        projetoDeEstimativaModel.setMedio(getIntegerOrNull(rs, "medio"));
        projetoDeEstimativaModel.setGrande(getIntegerOrNull(rs, "grande"));
        projetoDeEstimativaModel.setMvp(getIntegerOrNull(rs, "mvp"));
        projetoDeEstimativaModel.setBasico(getIntegerOrNull(rs, "basico"));
        projetoDeEstimativaModel.setProfissional(getIntegerOrNull(rs, "profissional"));
        projetoDeEstimativaModel.setCadastroPorEmailSenha(getIntegerOrNull(rs, "cadastro_por_email_senha"));
        projetoDeEstimativaModel.setCadastroPorFacebook(getIntegerOrNull(rs, "cadastro_por_facebook"));
        projetoDeEstimativaModel.setCadastroPorTwitter(getIntegerOrNull(rs, "cadastro_por_twitter"));
        projetoDeEstimativaModel.setCadastroPorGoogle(getIntegerOrNull(rs, "cadastro_por_google"));
        projetoDeEstimativaModel.setCadastroPorLinkedin(getIntegerOrNull(rs, "cadastro_por_linkedin"));
        projetoDeEstimativaModel.setCadastroPorGithub(getIntegerOrNull(rs, "cadastro_por_github"));
        projetoDeEstimativaModel.setCadastroPorConviteUsuario(getIntegerOrNull(rs, "cadastro_por_convite_usuario"));
        projetoDeEstimativaModel.setCadastroPorContasMultitenant(getIntegerOrNull(rs, "cadastro_por_contas_multitenant"));
        projetoDeEstimativaModel.setCadastroPorSubdominios(getIntegerOrNull(rs, "cadastro_por_subdominios"));
        projetoDeEstimativaModel.setCadastroPorDominiosPersonalizados(getIntegerOrNull(rs, "cadastro_por_dominios_personalizados"));
        projetoDeEstimativaModel.setPainel(getIntegerOrNull(rs, "painel"));
        projetoDeEstimativaModel.setFeedDeAtividades(getIntegerOrNull(rs, "feed_de_atividades"));
        projetoDeEstimativaModel.setUploadDeArquivos(getIntegerOrNull(rs, "upload_de_arquivos"));
        projetoDeEstimativaModel.setUploadDeMidia(getIntegerOrNull(rs, "upload_de_midia"));
        projetoDeEstimativaModel.setPerfisDeUsuario(getIntegerOrNull(rs, "perfis_de_usuario"));
        projetoDeEstimativaModel.setEmailsTransacionais(getIntegerOrNull(rs, "emails_transacionais"));
        projetoDeEstimativaModel.setTags(getIntegerOrNull(rs, "tags"));
        projetoDeEstimativaModel.setAvaliacoesOuComentarios(getIntegerOrNull(rs, "avaliacoes_ou_comentarios"));
        projetoDeEstimativaModel.setProcessamentoAudioVideo(getIntegerOrNull(rs, "processamento_audio_video"));
        projetoDeEstimativaModel.setPesquisaTextoLivre(getIntegerOrNull(rs, "pesquisa_texto_livre"));
        projetoDeEstimativaModel.setPesquisa(getIntegerOrNull(rs, "pesquisa"));
        projetoDeEstimativaModel.setCalendario(getIntegerOrNull(rs, "calendario"));
        projetoDeEstimativaModel.setExibicaoDadosMapaGeolocalizacao(getIntegerOrNull(rs, "exibicao_dados_mapa_geolocalizacao"));
        projetoDeEstimativaModel.setExibicaoMarcadoresRegioesMapaPersonalizados(getIntegerOrNull(rs, "exibicao_marcadores_regioes_mapa_personalizados"));
        projetoDeEstimativaModel.setReservas(getIntegerOrNull(rs, "reservas"));
        projetoDeEstimativaModel.setMensagens(getIntegerOrNull(rs, "mensagens"));
        projetoDeEstimativaModel.setForunsOuComentarios(getIntegerOrNull(rs, "foruns_ou_comentarios"));
        projetoDeEstimativaModel.setCompartilhamentoSocial(getIntegerOrNull(rs, "compartilhamento_social"));
        projetoDeEstimativaModel.setIntegracaoFacebookOpenGraph(getIntegerOrNull(rs, "integracao_facebook_open_graph"));
        projetoDeEstimativaModel.setNotificacaoPush(getIntegerOrNull(rs, "notificacao_push"));
        projetoDeEstimativaModel.setPlanosDeAssinatura(getIntegerOrNull(rs, "planos_de_assinatura"));
        projetoDeEstimativaModel.setProcessamentoDePagamento(getIntegerOrNull(rs, "processamento_de_pagamento"));
        projetoDeEstimativaModel.setCarrinhoDeCompras(getIntegerOrNull(rs, "carrinho_de_compras"));
        projetoDeEstimativaModel.setMarketplaceDeUsuarios(getIntegerOrNull(rs, "marketplace_de_usuarios"));
        projetoDeEstimativaModel.setGerenciamentoDeProdutos(getIntegerOrNull(rs, "gerenciamento_de_produtos"));
        projetoDeEstimativaModel.setComprasDentroDoAplicativo(getIntegerOrNull(rs, "compras_dentro_do_aplicativo"));
        projetoDeEstimativaModel.setColetaInformacaoPagamento(getIntegerOrNull(rs, "coleta_informacao_pagamento"));
        projetoDeEstimativaModel.setIntegracaoCms(getIntegerOrNull(rs, "integracao_cms"));
        projetoDeEstimativaModel.setPaginasAdministracaoUsuarios(getIntegerOrNull(rs, "paginas_administracao_usuarios"));
        projetoDeEstimativaModel.setModeracaoAprovacaoConteudo(getIntegerOrNull(rs, "moderacao_aprovacao_conteudo"));
        projetoDeEstimativaModel.setIntercom(getIntegerOrNull(rs, "intercom"));
        projetoDeEstimativaModel.setAnalisesUso(getIntegerOrNull(rs, "analises_uso"));
        projetoDeEstimativaModel.setRelatoriosErro(getIntegerOrNull(rs, "relatorios_erro"));
        projetoDeEstimativaModel.setMonitoramentoPerformance(getIntegerOrNull(rs, "monitoramento_performance"));
        projetoDeEstimativaModel.setSuporteMultilingue(getIntegerOrNull(rs, "suporte_multilingue"));
        projetoDeEstimativaModel.setConectarServicosDeTerceiros(getIntegerOrNull(rs, "conectar_servicos_de_terceiros"));
        projetoDeEstimativaModel.setApiParaTerceiros(getIntegerOrNull(rs, "api_para_terceiros"));
        projetoDeEstimativaModel.setEnvioSms(getIntegerOrNull(rs, "envio_sms"));
        projetoDeEstimativaModel.setMascaramentoNumeroTelefone(getIntegerOrNull(rs, "mascaramento_numero_telefone"));
        projetoDeEstimativaModel.setSegurancaBaseadaCertificadoSsl(getIntegerOrNull(rs, "seguranca_baseada_certificado_ssl"));
        projetoDeEstimativaModel.setProtecaoContraDos(getIntegerOrNull(rs, "protecao_contra_dos"));
        projetoDeEstimativaModel.setAutenticacaoDuasEtapas(getIntegerOrNull(rs, "autenticacao_duas_etapas"));
        projetoDeEstimativaModel.setDesenvolvimentoEspecificoApp(getIntegerOrNull(rs, "desenvolvimento_especifico_app"));
        projetoDeEstimativaModel.setDesignIconeApp(getIntegerOrNull(rs, "design_icone_app"));
        projetoDeEstimativaModel.setSincronizacaoNuvem(getIntegerOrNull(rs, "sincronizacao_nuvem"));
        projetoDeEstimativaModel.setDadosSensoresDispositivo(getIntegerOrNull(rs, "dados_sensores_dispositivo"));
        projetoDeEstimativaModel.setCodigoBarraQrCode(getIntegerOrNull(rs, "codigo_barra_qr_code"));
        projetoDeEstimativaModel.setDadosSaude(getIntegerOrNull(rs, "dados_saude"));
        projetoDeEstimativaModel.setAppleWatch(getIntegerOrNull(rs, "apple_watch"));
        projetoDeEstimativaModel.setGerenteDeProjetos(getIntegerOrNull(rs, "gerente_de_projetos"));
        projetoDeEstimativaModel.setCustoHardware(getDoubleOrNull(rs, "custo_hardware"));
        projetoDeEstimativaModel.setCustoSoftware(getDoubleOrNull(rs, "custo_software"));
        projetoDeEstimativaModel.setCustoRiscos(getDoubleOrNull(rs, "custo_riscos"));
        projetoDeEstimativaModel.setCustoGarantia(getDoubleOrNull(rs, "custo_garantia"));
        projetoDeEstimativaModel.setFundoDeReserva(getDoubleOrNull(rs, "fundo_de_reserva"));
        projetoDeEstimativaModel.setOutrosCustos(getDoubleOrNull(rs, "outros_custos"));
        projetoDeEstimativaModel.setPercentualComImpostos(getDoubleOrNull(rs, "percentual_com_impostos"));
        projetoDeEstimativaModel.setPercentualLucroDesejado(getDoubleOrNull(rs, "percentual_lucro_desejado"));

        return projetoDeEstimativaModel;
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
