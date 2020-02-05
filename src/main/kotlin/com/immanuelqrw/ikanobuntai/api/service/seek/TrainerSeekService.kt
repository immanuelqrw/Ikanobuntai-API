package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.repository.TrainerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrainerSeekService : BaseUniqueService<Trainer>(Trainer::class.java) {

    @Autowired
    private lateinit var trainerRepository: TrainerRepository

    fun findByName(name: String): Trainer? {
        return findAll("name:$name").firstOrNull()
    }

}
