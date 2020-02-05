package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Ability
import com.immanuelqrw.ikanobuntai.api.repository.AbilityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class AbilitySeekService : BaseUniqueService<Ability>(Ability::class.java) {

    @Autowired
    private lateinit var abilityRepository: AbilityRepository

    fun findByName(name: String): Ability {
        return abilityRepository.findByNameAndRemovedOnIsNull(name) ?: throw EntityNotFoundException()
    }

}
