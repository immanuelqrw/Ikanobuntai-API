package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.entity.*
import com.immanuelqrw.ikanobuntai.api.service.seek.TrainerPrizeSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.TrainerTitleSeekService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BattlePrizeService {

    @Autowired
    private lateinit var trainerPrizeSeekService: TrainerPrizeSeekService

    @Autowired
    private lateinit var trainerTitleSeekService: TrainerTitleSeekService

    fun grantPrize(defenderName: String, challenger: Trainer, league: League) {
        val trainerTitle: TrainerTitle = trainerTitleSeekService.findByTrainerAndTier(defenderName, league.tier)
        val prize: Prize = trainerTitle.tierTitle.prize

        val trainerPrize = TrainerPrize(
            trainer = challenger,
            prize = prize,
            league = league
        )

        trainerPrizeSeekService.create(trainerPrize)
    }

}
