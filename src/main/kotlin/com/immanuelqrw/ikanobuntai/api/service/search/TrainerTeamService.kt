package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import org.springframework.stereotype.Service

@Service
class TrainerTeamService : BaseUniqueService<TrainerTeam>() {

    fun findByName(name: String): TrainerTeam? {
        return findAll("name:$name").firstOrNull()
    }

}
