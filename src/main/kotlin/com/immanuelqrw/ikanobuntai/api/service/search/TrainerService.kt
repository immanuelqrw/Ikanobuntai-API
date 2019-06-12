package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.stereotype.Service

@Service
class TrainerService : BaseUniqueService<Trainer>() {

    fun findByName(name: String): Trainer? {
        return findAll("name:$name").firstOrNull()
    }

}
