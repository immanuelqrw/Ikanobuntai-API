package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.stereotype.Service

@Service
class TrainerUserService : BaseUniqueService<TrainerUser>() {

    fun findByName(name: String): TrainerUser? {
        return findAll("name:$name").firstOrNull()
    }

    fun findByEmail(email: String): TrainerUser? {
        return findAll("email:$email").firstOrNull()
    }
}
