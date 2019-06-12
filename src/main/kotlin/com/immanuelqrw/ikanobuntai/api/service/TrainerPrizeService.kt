package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTitleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerPrizeService

@Service
class BattlePrizeService {

    @Autowired
    private lateinit var trainerPrizeService: TrainerPrizeService

    @Autowired
    private lateinit var trainerTitleService: TrainerTitleService

    fun grantPrize(defenderId: UUID, challenger: Trainer, league: League) {
        val trainerTitle = trainerTitleService.findByTrainerIdTier(defenderId, league.tier)
        val prize = trainerTitle!!.tierTitle.prize

        val trainerPrize = TrainerPrize(
            trainer = challenger,
            prize = prize,
            league = league
        )

        trainerPrizeService.create(trainerPrize)
    }

}
