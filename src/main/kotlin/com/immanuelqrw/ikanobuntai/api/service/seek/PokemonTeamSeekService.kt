package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import com.immanuelqrw.ikanobuntai.api.repository.PokemonTeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PokemonTeamSeekService : BaseUniqueService<PokemonTeam>(PokemonTeam::class.java) {

    @Autowired
    private lateinit var pokemonTeamRepository: PokemonTeamRepository

    fun findAllTrainerPokemonByTrainerTeam(trainerTeamName: String): List<TrainerPokemon> {
        return pokemonTeamRepository.findAllByTrainerTeamNameAndRemovedOnIsNull(trainerTeamName).map { pokemonTeam ->
            pokemonTeam.trainerPokemon
        }
    }

    fun findTrainerTeamsByTrainerPokemon(trainerName: String): List<TrainerTeam> {
        return pokemonTeamRepository.findAllByTrainerTeamNameAndRemovedOnIsNull(trainerName).map { pokemonTeam ->
            pokemonTeam.trainerTeam
        }
    }
}
