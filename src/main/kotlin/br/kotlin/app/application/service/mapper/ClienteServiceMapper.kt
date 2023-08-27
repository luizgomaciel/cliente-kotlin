package br.kotlin.app.application.service.mapper

import br.kotlin.app.domain.cliente.Cliente
import br.kotlin.app.host.cliente.dto.ClienteRequest
import br.kotlin.app.host.cliente.dto.ClienteResponse
import org.springframework.data.domain.Page

class ClienteServiceMapper {
    companion object {
        fun toCliente(clienteRequest: ClienteRequest?): Cliente {
            return Cliente(
                nome = clienteRequest!!.nome,
                idade = clienteRequest.idade,
                estadoCivil = clienteRequest.estadoCivil,
                genero = clienteRequest.genero,
                naturalidade = clienteRequest.naturalidade,
                nacionalidade = clienteRequest.nacionalidade,
                profissao = clienteRequest.profissao,
                endereco = Cliente.Endereco(
                    nomeLogradouro = clienteRequest.endereco!!.nomeLogradouro,
                    numeroLogradouro = clienteRequest.endereco.numeroLogradouro,
                    cidade = clienteRequest.endereco.cidade,
                    estado = clienteRequest.endereco.estado,
                    cep = clienteRequest.endereco.cep
                ),
                documentos = clienteRequest.documentos?.let { toClienteDocumentos(it) }
            )
        }

        fun toClienteDocumentos(documentos: List<ClienteRequest.DocumentoRequest>): List<Cliente.DocumentoResponse>{
            return documentos.stream().map { doc -> toClienteDocumento(doc) }.toList();
        }

        fun toClienteDocumento(documento: ClienteRequest.DocumentoRequest): Cliente.DocumentoResponse{
            return Cliente.DocumentoResponse(
                tipo = documento.tipo,
                codigoDocumento = documento.codigoDocumento
            )
        }

        fun toClienteResponse(entity: Cliente): ClienteResponse? {
            return ClienteResponse(
                id = entity.id,
                nome = entity.nome,
                idade = entity.idade,
                estadoCivil = entity.estadoCivil,
                genero = entity.genero,
                naturalidade = entity.naturalidade,
                nacionalidade = entity.nacionalidade,
                profissao = entity.profissao,
                endereco = ClienteResponse.EnderecoResponse(
                    nomeLogradouro = entity.endereco!!.nomeLogradouro,
                    numeroLogradouro = entity.endereco.numeroLogradouro,
                    cidade = entity.endereco.cidade,
                    estado = entity.endereco.estado,
                    cep = entity.endereco.cep
                ),
                documentos = entity!!.documentos?.let { toResponseDocumentos(it) }
            )
        }

        fun toResponseDocumento(documento: Cliente.DocumentoResponse) : ClienteResponse.DocumentoResponse{
            return ClienteResponse.DocumentoResponse(
                tipo = documento.tipo,
                codigoDocumento = documento.codigoDocumento
            )
        }

        fun toResponseDocumentos(documentos: List<Cliente.DocumentoResponse>) : List<ClienteResponse.DocumentoResponse>{
            return documentos.stream().map { doc -> toResponseDocumento(doc) }.toList();
        }

        fun toPageClienteResponse(page: Page<Cliente?>?): Page<ClienteResponse?>? {
            return page!!.map { entity: Cliente? -> toClienteResponse(entity!!) }
        }

    }
}