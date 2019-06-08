package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import com.immanuelqrw.ikanobuntai.api.service.unit.PokemonTeamService as UnitPokemonTeamService

@Service
class PokemonTeamService {

    @Autowired
    private lateinit var pokemonTeamService: UnitPokemonTeamService

    fun findAllByTrainerTeam(trainerTeamId: UUID): List<PokemonTeam> {
        return pokemonTeamService.findAll("trainerTeamId:$trainerTeamId")
    }

    fun findAllByTrainerPokemon(trainerPokemonId: UUID): List<PokemonTeam> {
        return pokemonTeamService.findAll("trainerPokemonId:$trainerPokemonId")
    }

}
