package br.kotlin.app.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences_v2")
data class DatabaseSequence(
    @Id val id: String?,
    val seq: Long
)