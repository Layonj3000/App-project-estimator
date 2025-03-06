package br.projeto.model;

import br.projeto.enums.SimNao;
import br.projeto.enums.Status;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjetoDeEstimativaModel {
    //VERIFICAR ALGUMAS MUDANÇAS DE TIPO DEPOIS!!!!!!!!!!!

    private Integer id;
    private UsuarioModel usuarioModel;
    private SimNao compartilhado;
    private Integer compartilhadoPor;
    private Date dataCriacao;
    private String nomeProjetoDeEstimativa; 
    private Status status; 
    private SimNao pequeno;
    private SimNao medio;
    private SimNao grande;
    private SimNao mvp;
    private SimNao basico;
    private SimNao profissional;
    private SimNao cadastroPorEmailSenha;
    private SimNao cadastroPorFacebook;
    private SimNao cadastroPorTwitter;
    private SimNao cadastroPorGoogle;
    private SimNao cadastroPorLinkedin;
    private SimNao cadastroPorGithub;
    private SimNao cadastroPorConviteUsuario;
    private SimNao cadastroPorContasMultitenant;
    private SimNao cadastroPorSubdominios;
    private SimNao cadastroPorDominiosPersonalizados;
    private SimNao painel;
    private SimNao feedDeAtividades;
    private SimNao uploadDeArquivos;
    private SimNao uploadDeMidia;
    private SimNao perfisDeUsuario;
    private SimNao emailsTransacionais;
    private SimNao tags;
    private SimNao avaliacoesOuComentarios;
    private SimNao processamentoAudioVideo;
    private SimNao pesquisaTextoLivre;
    private SimNao pesquisa;
    private SimNao calendario;
    private SimNao exibicaoDadosMapaGeolocalizacao;
    private SimNao exibicaoMarcadoresRegioesMapaPersonalizados;
    private SimNao reservas;
    private SimNao mensagens;
    private SimNao forunsOuComentarios;
    private SimNao compartilhamentoSocial;
    private SimNao integracaoFacebookOpenGraph;
    private SimNao notificacaoPush;
    private SimNao planosDeAssinatura;
    private SimNao processamentoDePagamento;
    private SimNao carrinhoDeCompras;
    private SimNao marketplaceDeUsuarios;
    private SimNao gerenciamentoDeProdutos;
    private SimNao comprasDentroDoAplicativo;
    private SimNao coletaInformacaoPagamento;
    private SimNao integracaoCms;
    private SimNao paginasAdministracaoUsuarios;
    private SimNao moderacaoAprovacaoConteudo;
    private SimNao intercom;
    private SimNao analisesUso;
    private SimNao relatoriosErro;
    private SimNao monitoramentoPerformance;
    private SimNao suporteMultilingue;
    private SimNao conectarServicosDeTerceiros;
    private SimNao apiParaTerceiros;
    private SimNao envioSms;
    private SimNao mascaramentoNumeroTelefone;
    private SimNao segurancaBaseadaCertificadoSsl;
    private SimNao protecaoContraDos;
    private SimNao autenticacaoDuasEtapas;
    private SimNao desenvolvimentoEspecificoApp;
    private SimNao designIconeApp;
    private SimNao sincronizacaoNuvem;
    private SimNao dadosSensoresDispositivo;
    private SimNao codigoBarraQrCode;
    private SimNao dadosSaude;
    private SimNao appleWatch;
    private SimNao gerenteDeProjetos;
    private Double custoHardware;
    private Double custoSoftware;
    private Double custoRiscos;
    private Double custoGarantia;
    private Double fundoDeReserva;
    private Double outrosCustos;
    /*private Double subTotal;*/
    private Double percentualComImpostos;
    /*private Double totalComImposto;*/
    private Double percentualLucroDesejado;
    /*private Double lucroCalculado;
    private Integer dias;
    private Double meses;
    private Double precoFinalCliente;
    private Double mediaPorMes;*/
    private final Map<String, SimNao> funcionalidadesDisponiveis;

    public ProjetoDeEstimativaModel(){
            funcionalidadesDisponiveis = new LinkedHashMap<>();
    }
    public ProjetoDeEstimativaModel(Integer id, UsuarioModel usuarioModel, int compartilhado,Integer compartilhadorPor,Date dataCriacao,String nomeProjetoDeEstimativa,int status, int pequeno, int medio, int grande, int mvp, int basico, int profissional, int cadastroPorEmailSenha, int cadastroPorFacebook, int cadastroPorTwitter, int cadastroPorGoogle, int cadastroPorLinkedin, int cadastroPorGithub, int cadastroPorConviteUsuario, int cadastroPorContasMultitenant, int cadastroPorSubdominios, int cadastroPorDominiosPersonalizados, int painel, int feedDeAtividades, int uploadDeArquivos, int uploadDeMidia, int perfisDeUsuario, int emailsTransacionais, int tags, int avaliacoesOuComentarios, int processamentoAudioVideo, int pesquisaTextoLivre, int pesquisa, int calendario, int exibicaoDadosMapaGeolocalizacao, int exibicaoMarcadoresRegioesMapaPersonalizados, int reservas, int mensagens, int forunsOuComentarios, int compartilhamentoSocial, int integracaoFacebookOpenGraph, int notificacaoPush, int planosDeAssinatura, int processamentoDePagamento, int carrinhoDeCompras, int marketplaceDeUsuarios, int gerenciamentoDeProdutos, int comprasDentroDoAplicativo, int coletaInformacaoPagamento, int integracaoCms, int paginasAdministracaoUsuarios, int moderacaoAprovacaoConteudo, int intercom, int analisesUso, int relatoriosErro, int monitoramentoPerformance, int suporteMultilingue, int conectarServicosDeTerceiros, int apiParaTerceiros, int envioSms, int mascaramentoNumeroTelefone, int segurancaBaseadaCertificadoSsl, int protecaoContraDos, int autenticacaoDuasEtapas, int desenvolvimentoEspecificoApp, int designIconeApp, int sincronizacaoNuvem, int dados_sensoresDispositivo, int codigoBarraQrCode, int dadosSaude, int appleWatch, int gerenteDeProjetos, Double custoHardware, Double custoSoftware, Double custoRiscos, Double custoGarantia, Double fundoDeReserva, Double outrosCustos, /*Double subTotal,*/ Double percentualComImpostos, /*Double totalComImposto,*/ Double percentualLucroDesejado/*, Double lucroCalculado, Integer dias, Double meses, Double precoFinalCliente, Double mediaPorMes*/){
        this.id = id;
        this.usuarioModel = usuarioModel;
        this.compartilhado = SimNao.fromValue(compartilhado);
        this.compartilhadoPor = compartilhadorPor;
        this.dataCriacao = dataCriacao;
        this.nomeProjetoDeEstimativa = nomeProjetoDeEstimativa;
        this.status = Status.fromValue(status);
        this.pequeno = SimNao.fromValue(pequeno);
        this.medio = SimNao.fromValue(medio);
        this.grande = SimNao.fromValue(grande);
        this.mvp = SimNao.fromValue(mvp);
        this.basico = SimNao.fromValue(basico);
        this.profissional = SimNao.fromValue(profissional);
        this.cadastroPorEmailSenha = SimNao.fromValue(cadastroPorEmailSenha);
        this.cadastroPorFacebook = SimNao.fromValue(cadastroPorFacebook);
        this.cadastroPorTwitter = SimNao.fromValue(cadastroPorTwitter);
        this.cadastroPorGoogle = SimNao.fromValue(cadastroPorGoogle);
        this.cadastroPorLinkedin = SimNao.fromValue(cadastroPorLinkedin);
        this.cadastroPorGithub = SimNao.fromValue(cadastroPorGithub);
        this.cadastroPorConviteUsuario = SimNao.fromValue(cadastroPorConviteUsuario);
        this.cadastroPorContasMultitenant = SimNao.fromValue(cadastroPorContasMultitenant);
        this.cadastroPorSubdominios = SimNao.fromValue(cadastroPorSubdominios);
        this.cadastroPorDominiosPersonalizados = SimNao.fromValue(cadastroPorDominiosPersonalizados);
        this.painel = SimNao.fromValue(painel);
        this.feedDeAtividades = SimNao.fromValue(feedDeAtividades);
        this.uploadDeArquivos = SimNao.fromValue(uploadDeArquivos);
        this.uploadDeMidia = SimNao.fromValue(uploadDeMidia);
        this.perfisDeUsuario = SimNao.fromValue(perfisDeUsuario);
        this.emailsTransacionais = SimNao.fromValue(emailsTransacionais);
        this.tags = SimNao.fromValue(tags);
        this.avaliacoesOuComentarios = SimNao.fromValue(avaliacoesOuComentarios);
        this.processamentoAudioVideo = SimNao.fromValue(processamentoAudioVideo);
        this.pesquisaTextoLivre = SimNao.fromValue(pesquisaTextoLivre);
        this.pesquisa = SimNao.fromValue(pesquisa);
        this.calendario = SimNao.fromValue(calendario);
        this.exibicaoDadosMapaGeolocalizacao = SimNao.fromValue(exibicaoDadosMapaGeolocalizacao);
        this.exibicaoMarcadoresRegioesMapaPersonalizados = SimNao.fromValue(exibicaoMarcadoresRegioesMapaPersonalizados);
        this.reservas = SimNao.fromValue(reservas);
        this.mensagens = SimNao.fromValue(mensagens);
        this.forunsOuComentarios = SimNao.fromValue(forunsOuComentarios);
        this.compartilhamentoSocial = SimNao.fromValue(compartilhamentoSocial);
        this.integracaoFacebookOpenGraph = SimNao.fromValue(integracaoFacebookOpenGraph);
        this.notificacaoPush = SimNao.fromValue(notificacaoPush);
        this.planosDeAssinatura = SimNao.fromValue(planosDeAssinatura);
        this.processamentoDePagamento = SimNao.fromValue(processamentoDePagamento);
        this.carrinhoDeCompras = SimNao.fromValue(carrinhoDeCompras);
        this.marketplaceDeUsuarios = SimNao.fromValue(marketplaceDeUsuarios);
        this.gerenciamentoDeProdutos = SimNao.fromValue(gerenciamentoDeProdutos);
        this.comprasDentroDoAplicativo = SimNao.fromValue(comprasDentroDoAplicativo);
        this.coletaInformacaoPagamento = SimNao.fromValue(coletaInformacaoPagamento);
        this.integracaoCms = SimNao.fromValue(integracaoCms);
        this.paginasAdministracaoUsuarios = SimNao.fromValue(paginasAdministracaoUsuarios);
        this.moderacaoAprovacaoConteudo = SimNao.fromValue(moderacaoAprovacaoConteudo);
        this.intercom = SimNao.fromValue(intercom);
        this.analisesUso = SimNao.fromValue(analisesUso);
        this.relatoriosErro = SimNao.fromValue(relatoriosErro);
        this.monitoramentoPerformance = SimNao.fromValue(monitoramentoPerformance);
        this.suporteMultilingue = SimNao.fromValue(suporteMultilingue);
        this.conectarServicosDeTerceiros = SimNao.fromValue(conectarServicosDeTerceiros);
        this.apiParaTerceiros = SimNao.fromValue(apiParaTerceiros);
        this.envioSms = SimNao.fromValue(envioSms);
        this.mascaramentoNumeroTelefone = SimNao.fromValue(mascaramentoNumeroTelefone);
        this.segurancaBaseadaCertificadoSsl = SimNao.fromValue(segurancaBaseadaCertificadoSsl);
        this.protecaoContraDos = SimNao.fromValue(protecaoContraDos);
        this.autenticacaoDuasEtapas = SimNao.fromValue(autenticacaoDuasEtapas);
        this.desenvolvimentoEspecificoApp = SimNao.fromValue(desenvolvimentoEspecificoApp);
        this.designIconeApp = SimNao.fromValue(designIconeApp);
        this.sincronizacaoNuvem = SimNao.fromValue(sincronizacaoNuvem);
        this.dadosSensoresDispositivo = SimNao.fromValue(dados_sensoresDispositivo);
        this.codigoBarraQrCode = SimNao.fromValue(codigoBarraQrCode);
        this.dadosSaude = SimNao.fromValue(dadosSaude);
        this.appleWatch = SimNao.fromValue(appleWatch);
        this.gerenteDeProjetos = SimNao.fromValue(gerenteDeProjetos);
        this.custoHardware = custoHardware;
        this.custoSoftware = custoSoftware;
        this.custoRiscos = custoRiscos;
        this.custoGarantia = custoGarantia;
        this.fundoDeReserva = fundoDeReserva;
        this.outrosCustos = outrosCustos;
        /*this.subTotal = subTotal;*/
        this.percentualComImpostos = percentualComImpostos;
        /*this.totalComImposto = totalComImposto;*/
        this.percentualLucroDesejado = percentualLucroDesejado;
        /*this.lucroCalculado = lucroCalculado;
        this.dias = dias;
        this.meses = meses;
        this.precoFinalCliente = precoFinalCliente;
        this.mediaPorMes = mediaPorMes;*/
        funcionalidadesDisponiveis = new LinkedHashMap<>();
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
    
    public String getNomeUsuario(){
        return usuarioModel.getNome();
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public Integer getCompartilhadoValor() {
        return compartilhado != null ? compartilhado.getValor() : 0;
    }

    public void setCompartilhado(Integer compartilhado) {
        this.compartilhado = compartilhado != null ? SimNao.fromValue(compartilhado) : SimNao.NAO;
    }

    public Integer getCompartilhadoPor() {
        return compartilhadoPor;
    }

    public void setCompartilhadoPor(Integer compartilhadoPor) {
        this.compartilhadoPor = compartilhadoPor;
    }
    
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeProjetoDeEstimativa() {
        return nomeProjetoDeEstimativa;
    }

    public void setNomeProjetoDeEstimativa(String nomeProjetoDeEstimativa) {
        this.nomeProjetoDeEstimativa = nomeProjetoDeEstimativa;
    }

    public Integer getStatusValor(){return status != null ? status.getValor() : 0;}

    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Integer status){
        this.status = status != null ? Status.fromValue(status):Status.ESTIMADO;
    }


    public Integer getPequenoValor() {
        return pequeno != null ? pequeno.getValor() : 0;
    }

    public void setPequeno(Integer pequeno) {
        this.pequeno = pequeno != null ? SimNao.fromValue(pequeno) : SimNao.NAO;
    }

    public Integer getMedioValor() {
        return medio != null ? medio.getValor() : 0;
    }

    public void setMedio(Integer medio) {
        this.medio = medio != null ? SimNao.fromValue(medio) : SimNao.NAO;
    }

    public Integer getGrandeValor() {
        return grande != null ? grande.getValor() : 0;
    }

    public void setGrande(Integer grande) {
        this.grande = grande != null ? SimNao.fromValue(grande) : SimNao.NAO;
    }

    public Integer getMvpValor() {
        return mvp != null ? mvp.getValor() : 0;
    }

    public void setMvp(Integer mvp) {
        this.mvp = mvp != null ? SimNao.fromValue(mvp) : SimNao.NAO;
    }

    public Integer getBasicoValor() {
        return basico != null ? basico.getValor() : 0;
    }

    public void setBasico(Integer basico) {
        this.basico = basico != null ? SimNao.fromValue(basico) : SimNao.NAO;
    }

    public Integer getProfissionalValor() {
        return profissional != null ? profissional.getValor() : 0;
    }

    public void setProfissional(Integer profissional) {
        this.profissional = profissional != null ? SimNao.fromValue(profissional) : SimNao.NAO;
    }

    public Integer getCadastroPorEmailSenhaValor() {
        return cadastroPorEmailSenha != null ? cadastroPorEmailSenha.getValor() : 0;
    }

    public void setCadastroPorEmailSenha(Integer cadastroPorEmailSenha) {
        this.cadastroPorEmailSenha = cadastroPorEmailSenha != null ? SimNao.fromValue(cadastroPorEmailSenha) : SimNao.NAO;
    }

    public Integer getCadastroPorFacebookValor() {
        return cadastroPorFacebook != null ? cadastroPorFacebook.getValor() : 0;
    }

    public void setCadastroPorFacebook(Integer cadastroPorFacebook) {
        this.cadastroPorFacebook = cadastroPorFacebook != null ? SimNao.fromValue(cadastroPorFacebook) : SimNao.NAO;
    }

    public Integer getCadastroPorTwitterValor() {
        return cadastroPorTwitter != null ? cadastroPorTwitter.getValor() : 0;
    }

    public void setCadastroPorTwitter(Integer cadastroPorTwitter) {
        this.cadastroPorTwitter = cadastroPorTwitter != null ? SimNao.fromValue(cadastroPorTwitter) : SimNao.NAO;
    }

    public Integer getCadastroPorGoogleValor() {
        return cadastroPorGoogle != null ? cadastroPorGoogle.getValor() : 0;
    }

    public void setCadastroPorGoogle(Integer cadastroPorGoogle) {
        this.cadastroPorGoogle = cadastroPorGoogle != null ? SimNao.fromValue(cadastroPorGoogle) : SimNao.NAO;
    }

    public Integer getCadastroPorLinkedinValor() {
        return cadastroPorLinkedin != null ? cadastroPorLinkedin.getValor() : 0;
    }

    public void setCadastroPorLinkedin(Integer cadastroPorLinkedin) {
        this.cadastroPorLinkedin = cadastroPorLinkedin != null ? SimNao.fromValue(cadastroPorLinkedin) : SimNao.NAO;
    }

    public Integer getCadastroPorGithubValor() {
        return cadastroPorGithub != null ? cadastroPorGithub.getValor() : 0;
    }

    public void setCadastroPorGithub(Integer cadastroPorGithub) {
        this.cadastroPorGithub = cadastroPorGithub != null ? SimNao.fromValue(cadastroPorGithub) : SimNao.NAO;
    }

    public Integer getCadastroPorConviteUsuarioValor() {
        return cadastroPorConviteUsuario != null ? cadastroPorConviteUsuario.getValor() : 0;
    }

    public void setCadastroPorConviteUsuario(Integer cadastroPorConviteUsuario) {
        this.cadastroPorConviteUsuario = cadastroPorConviteUsuario != null ? SimNao.fromValue(cadastroPorConviteUsuario) : SimNao.NAO;
    }

    public Integer getCadastroPorContasMultitenantValor() {
        return cadastroPorContasMultitenant != null ? cadastroPorContasMultitenant.getValor() : 0;
    }

    public void setCadastroPorContasMultitenant(Integer cadastroPorContasMultitenant) {
        this.cadastroPorContasMultitenant = cadastroPorContasMultitenant != null ? SimNao.fromValue(cadastroPorContasMultitenant) : SimNao.NAO;
    }

    public Integer getCadastroPorSubdominiosValor() {
        return cadastroPorSubdominios != null ? cadastroPorSubdominios.getValor() : 0;
    }

    public void setCadastroPorSubdominios(Integer cadastroPorSubdominios) {
        this.cadastroPorSubdominios = cadastroPorSubdominios != null ? SimNao.fromValue(cadastroPorSubdominios) : SimNao.NAO;
    }

    public Integer getCadastroPorDominiosPersonalizadosValor() {
        return cadastroPorDominiosPersonalizados != null ? cadastroPorDominiosPersonalizados.getValor() : 0;
    }

    public void setCadastroPorDominiosPersonalizados(Integer cadastroPorDominiosPersonalizados) {
        this.cadastroPorDominiosPersonalizados = cadastroPorDominiosPersonalizados != null ? SimNao.fromValue(cadastroPorDominiosPersonalizados) : SimNao.NAO;
    }

    public Integer getPainelValor() {
        return painel != null ? painel.getValor() : 0;
    }

    public void setPainel(Integer painel) {
        this.painel = painel != null ? SimNao.fromValue(painel) : SimNao.NAO;
    }

    public Integer getFeedDeAtividadesValor() {
        return feedDeAtividades != null ? feedDeAtividades.getValor() : 0;
    }

    public void setFeedDeAtividades(Integer feedDeAtividades) {
        this.feedDeAtividades = feedDeAtividades != null ? SimNao.fromValue(feedDeAtividades) : SimNao.NAO;
    }

    public Integer getUploadDeArquivosValor() {
        return uploadDeArquivos != null ? uploadDeArquivos.getValor() : 0;
    }

    public void setUploadDeArquivos(Integer uploadDeArquivos) {
        this.uploadDeArquivos = uploadDeArquivos != null ? SimNao.fromValue(uploadDeArquivos) : SimNao.NAO;
    }

    public Integer getUploadDeMidiaValor() {
        return uploadDeMidia != null ? uploadDeMidia.getValor() : 0;
    }

    public void setUploadDeMidia(Integer uploadDeMidia) {
        this.uploadDeMidia = uploadDeMidia != null ? SimNao.fromValue(uploadDeMidia) : SimNao.NAO;
    }

    public Integer getPerfisDeUsuarioValor() {
        return perfisDeUsuario != null ? perfisDeUsuario.getValor() : 0;
    }

    public void setPerfisDeUsuario(Integer perfisDeUsuario) {
        this.perfisDeUsuario = perfisDeUsuario != null ? SimNao.fromValue(perfisDeUsuario) : SimNao.NAO;
    }

    public Integer getEmailsTransacionaisValor() {
        return emailsTransacionais != null ? emailsTransacionais.getValor() : 0;
    }

    public void setEmailsTransacionais(Integer emailsTransacionais) {
        this.emailsTransacionais = emailsTransacionais != null ? SimNao.fromValue(emailsTransacionais) : SimNao.NAO;
    }

    public Integer getTagsValor() {
        return tags != null ? tags.getValor() : 0;
    }

    public void setTags(Integer tags) {
        this.tags = tags != null ? SimNao.fromValue(tags) : SimNao.NAO;
    }

    public Integer getAvaliacoesOuComentariosValor() {
        return avaliacoesOuComentarios != null ? avaliacoesOuComentarios.getValor() : 0;
    }

    public void setAvaliacoesOuComentarios(Integer avaliacoesOuComentarios) {
        this.avaliacoesOuComentarios = avaliacoesOuComentarios != null ? SimNao.fromValue(avaliacoesOuComentarios) : SimNao.NAO;
    }

    public Integer getProcessamentoAudioVideoValor() {
        return processamentoAudioVideo != null ? processamentoAudioVideo.getValor() : 0;
    }

    public void setProcessamentoAudioVideo(Integer processamentoAudioVideo) {
        this.processamentoAudioVideo = processamentoAudioVideo != null ? SimNao.fromValue(processamentoAudioVideo) : SimNao.NAO;
    }

    public Integer getPesquisaTextoLivreValor() {
        return pesquisaTextoLivre != null ? pesquisaTextoLivre.getValor() : 0;
    }

    public void setPesquisaTextoLivre(Integer pesquisaTextoLivre) {
        this.pesquisaTextoLivre = pesquisaTextoLivre != null ? SimNao.fromValue(pesquisaTextoLivre) : SimNao.NAO;
    }

    public Integer getPesquisaValor() {
        return pesquisa != null ? pesquisa.getValor() : 0;
    }

    public void setPesquisa(Integer pesquisa) {
        this.pesquisa = pesquisa != null ? SimNao.fromValue(pesquisa) : SimNao.NAO;
    }

    public Integer getCalendarioValor() {
        return calendario != null ? calendario.getValor() : 0;
    }

    public void setCalendario(Integer calendario) {
        this.calendario = calendario != null ? SimNao.fromValue(calendario) : SimNao.NAO;
    }

    public Integer getExibicaoDadosMapaGeolocalizacaoValor() {
        return exibicaoDadosMapaGeolocalizacao != null ? exibicaoDadosMapaGeolocalizacao.getValor() : 0;
    }

    public void setExibicaoDadosMapaGeolocalizacao(Integer exibicaoDadosMapaGeolocalizacao) {
        this.exibicaoDadosMapaGeolocalizacao = exibicaoDadosMapaGeolocalizacao != null ? SimNao.fromValue(exibicaoDadosMapaGeolocalizacao) : SimNao.NAO;
    }

    public Integer getExibicaoMarcadoresRegioesMapaPersonalizadosValor() {
        return exibicaoMarcadoresRegioesMapaPersonalizados != null ? exibicaoMarcadoresRegioesMapaPersonalizados.getValor() : 0;
    }

    public void setExibicaoMarcadoresRegioesMapaPersonalizados(Integer exibicaoMarcadoresRegioesMapaPersonalizados) {
        this.exibicaoMarcadoresRegioesMapaPersonalizados = exibicaoMarcadoresRegioesMapaPersonalizados != null ? SimNao.fromValue(exibicaoMarcadoresRegioesMapaPersonalizados) : SimNao.NAO;
    }

    public Integer getReservasValor() {
        return reservas != null ? reservas.getValor() : 0;
    }

    public void setReservas(Integer reservas) {
        this.reservas = reservas != null ? SimNao.fromValue(reservas) : SimNao.NAO;
    }

    public Integer getMensagensValor() {
        return mensagens != null ? mensagens.getValor() : 0;
    }

    public void setMensagens(Integer mensagens) {
        this.mensagens = mensagens != null ? SimNao.fromValue(mensagens) : SimNao.NAO;
    }

    public Integer getForunsOuComentariosValor() {
        return forunsOuComentarios != null ? forunsOuComentarios.getValor() : 0;
    }

    public void setForunsOuComentarios(Integer forunsOuComentarios) {
        this.forunsOuComentarios = forunsOuComentarios != null ? SimNao.fromValue(forunsOuComentarios) : SimNao.NAO;
    }

    public Integer getCompartilhamentoSocialValor() {
        return compartilhamentoSocial != null ? compartilhamentoSocial.getValor() : 0;
    }

    public void setCompartilhamentoSocial(Integer compartilhamentoSocial) {
        this.compartilhamentoSocial = compartilhamentoSocial != null ? SimNao.fromValue(compartilhamentoSocial) : SimNao.NAO;
    }

    public Integer getIntegracaoFacebookOpenGraphValor() {
        return integracaoFacebookOpenGraph != null ? integracaoFacebookOpenGraph.getValor() : 0;
    }

    public void setIntegracaoFacebookOpenGraph(Integer integracaoFacebookOpenGraph) {
        this.integracaoFacebookOpenGraph = integracaoFacebookOpenGraph != null ? SimNao.fromValue(integracaoFacebookOpenGraph) : SimNao.NAO;
    }

    public Integer getNotificacaoPushValor() {
        return notificacaoPush != null ? notificacaoPush.getValor() : 0;
    }

    public void setNotificacaoPush(Integer notificacaoPush) {
        this.notificacaoPush = notificacaoPush != null ? SimNao.fromValue(notificacaoPush) : SimNao.NAO;
    }

    public Integer getPlanosDeAssinaturaValor() {
        return planosDeAssinatura != null ? planosDeAssinatura.getValor() : 0;
    }

    public void setPlanosDeAssinatura(Integer planosDeAssinatura) {
        this.planosDeAssinatura = planosDeAssinatura != null ? SimNao.fromValue(planosDeAssinatura) : SimNao.NAO;
    }

    public Integer getProcessamentoDePagamentoValor() {
        return processamentoDePagamento != null ? processamentoDePagamento.getValor() : 0;
    }

    public void setProcessamentoDePagamento(Integer processamentoDePagamento) {
        this.processamentoDePagamento = processamentoDePagamento != null ? SimNao.fromValue(processamentoDePagamento) : SimNao.NAO;
    }

    public Integer getCarrinhoDeComprasValor() {
        return carrinhoDeCompras != null ? carrinhoDeCompras.getValor() : 0;
    }

    public void setCarrinhoDeCompras(Integer carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras != null ? SimNao.fromValue(carrinhoDeCompras) : SimNao.NAO;
    }

    public Integer getMarketplaceDeUsuariosValor() {
        return marketplaceDeUsuarios != null ? marketplaceDeUsuarios.getValor() : 0;
    }

    public void setMarketplaceDeUsuarios(Integer marketplaceDeUsuarios) {
        this.marketplaceDeUsuarios = marketplaceDeUsuarios != null ? SimNao.fromValue(marketplaceDeUsuarios) : SimNao.NAO;
    }

    public Integer getGerenciamentoDeProdutosValor() {
        return gerenciamentoDeProdutos != null ? gerenciamentoDeProdutos.getValor() : 0;
    }

    public void setGerenciamentoDeProdutos(Integer gerenciamentoDeProdutos) {
        this.gerenciamentoDeProdutos = gerenciamentoDeProdutos != null ? SimNao.fromValue(gerenciamentoDeProdutos) : SimNao.NAO;
    }

    public Integer getComprasDentroDoAplicativoValor() {
        return comprasDentroDoAplicativo != null ? comprasDentroDoAplicativo.getValor() : 0;
    }

    public void setComprasDentroDoAplicativo(Integer comprasDentroDoAplicativo) {
        this.comprasDentroDoAplicativo = comprasDentroDoAplicativo != null ? SimNao.fromValue(comprasDentroDoAplicativo) : SimNao.NAO;
    }

    public Integer getColetaInformacaoPagamentoValor() {
        return coletaInformacaoPagamento != null ? coletaInformacaoPagamento.getValor() : 0;
    }

    public void setColetaInformacaoPagamento(Integer coletaInformacaoPagamento) {
        this.coletaInformacaoPagamento = coletaInformacaoPagamento != null ? SimNao.fromValue(coletaInformacaoPagamento) : SimNao.NAO;
    }

    public Integer getIntegracaoCmsValor() {
        return integracaoCms != null ? integracaoCms.getValor() : 0;
    }

    public void setIntegracaoCms(Integer integracaoCms) {
        this.integracaoCms = integracaoCms != null ? SimNao.fromValue(integracaoCms) : SimNao.NAO;
    }

    public Integer getPaginasAdministracaoUsuariosValor() {
        return paginasAdministracaoUsuarios != null ? paginasAdministracaoUsuarios.getValor() : 0;
    }

    public void setPaginasAdministracaoUsuarios(Integer paginasAdministracaoUsuarios) {
        this.paginasAdministracaoUsuarios = paginasAdministracaoUsuarios != null ? SimNao.fromValue(paginasAdministracaoUsuarios) : SimNao.NAO;
    }

    public Integer getModeracaoAprovacaoConteudoValor() {
        return moderacaoAprovacaoConteudo != null ? moderacaoAprovacaoConteudo.getValor() : 0;
    }

    public void setModeracaoAprovacaoConteudo(Integer moderacaoAprovacaoConteudo) {
        this.moderacaoAprovacaoConteudo = moderacaoAprovacaoConteudo != null ? SimNao.fromValue(moderacaoAprovacaoConteudo) : SimNao.NAO;
    }

    public Integer getIntercomValor() {
        return intercom != null ? intercom.getValor() : 0;
    }

    public void setIntercom(Integer intercom) {
        this.intercom = intercom != null ? SimNao.fromValue(intercom) : SimNao.NAO;
    }

    public Integer getAnalisesUsoValor() {
        return analisesUso != null ? analisesUso.getValor() : 0;
    }

    public void setAnalisesUso(Integer analisesUso) {
        this.analisesUso = analisesUso != null ? SimNao.fromValue(analisesUso) : SimNao.NAO;
    }

    public Integer getRelatoriosErroValor() {
        return relatoriosErro != null ? relatoriosErro.getValor() : 0;
    }

    public void setRelatoriosErro(Integer relatoriosErro) {
        this.relatoriosErro = relatoriosErro != null ? SimNao.fromValue(relatoriosErro) : SimNao.NAO;
    }

    public Integer getMonitoramentoPerformanceValor() {
        return monitoramentoPerformance != null ? monitoramentoPerformance.getValor() : 0;
    }

    public void setMonitoramentoPerformance(Integer monitoramentoPerformance) {
        this.monitoramentoPerformance = monitoramentoPerformance != null ? SimNao.fromValue(monitoramentoPerformance) : SimNao.NAO;
    }

    public Integer getSuporteMultilingueValor() {
        return suporteMultilingue != null ? suporteMultilingue.getValor() : 0;
    }

    public void setSuporteMultilingue(Integer suporteMultilingue) {
        this.suporteMultilingue = suporteMultilingue != null ? SimNao.fromValue(suporteMultilingue) : SimNao.NAO;
    }

    public Integer getConectarServicosDeTerceirosValor() {
        return conectarServicosDeTerceiros != null ? conectarServicosDeTerceiros.getValor() : 0;
    }

    public void setConectarServicosDeTerceiros(Integer conectarServicosDeTerceiros) {
        this.conectarServicosDeTerceiros = conectarServicosDeTerceiros != null ? SimNao.fromValue(conectarServicosDeTerceiros) : SimNao.NAO;
    }

    public Integer getApiParaTerceirosValor() {
        return apiParaTerceiros != null ? apiParaTerceiros.getValor() : 0;
    }

    public void setApiParaTerceiros(Integer apiParaTerceiros) {
        this.apiParaTerceiros = apiParaTerceiros != null ? SimNao.fromValue(apiParaTerceiros) : SimNao.NAO;
    }

    public Integer getEnvioSmsValor() {
        return envioSms != null ? envioSms.getValor() : 0;
    }

    public void setEnvioSms(Integer envioSms) {
        this.envioSms = envioSms != null ? SimNao.fromValue(envioSms) : SimNao.NAO;
    }

    public Integer getMascaramentoNumeroTelefoneValor() {
        return mascaramentoNumeroTelefone != null ? mascaramentoNumeroTelefone.getValor() : 0;
    }

    public void setMascaramentoNumeroTelefone(Integer mascaramentoNumeroTelefone) {
        this.mascaramentoNumeroTelefone = mascaramentoNumeroTelefone != null ? SimNao.fromValue(mascaramentoNumeroTelefone) : SimNao.NAO;
    }

    public Integer getSegurancaBaseadaCertificadoSslValor() {
        return segurancaBaseadaCertificadoSsl != null ? segurancaBaseadaCertificadoSsl.getValor() : 0;
    }

    public void setSegurancaBaseadaCertificadoSsl(Integer segurancaBaseadaCertificadoSsl) {
        this.segurancaBaseadaCertificadoSsl = segurancaBaseadaCertificadoSsl != null ? SimNao.fromValue(segurancaBaseadaCertificadoSsl) : SimNao.NAO;
    }

    public Integer getProtecaoContraDosValor() {
        return protecaoContraDos != null ? protecaoContraDos.getValor() : 0;
    }

    public void setProtecaoContraDos(Integer protecaoContraDos) {
        this.protecaoContraDos = protecaoContraDos != null ? SimNao.fromValue(protecaoContraDos) : SimNao.NAO;
    }

    public Integer getAutenticacaoDuasEtapasValor() {
        return autenticacaoDuasEtapas != null ? autenticacaoDuasEtapas.getValor() : 0;
    }

    public void setAutenticacaoDuasEtapas(Integer autenticacaoDuasEtapas) {
        this.autenticacaoDuasEtapas = autenticacaoDuasEtapas != null ? SimNao.fromValue(autenticacaoDuasEtapas) : SimNao.NAO;
    }

    public Integer getDesenvolvimentoEspecificoAppValor() {
        return desenvolvimentoEspecificoApp != null ? desenvolvimentoEspecificoApp.getValor() : 0;
    }

    public void setDesenvolvimentoEspecificoApp(Integer desenvolvimentoEspecificoApp) {
        this.desenvolvimentoEspecificoApp = desenvolvimentoEspecificoApp != null ? SimNao.fromValue(desenvolvimentoEspecificoApp) : SimNao.NAO;
    }

    public Integer getDesignIconeAppValor() {
        return designIconeApp != null ? designIconeApp.getValor() : 0;
    }

    public void setDesignIconeApp(Integer designIconeApp) {
        this.designIconeApp = designIconeApp != null ? SimNao.fromValue(designIconeApp) : SimNao.NAO;
    }

    public Integer getSincronizacaoNuvemValor() {
        return sincronizacaoNuvem != null ? sincronizacaoNuvem.getValor() : 0;
    }

    public void setSincronizacaoNuvem(Integer sincronizacaoNuvem) {
        this.sincronizacaoNuvem = sincronizacaoNuvem != null ? SimNao.fromValue(sincronizacaoNuvem) : SimNao.NAO;
    }

    public Integer getDadosSensoresDispositivoValor() {
        return dadosSensoresDispositivo != null ? dadosSensoresDispositivo.getValor() : 0;
    }

    public void setDadosSensoresDispositivo(Integer dadosSensoresDispositivo) {
        this.dadosSensoresDispositivo = dadosSensoresDispositivo != null ? SimNao.fromValue(dadosSensoresDispositivo) : SimNao.NAO;
    }

    public Integer getCodigoBarraQrCodeValor() {
        return codigoBarraQrCode != null ? codigoBarraQrCode.getValor() : 0;
    }

    public void setCodigoBarraQrCode(Integer codigoBarraQrCode) {
        this.codigoBarraQrCode = codigoBarraQrCode != null ? SimNao.fromValue(codigoBarraQrCode) : SimNao.NAO;
    }

    public Integer getDadosSaudeValor() {
        return dadosSaude != null ? dadosSaude.getValor() : 0;
    }

    public void setDadosSaude(Integer dadosSaude) {
        this.dadosSaude = dadosSaude != null ? SimNao.fromValue(dadosSaude) : SimNao.NAO;
    }

    public Integer getAppleWatchValor() {
        return appleWatch != null ? appleWatch.getValor() : 0;
    }

    public void setAppleWatch(Integer appleWatch) {
        this.appleWatch = appleWatch != null ? SimNao.fromValue(appleWatch) : SimNao.NAO;
    }

    public Integer getGerenteDeProjetosValor() {
        return gerenteDeProjetos != null ? gerenteDeProjetos.getValor() : 0;
    }

    public SimNao getGerenteDeProjetos() {
        return gerenteDeProjetos;
    }
    
    public void setGerenteDeProjetos(Integer gerenteDeProjetos) {
        this.gerenteDeProjetos = gerenteDeProjetos != null ? SimNao.fromValue(gerenteDeProjetos) : SimNao.NAO;
    }

    public Double getCustoHardware() {
        return custoHardware;
    }

    public void setCustoHardware(Double custoHardware) {
        this.custoHardware = custoHardware;
    }

    public Double getCustoSoftware() {
        return custoSoftware;
    }

    public void setCustoSoftware(Double custoSoftware) {
        this.custoSoftware = custoSoftware;
    }

    public Double getCustoRiscos() {
        return custoRiscos;
    }

    public void setCustoRiscos(Double custoRiscos) {
        this.custoRiscos = custoRiscos;
    }

    public Double getCustoGarantia() {
        return custoGarantia;
    }

    public void setCustoGarantia(Double custoGarantia) {
        this.custoGarantia = custoGarantia;
    }

    public Double getFundoDeReserva() {
        return fundoDeReserva;
    }

    public void setFundoDeReserva(Double fundoDeReserva) {
        this.fundoDeReserva = fundoDeReserva;
    }
    
    

    public Double getOutrosCustos() {
        return outrosCustos;
    }

    public void setOutrosCustos(Double outrosCustos) {
        this.outrosCustos = outrosCustos;
    }

    /*public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }*/

    public Double getPercentualComImpostos() {
        return percentualComImpostos;
    }

    public void setPercentualComImpostos(Double percentualComImpostos) {
        this.percentualComImpostos = percentualComImpostos;
    }

    /*public Double getTotalComImposto() {
        return totalComImposto;
    }

    public void setTotalComImposto(Double totalComImposto) {
        this.totalComImposto = totalComImposto;
    }*/

    public Double getPercentualLucroDesejado() {
        return percentualLucroDesejado;
    }

    public void setPercentualLucroDesejado(Double percentualLucroDesejado) {
        this.percentualLucroDesejado = percentualLucroDesejado;
    }

    /*public Double getLucroCalculado() {
        return lucroCalculado;
    }

    public void setLucroCalculado(Double lucroCalculado) {
        this.lucroCalculado = lucroCalculado;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getMeses() {
        return meses;
    }

    public void setMeses(Double meses) {
        this.meses = meses;
    }

    public Double getPrecoFinalCliente() {
        return precoFinalCliente;
    }

    public void setPrecoFinalCliente(Double precoFinalCliente) {
        this.precoFinalCliente = precoFinalCliente;
    }

    public Double getMediaPorMes() {
        return mediaPorMes;
    }

    public void setMediaPorMes(Double mediaPorMes) {
        this.mediaPorMes = mediaPorMes;
    }*/

public Map<String, SimNao> getFuncionalidadesDisponiveis() {
    funcionalidadesDisponiveis.put("Compartilhado", compartilhado);
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
        return "ProjetoDeEstimativaModel{" +
                "id=" + id +
                ", usuarioModel=" + usuarioModel +
                ", compartilhado=" + compartilhado +
                ", compartilhadoPor=" + compartilhadoPor +
                ", dataCriacao=" + dataCriacao +
                ", nomeProjetoDeEstimativa='" + nomeProjetoDeEstimativa + '\'' +
                ", status=" + status +
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
                ", custoHardware=" + custoHardware +
                ", custoSoftware=" + custoSoftware +
                ", custoRiscos=" + custoRiscos +
                ", custoGarantia=" + custoGarantia +
                ", fundoDeReserva=" + fundoDeReserva +
                ", outrosCustos=" + outrosCustos +
                ", percentualComImpostos=" + percentualComImpostos +
                ", percentualLucroDesejado=" + percentualLucroDesejado +
                '}'+ "\n";
    }
}
