/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.perfil_builder;

import br.projeto.model.UsuarioModel;
import java.sql.Date;

/**
 *
 * @author David Potentini
 */
public class AndroidBuilder extends PerfilBuilder{

    public AndroidBuilder(UsuarioModel usuarioModel) {
        super(usuarioModel);
    }

    @Override
    public void setNomePerfil() {
        perfilModel.setNomePerfil("Android");
    }

    @Override
    public void setFuncionalidades() {
        perfilModel.setPequeno(10);
        perfilModel.setMedio(30);
        perfilModel.setGrande(50);
        perfilModel.setMvp(30);
        perfilModel.setBasico(50);
        perfilModel.setProfissional(70);
        perfilModel.setCadastroPorEmailSenha(1);
        perfilModel.setCadastroPorFacebook(2);
        perfilModel.setCadastroPorTwitter(2);
        perfilModel.setCadastroPorGoogle(2);
        perfilModel.setCadastroPorLinkedin(2);
        perfilModel.setCadastroPorGithub(2);
//        perfilModel.setCadastroPorConviteUsuario(null);
//        perfilModel.setCadastroPorContasMultitenant(null);
//        perfilModel.setCadastroPorSubdominios(null);
//        perfilModel.setCadastroPorDominiosPersonalizados(null);
        perfilModel.setPainel(5);
        perfilModel.setFeedDeAtividades(4);
        //perfilModel.setUploadDeArquivos(null);
        perfilModel.setUploadDeMidia(4);
        perfilModel.setPerfisDeUsuario(2);
        //perfilModel.setEmailsTransacionais(null);
        perfilModel.setTags(2);
        perfilModel.setAvaliacoesOuComentarios(5);
//        perfilModel.setProcessamentoAudioVideo(null);
//        perfilModel.setPesquisaTextoLivre(null);
        perfilModel.setPesquisa(3);
        perfilModel.setCalendario(6);
        perfilModel.setExibicaoDadosMapaGeolocalizacao(3);
        perfilModel.setExibicaoMarcadoresRegioesMapaPersonalizados(3);
        perfilModel.setReservas(5);
        perfilModel.setMensagens(5);
        perfilModel.setForunsOuComentarios(5);
        perfilModel.setCompartilhamentoSocial(1);
        perfilModel.setIntegracaoFacebookOpenGraph(3);
        perfilModel.setNotificacaoPush(3);
        //perfilModel.setPlanosDeAssinatura(null);
        perfilModel.setProcessamentoDePagamento(5);
        perfilModel.setCarrinhoDeCompras(5);
//        perfilModel.setMarketplaceDeUsuarios(null);
//        perfilModel.setGerenciamentoDeProdutos(null);
        perfilModel.setComprasDentroDoAplicativo(5);
        perfilModel.setColetaInformacaoPagamento(3);
//        perfilModel.setIntegracaoCms(null);
//        perfilModel.setPaginasAdministracaoUsuarios(null);
//        perfilModel.setModeracaoAprovacaoConteudo(null);
        perfilModel.setIntercom(3);
        perfilModel.setAnalisesUso(3);
        perfilModel.setRelatoriosErro(1);
        //perfilModel.setMonitoramentoPerformance(null);
        perfilModel.setSuporteMultilingue(4);
        perfilModel.setConectarServicosDeTerceiros(3);
        //perfilModel.setApiParaTerceiros(null);
        perfilModel.setEnvioSms(4);
        perfilModel.setMascaramentoNumeroTelefone(4);
        //perfilModel.setSegurancaBaseadaCertificadoSsl(null);
        //perfilModel.setProtecaoContraDos(null);
        perfilModel.setAutenticacaoDuasEtapas(5);
        perfilModel.setDesenvolvimentoEspecificoApp(0);
        perfilModel.setDesignIconeApp(7);
        perfilModel.setSincronizacaoNuvem(5);
        perfilModel.setDadosSensoresDispositivo(5);
        perfilModel.setCodigoBarraQrCode(2);
        perfilModel.setDadosSaude(4);
        //perfilModel.setAppleWatch(null);
        perfilModel.setGerenteDeProjetos(10);
    }

    @Override
    public void setTaxas() {
        perfilModel.setTaxaDiariaDesign(450.00);
        perfilModel.setTaxaDiariaGerenciaProjeto(300.00);
        perfilModel.setTaxaDiariaDesenvolvimento(450.00);
    }

    @Override
    public void setDataCriacao() {
        perfilModel.setDataCriacao(new Date(System.currentTimeMillis())); 
    }
    
}
