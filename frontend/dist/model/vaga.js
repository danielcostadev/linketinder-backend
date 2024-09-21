export class Vaga {
    constructor(codigo, nome, descricao, competencias) {
        this.codigo = codigo;
        this._nome = nome;
        this._descricao = descricao;
        this._competencias = competencias;
    }
    get nome() {
        return this._nome;
    }
    set nome(value) {
        this._nome = value;
    }
    get descricao() {
        return this.descricao;
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
}
