package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerUserService as UnitTrainerUserService

@Service
class TrainerUserService {

    @Autowired
    private lateinit var trainerUserService: UnitTrainerUserService

    fun findByName(name: String): TrainerUser? {
        val page = PageRequest.of(1, 1)

        return trainerUserService.findAll(page, "name:$name").content.firstOrNull()
    }

    fun findByEmail(email: String): TrainerUser? {
        val page = PageRequest.of(1, 1)

        return trainerUserService.findAll(page, "email:$email").content.firstOrNull()
    }

}
