package br.kotlin.app.domain.cliente

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : MongoRepository<Cliente?, Long?> {
    @Query(value = "{ 'nome': ?0, 'idade': ?1 }", exists = true)
    fun existsByNomeAndIdade(nome: String?, idade: Int?): Boolean

    @Query(value = "{ 'idade': ?0 }")
    fun findByIdade(idade: Int?, pageable: Pageable?): Page<Cliente?>?
}