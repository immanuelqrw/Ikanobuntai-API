package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class TrainerService {

    @Autowired
    private lateinit var trainerService: UnitTrainerService

    fun findByName(name: String): Trainer? {
        return trainerService.findAll("name:$name").firstOrNull()
    }

}
