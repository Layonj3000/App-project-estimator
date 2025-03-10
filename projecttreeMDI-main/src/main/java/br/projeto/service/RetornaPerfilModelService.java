/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class RetornaPerfilModelService {
    private Map<String, Integer> mapPerfil;
    private PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel;
    //private PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel;
    
    public RetornaPerfilModelService(Map<String, Integer> mapPerfil) {
        this.mapPerfil = mapPerfil;
        perfilProjetoDeEstimativaModel = new PerfilProjetoDeEstimativaModel();
        //perfilFuncionalidadesPersonalizadasModel = new PerfilFuncionalidadesPersonalizadasModel();
    }
    
    public PerfilProjetoDeEstimativaModel getPerfil(){
        //perfilProjetoDeEstimativaModel.setId(rs.getInt("id"));
        //perfilProjetoDeEstimativaModel.setNomePerfil(rs.getString("nome_perfil"));
        //perfilProjetoDeEstimativaModel.setUsuarioModel(usuarioModel);
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
        /*perfilProjetoDeEstimativaModel.setTaxaDiariaDesign(rs.getDouble("taxa_diaria_design"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaGerenciaProjeto(rs.getDouble("taxa_diaria_gerencia_projeto"));
        perfilProjetoDeEstimativaModel.setTaxaDiariaDesenvolvimento(rs.getDouble("taxa_diaria_desenvolvimento"));
        long timestamp = rs.getLong("data_criacao");
        Date data = new Date(timestamp);
        perfilProjetoDeEstimativaModel.setDataCriacao(data);*/

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
    
}
