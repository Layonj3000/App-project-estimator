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
public class BackEndBuilder extends PerfilBuilder{
    
    public BackEndBuilder(UsuarioModel model){
        super(model);
    }

    @Override
    public void setNomePerfil() {
        perfilModel.setNomePerfil("Web e Back-end");
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
        perfilModel.setCadastroPorConviteUsuario(2);
        perfilModel.setCadastroPorContasMultitenant(3);
        perfilModel.setCadastroPorSubdominios(4);
        perfilModel.setCadastroPorDominiosPersonalizados(4);
        perfilModel.setPainel(5);
        perfilModel.setFeedDeAtividades(4);
        perfilModel.setUploadDeArquivos(4);
        perfilModel.setPerfisDeUsuario(2);
        perfilModel.setEmailsTransacionais(2);
        perfilModel.setTags(2);
        perfilModel.setAvaliacoesOuComentarios(5);
        perfilModel.setProcessamentoAudioVideo(5);
        perfilModel.setPesquisaTextoLivre(5);
        perfilModel.setCalendario(7);
        perfilModel.setExibicaoDadosMapaGeolocalizacao(3);
        perfilModel.setExibicaoMarcadoresRegioesMapaPersonalizados(3);
        perfilModel.setReservas(8);
        perfilModel.setMensagens(6);
        perfilModel.setForunsOuComentarios(5);
        perfilModel.setCompartilhamentoSocial(2);
        perfilModel.setIntegracaoFacebookOpenGraph(5);
        perfilModel.setPlanosDeAssinatura(8);
        perfilModel.setProcessamentoDePagamento(5);
        perfilModel.setCarrinhoDeCompras(8);
        perfilModel.setMarketplaceDeUsuarios(12);
        perfilModel.setGerenciamentoDeProdutos(4);
        perfilModel.setIntegracaoCms(7);
        perfilModel.setPaginasAdministracaoUsuarios(3);
        perfilModel.setModeracaoAprovacaoConteudo(4);
        perfilModel.setIntercom(3);
        perfilModel.setAnalisesUso(3);
        perfilModel.setRelatoriosErro(0);
        perfilModel.setMonitoramentoPerformance(1);
        perfilModel.setSuporteMultilingue(4);
        perfilModel.setConectarServicosDeTerceiros(6);
        perfilModel.setApiParaTerceiros(8);
        perfilModel.setEnvioSms(4);
        perfilModel.setMascaramentoNumeroTelefone(4);
        perfilModel.setSegurancaBaseadaCertificadoSsl(3);
        perfilModel.setProtecaoContraDos(5);
        perfilModel.setAutenticacaoDuasEtapas(5);
        perfilModel.setDesenvolvimentoEspecificoApp(0);
        perfilModel.setGerenteDeProjetos(10);
    }

    @Override
    public void setTaxas() {
        perfilModel.setTaxaDiariaGerenciaProjeto(300.00);
        perfilModel.setTaxaDiariaDesenvolvimento(450.00);
    }

    @Override
    public void setDataCriacao() {
        perfilModel.setDataCriacao(new Date(System.currentTimeMillis())); 
    }


    
    
}
