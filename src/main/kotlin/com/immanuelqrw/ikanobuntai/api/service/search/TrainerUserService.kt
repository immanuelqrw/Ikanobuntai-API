package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerUserService as UnitTrainerUserService

@Service
class TrainerUserService {

    @Autowired
    private lateinit var trainerUserService: UnitTrainerUserService

    fun findByName(name: String): TrainerUser? {
        return trainerUserService.findAll("name:$name").firstOrNull()
    }

    fun findByEmail(email: String): TrainerUser? {
        return trainerUserService.findAll("email:$email").firstOrNull()
    }

}
