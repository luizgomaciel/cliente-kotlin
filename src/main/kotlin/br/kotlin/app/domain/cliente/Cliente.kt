package br.kotlin.app.domain.cliente

import br.kotlin.app.domain.enums.DocumentoEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cliente")
@CompoundIndexes(CompoundIndex(name = "idx_idade", def = "{ 'idade' : 1 }", unique = false, background = true))
data class Cliente(
    @Id
    var id: Long? = null,
    val nome: String? = null,
    val idade: Int? = null,
    val estadoCivil: String? = null,
    val genero: String? = null,
    val naturalidade: String? = null,
    val nacionalidade: String? = null,
    val profissao: String? = null,
    val endereco: Endereco? = null,
    val documentos: List<DocumentoResponse>? = null,
) {

    data class Endereco(
        val tipoLogradouro: String? = null,
        val nomeLogradouro: String? = null,
        val numeroLogradouro: Int? = null,
        val cidade: String? = null,
        val estado: String? = null,
        val cep: Int? = null,
    )

    data class DocumentoResponse(
        val tipo: DocumentoEnum? = null,
        val codigoDocumento: String? = null
    )

    companion object {
        @Transient
        val SEQUENCE_NAME = "cliente_sequence"
    }
}