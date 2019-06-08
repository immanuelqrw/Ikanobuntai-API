package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.PokemonTeamService as UnitPokemonTeamService

@Service
class PokemonTeamService {

    @Autowired
    private lateinit var pokemonTeamService: UnitPokemonTeamService

    fun findAllTrainerPokemonByTrainerTeam(trainerTeamId: UUID): List<TrainerPokemon> {
        return pokemonTeamService.findAll("trainerTeamId:$trainerTeamId").map { pokemonTeam ->
            pokemonTeam.trainerPokemon
        }
    }

    fun findTrainerTeamsByTrainerPokemon(trainerPokemonId: UUID): List<TrainerTeam> {
        return pokemonTeamService.findAll("trainerPokemonId:$trainerPokemonId").map { pokemonTeam ->
            pokemonTeam.trainerTeam
        }
    }

}
