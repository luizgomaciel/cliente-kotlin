package br.kotlin.app.host.cliente

import br.kotlin.app.application.exception.BusinessException
import br.kotlin.app.application.service.ClienteService
import br.kotlin.app.host.MessageError
import br.kotlin.app.host.cliente.dto.ClienteRequest
import br.kotlin.app.host.cliente.dto.ClienteResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Cliente Kotlin", description = "Operações relacionadas ao domínio de Cliente")
@RestController
@Validated
@RequestMapping(value = ["/v1"])
class ClienteHost {

    @Autowired
    private val clienteService: ClienteService? = null;

    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Criação de cliente")
    @PostMapping(value = ["/cadastros"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = ClienteResponse::class))
            )],
            description = "CREATED"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun incluirCliente(
        @Parameter(name = "cliente") @RequestBody clienteRequest: @Valid ClienteRequest?
    ): ResponseEntity<ClienteResponse?> {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService!!.incluirCliente(clienteRequest))
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Atualização de cliente")
    @PutMapping(value = ["/atualizacoes/{id}"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = ClienteResponse::class))
            )],
            description = "CREATED"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun alterarCliente(
        @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) id: Long?,
        @Parameter(name = "cliente") @RequestBody clienteRequest: @Valid ClienteRequest?
    ): ResponseEntity<ClienteResponse?> {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService!!.alterarCliente(id, clienteRequest))
    }

    @Operation(description = "Consulta de clientes")
    @GetMapping(value = ["/consultas"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = Page::class))
            )],
            description = "OK"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun consultarClientes(
        @Parameter(name = "page", description = "Número da página") @RequestParam("page") pageIndex: Int,
        @Parameter(name = "size", description = "Quantidade de itens por página") @RequestParam("size") pageSize: Int
    ): ResponseEntity<Page<ClienteResponse?>?> {
        return ResponseEntity.status(HttpStatus.OK).body(
            clienteService!!.consultarClientes(PageRequest.of(pageIndex, pageSize))
        )
    }

    @Operation(description = "Consulta de clientes")
    @GetMapping(value = ["/consultas/{idade}/caracteristicas"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = Page::class))
            )],
            description = "OK"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun consultarClienteByIdade(
        @Parameter(name = "idade", description = "Idade do Cliente") @PathVariable(
            name = "idade",
            required = true
        ) idade: Int?,
        @Parameter(name = "page", description = "Número da página") @RequestParam("page") pageIndex: Int,
        @Parameter(name = "size", description = "Quantidade de itens por página") @RequestParam("size") pageSize: Int
    ): ResponseEntity<Page<ClienteResponse?>?> {
        return ResponseEntity.status(HttpStatus.OK).body(
            clienteService!!.consultarClienteByIdade(idade, PageRequest.of(pageIndex, pageSize))
        )
    }

    @Operation(description = "Consulta de cliente por ID")
    @GetMapping(value = ["/consultas/{id}"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = ClienteResponse::class))
            )],
            description = "OK"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun consultarCliente(
        @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) id: Long?
    ): ResponseEntity<ClienteResponse?> {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService!!.consultarCliente(id))
    }

    @Operation(description = "Excluir de cliente por ID")
    @DeleteMapping(value = ["/exclusoes/{id}"])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = ClienteResponse::class))
            )],
            description = "OK"
        ), ApiResponse(responseCode = "204", description = "NO_CONTENT"), ApiResponse(
            responseCode = "400",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "BAD_REQUEST"
        ), ApiResponse(
            responseCode = "422",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = BusinessException::class))
            )],
            description = "UNPROCESSABLE_ENTITY"
        ), ApiResponse(
            responseCode = "500",
            content = [Content(
                mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = MessageError::class))
            )],
            description = "INTERNAL_SERVER_ERROR"
        )]
    )
    fun excluirCliente(
        @Parameter(name = "id", description = "ID do Cliente") @PathVariable(name = "id", required = true) id: Long?
    ): ResponseEntity<Void> {
        clienteService!!.excluirCliente(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}