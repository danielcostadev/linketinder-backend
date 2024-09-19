import { Pessoa } from './pessoa';
import { Vaga } from './vaga';

export class Empresa extends Pessoa {

    protected _cnpj: string;
    protected _pais: string;

    constructor(
        id: number,
        nome: string,
        email: string,
        cnpj: string,
        pais: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[],
        vagas?: Vaga[]
    ) {
        super(id, nome, email, estado, cep, descricao, competencias,vagas);
        this.id = id;
        this._cnpj = cnpj;
        this._pais = pais;
    }   

    public get cnpj() : string {
        return this._cnpj;
    }
    
    public set cnpj(value : string) {
        this._cnpj = value;
    }
    
    public get pais() : string {
        return this._pais;
    }
    
    public set pais(value : string) {
        this._pais = value;
    }

}