package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.repository.BattleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattleSeekService : BaseUniqueService<Battle>(Battle::class.java) {

    @Autowired
    private lateinit var battleRepository: BattleRepository

    fun findAllByType(type: BattleType): List<Battle> {
        return battleRepository.findAllByTypeAndRemovedOnIsNull(type)
    }

    fun findAllByWinner(winnerName: String): List<Battle> {
        return battleRepository.findAllByWinnerNameAndRemovedOnIsNull(winnerName)
    }

}
