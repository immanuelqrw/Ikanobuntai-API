package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import org.springframework.stereotype.Repository

/**
 * Repository interface for [PokemonTeam]
 */
@Repository
interface PokemonTeamRepository : BaseUniqueRepository<PokemonTeam> {

    fun findAllByTrainerTeamNameAndRemovedOnIsNull(trainerTeamName: String): List<PokemonTeam>

    fun findAllByTrainerPokemonTrainerNameAndRemovedOnIsNull(trainerName: String): List<PokemonTeam>

}
