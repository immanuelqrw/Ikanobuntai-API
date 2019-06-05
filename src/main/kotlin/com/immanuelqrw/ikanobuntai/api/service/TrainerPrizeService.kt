package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerPrizeService as UnitTrainerPrizeService

@Service
class TrainerPrizeService {

    @Autowired
    private lateinit var trainerPrizeService: UnitTrainerPrizeService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    fun grantPrize(defender: Trainer, challenger: Trainer, league: League) {
        val trainerTitle = trainerTitleService.findByTrainerId(defender.id!!)
        val prize = trainerTitle!!.tierTitle.prize

        val trainerPrize = TrainerPrize(
            trainer = challenger,
            prize = prize,
            league = league
        )

        trainerPrizeService.create(trainerPrize)
    }

}
