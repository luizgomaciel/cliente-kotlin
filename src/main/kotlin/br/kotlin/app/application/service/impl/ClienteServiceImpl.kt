package br.kotlin.app.application.service.impl

import br.kotlin.app.application.exception.BusinessException
import br.kotlin.app.application.exception.NoContentException
import br.kotlin.app.application.service.ClienteService
import br.kotlin.app.application.service.SequenceGeneratorService
import br.kotlin.app.application.service.mapper.ClienteServiceMapper
import br.kotlin.app.domain.cliente.Cliente
import br.kotlin.app.domain.cliente.ClienteRepository
import br.kotlin.app.host.cliente.dto.ClienteRequest
import br.kotlin.app.host.cliente.dto.ClienteResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ClienteServiceImpl : ClienteService {
    @Autowired
    private val sequenceGeneratorService: SequenceGeneratorService? = null
    @Autowired
    private val clienteRepository: ClienteRepository? = null

    override fun incluirCliente(clienteRequest: ClienteRequest?): ClienteResponse? {
        val isExists = clienteRepository!!.existsByNomeAndIdade(clienteRequest?.nome, clienteRequest?.idade)
        if (!isExists) {
            var entity = ClienteServiceMapper.toCliente(clienteRequest)
            entity?.id = sequenceGeneratorService!!.generateSequence(Cliente.SEQUENCE_NAME)
            entity = clienteRepository.save(entity)
            return ClienteServiceMapper.toClienteResponse(entity)
        }
        throw BusinessException("Objeto Cliente já existe cadastrado.")
    }

    override fun alterarCliente(id: Long?, clienteRequest: ClienteRequest?): ClienteResponse? {
        val isExists = id?.let { clienteRepository!!.existsById(it) }
        if (isExists!!) {
            var entity = ClienteServiceMapper.toCliente(clienteRequest)
            entity?.id = id
            entity = clienteRepository!!.save(entity)
            return ClienteServiceMapper.toClienteResponse(entity)
        }
        throw BusinessException("Objeto Cliente não existe cadastrado.")
    }

    override fun consultarCliente(id: Long?): ClienteResponse? {
        val entityOpt = id?.let { clienteRepository!!.findById(it) }
        if (entityOpt!!.isPresent) {
            return ClienteServiceMapper.toClienteResponse(entityOpt.get())
        }
        throw NoContentException()
    }

    override fun excluirCliente(id: Long?) {
        val entityOpt = id?.let { clienteRepository!!.findById(it) }
        if (entityOpt!!.isEmpty) {
            throw BusinessException("Objeto Cliente não existe para exclusão.")
        }
        clienteRepository!!.delete(entityOpt.get())
    }

    override fun consultarClientes(pageable: PageRequest): Page<ClienteResponse?>? {
        val page = clienteRepository!!.findAll(pageable)
        if (!page.isEmpty) {
            return ClienteServiceMapper.toPageClienteResponse(page)
        }
        throw NoContentException()
    }

    override fun consultarClienteByIdade(idade: Int?, pageable: PageRequest): Page<ClienteResponse?>? {
        val page = clienteRepository!!.findByIdade(idade, pageable)
        if (!page!!.isEmpty) {
            return ClienteServiceMapper.toPageClienteResponse(page)
        }
        throw NoContentException()
    }

}