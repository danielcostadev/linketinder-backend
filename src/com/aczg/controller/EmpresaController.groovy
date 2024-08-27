package com.aczg.controller

import com.aczg.model.Empresa
import com.aczg.repository.EmpresaRepository

class EmpresaController implements Renderizavel{
    EmpresaRepository empresaRepository = new EmpresaRepository()

    @Override
    void mostrarDados(){
        List<Empresa> empresasPreCadastradas = empresaRepository.obterEmpresasCadastradas()

        for(empresa in empresasPreCadastradas){
            empresa.mostrarInformacoes()
            50.times {print '-'}
            println " "
        }

    }
}
