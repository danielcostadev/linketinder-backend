import { Paises } from "../model/paises";
import { Estados } from "../model/estados";
const dadosPaises = new Paises();
const dadosEstados = new Estados();
export class Formulario {
    gerarPaises() {
        const selectPais = document.getElementById("paises");
        if (selectPais) {
            dadosPaises.getPaises().forEach(pais => {
                const option = document.createElement("option");
                option.value = pais.toLowerCase();
                option.textContent = pais;
                selectPais.appendChild(option);
            });
        }
    }
    gerarEstados() {
        const selectEstado = document.getElementById("estado");
        if (selectEstado) {
            dadosEstados.getEstados().forEach(estado => {
                const option = document.createElement("option");
                option.value = estado.sigla;
                option.textContent = estado.nome;
                selectEstado.appendChild(option);
            });
        }
    }
    validarEntradaDados(campos, formulario) {
        for (const campo of campos) {
            const elemento = formulario.elements.namedItem(campo.nome);
            if (!elemento) {
                console.error(`Campo com nome "${campo.nome}" não encontrado no formulário.`);
                return false;
            }
            const valor = elemento.value.trim();
            if (!valor) {
                alert(campo.mensagem);
                elemento.focus();
                return false;
            }
        }
        return true;
    }
    ;
}
