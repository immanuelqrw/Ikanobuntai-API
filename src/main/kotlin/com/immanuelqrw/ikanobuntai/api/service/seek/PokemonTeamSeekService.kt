package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import com.immanuelqrw.ikanobuntai.api.repository.PokemonTeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PokemonTeamSeekService : BaseUniqueService<PokemonTeam>(PokemonTeam::class.java) {

    @Autowired
    private lateinit var pokemonTeamRepository: PokemonTeamRepository

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
