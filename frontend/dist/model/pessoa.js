export class Pessoa {
    constructor(id, nome, email, estado, cep, descricao, competencias, vagas) {
        this.id = id;
        this._nome = nome;
        this._email = email;
        this._estado = estado;
        this._cep = cep;
        this._descricao = descricao;
        this._competencias = competencias;
        this._vagas = vagas;
    }
    get nome() {
        return this._nome;
    }
    set nome(value) {
        this._nome = value;
    }
    get email() {
        return this._email;
    }
    set email(value) {
        this._email = value;
    }
    get estado() {
        return this._estado;
    }
    set estado(value) {
        this._estado = value;
    }
    get cep() {
        return this._cep;
    }
    set cep(value) {
        this._cep = value;
    }
    get descricao() {
        return this._descricao;
    }
    set descricao(value) {
        this._descricao = value;
    }
    get competencias() {
        return this._competencias;
    }
    set competencias(value) {
        this._competencias = value;
    }
    get vagas() {
        return this._vagas;
    }
    set vagas(value) {
        this._vagas = value;
    }
}
