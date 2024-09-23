export class BotoesService {
    criarBotaoContainerReadUpdateDelete() {
        // Cria o div com a classe 'botao-container'
        const divBotaoContainerRUD = document.createElement("div");
        divBotaoContainerRUD.className = "botao-container-read-update-delete";
        // Função auxiliar para criar botões com SVG
        function criarBotao(classeBotao) {
            const botao = document.createElement("button");
            botao.className = classeBotao;
            // Adiciona o SVG ao botão (ajustado para usar texto vazio no momento)
            botao.appendChild(document.createTextNode(" "));
            return botao;
        }
        const botaoVer = criarBotao("botao-ver");
        const botaoEditar = criarBotao("botao-editar");
        const botaoExcluir = criarBotao("botao-excluir");
        // Adiciona os botões ao container
        divBotaoContainerRUD.appendChild(botaoVer);
        divBotaoContainerRUD.appendChild(botaoEditar);
        divBotaoContainerRUD.appendChild(botaoExcluir);
        return divBotaoContainerRUD;
    }
    criarBotaoContainerLike() {
        const divBotaoContainerLike = document.createElement("div");
        divBotaoContainerLike.className = "botao-container-curtir-reijeitar";
        function criarBotao(classeBotao) {
            const botao = document.createElement("button");
            botao.className = classeBotao;
            botao.appendChild(document.createTextNode(" "));
            return botao;
        }
        const botaoCurtir = criarBotao("botao-curtir");
        const botaoRejeitar = criarBotao("botao-rejeitar");
        divBotaoContainerLike.appendChild(botaoCurtir);
        divBotaoContainerLike.appendChild(botaoRejeitar);
        return divBotaoContainerLike;
    }
}
