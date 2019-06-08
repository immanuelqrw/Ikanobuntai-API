package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerTeamService as UnitTrainerTeamService

@Service
class TrainerTeamService {

    @Autowired
    private lateinit var trainerTeamService: UnitTrainerTeamService

    fun findByName(name: String): TrainerTeam? {
        return trainerTeamService.findAll("name:$name").firstOrNull()
    }

}
