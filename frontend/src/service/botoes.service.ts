export class BotoesService { 
    public criarBotaoContainer(): HTMLDivElement {
        // Cria o div com a classe 'botao-container'
        const divContainer: HTMLDivElement = document.createElement("div");
        divContainer.className = "botao-container";
    
        // Função auxiliar para criar botões com SVG
        function criarBotao(classeBotao: string): HTMLButtonElement {
            const botao: HTMLButtonElement = document.createElement("button");
            botao.className = classeBotao;
    
            // Adiciona o SVG ao botão (ajustado para usar texto vazio no exemplo)
            botao.appendChild(document.createTextNode(" "));
    
            return botao;
        }
    
        // Criar os três botões (No caso do Linketinder apenas 2)
        const botaoCurtir: HTMLButtonElement = criarBotao("botao1");
        // const botaoEditar: HTMLButtonElement = criarBotao("botao2");
        const botaoRejeitar: HTMLButtonElement = criarBotao("botao3");
    
        // Adiciona os botões ao container
        divContainer.appendChild(botaoCurtir);
        // divContainer.appendChild(botaoEditar);
        divContainer.appendChild(botaoRejeitar);
    
        return divContainer;
    }
}