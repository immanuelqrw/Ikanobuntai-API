package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.League
import org.springframework.stereotype.Repository

/**
 * Repository interface for [League]
 */
@Repository
interface LeagueRepository : BaseUniqueRepository<League> {

    fun findByNameAndRemovedOnIsNull(name: String): League?

}
