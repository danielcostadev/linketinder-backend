package com.aczg.controller

import com.aczg.DAO.CandidatoDAO
import com.aczg.DAO.interfaces.ICandidatoDAO
import com.aczg.controller.interfaces.ICandidatoController

import com.aczg.exceptions.DatabaseException
import com.aczg.exceptions.EntidadeJaExisteException

import com.aczg.exceptions.InvalidDataException
import com.aczg.model.Candidato
import com.aczg.service.CandidatoService
import com.aczg.service.interfaces.ICandidatoService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.sql.SQLException
import java.time.LocalDate

class CandidatoServlet extends HttpServlet {

    ICandidatoController candidatoController;

    CandidatoServlet() {}

    @Override
    void init(ServletConfig config) throws ServletException {
        super.init(config)
        ICandidatoDAO candidatoDAO = new CandidatoDAO()
        ICandidatoService candidatoService = new CandidatoService(candidatoDAO)
        this.candidatoController = new CandidatoController(candidatoService)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            Candidato candidato = criarCandidato(jsonMap)

            getCandidatoController().cadastrar(candidato)

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Candidato criado com sucesso.")

        } catch (EntidadeJaExisteException e) {
            response.status = HttpServletResponse.SC_CONFLICT  // 409 Conflict
            ErrorResponse error = new ErrorResponse("O candidato já existe. " + e.message, "CANDIDATO_DUPLICADO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (InvalidDataException e) {
            response.status = HttpServletResponse.SC_BAD_REQUEST  // 400 Bad Request
            ErrorResponse error = new ErrorResponse("Dados inválidos. " + e.message, "DADOS_INVALIDOS")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (DatabaseException e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR  // 500 Internal Server Error
            ErrorResponse error = new ErrorResponse("Erro ao processar a requisição no banco de dados. " + e.message, "ERRO_BANCO")
            response.writer.write(new JsonBuilder(error).toString())

        } catch (Exception e) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR  // 500 Internal Server Error
            ErrorResponse error = new ErrorResponse("Erro desconhecido. Por favor, tente novamente mais tarde.", "ERRO_DESCONHECIDO")
            response.writer.write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String id = request.getParameter("id")

            if (id == null || id.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_OK)

                List<Candidato> candidatos = getCandidatoController().listar()

                response.getWriter().write(new JsonBuilder(candidatos).toString())
                return
            }

            Long candidatoId = Long.parseLong(id)

            Candidato candidato = getCandidatoController().buscarPorId(candidatoId)

            if (candidato == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Candidato não encontrado.", "CANDIDATO_NAO_ENCONTRADO")
                response.getWriter().write(new JsonBuilder(error).toString())
            } else {
                response.setStatus(HttpServletResponse.SC_OK)
                response.getWriter().write(new JsonBuilder(candidato).toString())
            }
        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: " + e.getMessage(), "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (SQLException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: " + e.getMessage(), "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar candidatos: " + e.getMessage(), "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String json = request.reader.text
            Map jsonMap = new JsonSlurper().parseText(json)

            String id = jsonMap.get("id")
            if (id == null || id.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                ErrorResponse error = new ErrorResponse("ID do candidato não inserido.", "ID_NAO_FORNECIDO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            Long candidatoId = Long.parseLong(id)
            Candidato candidatoExistente = getCandidatoController().buscarPorId(candidatoId)

            if (candidatoExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Candidato com ID ${candidatoId} não encontrado.", "CANDIDATO_NAO_ENCONTRADO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            candidatoExistente = editarCandidato(jsonMap, candidatoExistente)
            getCandidatoController().editar(candidatoExistente)

            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("Candidato atualizado com sucesso.")

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: " + e.getMessage(), "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (DatabaseException e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao atualizar o candidato: " + e.getMessage(), "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao atualizar candidato: " + e.getMessage(), "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            response.setContentType("application/json; charset=UTF-8")
            response.setCharacterEncoding("UTF-8")

            String id = request.getParameter("id")

            Long candidatoId = Long.parseLong(id)
            Candidato candidatoExistente = getCandidatoController().buscarPorId(candidatoId)

            if (candidatoExistente == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND)
                ErrorResponse error = new ErrorResponse("Candidato com ID ${candidatoId} não encontrado.", "CANDIDATO_NAO_ENCONTRADO")
                response.getWriter().write(new JsonBuilder(error).toString())
                return
            }

            getCandidatoController().remover(candidatoId)
            response.setStatus(HttpServletResponse.SC_OK)
            response.getWriter().write("Candidato removido com sucesso!")

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            ErrorResponse error = new ErrorResponse("ID inválido: " + e.getMessage(), "ID_INVALIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao acessar o banco de dados: " + e.getMessage(), "ERRO_BANCO")
            response.getWriter().write(new JsonBuilder(error).toString())
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            ErrorResponse error = new ErrorResponse("Erro ao buscar candidatos: " + e.getMessage(), "ERRO_DESCONHECIDO")
            response.getWriter().write(new JsonBuilder(error).toString())
        }

    }


    private Candidato criarCandidato(Map jsonMap) {

        String nome = jsonMap.get("nome")
        String sobrenome = jsonMap.get("sobrenome")
        String email = jsonMap.get("email")
        String telefone = jsonMap.get("telefone")
        String linkedin = jsonMap.get("linkedin")
        String cpf = jsonMap.get("cpf")
        LocalDate dataNascimento = LocalDate.parse(jsonMap.get("dataNascimento"))
        String estado = jsonMap.get("estado")
        String cep = jsonMap.get("cep")
        String descricao = jsonMap.get("descricao")
        String formacao = jsonMap.get("formacao")
        String senha = jsonMap.get("senha")

        return new Candidato(nome, sobrenome, email, telefone, linkedin, cpf, dataNascimento, estado, cep, descricao, formacao, senha)
    }

    private Candidato editarCandidato(Map jsonMap, Candidato candidatoExistente) {
        candidatoExistente.id = (jsonMap.id ? jsonMap.id.toLong() : candidatoExistente.id)
        candidatoExistente.nome = jsonMap.nome ?: candidatoExistente.nome
        candidatoExistente.sobrenome = jsonMap.sobrenome ?: candidatoExistente.sobrenome
        candidatoExistente.email = jsonMap.email ?: candidatoExistente.email
        candidatoExistente.telefone = jsonMap.telefone ?: candidatoExistente.telefone
        candidatoExistente.linkedin = jsonMap.linkedin ?: candidatoExistente.linkedin
        candidatoExistente.estado = jsonMap.estado ?: candidatoExistente.estado
        candidatoExistente.cep = jsonMap.cep ?: candidatoExistente.cep
        candidatoExistente.descricao = jsonMap.descricao ?: candidatoExistente.descricao
        candidatoExistente.formacao = jsonMap.formacao ?: candidatoExistente.formacao
        candidatoExistente.senha = jsonMap.senha ?: candidatoExistente.senha

        return new Candidato(candidatoExistente.id, candidatoExistente.nome, candidatoExistente.sobrenome, candidatoExistente.email, candidatoExistente.telefone,
                candidatoExistente.linkedin, candidatoExistente.cpf, candidatoExistente.dataNascimento, candidatoExistente.estado,
                candidatoExistente.cep, candidatoExistente.descricao, candidatoExistente.formacao, candidatoExistente.senha)

    }

}
