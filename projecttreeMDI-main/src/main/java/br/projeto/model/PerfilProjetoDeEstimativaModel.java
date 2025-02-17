package br.projeto.model;

import br.projeto.enums.SimNao;
import java.util.HashMap;
import java.util.Map;

public class PerfilProjetoDeEstimativaModel {
    private Integer id;
    private UsuarioModel usuarioModel;
    private String nomePerfil;
    private int pequeno;
    private int medio;
    private int grande;
    private int mvp;
    private int basico;
    private int profissional;
    private int cadastroPorEmailSenha;
    private int cadastroPorFacebook;
    private int cadastroPorTwitter;
    private int cadastroPorGoogle;
    private int cadastroPorLinkedin;
    private int cadastroPorGithub;
    private int cadastroPorConviteUsuario;
    private int cadastroPorContasMultitenant;
    private int cadastroPorSubdominios;
    private int cadastroPorDominiosPersonalizados;
    private int painel;
    private int feedDeAtividades;
    private int uploadDeArquivos;
    private int uploadDeMidia;
    private int perfisDeUsuario;
    private int emailsTransacionais;
    private int tags;
    private int avaliacoesOuComentarios;
    private int processamentoAudioVideo;
    private int pesquisaTextoLivre;
    private int pesquisa;
    private int calendario;
    private int exibicaoDadosMapaGeolocalizacao;
    private int exibicaoMarcadoresRegioesMapaPersonalizados;
    private int reservas;
    private int mensagens;
    private int forunsOuComentarios;
    private int compartilhamentoSocial;
    private int integracaoFacebookOpenGraph;
    private int notificacaoPush;
    private int planosDeAssinatura;
    private int processamentoDePagamento;
    private int carrinhoDeCompras;
    private int marketplaceDeUsuarios;
    private int gerenciamentoDeProdutos;
    private int comprasDentroDoAplicativo;
    private int coletaInformacaoPagamento;
    private int integracaoCms;
    private int paginasAdministracaoUsuarios;
    private int moderacaoAprovacaoConteudo;
    private int intercom;
    private int analisesUso;
    private int relatoriosErro;
    private int monitoramentoPerformance;
    private int suporteMultilingue;
    private int conectarServicosDeTerceiros;
    private int apiParaTerceiros;
    private int envioSms;
    private int mascaramentoNumeroTelefone;
    private int segurancaBaseadaCertificadoSsl;
    private int protecaoContraDos;
    private int autenticacaoDuasEtapas;
    private int desenvolvimentoEspecificoApp;
    private int designIconeApp;
    private int sincronizacaoNuvem;
    private int dadosSensoresDispositivo;
    private int codigoBarraQrCode;
    private int dadosSaude;
    private int appleWatch;
    private int gerenteDeProjetos;
    private double taxaDiariaDesign;
    private double taxaDiariaGerenciaProjeto;
    private double taxaDiariaDesenvolvimento;
    private final Map<String, Integer> funcionalidadesDisponiveis;

