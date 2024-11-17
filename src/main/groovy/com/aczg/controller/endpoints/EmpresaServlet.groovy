package com.aczg.controller.endpoints

import com.aczg.DAO.EmpresaDAO
import com.aczg.DAO.interfaces.IEmpresaDAO
import com.aczg.controller.EmpresaController
import com.aczg.controller.interfaces.IEmpresaController
import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException
import com.aczg.exceptions.InvalidDataException
import com.aczg.model.Empresa
import com.aczg.service.EmpresaService
import com.aczg.service.interfaces.IEmpresaService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.sql.SQLException

class EmpresaServlet extends HttpServlet{

    IEmpresaController empresaController

    EmpresaServlet(){}

    @Override
    void init(ServletConfig config) throws ServletException {
        super.init(config)
        IEmpresaDAO empresaDAO = new EmpresaDAO()
        IEmpresaService empresaService = new EmpresaService(empresaDAO)
        this.empresaController = new EmpresaController(empresaService)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            Empresa empresa = criarEmpresa(jsonMap)

            Long empresaId = getEmpresaController().cadastrar(empresa)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.setHeader("Location", "/api/v1/empresas/${empresaId}")

            Map responseMap = [
                    message: "Empresa criada com sucesso.",
                    empresa: [
                            id: empresaId,
                            nome: empresa.getNome(),
                            email: empresa.getEmail(),
                            estado: empresa.getEstado(),
                            cnpj: empresa.getCnpj(),
                            pais: empresa.getPais(),
                            cep: empresa.getCep(),
                            descricao: empresa.getDescricao()
                    ],
                    links: [
                            self: "/api/v1/empresas/${empresaId}",
                            list: "/api/v1/empresas"
                    ]
            ]

            response.writer.write(new JsonBuilder(responseMap).toString())

        } catch (EntidadeJaExisteException e) {
            response.status = HttpServletResponse.SC_CONFLICT
            ErrorResponse error = new ErrorResponse("A empresa já existe. ${e.getMessage()}", "EMPRESA_DUPLICADA")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (InvalidDataException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            ErrorResponse error = new ErrorResponse("Dados inválidos. ${e.getMessage()}", "DADOS_INVALIDOS")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            ErrorResponse error = new ErrorResponse("Erro ao processar a requisição no banco de dados. ${e.getMessage()}", "ERRO_BANCO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            ErrorResponse error = new ErrorResponse("Erro desconhecido. Por favor, tente novamente mais tarde.", "ERRO_DESCONHECIDO")
            response.writer.write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String pathInfo = request.getPathInfo()
            if (pathInfo == null || pathInfo == "/") {
                List<Empresa> empresas = getEmpresaController().listar()
                response.setStatus(HttpServletResponse.SC_OK)
                response.getWriter().write(new JsonBuilder(empresas).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long empresaId = Long.parseLong(idStr)
            Empresa empresa = getEmpresaController().buscarPorId(empresaId)

            if (empresa == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Empresa não encontrada.", "EMPRESA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
            } else {
                response.setStatus(HttpServletResponse.SC_OK)
                response.getWriter().write(new JsonBuilder(empresa).toString())
            }
        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (SQLException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar empresas: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String pathInfo = request.getPathInfo()
            if (pathInfo == null || pathInfo == ("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID da empresa não fornecido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long empresaId = Long.parseLong(idStr)
            Empresa empresaExistente = getEmpresaController().buscarPorId(empresaId)

            if (empresaExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Empresa com ID ${empresaId} não encontrada.", "EMPRESA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            empresaExistente = editarEmpresa(jsonMap, empresaExistente)
            getEmpresaController().editar(empresaExistente)

            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("Empresa com ID ${empresaId} atualizada com sucesso.")

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao atualizar o candidato: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao atualizar candidato: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String pathInfo = request.getPathInfo()
            if (pathInfo == null || pathInfo == ("/")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID da empresa não fornecido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            String idStr = pathInfo.substring(1)
            Long empresaId = Long.parseLong(idStr)

            Empresa empresaExistente = getEmpresaController().buscarPorId(empresaId)
            if (empresaExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Empresa com ID ${empresaId} não encontrada.", "EMPRESA_NAO_ENCONTRADA")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            getEmpresaController().remover(empresaId)
            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("Empresa com ID ${empresaId} removida com sucesso!")

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: ${e.getMessage()}", "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: ${e.getMessage()}", "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar candidatos: ${e.getMessage()}", "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }

    }

    private Empresa criarEmpresa(Map jsonMap) {

        String nome = jsonMap.get("nome")
        String email = jsonMap.get("email")
        String estado = jsonMap.get("estado")
        String cnpj = jsonMap.get("cnpj")
        String pais = jsonMap.get("pais")
        String cep = jsonMap.get("cep")
        String descricao = jsonMap.get("descricao")
        String senha = jsonMap.get("senha")

        return new Empresa(nome, email, estado, cnpj, pais, cep, descricao, senha)
    }

    private Empresa editarEmpresa(Map jsonMap, Empresa empresaExistente) {
        empresaExistente.id = (empresaExistente.id)
        empresaExistente.nome = jsonMap.nome ?: empresaExistente.nome
        empresaExistente.email = jsonMap.email ?: empresaExistente.email
        empresaExistente.estado = jsonMap.estado ?: empresaExistente.estado
        empresaExistente.cnpj = jsonMap.cnpj ?: empresaExistente.cnpj
        empresaExistente.pais = jsonMap.pais ?: empresaExistente.pais
        empresaExistente.cep = jsonMap.cep ?: empresaExistente.cep
        empresaExistente.descricao = jsonMap.descricao ?: empresaExistente.descricao
        empresaExistente.senha = jsonMap.senha ?: empresaExistente.senha

        return new Empresa(empresaExistente.id, empresaExistente.nome, empresaExistente.email,
                empresaExistente.estado, empresaExistente.cnpj, empresaExistente.pais,
                empresaExistente.cep, empresaExistente.descricao, empresaExistente.senha)

    }
}
