package br.projeto.model;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PerfilProjetoDeEstimativaModel {
    private Integer id;
    private UsuarioModel usuarioModel;
    private String nomePerfil;
    private Integer pequeno;
    private Integer medio;
    private Integer grande;
    private Integer mvp;
    private Integer basico;
    private Integer profissional;
    private Integer cadastroPorEmailSenha;
    private Integer cadastroPorFacebook;
    private Integer cadastroPorTwitter;
    private Integer cadastroPorGoogle;
    private Integer cadastroPorLinkedin;
    private Integer cadastroPorGithub;
    private Integer cadastroPorConviteUsuario;
    private Integer cadastroPorContasMultitenant;
    private Integer cadastroPorSubdominios;
    private Integer cadastroPorDominiosPersonalizados;
    private Integer painel;
    private Integer feedDeAtividades;
    private Integer uploadDeArquivos;
    private Integer uploadDeMidia;
    private Integer perfisDeUsuario;
    private Integer emailsTransacionais;
    private Integer tags;
    private Integer avaliacoesOuComentarios;
    private Integer processamentoAudioVideo;
    private Integer pesquisaTextoLivre;
    private Integer pesquisa;
    private Integer calendario;
    private Integer exibicaoDadosMapaGeolocalizacao;
    private Integer exibicaoMarcadoresRegioesMapaPersonalizados;
    private Integer reservas;
    private Integer mensagens;
    private Integer forunsOuComentarios;
    private Integer compartilhamentoSocial;
    private Integer IntegeregracaoFacebookOpenGraph;
    private Integer notificacaoPush;
    private Integer planosDeAssinatura;
    private Integer processamentoDePagamento;
    private Integer carrinhoDeCompras;
    private Integer marketplaceDeUsuarios;
    private Integer gerenciamentoDeProdutos;
    private Integer comprasDentroDoAplicativo;
    private Integer coletaInformacaoPagamento;
    private Integer IntegeregracaoCms;
    private Integer paginasAdministracaoUsuarios;
    private Integer moderacaoAprovacaoConteudo;
    private Integer Integerercom;
    private Integer analisesUso;
    private Integer relatoriosErro;
    private Integer monitoramentoPerformance;
    private Integer suporteMultilingue;
    private Integer conectarServicosDeTerceiros;
    private Integer apiParaTerceiros;
    private Integer envioSms;
    private Integer mascaramentoNumeroTelefone;
    private Integer segurancaBaseadaCertificadoSsl;
    private Integer protecaoContraDos;
    private Integer autenticacaoDuasEtapas;
    private Integer desenvolvimentoEspecificoApp;
    private Integer designIconeApp;
    private Integer sincronizacaoNuvem;
    private Integer dadosSensoresDispositivo;
    private Integer codigoBarraQrCode;
    private Integer dadosSaude;
    private Integer appleWatch;
    private Integer gerenteDeProjetos;
    private double taxaDiariaDesign;
    private double taxaDiariaGerenciaProjeto;
    private double taxaDiariaDesenvolvimento;
    private Date dataCriacao;
    private final Map<String, Integer> funcionalidadesDisponiveis;
    //private final Map<String, String> relacaoBancoNomeReal;

    public PerfilProjetoDeEstimativaModel(){
            this.funcionalidadesDisponiveis = new LinkedHashMap<>();
            //this.relacaoBancoNomeReal = new HashMap<>();
    }
    public PerfilProjetoDeEstimativaModel(Integer id, UsuarioModel usuarioModel, String nomePerfil, Integer pequeno, Integer medio, Integer grande, Integer mvp, Integer basico, Integer profissional, Integer cadastroPorEmailSenha, Integer cadastroPorFacebook, Integer cadastroPorTwitter, Integer cadastroPorGoogle, Integer cadastroPorLinkedin, Integer cadastroPorGithub, Integer cadastroPorConviteUsuario, Integer cadastroPorContasMultitenant, Integer cadastroPorSubdominios, Integer cadastroPorDominiosPersonalizados, Integer painel, Integer feedDeAtividades, Integer uploadDeArquivos, Integer uploadDeMidia, Integer perfisDeUsuario, Integer emailsTransacionais, Integer tags, Integer avaliacoesOuComentarios, Integer processamentoAudioVideo, Integer pesquisaTextoLivre, Integer pesquisa, Integer calendario, Integer exibicaoDadosMapaGeolocalizacao, Integer exibicaoMarcadoresRegioesMapaPersonalizados, Integer reservas, Integer mensagens, Integer forunsOuComentarios, Integer compartilhamentoSocial, Integer IntegeregracaoFacebookOpenGraph, Integer notificacaoPush, Integer planosDeAssinatura, Integer processamentoDePagamento, Integer carrinhoDeCompras, Integer marketplaceDeUsuarios, Integer gerenciamentoDeProdutos, Integer comprasDentroDoAplicativo, Integer coletaInformacaoPagamento, Integer IntegeregracaoCms, Integer paginasAdministracaoUsuarios, Integer moderacaoAprovacaoConteudo, Integer Integerercom, Integer analisesUso, Integer relatoriosErro, Integer monitoramentoPerformance, Integer suporteMultilingue, Integer conectarServicosDeTerceiros, Integer apiParaTerceiros, Integer envioSms, Integer mascaramentoNumeroTelefone, Integer segurancaBaseadaCertificadoSsl, Integer protecaoContraDos, Integer autenticacaoDuasEtapas, Integer desenvolvimentoEspecificoApp, Integer designIconeApp, Integer sincronizacaoNuvem, Integer dadosSensoresDispositivo, Integer codigoBarraQrCode, Integer dadosSaude, Integer appleWatch, Integer gerenteDeProjetos, double taxaDiariaDesign, double taxaDiariaGerenciaProjeto, double taxaDiariaDesenvolvimento, Date dataCriacao) {
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
        this.IntegeregracaoFacebookOpenGraph = IntegeregracaoFacebookOpenGraph;
        this.notificacaoPush = notificacaoPush;
        this.planosDeAssinatura = planosDeAssinatura;
        this.processamentoDePagamento = processamentoDePagamento;
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.marketplaceDeUsuarios = marketplaceDeUsuarios;
        this.gerenciamentoDeProdutos = gerenciamentoDeProdutos;
        this.comprasDentroDoAplicativo = comprasDentroDoAplicativo;
        this.coletaInformacaoPagamento = coletaInformacaoPagamento;
        this.IntegeregracaoCms = IntegeregracaoCms;
        this.paginasAdministracaoUsuarios = paginasAdministracaoUsuarios;
        this.moderacaoAprovacaoConteudo = moderacaoAprovacaoConteudo;
        this.Integerercom = Integerercom;
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
        this.dataCriacao = dataCriacao;
        this.funcionalidadesDisponiveis = new LinkedHashMap<>();
        //this.relacaoBancoNomeReal = new HashMap<>();
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
    
    public String getNomeUsuario(){
        return usuarioModel.getNome();
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public Integer getPequeno() {
        return pequeno;
    }

    public void setPequeno(Integer pequeno) {
        this.pequeno = pequeno;
    }

    public Integer getMedio() {
        return medio;
    }

    public void setMedio(Integer medio) {
        this.medio = medio;
    }

    public Integer getGrande() {
        return grande;
    }

    public void setGrande(Integer grande) {
        this.grande = grande;
    }

    public Integer getMvp() {
        return mvp;
    }

    public void setMvp(Integer mvp) {
        this.mvp = mvp;
    }

    public Integer getBasico() {
        return basico;
    }

    public void setBasico(Integer basico) {
        this.basico = basico;
    }

    public Integer getProfissional() {
        return profissional;
    }

    public void setProfissional(Integer profissional) {
        this.profissional = profissional;
    }

    public Integer getCadastroPorEmailSenha() {
        return cadastroPorEmailSenha;
    }

    public void setCadastroPorEmailSenha(Integer cadastroPorEmailSenha) {
        this.cadastroPorEmailSenha = cadastroPorEmailSenha;
    }

    public Integer getCadastroPorFacebook() {
        return cadastroPorFacebook;
    }

    public void setCadastroPorFacebook(Integer cadastroPorFacebook) {
        this.cadastroPorFacebook = cadastroPorFacebook;
    }

    public Integer getCadastroPorTwitter() {
        return cadastroPorTwitter;
    }

    public void setCadastroPorTwitter(Integer cadastroPorTwitter) {
        this.cadastroPorTwitter = cadastroPorTwitter;
    }

    public Integer getCadastroPorGoogle() {
        return cadastroPorGoogle;
    }

    public void setCadastroPorGoogle(Integer cadastroPorGoogle) {
        this.cadastroPorGoogle = cadastroPorGoogle;
    }

    public Integer getCadastroPorLinkedin() {
        return cadastroPorLinkedin;
    }

    public void setCadastroPorLinkedin(Integer cadastroPorLinkedin) {
        this.cadastroPorLinkedin = cadastroPorLinkedin;
    }

    public Integer getCadastroPorGithub() {
        return cadastroPorGithub;
    }

    public void setCadastroPorGithub(Integer cadastroPorGithub) {
        this.cadastroPorGithub = cadastroPorGithub;
    }

    public Integer getCadastroPorConviteUsuario() {
        return cadastroPorConviteUsuario;
    }

    public void setCadastroPorConviteUsuario(Integer cadastroPorConviteUsuario) {
        this.cadastroPorConviteUsuario = cadastroPorConviteUsuario;
    }

    public Integer getCadastroPorContasMultitenant() {
        return cadastroPorContasMultitenant;
    }

    public void setCadastroPorContasMultitenant(Integer cadastroPorContasMultitenant) {
        this.cadastroPorContasMultitenant = cadastroPorContasMultitenant;
    }

    public Integer getCadastroPorSubdominios() {
        return cadastroPorSubdominios;
    }

    public void setCadastroPorSubdominios(Integer cadastroPorSubdominios) {
        this.cadastroPorSubdominios = cadastroPorSubdominios;
    }

    public Integer getCadastroPorDominiosPersonalizados() {
        return cadastroPorDominiosPersonalizados;
    }

    public void setCadastroPorDominiosPersonalizados(Integer cadastroPorDominiosPersonalizados) {
        this.cadastroPorDominiosPersonalizados = cadastroPorDominiosPersonalizados;
    }

    public Integer getPainel() {
        return painel;
    }

    public void setPainel(Integer painel) {
        this.painel = painel;
    }

    public Integer getFeedDeAtividades() {
        return feedDeAtividades;
    }

    public void setFeedDeAtividades(Integer feedDeAtividades) {
        this.feedDeAtividades = feedDeAtividades;
    }

    public Integer getUploadDeArquivos() {
        return uploadDeArquivos;
    }

    public void setUploadDeArquivos(Integer uploadDeArquivos) {
        this.uploadDeArquivos = uploadDeArquivos;
    }

    public Integer getUploadDeMidia() {
        return uploadDeMidia;
    }

    public void setUploadDeMidia(Integer uploadDeMidia) {
        this.uploadDeMidia = uploadDeMidia;
    }

    public Integer getPerfisDeUsuario() {
        return perfisDeUsuario;
    }

    public void setPerfisDeUsuario(Integer perfisDeUsuario) {
        this.perfisDeUsuario = perfisDeUsuario;
    }

    public Integer getEmailsTransacionais() {
        return emailsTransacionais;
    }

    public void setEmailsTransacionais(Integer emailsTransacionais) {
        this.emailsTransacionais = emailsTransacionais;
    }

    public Integer getTags() {
        return tags;
    }

    public void setTags(Integer tags) {
        this.tags = tags;
    }

    public Integer getAvaliacoesOuComentarios() {
        return avaliacoesOuComentarios;
    }

    public void setAvaliacoesOuComentarios(Integer avaliacoesOuComentarios) {
        this.avaliacoesOuComentarios = avaliacoesOuComentarios;
    }

    public Integer getProcessamentoAudioVideo() {
        return processamentoAudioVideo;
    }

    public void setProcessamentoAudioVideo(Integer processamentoAudioVideo) {
        this.processamentoAudioVideo = processamentoAudioVideo;
    }

    public Integer getPesquisaTextoLivre() {
        return pesquisaTextoLivre;
    }

    public void setPesquisaTextoLivre(Integer pesquisaTextoLivre) {
        this.pesquisaTextoLivre = pesquisaTextoLivre;
    }

    public Integer getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Integer pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Integer getCalendario() {
        return calendario;
    }

    public void setCalendario(Integer calendario) {
        this.calendario = calendario;
    }

    public Integer getExibicaoDadosMapaGeolocalizacao() {
        return exibicaoDadosMapaGeolocalizacao;
    }

    public void setExibicaoDadosMapaGeolocalizacao(Integer exibicaoDadosMapaGeolocalizacao) {
        this.exibicaoDadosMapaGeolocalizacao = exibicaoDadosMapaGeolocalizacao;
    }

    public Integer getExibicaoMarcadoresRegioesMapaPersonalizados() {
        return exibicaoMarcadoresRegioesMapaPersonalizados;
    }

    public void setExibicaoMarcadoresRegioesMapaPersonalizados(Integer exibicaoMarcadoresRegioesMapaPersonalizados) {
        this.exibicaoMarcadoresRegioesMapaPersonalizados = exibicaoMarcadoresRegioesMapaPersonalizados;
    }

    public Integer getReservas() {
        return reservas;
    }

    public void setReservas(Integer reservas) {
        this.reservas = reservas;
    }

    public Integer getMensagens() {
        return mensagens;
    }

    public void setMensagens(Integer mensagens) {
        this.mensagens = mensagens;
    }

    public Integer getForunsOuComentarios() {
        return forunsOuComentarios;
    }

    public void setForunsOuComentarios(Integer forunsOuComentarios) {
        this.forunsOuComentarios = forunsOuComentarios;
    }

    public Integer getCompartilhamentoSocial() {
        return compartilhamentoSocial;
    }

    public void setCompartilhamentoSocial(Integer compartilhamentoSocial) {
        this.compartilhamentoSocial = compartilhamentoSocial;
    }

    public Integer getIntegracaoFacebookOpenGraph() {
        return IntegeregracaoFacebookOpenGraph;
    }

    public void setIntegracaoFacebookOpenGraph(Integer IntegeregracaoFacebookOpenGraph) {
        this.IntegeregracaoFacebookOpenGraph = IntegeregracaoFacebookOpenGraph;
    }

    public Integer getNotificacaoPush() {
        return notificacaoPush;
    }

    public void setNotificacaoPush(Integer notificacaoPush) {
        this.notificacaoPush = notificacaoPush;
    }

    public Integer getPlanosDeAssinatura() {
        return planosDeAssinatura;
    }

    public void setPlanosDeAssinatura(Integer planosDeAssinatura) {
        this.planosDeAssinatura = planosDeAssinatura;
    }

    public Integer getProcessamentoDePagamento() {
        return processamentoDePagamento;
    }

    public void setProcessamentoDePagamento(Integer processamentoDePagamento) {
        this.processamentoDePagamento = processamentoDePagamento;
    }

    public Integer getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void setCarrinhoDeCompras(Integer carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public Integer getMarketplaceDeUsuarios() {
        return marketplaceDeUsuarios;
    }

    public void setMarketplaceDeUsuarios(Integer marketplaceDeUsuarios) {
        this.marketplaceDeUsuarios = marketplaceDeUsuarios;
    }

    public Integer getGerenciamentoDeProdutos() {
        return gerenciamentoDeProdutos;
    }

    public void setGerenciamentoDeProdutos(Integer gerenciamentoDeProdutos) {
        this.gerenciamentoDeProdutos = gerenciamentoDeProdutos;
    }

    public Integer getComprasDentroDoAplicativo() {
        return comprasDentroDoAplicativo;
    }

    public void setComprasDentroDoAplicativo(Integer comprasDentroDoAplicativo) {
        this.comprasDentroDoAplicativo = comprasDentroDoAplicativo;
    }

    public Integer getColetaInformacaoPagamento() {
        return coletaInformacaoPagamento;
    }

    public void setColetaInformacaoPagamento(Integer coletaInformacaoPagamento) {
        this.coletaInformacaoPagamento = coletaInformacaoPagamento;
    }

    public Integer getIntegracaoCms() {
        return IntegeregracaoCms;
    }

    public void setIntegracaoCms(Integer IntegeregracaoCms) {
        this.IntegeregracaoCms = IntegeregracaoCms;
    }

    public Integer getPaginasAdministracaoUsuarios() {
        return paginasAdministracaoUsuarios;
    }

    public void setPaginasAdministracaoUsuarios(Integer paginasAdministracaoUsuarios) {
        this.paginasAdministracaoUsuarios = paginasAdministracaoUsuarios;
    }

    public Integer getModeracaoAprovacaoConteudo() {
        return moderacaoAprovacaoConteudo;
    }

    public void setModeracaoAprovacaoConteudo(Integer moderacaoAprovacaoConteudo) {
        this.moderacaoAprovacaoConteudo = moderacaoAprovacaoConteudo;
    }

    public Integer getIntercom() {
        return Integerercom;
    }

    public void setIntercom(Integer Integerercom) {
        this.Integerercom = Integerercom;
    }

    public Integer getAnalisesUso() {
        return analisesUso;
    }

    public void setAnalisesUso(Integer analisesUso) {
        this.analisesUso = analisesUso;
    }

    public Integer getRelatoriosErro() {
        return relatoriosErro;
    }

    public void setRelatoriosErro(Integer relatoriosErro) {
        this.relatoriosErro = relatoriosErro;
    }

    public Integer getMonitoramentoPerformance() {
        return monitoramentoPerformance;
    }

    public void setMonitoramentoPerformance(Integer monitoramentoPerformance) {
        this.monitoramentoPerformance = monitoramentoPerformance;
    }

    public Integer getSuporteMultilingue() {
        return suporteMultilingue;
    }

    public void setSuporteMultilingue(Integer suporteMultilingue) {
        this.suporteMultilingue = suporteMultilingue;
    }

    public Integer getConectarServicosDeTerceiros() {
        return conectarServicosDeTerceiros;
    }

    public void setConectarServicosDeTerceiros(Integer conectarServicosDeTerceiros) {
        this.conectarServicosDeTerceiros = conectarServicosDeTerceiros;
    }

    public Integer getApiParaTerceiros() {
        return apiParaTerceiros;
    }

    public void setApiParaTerceiros(Integer apiParaTerceiros) {
        this.apiParaTerceiros = apiParaTerceiros;
    }

    public Integer getEnvioSms() {
        return envioSms;
    }

    public void setEnvioSms(Integer envioSms) {
        this.envioSms = envioSms;
    }

    public Integer getMascaramentoNumeroTelefone() {
        return mascaramentoNumeroTelefone;
    }

    public void setMascaramentoNumeroTelefone(Integer mascaramentoNumeroTelefone) {
        this.mascaramentoNumeroTelefone = mascaramentoNumeroTelefone;
    }

    public Integer getSegurancaBaseadaCertificadoSsl() {
        return segurancaBaseadaCertificadoSsl;
    }

    public void setSegurancaBaseadaCertificadoSsl(Integer segurancaBaseadaCertificadoSsl) {
        this.segurancaBaseadaCertificadoSsl = segurancaBaseadaCertificadoSsl;
    }

    public Integer getProtecaoContraDos() {
        return protecaoContraDos;
    }

    public void setProtecaoContraDos(Integer protecaoContraDos) {
        this.protecaoContraDos = protecaoContraDos;
    }

    public Integer getAutenticacaoDuasEtapas() {
        return autenticacaoDuasEtapas;
    }

    public void setAutenticacaoDuasEtapas(Integer autenticacaoDuasEtapas) {
        this.autenticacaoDuasEtapas = autenticacaoDuasEtapas;
    }

    public Integer getDesenvolvimentoEspecificoApp() {
        return desenvolvimentoEspecificoApp;
    }

    public void setDesenvolvimentoEspecificoApp(Integer desenvolvimentoEspecificoApp) {
        this.desenvolvimentoEspecificoApp = desenvolvimentoEspecificoApp;
    }

    public Integer getDesignIconeApp() {
        return designIconeApp;
    }

    public void setDesignIconeApp(Integer designIconeApp) {
        this.designIconeApp = designIconeApp;
    }

    public Integer getSincronizacaoNuvem() {
        return sincronizacaoNuvem;
    }

    public void setSincronizacaoNuvem(Integer sincronizacaoNuvem) {
        this.sincronizacaoNuvem = sincronizacaoNuvem;
    }

    public Integer getDadosSensoresDispositivo() {
        return dadosSensoresDispositivo;
    }

    public void setDadosSensoresDispositivo(Integer dadosSensoresDispositivo) {
        this.dadosSensoresDispositivo = dadosSensoresDispositivo;
    }

    public Integer getCodigoBarraQrCode() {
        return codigoBarraQrCode;
    }

    public void setCodigoBarraQrCode(Integer codigoBarraQrCode) {
        this.codigoBarraQrCode = codigoBarraQrCode;
    }

    public Integer getDadosSaude() {
        return dadosSaude;
    }

    public void setDadosSaude(Integer dadosSaude) {
        this.dadosSaude = dadosSaude;
    }

    public Integer getAppleWatch() {
        return appleWatch;
    }

    public void setAppleWatch(Integer appleWatch) {
        this.appleWatch = appleWatch;
    }

    public Integer getGerenteDeProjetos() {
        return gerenteDeProjetos;
    }

    public void setGerenteDeProjetos(Integer gerenteDeProjetos) {
        this.gerenteDeProjetos = gerenteDeProjetos;
    }

    public double getTaxaDiariaDesign() {
        return taxaDiariaDesign;
    }

    public void setTaxaDiariaDesign(Double taxaDiariaDesign) {
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
    
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
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
    funcionalidadesDisponiveis.put("Integração com Facebook Open Graph", IntegeregracaoFacebookOpenGraph);
    funcionalidadesDisponiveis.put("Notificação Push", notificacaoPush);
    funcionalidadesDisponiveis.put("Planos de Assinatura", planosDeAssinatura);
    funcionalidadesDisponiveis.put("Processamento de Pagamento", processamentoDePagamento);
    funcionalidadesDisponiveis.put("Carrinho de Compras", carrinhoDeCompras);
    funcionalidadesDisponiveis.put("Marketplace de Usuários", marketplaceDeUsuarios);
    funcionalidadesDisponiveis.put("Gerenciamento de Produtos", gerenciamentoDeProdutos);
    funcionalidadesDisponiveis.put("Compras dentro do Aplicativo", comprasDentroDoAplicativo);
    funcionalidadesDisponiveis.put("Coleta de Informação de Pagamento", coletaInformacaoPagamento);
    funcionalidadesDisponiveis.put("Integração com CMS", IntegeregracaoCms);
    funcionalidadesDisponiveis.put("Páginas de Administração de Usuários", paginasAdministracaoUsuarios);
    funcionalidadesDisponiveis.put("Moderação e Aprovação de Conteúdo", moderacaoAprovacaoConteudo);
    funcionalidadesDisponiveis.put("Intercom", Integerercom);
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

    /*public Map<String, String> getRelacaoBancoNomeReal() {
                relacaoBancoNomeReal.put("pequeno", "Pequeno");
        relacaoBancoNomeReal.put("medio", "Médio");
        relacaoBancoNomeReal.put("grande", "Grande");
        relacaoBancoNomeReal.put("mvp", "MVP");
        relacaoBancoNomeReal.put("basico", "Básico");
        relacaoBancoNomeReal.put("profissional", "Profissional");
        relacaoBancoNomeReal.put("cadastro_por_email_senha", "Cadastro por Email/Senha");
        relacaoBancoNomeReal.put("cadastro_por_facebook", "Cadastro por Facebook");
        relacaoBancoNomeReal.put("cadastro_por_twitter", "Cadastro por Twitter");
        relacaoBancoNomeReal.put("cadastro_por_google", "Cadastro por Google");
        relacaoBancoNomeReal.put("cadastro_por_linkedin", "Cadastro por LinkedIn");
        relacaoBancoNomeReal.put("cadastro_por_github", "Cadastro por GitHub");
        relacaoBancoNomeReal.put("cadastro_por_convite_usuario", "Cadastro por Convite de Usuário");
        relacaoBancoNomeReal.put("cadastro_por_contas_multitenant", "Cadastro por Contas Multi-tenant");
        relacaoBancoNomeReal.put("cadastro_por_subdominios", "Cadastro por Subdomínios");
        relacaoBancoNomeReal.put("cadastro_por_dominios_personalizados", "Cadastro por Domínios Personalizados");
        relacaoBancoNomeReal.put("painel", "Painel");
        relacaoBancoNomeReal.put("feed_de_atividades", "Feed de Atividades");
        relacaoBancoNomeReal.put("upload_de_arquivos", "Upload de Arquivos");
        relacaoBancoNomeReal.put("upload_de_midia", "Upload de Mídia");
        relacaoBancoNomeReal.put("perfis_de_usuario", "Perfis de Usuário");
        relacaoBancoNomeReal.put("emails_transacionais", "Emails Transacionais");
        relacaoBancoNomeReal.put("tags", "Tags");
        relacaoBancoNomeReal.put("avaliacoes_ou_comentarios", "Avaliações ou Comentários");
        relacaoBancoNomeReal.put("processamento_audio_video", "Processamento de Áudio e Vídeo");
        relacaoBancoNomeReal.put("pesquisa_texto_livre", "Pesquisa Texto Livre");
        relacaoBancoNomeReal.put("pesquisa", "Pesquisa");
        relacaoBancoNomeReal.put("calendario", "Calendário");
        relacaoBancoNomeReal.put("exibicao_dados_mapa_geolocalizacao", "Exibição de Dados no Mapa");
        relacaoBancoNomeReal.put("exibicao_marcadores_regioes_mapa_personalizados", "Exibição de Marcadores no Mapa");
        relacaoBancoNomeReal.put("reservas", "Reservas");
        relacaoBancoNomeReal.put("mensagens", "Mensagens");
        relacaoBancoNomeReal.put("foruns_ou_comentarios", "Fóruns ou Comentários");
        relacaoBancoNomeReal.put("compartilhamento_social", "Compartilhamento Social");
        relacaoBancoNomeReal.put("integracao_facebook_open_graph", "Integração com Facebook Open Graph");
        relacaoBancoNomeReal.put("notificacao_push", "Notificação Push");
        relacaoBancoNomeReal.put("planos_de_assinatura", "Planos de Assinatura");
        relacaoBancoNomeReal.put("processamento_de_pagamento", "Processamento de Pagamento");
        relacaoBancoNomeReal.put("carrinho_de_compras", "Carrinho de Compras");
        relacaoBancoNomeReal.put("marketplace_de_usuarios", "Marketplace de Usuários");
        relacaoBancoNomeReal.put("gerenciamento_de_produtos", "Gerenciamento de Produtos");
        relacaoBancoNomeReal.put("compras_dentro_do_aplicativo", "Compras dentro do Aplicativo");
        relacaoBancoNomeReal.put("coleta_informacao_pagamento", "Coleta de Informação de Pagamento");
        relacaoBancoNomeReal.put("integracao_cms", "Integração com CMS");
        relacaoBancoNomeReal.put("paginas_administracao_usuarios", "Páginas de Administração de Usuários");
        relacaoBancoNomeReal.put("moderacao_aprovacao_conteudo", "Moderação e Aprovação de Conteúdo");
        relacaoBancoNomeReal.put("intercom", "Intercom");
        relacaoBancoNomeReal.put("analises_uso", "Análises de Uso");
        relacaoBancoNomeReal.put("relatorios_erro", "Relatórios de Erro");
        relacaoBancoNomeReal.put("monitoramento_performance", "Monitoramento de Performance");
        relacaoBancoNomeReal.put("suporte_multilingue", "Suporte Multilíngue");
        relacaoBancoNomeReal.put("conectar_servicos_de_terceiros", "Conectar com Serviços de Terceiros");
        relacaoBancoNomeReal.put("api_para_terceiros", "API para Terceiros");
        relacaoBancoNomeReal.put("envio_sms", "Envio de SMS");
        relacaoBancoNomeReal.put("mascaramento_numero_telefone", "Mascaramento de Número de Telefone");
        relacaoBancoNomeReal.put("seguranca_baseada_certificado_ssl", "Segurança Baseada em Certificado SSL");
        relacaoBancoNomeReal.put("protecao_contra_dos", "Proteção Contra DoS");
        relacaoBancoNomeReal.put("autenticacao_duas_etapas", "Autenticação em Duas Etapas");
        relacaoBancoNomeReal.put("desenvolvimento_especifico_app", "Desenvolvimento Específico de App");
        relacaoBancoNomeReal.put("design_icone_app", "Design de Ícone para App");
        relacaoBancoNomeReal.put("sincronizacao_nuvem", "Sincronização com a Nuvem");
        relacaoBancoNomeReal.put("dados_sensores_dispositivo", "Dados de Sensores do Dispositivo");
        relacaoBancoNomeReal.put("codigo_barra_qr_code", "Código de Barras ou QR Code");
        relacaoBancoNomeReal.put("dados_saude", "Dados de Saúde");
        relacaoBancoNomeReal.put("apple_watch", "Apple Watch");
        relacaoBancoNomeReal.put("gerente_de_projetos", "Gerente de Projetos");
        
        return relacaoBancoNomeReal;
    }*/
    

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
                ", IntegeregracaoFacebookOpenGraph=" + IntegeregracaoFacebookOpenGraph +
                ", notificacaoPush=" + notificacaoPush +
                ", planosDeAssinatura=" + planosDeAssinatura +
                ", processamentoDePagamento=" + processamentoDePagamento +
                ", carrinhoDeCompras=" + carrinhoDeCompras +
                ", marketplaceDeUsuarios=" + marketplaceDeUsuarios +
                ", gerenciamentoDeProdutos=" + gerenciamentoDeProdutos +
                ", comprasDentroDoAplicativo=" + comprasDentroDoAplicativo +
                ", coletaInformacaoPagamento=" + coletaInformacaoPagamento +
                ", IntegeregracaoCms=" + IntegeregracaoCms +
                ", paginasAdministracaoUsuarios=" + paginasAdministracaoUsuarios +
                ", moderacaoAprovacaoConteudo=" + moderacaoAprovacaoConteudo +
                ", Integerercom=" + Integerercom +
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
