package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Move
import com.immanuelqrw.ikanobuntai.api.repository.MoveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MoveSeekService : BaseUniqueService<Move>(Move::class.java) {

    @Autowired
    private lateinit var moveRepository: MoveRepository

    fun findByName(name: String): Move? {
        return findAll("name:$name").firstOrNull()
    }

}
