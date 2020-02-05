package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam

/**
 * Repository interface for [PokemonTeam]
 */
interface PokemonTeamRepository : BaseUniqueRepository<PokemonTeam> {

    fun findAllByTrainerTeamNameAndRemovedOnIsNull(trainerTeamName: String): List<PokemonTeam>

    fun findAllByTrainerPokemonTrainerNameAndRemovedOnIsNull(trainerName: String): List<PokemonTeam>

}
