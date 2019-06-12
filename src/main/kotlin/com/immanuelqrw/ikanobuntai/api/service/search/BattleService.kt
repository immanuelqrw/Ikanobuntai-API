package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BattleService : BaseUniqueService<Battle>() {


    fun findAllByType(type: BattleType): List<Battle> {
        return findAll("type:$type")
    }

    fun findAllByWinner(winnerId: UUID): List<Battle> {
        return findAll("winnerId:$winnerId")
    }

}
