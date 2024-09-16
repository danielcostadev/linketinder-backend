import { Empresa } from '../model/empresa';

export class DadosDAO {

    public salvarNoLocalStorage(chave: string, dados: Empresa[]): void {
        try {
            localStorage.setItem(chave, JSON.stringify(dados));
        } catch (erro) {
            console.error("Erro ao salvar dados no localStorage:", erro);
            alert("Ocorreu um erro ao salvar os dados. Tente novamente mais tarde.");
        }
    }
    
    public obterDoLocalStorage(chave:string): Empresa[]  {
        try {
            const dados = localStorage.getItem(chave);
            return dados? JSON.parse(dados) as Empresa[] : [];
        } catch (erro) {
            console.error("Erro ao obter dados do localStorage:", erro);
            alert("Ocorreu um erro ao recuperar os dados. Tente novamente mais tarde.");
            return [];
        }
    }
}