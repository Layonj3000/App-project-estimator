/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.enums.SimNao;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class RetornaProjetoModelService {
    private ProjetoDeEstimativaModel projetoDeEstimativaModel;
    
    
    private Map<String, Integer> mapProjetos;
    
    public RetornaProjetoModelService(Map<String, Integer> mapProjetos){
        this.mapProjetos = mapProjetos;
        this.projetoDeEstimativaModel = new ProjetoDeEstimativaModel();
        
    }
    
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
}
