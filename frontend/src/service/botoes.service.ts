export class BotoesService {
    public criarBotaoContainerReadUpdateDelete(): HTMLDivElement {
        // Cria o div com a classe 'botao-container'
        const divBotaoContainerRUD: HTMLDivElement = document.createElement("div");
        divBotaoContainerRUD.className = "botao-container-read-update-delete";

        // Função auxiliar para criar botões com SVG
        function criarBotao(classeBotao: string): HTMLButtonElement {
            const botao: HTMLButtonElement = document.createElement("button");
            botao.className = classeBotao;

            // Adiciona o SVG ao botão (ajustado para usar texto vazio no momento)
            botao.appendChild(document.createTextNode(" "));

            return botao;
        }

        const botaoVer: HTMLButtonElement = criarBotao("botao-ver");
        const botaoEditar: HTMLButtonElement = criarBotao("botao-editar");
        const botaoExcluir: HTMLButtonElement = criarBotao("botao-excluir");

        // Adiciona os botões ao container
        divBotaoContainerRUD.appendChild(botaoVer);
        divBotaoContainerRUD.appendChild(botaoEditar);
        divBotaoContainerRUD.appendChild(botaoExcluir);

        return divBotaoContainerRUD;
    }

    public criarBotaoContainerLike(): HTMLDivElement {
        const divBotaoContainerLike: HTMLDivElement = document.createElement("div");
        divBotaoContainerLike.className = "botao-container-curtir-reijeitar";

        function criarBotao(classeBotao: string): HTMLButtonElement {
            const botao: HTMLButtonElement = document.createElement("button");
            botao.className = classeBotao;
            botao.appendChild(document.createTextNode(" "));
            return botao;
        }

        const botaoCurtir: HTMLButtonElement = criarBotao("botao-curtir");
        const botaoRejeitar: HTMLButtonElement = criarBotao("botao-rejeitar");

        divBotaoContainerLike.appendChild(botaoCurtir);
        divBotaoContainerLike.appendChild(botaoRejeitar);

        return divBotaoContainerLike;
    }

}