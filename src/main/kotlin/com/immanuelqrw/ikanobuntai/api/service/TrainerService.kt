package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class TrainerService {

    @Autowired
    private lateinit var trainerService: UnitTrainerService

    fun findByName(name: String): Trainer? {
        return trainerService.findAll(UNIQUE_PAGE_REQUEST, "name:$name").content.firstOrNull()
    }

}
