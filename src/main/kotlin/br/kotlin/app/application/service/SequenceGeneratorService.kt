package br.kotlin.app.application.service

interface SequenceGeneratorService {
    fun generateSequence(seqName: String?): Long
}