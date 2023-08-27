package br.kotlin.app.application.service

import br.kotlin.app.host.cliente.dto.ClienteRequest
import br.kotlin.app.host.cliente.dto.ClienteResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ClienteService {
    fun incluirCliente(clienteRequest: ClienteRequest?): ClienteResponse?
    fun alterarCliente(id: Long?, clienteRequest: ClienteRequest?): ClienteResponse?
    fun consultarClientes(of: PageRequest): Page<ClienteResponse?>?
    fun consultarClienteByIdade(idade: Int?, of: PageRequest): Page<ClienteResponse?>?
    fun consultarCliente(id: Long?): ClienteResponse?
    fun excluirCliente(id: Long?)
}