    public PerfilProjetoDeEstimativaModel(){
            funcionalidadesDisponiveis = new HashMap<>();
    }
    public PerfilProjetoDeEstimativaModel(Integer id, UsuarioModel usuarioModel, String nomePerfil, int pequeno, int medio, int grande, int mvp, int basico, int profissional, int cadastroPorEmailSenha, int cadastroPorFacebook, int cadastroPorTwitter, int cadastroPorGoogle, int cadastroPorLinkedin, int cadastroPorGithub, int cadastroPorConviteUsuario, int cadastroPorContasMultitenant, int cadastroPorSubdominios, int cadastroPorDominiosPersonalizados, int painel, int feedDeAtividades, int uploadDeArquivos, int uploadDeMidia, int perfisDeUsuario, int emailsTransacionais, int tags, int avaliacoesOuComentarios, int processamentoAudioVideo, int pesquisaTextoLivre, int pesquisa, int calendario, int exibicaoDadosMapaGeolocalizacao, int exibicaoMarcadoresRegioesMapaPersonalizados, int reservas, int mensagens, int forunsOuComentarios, int compartilhamentoSocial, int integracaoFacebookOpenGraph, int notificacaoPush, int planosDeAssinatura, int processamentoDePagamento, int carrinhoDeCompras, int marketplaceDeUsuarios, int gerenciamentoDeProdutos, int comprasDentroDoAplicativo, int coletaInformacaoPagamento, int integracaoCms, int paginasAdministracaoUsuarios, int moderacaoAprovacaoConteudo, int intercom, int analisesUso, int relatoriosErro, int monitoramentoPerformance, int suporteMultilingue, int conectarServicosDeTerceiros, int apiParaTerceiros, int envioSms, int mascaramentoNumeroTelefone, int segurancaBaseadaCertificadoSsl, int protecaoContraDos, int autenticacaoDuasEtapas, int desenvolvimentoEspecificoApp, int designIconeApp, int sincronizacaoNuvem, int dadosSensoresDispositivo, int codigoBarraQrCode, int dadosSaude, int appleWatch, int gerenteDeProjetos, double taxaDiariaDesign, double taxaDiariaGerenciaProjeto, double taxaDiariaDesenvolvimento) {
        this.id = id;
        this.usuarioModel = usuarioModel;
        this.nomePerfil = nomePerfil;
        this.pequeno = pequeno;
        this.medio = medio;
        this.grande = grande;
        this.mvp = mvp;
        this.basico = basico;
        this.profissional = profissional;
        this.cadastroPorEmailSenha = cadastroPorEmailSenha;
        this.cadastroPorFacebook = cadastroPorFacebook;
        this.cadastroPorTwitter = cadastroPorTwitter;
        this.cadastroPorGoogle = cadastroPorGoogle;
        this.cadastroPorLinkedin = cadastroPorLinkedin;
        this.cadastroPorGithub = cadastroPorGithub;
        this.cadastroPorConviteUsuario = cadastroPorConviteUsuario;
        this.cadastroPorContasMultitenant = cadastroPorContasMultitenant;
        this.cadastroPorSubdominios = cadastroPorSubdominios;
        this.cadastroPorDominiosPersonalizados = cadastroPorDominiosPersonalizados;
        this.painel = painel;
        this.feedDeAtividades = feedDeAtividades;
        this.uploadDeArquivos = uploadDeArquivos;
        this.uploadDeMidia = uploadDeMidia;
        this.perfisDeUsuario = perfisDeUsuario;
        this.emailsTransacionais = emailsTransacionais;
        this.tags = tags;
        this.avaliacoesOuComentarios = avaliacoesOuComentarios;
        this.processamentoAudioVideo = processamentoAudioVideo;
        this.pesquisaTextoLivre = pesquisaTextoLivre;
        this.pesquisa = pesquisa;
        this.calendario = calendario;
        this.exibicaoDadosMapaGeolocalizacao = exibicaoDadosMapaGeolocalizacao;
        this.exibicaoMarcadoresRegioesMapaPersonalizados = exibicaoMarcadoresRegioesMapaPersonalizados;
        this.reservas = reservas;
        this.mensagens = mensagens;
        this.forunsOuComentarios = forunsOuComentarios;
        this.compartilhamentoSocial = compartilhamentoSocial;
        this.integracaoFacebookOpenGraph = integracaoFacebookOpenGraph;
        this.notificacaoPush = notificacaoPush;
        this.planosDeAssinatura = planosDeAssinatura;
        this.processamentoDePagamento = processamentoDePagamento;
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.marketplaceDeUsuarios = marketplaceDeUsuarios;
        this.gerenciamentoDeProdutos = gerenciamentoDeProdutos;
        this.comprasDentroDoAplicativo = comprasDentroDoAplicativo;
        this.coletaInformacaoPagamento = coletaInformacaoPagamento;
        this.integracaoCms = integracaoCms;
        this.paginasAdministracaoUsuarios = paginasAdministracaoUsuarios;
        this.moderacaoAprovacaoConteudo = moderacaoAprovacaoConteudo;
        this.intercom = intercom;
        this.analisesUso = analisesUso;
        this.relatoriosErro = relatoriosErro;
        this.monitoramentoPerformance = monitoramentoPerformance;
        this.suporteMultilingue = suporteMultilingue;
        this.conectarServicosDeTerceiros = conectarServicosDeTerceiros;
        this.apiParaTerceiros = apiParaTerceiros;
        this.envioSms = envioSms;
        this.mascaramentoNumeroTelefone = mascaramentoNumeroTelefone;
        this.segurancaBaseadaCertificadoSsl = segurancaBaseadaCertificadoSsl;
        this.protecaoContraDos = protecaoContraDos;
        this.autenticacaoDuasEtapas = autenticacaoDuasEtapas;
        this.desenvolvimentoEspecificoApp = desenvolvimentoEspecificoApp;
        this.designIconeApp = designIconeApp;
        this.sincronizacaoNuvem = sincronizacaoNuvem;
        this.dadosSensoresDispositivo = dadosSensoresDispositivo;
        this.codigoBarraQrCode = codigoBarraQrCode;
        this.dadosSaude = dadosSaude;
        this.appleWatch = appleWatch;
        this.gerenteDeProjetos = gerenteDeProjetos;
        this.taxaDiariaDesign = taxaDiariaDesign;
        this.taxaDiariaGerenciaProjeto = taxaDiariaGerenciaProjeto;
        this.taxaDiariaDesenvolvimento = taxaDiariaDesenvolvimento;
        funcionalidadesDisponiveis = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public int getPequeno() {
        return pequeno;
    }

    public void setPequeno(int pequeno) {
        this.pequeno = pequeno;
    }

    public int getMedio() {
        return medio;
    }

    public void setMedio(int medio) {
        this.medio = medio;
    }

    public int getGrande() {
        return grande;
    }

    public void setGrande(int grande) {
        this.grande = grande;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public int getBasico() {
        return basico;
    }

    public void setBasico(int basico) {
        this.basico = basico;
    }

    public int getProfissional() {
        return profissional;
    }

    public void setProfissional(int profissional) {
        this.profissional = profissional;
    }

    public int getCadastroPorEmailSenha() {
        return cadastroPorEmailSenha;
    }

    public void setCadastroPorEmailSenha(int cadastroPorEmailSenha) {
        this.cadastroPorEmailSenha = cadastroPorEmailSenha;
    }

    public int getCadastroPorFacebook() {
        return cadastroPorFacebook;
    }

    public void setCadastroPorFacebook(int cadastroPorFacebook) {
        this.cadastroPorFacebook = cadastroPorFacebook;
    }

    public int getCadastroPorTwitter() {
        return cadastroPorTwitter;
    }

    public void setCadastroPorTwitter(int cadastroPorTwitter) {
        this.cadastroPorTwitter = cadastroPorTwitter;
    }

    public int getCadastroPorGoogle() {
        return cadastroPorGoogle;
    }

    public void setCadastroPorGoogle(int cadastroPorGoogle) {
        this.cadastroPorGoogle = cadastroPorGoogle;
    }

    public int getCadastroPorLinkedin() {
        return cadastroPorLinkedin;
    }

    public void setCadastroPorLinkedin(int cadastroPorLinkedin) {
        this.cadastroPorLinkedin = cadastroPorLinkedin;
    }

    public int getCadastroPorGithub() {
        return cadastroPorGithub;
    }

    public void setCadastroPorGithub(int cadastroPorGithub) {
        this.cadastroPorGithub = cadastroPorGithub;
    }

    public int getCadastroPorConviteUsuario() {
        return cadastroPorConviteUsuario;
    }

    public void setCadastroPorConviteUsuario(int cadastroPorConviteUsuario) {
        this.cadastroPorConviteUsuario = cadastroPorConviteUsuario;
    }

    public int getCadastroPorContasMultitenant() {
        return cadastroPorContasMultitenant;
    }

    public void setCadastroPorContasMultitenant(int cadastroPorContasMultitenant) {
        this.cadastroPorContasMultitenant = cadastroPorContasMultitenant;
    }

    public int getCadastroPorSubdominios() {
        return cadastroPorSubdominios;
    }

    public void setCadastroPorSubdominios(int cadastroPorSubdominios) {
        this.cadastroPorSubdominios = cadastroPorSubdominios;
    }

    public int getCadastroPorDominiosPersonalizados() {
        return cadastroPorDominiosPersonalizados;
    }

    public void setCadastroPorDominiosPersonalizados(int cadastroPorDominiosPersonalizados) {
        this.cadastroPorDominiosPersonalizados = cadastroPorDominiosPersonalizados;
    }

    public int getPainel() {
        return painel;
    }

    public void setPainel(int painel) {
        this.painel = painel;
    }

    public int getFeedDeAtividades() {
        return feedDeAtividades;
    }

    public void setFeedDeAtividades(int feedDeAtividades) {
        this.feedDeAtividades = feedDeAtividades;
    }

    public int getUploadDeArquivos() {
        return uploadDeArquivos;
    }

    public void setUploadDeArquivos(int uploadDeArquivos) {
        this.uploadDeArquivos = uploadDeArquivos;
    }

    public int getUploadDeMidia() {
        return uploadDeMidia;
    }

    public void setUploadDeMidia(int uploadDeMidia) {
        this.uploadDeMidia = uploadDeMidia;
    }

    public int getPerfisDeUsuario() {
        return perfisDeUsuario;
    }

    public void setPerfisDeUsuario(int perfisDeUsuario) {
        this.perfisDeUsuario = perfisDeUsuario;
    }

    public int getEmailsTransacionais() {
        return emailsTransacionais;
    }

    public void setEmailsTransacionais(int emailsTransacionais) {
        this.emailsTransacionais = emailsTransacionais;
    }

    public int getTags() {
        return tags;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }

    public int getAvaliacoesOuComentarios() {
        return avaliacoesOuComentarios;
    }

    public void setAvaliacoesOuComentarios(int avaliacoesOuComentarios) {
        this.avaliacoesOuComentarios = avaliacoesOuComentarios;
    }

    public int getProcessamentoAudioVideo() {
        return processamentoAudioVideo;
    }

    public void setProcessamentoAudioVideo(int processamentoAudioVideo) {
        this.processamentoAudioVideo = processamentoAudioVideo;
    }

    public int getPesquisaTextoLivre() {
        return pesquisaTextoLivre;
    }

    public void setPesquisaTextoLivre(int pesquisaTextoLivre) {
        this.pesquisaTextoLivre = pesquisaTextoLivre;
    }

    public int getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(int pesquisa) {
        this.pesquisa = pesquisa;
    }

    public int getCalendario() {
        return calendario;
    }

    public void setCalendario(int calendario) {
        this.calendario = calendario;
    }

    public int getExibicaoDadosMapaGeolocalizacao() {
        return exibicaoDadosMapaGeolocalizacao;
    }

    public void setExibicaoDadosMapaGeolocalizacao(int exibicaoDadosMapaGeolocalizacao) {
        this.exibicaoDadosMapaGeolocalizacao = exibicaoDadosMapaGeolocalizacao;
    }

    public int getExibicaoMarcadoresRegioesMapaPersonalizados() {
        return exibicaoMarcadoresRegioesMapaPersonalizados;
    }

    public void setExibicaoMarcadoresRegioesMapaPersonalizados(int exibicaoMarcadoresRegioesMapaPersonalizados) {
        this.exibicaoMarcadoresRegioesMapaPersonalizados = exibicaoMarcadoresRegioesMapaPersonalizados;
    }

    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public int getMensagens() {
        return mensagens;
    }

    public void setMensagens(int mensagens) {
        this.mensagens = mensagens;
    }

    public int getForunsOuComentarios() {
        return forunsOuComentarios;
    }

    public void setForunsOuComentarios(int forunsOuComentarios) {
        this.forunsOuComentarios = forunsOuComentarios;
    }

    public int getCompartilhamentoSocial() {
        return compartilhamentoSocial;
    }

    public void setCompartilhamentoSocial(int compartilhamentoSocial) {
        this.compartilhamentoSocial = compartilhamentoSocial;
    }

    public int getIntegracaoFacebookOpenGraph() {
        return integracaoFacebookOpenGraph;
    }

    public void setIntegracaoFacebookOpenGraph(int integracaoFacebookOpenGraph) {
        this.integracaoFacebookOpenGraph = integracaoFacebookOpenGraph;
    }

    public int getNotificacaoPush() {
        return notificacaoPush;
    }

    public void setNotificacaoPush(int notificacaoPush) {
        this.notificacaoPush = notificacaoPush;
    }

    public int getPlanosDeAssinatura() {
        return planosDeAssinatura;
    }

    public void setPlanosDeAssinatura(int planosDeAssinatura) {
        this.planosDeAssinatura = planosDeAssinatura;
    }

    public int getProcessamentoDePagamento() {
        return processamentoDePagamento;
    }

    public void setProcessamentoDePagamento(int processamentoDePagamento) {
        this.processamentoDePagamento = processamentoDePagamento;
    }

    public int getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void setCarrinhoDeCompras(int carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public int getMarketplaceDeUsuarios() {
        return marketplaceDeUsuarios;
    }

    public void setMarketplaceDeUsuarios(int marketplaceDeUsuarios) {
        this.marketplaceDeUsuarios = marketplaceDeUsuarios;
    }

    public int getGerenciamentoDeProdutos() {
        return gerenciamentoDeProdutos;
    }

    public void setGerenciamentoDeProdutos(int gerenciamentoDeProdutos) {
        this.gerenciamentoDeProdutos = gerenciamentoDeProdutos;
    }

    public int getComprasDentroDoAplicativo() {
        return comprasDentroDoAplicativo;
    }

    public void setComprasDentroDoAplicativo(int comprasDentroDoAplicativo) {
        this.comprasDentroDoAplicativo = comprasDentroDoAplicativo;
    }

    public int getColetaInformacaoPagamento() {
        return coletaInformacaoPagamento;
    }

    public void setColetaInformacaoPagamento(int coletaInformacaoPagamento) {
        this.coletaInformacaoPagamento = coletaInformacaoPagamento;
    }

    public int getIntegracaoCms() {
        return integracaoCms;
    }

    public void setIntegracaoCms(int integracaoCms) {
        this.integracaoCms = integracaoCms;
    }

    public int getPaginasAdministracaoUsuarios() {
        return paginasAdministracaoUsuarios;
    }

    public void setPaginasAdministracaoUsuarios(int paginasAdministracaoUsuarios) {
        this.paginasAdministracaoUsuarios = paginasAdministracaoUsuarios;
    }

    public int getModeracaoAprovacaoConteudo() {
        return moderacaoAprovacaoConteudo;
    }

    public void setModeracaoAprovacaoConteudo(int moderacaoAprovacaoConteudo) {
        this.moderacaoAprovacaoConteudo = moderacaoAprovacaoConteudo;
    }

    public int getIntercom() {
        return intercom;
    }

    public void setIntercom(int intercom) {
        this.intercom = intercom;
    }

    public int getAnalisesUso() {
        return analisesUso;
    }

    public void setAnalisesUso(int analisesUso) {
        this.analisesUso = analisesUso;
    }

    public int getRelatoriosErro() {
        return relatoriosErro;
    }

    public void setRelatoriosErro(int relatoriosErro) {
        this.relatoriosErro = relatoriosErro;
    }

    public int getMonitoramentoPerformance() {
        return monitoramentoPerformance;
    }

    public void setMonitoramentoPerformance(int monitoramentoPerformance) {
        this.monitoramentoPerformance = monitoramentoPerformance;
    }

    public int getSuporteMultilingue() {
        return suporteMultilingue;
    }

    public void setSuporteMultilingue(int suporteMultilingue) {
        this.suporteMultilingue = suporteMultilingue;
    }

    public int getConectarServicosDeTerceiros() {
        return conectarServicosDeTerceiros;
    }

    public void setConectarServicosDeTerceiros(int conectarServicosDeTerceiros) {
        this.conectarServicosDeTerceiros = conectarServicosDeTerceiros;
    }

    public int getApiParaTerceiros() {
        return apiParaTerceiros;
    }

    public void setApiParaTerceiros(int apiParaTerceiros) {
        this.apiParaTerceiros = apiParaTerceiros;
    }

    public int getEnvioSms() {
        return envioSms;
    }

    public void setEnvioSms(int envioSms) {
        this.envioSms = envioSms;
    }

    public int getMascaramentoNumeroTelefone() {
        return mascaramentoNumeroTelefone;
    }

    public void setMascaramentoNumeroTelefone(int mascaramentoNumeroTelefone) {
        this.mascaramentoNumeroTelefone = mascaramentoNumeroTelefone;
    }

    public int getSegurancaBaseadaCertificadoSsl() {
        return segurancaBaseadaCertificadoSsl;
    }

    public void setSegurancaBaseadaCertificadoSsl(int segurancaBaseadaCertificadoSsl) {
        this.segurancaBaseadaCertificadoSsl = segurancaBaseadaCertificadoSsl;
    }

    public int getProtecaoContraDos() {
        return protecaoContraDos;
    }

    public void setProtecaoContraDos(int protecaoContraDos) {
        this.protecaoContraDos = protecaoContraDos;
    }

    public int getAutenticacaoDuasEtapas() {
        return autenticacaoDuasEtapas;
    }

    public void setAutenticacaoDuasEtapas(int autenticacaoDuasEtapas) {
        this.autenticacaoDuasEtapas = autenticacaoDuasEtapas;
    }

    public int getDesenvolvimentoEspecificoApp() {
        return desenvolvimentoEspecificoApp;
    }

    public void setDesenvolvimentoEspecificoApp(int desenvolvimentoEspecificoApp) {
        this.desenvolvimentoEspecificoApp = desenvolvimentoEspecificoApp;
    }

    public int getDesignIconeApp() {
        return designIconeApp;
    }

    public void setDesignIconeApp(int designIconeApp) {
        this.designIconeApp = designIconeApp;
    }

    public int getSincronizacaoNuvem() {
        return sincronizacaoNuvem;
    }

    public void setSincronizacaoNuvem(int sincronizacaoNuvem) {
        this.sincronizacaoNuvem = sincronizacaoNuvem;
    }

    public int getDadosSensoresDispositivo() {
        return dadosSensoresDispositivo;
    }

    public void setDadosSensoresDispositivo(int dadosSensoresDispositivo) {
        this.dadosSensoresDispositivo = dadosSensoresDispositivo;
    }

    public int getCodigoBarraQrCode() {
        return codigoBarraQrCode;
    }

    public void setCodigoBarraQrCode(int codigoBarraQrCode) {
        this.codigoBarraQrCode = codigoBarraQrCode;
    }

    public int getDadosSaude() {
        return dadosSaude;
    }

    public void setDadosSaude(int dadosSaude) {
        this.dadosSaude = dadosSaude;
    }

    public int getAppleWatch() {
        return appleWatch;
    }

    public void setAppleWatch(int appleWatch) {
        this.appleWatch = appleWatch;
    }

    public int getGerenteDeProjetos() {
        return gerenteDeProjetos;
    }

    public void setGerenteDeProjetos(int gerenteDeProjetos) {
        this.gerenteDeProjetos = gerenteDeProjetos;
    }

    public double getTaxaDiariaDesign() {
        return taxaDiariaDesign;
    }

    public void setTaxaDiariaDesign(double taxaDiariaDesign) {
        this.taxaDiariaDesign = taxaDiariaDesign;
    }

    public double getTaxaDiariaGerenciaProjeto() {
        return taxaDiariaGerenciaProjeto;
    }

    public void setTaxaDiariaGerenciaProjeto(double taxaDiariaGerenciaProjeto) {
        this.taxaDiariaGerenciaProjeto = taxaDiariaGerenciaProjeto;
    }

    public double getTaxaDiariaDesenvolvimento() {
        return taxaDiariaDesenvolvimento;
    }

    public void setTaxaDiariaDesenvolvimento(double taxaDiariaDesenvolvimento) {
        this.taxaDiariaDesenvolvimento = taxaDiariaDesenvolvimento;
    }
    
    public Map<String, Integer> getFuncionalidadesDisponiveis() {
    
    funcionalidadesDisponiveis.put("Pequeno", pequeno);
    funcionalidadesDisponiveis.put("Médio", medio);
    funcionalidadesDisponiveis.put("Grande", grande);
    funcionalidadesDisponiveis.put("MVP", mvp);
    funcionalidadesDisponiveis.put("Básico", basico);
    funcionalidadesDisponiveis.put("Profissional", profissional);
    funcionalidadesDisponiveis.put("Cadastro por Email/Senha", cadastroPorEmailSenha);
    funcionalidadesDisponiveis.put("Cadastro por Facebook", cadastroPorFacebook);
    funcionalidadesDisponiveis.put("Cadastro por Twitter", cadastroPorTwitter);
    funcionalidadesDisponiveis.put("Cadastro por Google", cadastroPorGoogle);
    funcionalidadesDisponiveis.put("Cadastro por LinkedIn", cadastroPorLinkedin);
    funcionalidadesDisponiveis.put("Cadastro por GitHub", cadastroPorGithub);
    funcionalidadesDisponiveis.put("Cadastro por Convite de Usuário", cadastroPorConviteUsuario);
    funcionalidadesDisponiveis.put("Cadastro por Contas Multi-tenant", cadastroPorContasMultitenant);
    funcionalidadesDisponiveis.put("Cadastro por Subdomínios", cadastroPorSubdominios);
    funcionalidadesDisponiveis.put("Cadastro por Domínios Personalizados", cadastroPorDominiosPersonalizados);
    funcionalidadesDisponiveis.put("Painel", painel);
    funcionalidadesDisponiveis.put("Feed de Atividades", feedDeAtividades);
    funcionalidadesDisponiveis.put("Upload de Arquivos", uploadDeArquivos);
    funcionalidadesDisponiveis.put("Upload de Mídia", uploadDeMidia);
    funcionalidadesDisponiveis.put("Perfis de Usuário", perfisDeUsuario);
    funcionalidadesDisponiveis.put("Emails Transacionais", emailsTransacionais);
    funcionalidadesDisponiveis.put("Tags", tags);
    funcionalidadesDisponiveis.put("Avaliações ou Comentários", avaliacoesOuComentarios);
    funcionalidadesDisponiveis.put("Processamento de Áudio e Vídeo", processamentoAudioVideo);
    funcionalidadesDisponiveis.put("Pesquisa Texto Livre", pesquisaTextoLivre);
    funcionalidadesDisponiveis.put("Pesquisa", pesquisa);
    funcionalidadesDisponiveis.put("Calendário", calendario);
    funcionalidadesDisponiveis.put("Exibição de Dados no Mapa", exibicaoDadosMapaGeolocalizacao);
    funcionalidadesDisponiveis.put("Exibição de Marcadores no Mapa", exibicaoMarcadoresRegioesMapaPersonalizados);
    funcionalidadesDisponiveis.put("Reservas", reservas);
    funcionalidadesDisponiveis.put("Mensagens", mensagens);
    funcionalidadesDisponiveis.put("Fóruns ou Comentários", forunsOuComentarios);
    funcionalidadesDisponiveis.put("Compartilhamento Social", compartilhamentoSocial);
    funcionalidadesDisponiveis.put("Integração com Facebook Open Graph", integracaoFacebookOpenGraph);
    funcionalidadesDisponiveis.put("Notificação Push", notificacaoPush);
    funcionalidadesDisponiveis.put("Planos de Assinatura", planosDeAssinatura);
    funcionalidadesDisponiveis.put("Processamento de Pagamento", processamentoDePagamento);
    funcionalidadesDisponiveis.put("Carrinho de Compras", carrinhoDeCompras);
    funcionalidadesDisponiveis.put("Marketplace de Usuários", marketplaceDeUsuarios);
    funcionalidadesDisponiveis.put("Gerenciamento de Produtos", gerenciamentoDeProdutos);
    funcionalidadesDisponiveis.put("Compras dentro do Aplicativo", comprasDentroDoAplicativo);
    funcionalidadesDisponiveis.put("Coleta de Informação de Pagamento", coletaInformacaoPagamento);
    funcionalidadesDisponiveis.put("Integração com CMS", integracaoCms);
    funcionalidadesDisponiveis.put("Páginas de Administração de Usuários", paginasAdministracaoUsuarios);
    funcionalidadesDisponiveis.put("Moderação e Aprovação de Conteúdo", moderacaoAprovacaoConteudo);
    funcionalidadesDisponiveis.put("Intercom", intercom);
    funcionalidadesDisponiveis.put("Análises de Uso", analisesUso);
    funcionalidadesDisponiveis.put("Relatórios de Erro", relatoriosErro);
    funcionalidadesDisponiveis.put("Monitoramento de Performance", monitoramentoPerformance);
    funcionalidadesDisponiveis.put("Suporte Multilíngue", suporteMultilingue);
    funcionalidadesDisponiveis.put("Conectar com Serviços de Terceiros", conectarServicosDeTerceiros);
    funcionalidadesDisponiveis.put("API para Terceiros", apiParaTerceiros);
    funcionalidadesDisponiveis.put("Envio de SMS", envioSms);
    funcionalidadesDisponiveis.put("Mascaramento de Número de Telefone", mascaramentoNumeroTelefone);
    funcionalidadesDisponiveis.put("Segurança Baseada em Certificado SSL", segurancaBaseadaCertificadoSsl);
    funcionalidadesDisponiveis.put("Proteção Contra DoS", protecaoContraDos);
    funcionalidadesDisponiveis.put("Autenticação em Duas Etapas", autenticacaoDuasEtapas);
    funcionalidadesDisponiveis.put("Desenvolvimento Específico de App", desenvolvimentoEspecificoApp);
    funcionalidadesDisponiveis.put("Design de Ícone para App", designIconeApp);
    funcionalidadesDisponiveis.put("Sincronização com a Nuvem", sincronizacaoNuvem);
    funcionalidadesDisponiveis.put("Dados de Sensores do Dispositivo", dadosSensoresDispositivo);
    funcionalidadesDisponiveis.put("Código de Barras ou QR Code", codigoBarraQrCode);
    funcionalidadesDisponiveis.put("Dados de Saúde", dadosSaude);
    funcionalidadesDisponiveis.put("Apple Watch", appleWatch);
    funcionalidadesDisponiveis.put("Gerente de Projetos", gerenteDeProjetos);

    return funcionalidadesDisponiveis;
}

    @Override
    public String toString() {
        return "PerfilProjetoDeEstimativaDaoJDBC{" +
                "id=" + id +
                ", usuarioModel=" + usuarioModel +
                ", nomePerfil='" + nomePerfil + '\'' +
                ", pequeno=" + pequeno +
                ", medio=" + medio +
                ", grande=" + grande +
                ", mvp=" + mvp +
                ", basico=" + basico +
                ", profissional=" + profissional +
                ", cadastroPorEmailSenha=" + cadastroPorEmailSenha +
                ", cadastroPorFacebook=" + cadastroPorFacebook +
                ", cadastroPorTwitter=" + cadastroPorTwitter +
                ", cadastroPorGoogle=" + cadastroPorGoogle +
                ", cadastroPorLinkedin=" + cadastroPorLinkedin +
                ", cadastroPorGithub=" + cadastroPorGithub +
                ", cadastroPorConviteUsuario=" + cadastroPorConviteUsuario +
                ", cadastroPorContasMultitenant=" + cadastroPorContasMultitenant +
                ", cadastroPorSubdominios=" + cadastroPorSubdominios +
                ", cadastroPorDominiosPersonalizados=" + cadastroPorDominiosPersonalizados +
                ", painel=" + painel +
                ", feedDeAtividades=" + feedDeAtividades +
                ", uploadDeArquivos=" + uploadDeArquivos +
                ", uploadDeMidia=" + uploadDeMidia +
                ", perfisDeUsuario=" + perfisDeUsuario +
                ", emailsTransacionais=" + emailsTransacionais +
                ", tags=" + tags +
                ", avaliacoesOuComentarios=" + avaliacoesOuComentarios +
                ", processamentoAudioVideo=" + processamentoAudioVideo +
                ", pesquisaTextoLivre=" + pesquisaTextoLivre +
                ", pesquisa=" + pesquisa +
                ", calendario=" + calendario +
                ", exibicaoDadosMapaGeolocalizacao=" + exibicaoDadosMapaGeolocalizacao +
                ", exibicaoMarcadoresRegioesMapaPersonalizados=" + exibicaoMarcadoresRegioesMapaPersonalizados +
                ", reservas=" + reservas +
                ", mensagens=" + mensagens +
                ", forunsOuComentarios=" + forunsOuComentarios +
                ", compartilhamentoSocial=" + compartilhamentoSocial +
                ", integracaoFacebookOpenGraph=" + integracaoFacebookOpenGraph +
                ", notificacaoPush=" + notificacaoPush +
                ", planosDeAssinatura=" + planosDeAssinatura +
                ", processamentoDePagamento=" + processamentoDePagamento +
                ", carrinhoDeCompras=" + carrinhoDeCompras +
                ", marketplaceDeUsuarios=" + marketplaceDeUsuarios +
                ", gerenciamentoDeProdutos=" + gerenciamentoDeProdutos +
                ", comprasDentroDoAplicativo=" + comprasDentroDoAplicativo +
                ", coletaInformacaoPagamento=" + coletaInformacaoPagamento +
                ", integracaoCms=" + integracaoCms +
                ", paginasAdministracaoUsuarios=" + paginasAdministracaoUsuarios +
                ", moderacaoAprovacaoConteudo=" + moderacaoAprovacaoConteudo +
                ", intercom=" + intercom +
                ", analisesUso=" + analisesUso +
                ", relatoriosErro=" + relatoriosErro +
                ", monitoramentoPerformance=" + monitoramentoPerformance +
                ", suporteMultilingue=" + suporteMultilingue +
                ", conectarServicosDeTerceiros=" + conectarServicosDeTerceiros +
                ", apiParaTerceiros=" + apiParaTerceiros +
                ", envioSms=" + envioSms +
                ", mascaramentoNumeroTelefone=" + mascaramentoNumeroTelefone +
                ", segurancaBaseadaCertificadoSsl=" + segurancaBaseadaCertificadoSsl +
                ", protecaoContraDos=" + protecaoContraDos +
                ", autenticacaoDuasEtapas=" + autenticacaoDuasEtapas +
                ", desenvolvimentoEspecificoApp=" + desenvolvimentoEspecificoApp +
                ", designIconeApp=" + designIconeApp +
                ", sincronizacaoNuvem=" + sincronizacaoNuvem +
                ", dadosSensoresDispositivo=" + dadosSensoresDispositivo +
                ", codigoBarraQrCode=" + codigoBarraQrCode +
                ", dadosSaude=" + dadosSaude +
                ", appleWatch=" + appleWatch +
                ", gerenteDeProjetos=" + gerenteDeProjetos +
                ", taxaDiariaDesign=" + taxaDiariaDesign +
                ", taxaDiariaGerenciaProjeto=" + taxaDiariaGerenciaProjeto +
                ", taxaDiariaDesenvolvimento=" + taxaDiariaDesenvolvimento +
                '}'+"\n";
    }
}
