package br.kotlin.app.application.service.impl

import br.kotlin.app.application.service.SequenceGeneratorService
import br.kotlin.app.domain.DatabaseSequence
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Service
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import java.util.Objects

@Service
class SequenceGeneratorServiceImpl @Autowired constructor(private val mongoOperations: MongoOperations) : SequenceGeneratorService {
    override fun generateSequence(seqName: String?): Long {
        val counter = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").`is`(seqName)),
            Update().inc("seq", 1),
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            DatabaseSequence::class.java
        )
        return if (!Objects.isNull(counter)) counter!!.seq else 1
    }
}