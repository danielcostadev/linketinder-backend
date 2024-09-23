import { Pessoa } from './pessoa';
export class Candidato extends Pessoa {
    constructor(id, nome, email, telefone, linkedin, cpf, idade, estado, cep, descricao, formacao, competencias, tags, vagas) {
        super(id, nome, email, estado, cep, descricao, competencias, tags, vagas);
        this.id = id;
        this._cpf = cpf;
        this._idade = idade;
        this._formacao = formacao;
        this._telefone = telefone;
        this._linkedin = linkedin;
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
    get telefone() {
        return this._telefone;
    }
    set telefone(value) {
        this._telefone = value;
    }
    get linkedin() {
        return this._linkedin;
    }
    set linkedin(value) {
        this._linkedin = value;
    }
}
