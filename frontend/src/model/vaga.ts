import { Candidato } from './candidato';
import { Empresa } from './empresa';


export class Vaga {
    public codigo: number;
    protected _idEmpresa: number;
    protected _nome: string;
    protected _descricao: string;
    protected _candidato?: Candidato;
    protected _competencias: string[]

    constructor(
        codigo: number,
        idEmpresa: number,
        nome: string,
        descricao: string,
        competencias: string[]
    ){
        this.codigo = codigo;
        this._idEmpresa = idEmpresa;
        this._nome = nome;
        this._descricao = descricao;
        this._competencias = competencias;
    }

    public get idEmpresa() : number {
        return this._idEmpresa;
    }
    public set idEmpresa(value : number) {
        this._idEmpresa = value;
    }
    
    public get nome() : string {
        return this._nome;
    }
    public set nome(value : string) {
        this._nome = value;
    }

    public get descricao() : string {
        return this.descricao;
    }
    public set descricao(value : string) {
        this._descricao = value;
    }

    public get competencias(): string[] {
        return this._competencias;
    }
    public set competencias(value: string[]) {
        this._competencias = value;
    }
    
    

}