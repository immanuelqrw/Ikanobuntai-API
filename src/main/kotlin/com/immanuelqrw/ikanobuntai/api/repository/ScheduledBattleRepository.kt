package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.ScheduledBattle
import org.springframework.stereotype.Repository

/**
 * Repository interface for [ScheduledBattle]
 */
@Repository
interface ScheduledBattleRepository : BaseUniqueRepository<ScheduledBattle>
