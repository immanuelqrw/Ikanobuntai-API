package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType

/**
 * Repository interface for [Battle]
 */
interface BattleRepository : BaseUniqueRepository<Battle> {

    fun findAllByTypeAndRemovedOnIsNull(type: BattleType): List<Battle>

    fun findAllByWinnerNameAndRemovedOnIsNull(winnerName: String): List<Battle>

}
