import { Pessoa } from './pessoa';
export class Candidato extends Pessoa {
    constructor(id, nome, email, cpf, idade, estado, cep, descricao, formacao, competencias, vagas) {
        super(id, nome, email, estado, cep, descricao, competencias, vagas);
        this.id = id;
        this._cpf = cpf;
        this._idade = idade;
        this._formacao = formacao;
    }
    get cpf() {
        return this._cpf;
    }
    set cpf(value) {
        this._cpf = value;
    }
    get idade() {
        return this._idade;
    }
    set idade(value) {
        this._idade = value;
    }
    get formacao() {
        return this._formacao;
    }
    set formacao(value) {
        this._formacao = value;
    }
}
