package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import com.immanuelqrw.ikanobuntai.api.repository.TrainerUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrainerUserSeekService : BaseUniqueService<TrainerUser>(TrainerUser::class.java) {

    @Autowired
    private lateinit var trainerUserRepository: TrainerUserRepository

    fun findByName(name: String): TrainerUser? {
        return findAll("name:$name").firstOrNull()
    }

    fun findByEmail(email: String): TrainerUser? {
        return findAll("email:$email").firstOrNull()
    }
}
