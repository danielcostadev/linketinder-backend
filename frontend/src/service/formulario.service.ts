import { Paises } from "../model/paises";
import { Estados } from "../model/estados";


const dadosPaises = new Paises();
const dadosEstados = new Estados();

export class Formulario {

    public gerarPaises(): void {
        const selectPais = document.getElementById("paises") as HTMLSelectElement;

        if (selectPais) {
            dadosPaises.getPaises().forEach(pais => {
                const option = document.createElement("option");
                option.value = pais.toLowerCase();
                option.textContent = pais;
                selectPais.appendChild(option);
            });
        }
    }

    public gerarEstados() {
        const selectEstado = document.getElementById("estado") as HTMLSelectElement;

        if (selectEstado) {
            dadosEstados.getEstados().forEach(estado => {
                const option = document.createElement("option");
                option.value = estado.sigla;
                option.textContent = estado.nome;
                selectEstado.appendChild(option);
            });
        }
    }

    public validarEntradaDados(campos: { nome: string, mensagem: string, tipo?: string }[], formulario: HTMLFormElement): boolean {

        for (const campo of campos) {

            const elemento = formulario.elements.namedItem(campo.nome) as HTMLInputElement;

            if (!elemento) {
                console.error(`Campo com nome "${campo.nome}" não encontrado no formulário.`);
                return false;
            }

            const valor: string = elemento.value.trim();
            if (!valor) {
                alert(campo.mensagem);
                elemento.focus();
                return false;
            }

            if (campo.tipo) {
                let regex: RegExp | null = null;

                switch (campo.tipo) {
                    case 'nome':
                        regex = /^(?:[A-Za-zÀ-ÿ]+(?:\s+[A-Za-zÀ-ÿ]+)+)$/;
                        break;
                    case 'email':
                        regex = /^[a-zA-Z0-9._%+-]+@\w+\.\w{2,}(?:\.[a-z]{2})?$/;
                        break;
                    case 'telefone':
                        regex = /^\(\d{2}\)\s?\d{4,5}-\d{4}$/;
                        break;
                    case 'linkedin':
                        regex = /^https:\/\/[a-z]{2,3}\.linkedin\.com\/in\/[A-Za-z0-9._-]{1,30}$/;
                        break;
                    case 'cpf':
                        regex = /^(?:\d{11}|\d{3}\.\d{3}\.\d{3}-\d{2})$/;
                        break;
                    case 'cnpj':
                        regex = /^(?:\d{14}|\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2})$/;
                        break;
                    case 'idade':
                        regex = /^(?:1[6-9]|[2-5][0-9]|6[0-5])$/;
                        break;
                    case 'cep':
                        regex = /^(?:\d{8}|\d{5}-\d{3})$/;
                        break;
                    case 'descricao':
                        regex = /^[\w\W\s]{10,500}$/;
                        break;
                    case 'competencias':
                        regex = /^(?:[a-zA-ZÀ-ÿ\s]+,){2,}[a-zA-ZÀ-ÿ\s]+$/;
                        break;
                    case 'tags':
                        regex = /^(?:[a-zA-ZÀ-ÿ\s]+,){2,}[a-zA-ZÀ-ÿ\s]+$/

                }

                if (regex && !regex.test(valor)) {
                    alert(`O campo ${campo.nome} está inválido!`)
                    elemento.focus();
                    return false
                }
            }

        }
        return true;
    };
}