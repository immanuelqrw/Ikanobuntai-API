package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.stereotype.Repository

/**
 * Repository interface for [TrainerUser]
 */
@Repository
interface TrainerUserRepository : BaseUniqueRepository<TrainerUser> {

    fun findByNameAndRemovedOnIsNull(name: String): TrainerUser?

    fun findByEmailAndRemovedOnIsNull(email: String): TrainerUser?

}
