package com.immanuelqrw.ikanobuntai.api.service

import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTitleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerPrizeService as UnitTrainerPrizeService

@Service
class TrainerPrizeService {

    @Autowired
    private lateinit var trainerPrizeService: UnitTrainerPrizeService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    fun grantPrize(defenderId: UUID, challenger: Trainer, league: League) {
        val trainerTitle = trainerTitleService.findByTrainerId(defenderId)
        val prize = trainerTitle!!.tierTitle.prize

        val trainerPrize = TrainerPrize(
            trainer = challenger,
            prize = prize,
            league = league
        )

        trainerPrizeService.create(trainerPrize)
    }

}
