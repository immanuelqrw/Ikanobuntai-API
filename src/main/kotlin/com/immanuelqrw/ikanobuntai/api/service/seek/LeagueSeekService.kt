package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.repository.LeagueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LeagueSeekService : BaseUniqueService<League>(League::class.java) {

    @Autowired
    private lateinit var leagueRepository: LeagueRepository

    fun findByName(name: String): League? {
        return findAll("name:$name").firstOrNull()
    }

}
