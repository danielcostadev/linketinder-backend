import { Pessoa } from './pessoa';
export class Empresa extends Pessoa {
    constructor(id, nome, email, cnpj, pais, estado, cep, descricao, competencias, tags, vagas) {
        super(id, nome, email, estado, cep, descricao, competencias, tags, vagas);
        this.id = id;
        this._cnpj = cnpj;
        this._pais = pais;
    }
    get cnpj() {
        return this._cnpj;
    }
    set cnpj(value) {
        this._cnpj = value;
    }
    get pais() {
        return this._pais;
    }
    set pais(value) {
        this._pais = value;
    }
}
