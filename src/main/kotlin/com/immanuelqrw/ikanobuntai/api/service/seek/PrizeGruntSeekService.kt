package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import com.immanuelqrw.ikanobuntai.api.repository.PrizeGruntRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PrizeGruntSeekService : BaseUniqueService<PrizeGrunt>(PrizeGrunt::class.java) {

    @Autowired
    private lateinit var prizeGruntRepository: PrizeGruntRepository

    fun findByLeagueTrainer(leagueId: UUID, trainerId: UUID): PrizeGrunt? {
        return findAll("leagueId:$leagueId;trainerId:$trainerId").firstOrNull()
    }

    fun findAllByLeagueTitle(leagueId: UUID, tierTitleId: UUID): List<PrizeGrunt> {
        return findAll("leagueId:$leagueId;tierTitleId:$tierTitleId")
    }

}
