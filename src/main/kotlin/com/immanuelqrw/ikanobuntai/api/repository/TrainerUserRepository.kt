package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser

/**
 * Repository interface for [TrainerUser]
 */
interface TrainerUserRepository : BaseUniqueRepository<TrainerUser> {

    fun findByNameAndRemovedOnIsNull(name: String): TrainerUser?

    fun findByEmailAndRemovedOnIsNull(email: String): TrainerUser?

}
