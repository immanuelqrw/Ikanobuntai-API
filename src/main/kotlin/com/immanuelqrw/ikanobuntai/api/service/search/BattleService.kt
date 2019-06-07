package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.BattleService as UnitBattleService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerRatingService as UnitTrainerRatingService
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerService as UnitTrainerService

@Service
class BattleService {

    @Autowired
    private lateinit var battleService: UnitBattleService

    fun findAllByType(type: BattleType): List<Battle> {
        return battleService.findAll("type:$type")
    }

    fun findAllByWinner(winnerId: UUID): List<Battle> {
        return battleService.findAll("winnerId:$winnerId")
    }

}
