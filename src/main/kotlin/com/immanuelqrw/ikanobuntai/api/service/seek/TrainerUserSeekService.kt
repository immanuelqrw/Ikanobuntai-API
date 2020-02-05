package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import com.immanuelqrw.ikanobuntai.api.repository.TrainerUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class TrainerUserSeekService : BaseUniqueService<TrainerUser>(TrainerUser::class.java) {

    @Autowired
    private lateinit var trainerUserRepository: TrainerUserRepository

    fun findByName(name: String): TrainerUser {
        return trainerUserRepository.findByNameAndRemovedOnIsNull(name) ?: throw EntityNotFoundException()
    }

    fun findByEmail(email: String): TrainerUser {
        return trainerUserRepository.findByEmailAndRemovedOnIsNull(email) ?: throw EntityNotFoundException()
    }
}
