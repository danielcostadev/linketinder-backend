export class DadosDAO {
    removerUnderscores(obj) {
        const novoObj = {};
        for (const chave in obj) {
            const novaChave = chave.startsWith('_') ? chave.substring(1) : chave;
            novoObj[novaChave] = obj[chave];
        }
        return novoObj;
    }
    // Empresa
    salvarEmpresaNoLocalStorage(chave, dados) {
        try {
            const dadosTratados = dados.map(empresa => this.removerUnderscores(empresa));
            localStorage.setItem(chave, JSON.stringify(dadosTratados));
        }
        catch (erro) {
            console.error("Erro ao salvar dados no localStorage:", erro);
            alert("Ocorreu um erro ao salvar os dados. Tente novamente mais tarde.");
        }
    }
    obterEmpresaDoLocalStorage(chave) {
        try {
            const dados = localStorage.getItem(chave);
            return dados ? JSON.parse(dados) : [];
        }
        catch (erro) {
            console.error("Erro ao obter dados do localStorage:", erro);
            alert("Ocorreu um erro ao recuperar os dados. Tente novamente mais tarde.");
            return [];
        }
    }
    // Candidato
    salvarCandidatoNoLocalStorage(chave, dados) {
        try {
            const dadosTratados = dados.map(candidato => this.removerUnderscores(candidato));
            localStorage.setItem(chave, JSON.stringify(dadosTratados));
        }
        catch (erro) {
            console.error("Erro ao salvar dados no localStorage:", erro);
            alert("Ocorreu um erro ao salvar os dados. Tente novamente mais tarde.");
        }
    }
    obterCandidatoDoLocalStorage(chave) {
        try {
            const dados = localStorage.getItem(chave);
            return dados ? JSON.parse(dados) : [];
        }
        catch (erro) {
            console.error("Erro ao obter dados do localStorage:", erro);
            alert("Ocorreu um erro ao recuperar os dados. Tente novamente mais tarde.");
            return [];
        }
    }
    // Vaga
    salvarVagaNoLocalStorage(chave, dados) {
        try {
            const dadosTratados = dados.map(vaga => this.removerUnderscores(vaga));
            localStorage.setItem(chave, JSON.stringify(dadosTratados));
        }
        catch (erro) {
            console.error("Erro ao salvar dados no localStorage:", erro);
            alert("Ocorreu um erro ao salvar os dados. Tente novamente mais tarde.");
        }
    }
    obterVagaDoLocalStorage(chave) {
        try {
            const dados = localStorage.getItem(chave);
            return dados ? JSON.parse(dados) : [];
        }
        catch (erro) {
            console.error("Erro ao obter dados do localStorage:", erro);
            alert("Ocorreu um erro ao recuperar os dados. Tente novamente mais tarde.");
            return [];
        }
    }
}
