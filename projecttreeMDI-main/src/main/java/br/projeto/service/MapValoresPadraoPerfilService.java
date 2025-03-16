/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author David Potentini
 */
public class MapValoresPadraoPerfilService {
    private static MapValoresPadraoPerfilService mapValoresPadraoPerfilService = null;
    private Map<String, Integer> mapValoresPerfilPadrao;
    
    private MapValoresPadraoPerfilService(){
        mapValoresPerfilPadrao = new LinkedHashMap<>();
    }
    
    public static MapValoresPadraoPerfilService getInstance(){
        if(mapValoresPadraoPerfilService == null){
            mapValoresPadraoPerfilService = new MapValoresPadraoPerfilService();
        }
        return mapValoresPadraoPerfilService;
    }
    
    public Map<String, Integer> getMap(){
        mapValoresPerfilPadrao.put("Pequeno", 10);
        mapValoresPerfilPadrao.put("Médio", 30);
        mapValoresPerfilPadrao.put("Grande", 50);
        mapValoresPerfilPadrao.put("MVP", 30);
        mapValoresPerfilPadrao.put("Básico", 50);
        mapValoresPerfilPadrao.put("Profissional", 70);
        mapValoresPerfilPadrao.put("Cadastro por Email/Senha", 1);
        mapValoresPerfilPadrao.put("Cadastro por Facebook", 2);
        mapValoresPerfilPadrao.put("Cadastro por Twitter", 2);
        mapValoresPerfilPadrao.put("Cadastro por Google", 2);
        mapValoresPerfilPadrao.put("Cadastro por LinkedIn", 2);
        mapValoresPerfilPadrao.put("Cadastro por GitHub", 2);
        mapValoresPerfilPadrao.put("Cadastro por Convite de Usuário", 2);
        mapValoresPerfilPadrao.put("Cadastro por Contas Multi-tenant", 3);
        mapValoresPerfilPadrao.put("Cadastro por Subdomínios", 4);
        mapValoresPerfilPadrao.put("Cadastro por Domínios Personalizados", 4);
        mapValoresPerfilPadrao.put("Painel", 5);
        mapValoresPerfilPadrao.put("Feed de Atividades", 4);
        mapValoresPerfilPadrao.put("Upload de Arquivos", 4);
        mapValoresPerfilPadrao.put("Upload de Mídia", 4);
        mapValoresPerfilPadrao.put("Perfis de Usuário", 2);
        mapValoresPerfilPadrao.put("Emails Transacionais", 2);
        mapValoresPerfilPadrao.put("Tags", 2);
        mapValoresPerfilPadrao.put("Avaliações ou Comentários", 5);
        mapValoresPerfilPadrao.put("Processamento de Áudio e Vídeo", 5);
        mapValoresPerfilPadrao.put("Pesquisa Texto Livre", 5);
        mapValoresPerfilPadrao.put("Pesquisa", 3);
        mapValoresPerfilPadrao.put("Calendário", 6);
        mapValoresPerfilPadrao.put("Exibição de Dados no Mapa", 3);
        mapValoresPerfilPadrao.put("Exibição de Marcadores no Mapa", 3);
        mapValoresPerfilPadrao.put("Reservas", 5);
        mapValoresPerfilPadrao.put("Mensagens", 5);
        mapValoresPerfilPadrao.put("Fóruns ou Comentários", 5);
        mapValoresPerfilPadrao.put("Compartilhamento Social", 1);
        mapValoresPerfilPadrao.put("Integração com Facebook Open Graph", 3);
        mapValoresPerfilPadrao.put("Notificação Push", 3);
        mapValoresPerfilPadrao.put("Planos de Assinatura", 8);
        mapValoresPerfilPadrao.put("Processamento de Pagamento", 5);
        mapValoresPerfilPadrao.put("Carrinho de Compras", 5);
        mapValoresPerfilPadrao.put("Marketplace de Usuários", 12);
        mapValoresPerfilPadrao.put("Gerenciamento de Produtos", 4);
        mapValoresPerfilPadrao.put("Compras dentro do Aplicativo", 3);
        mapValoresPerfilPadrao.put("Coleta de Informação de Pagamento", 3);
        mapValoresPerfilPadrao.put("Integração com CMS", 7);
        mapValoresPerfilPadrao.put("Páginas de Administração de Usuários", 3);
        mapValoresPerfilPadrao.put("Moderação e Aprovação de Conteúdo", 4);
        mapValoresPerfilPadrao.put("Intercom", 3);
        mapValoresPerfilPadrao.put("Análises de Uso", 3);
        mapValoresPerfilPadrao.put("Relatórios de Erro", 1);
        mapValoresPerfilPadrao.put("Monitoramento de Performance", 1);
        mapValoresPerfilPadrao.put("Suporte Multilíngue", 4);
        mapValoresPerfilPadrao.put("Conectar com Serviços de Terceiros", 3);
        mapValoresPerfilPadrao.put("API para Terceiros", 8);
        mapValoresPerfilPadrao.put("Envio de SMS", 4);
        mapValoresPerfilPadrao.put("Mascaramento de Número de Telefone", 4);
        mapValoresPerfilPadrao.put("Segurança Baseada em Certificado SSL", 3);
        mapValoresPerfilPadrao.put("Proteção Contra DoS", 5);
        mapValoresPerfilPadrao.put("Autenticação em Duas Etapas", 5);
        mapValoresPerfilPadrao.put("Desenvolvimento Específico de App", 0);
        mapValoresPerfilPadrao.put("Design de Ícone para App", 7);
        mapValoresPerfilPadrao.put("Sincronização com a Nuvem", 5);
        mapValoresPerfilPadrao.put("Dados de Sensores do Dispositivo", 5);
        mapValoresPerfilPadrao.put("Código de Barras ou QR Code", 2);
        mapValoresPerfilPadrao.put("Dados de Saúde", 4);
        mapValoresPerfilPadrao.put("Apple Watch", 7);
        mapValoresPerfilPadrao.put("Gerente de Projetos", 10);

    return mapValoresPerfilPadrao;
    }
}
