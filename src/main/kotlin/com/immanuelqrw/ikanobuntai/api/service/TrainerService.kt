package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class TrainerService {

    @Autowired
    private lateinit var trainerService: UnitTrainerService

    fun findByName(name: String): Trainer? {
        val page = PageRequest.of(1, 1)

        return trainerService.findAll(page, "name:$name").content.firstOrNull()
    }

}
