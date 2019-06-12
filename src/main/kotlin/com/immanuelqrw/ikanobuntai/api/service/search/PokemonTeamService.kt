package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PokemonTeamService : BaseUniqueService<PokemonTeam>() {

    fun findAllTrainerPokemonByTrainerTeam(trainerTeamId: UUID): List<TrainerPokemon> {
        return findAll("trainerTeamId:$trainerTeamId").map { pokemonTeam ->
            pokemonTeam.trainerPokemon
        }
    }

    fun findTrainerTeamsByTrainerPokemon(trainerPokemonId: UUID): List<TrainerTeam> {
        return findAll("trainerPokemonId:$trainerPokemonId").map { pokemonTeam ->
            pokemonTeam.trainerTeam
        }
    }
}
