package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.TeamVerification
import com.immanuelqrw.ikanobuntai.api.rule.FormatRule
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueFormatService
import com.immanuelqrw.ikanobuntai.api.service.search.PokemonTeamService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerTeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeamVerificationService {

    @Autowired
    private lateinit var trainerTeamService: TrainerTeamService

    @Autowired
    private lateinit var pokemonTeamService: PokemonTeamService

    @Autowired
    private lateinit var leagueFormatService: LeagueFormatService

    fun validateTeams(teamVerification: TeamVerification) {
        val defenderTrainerTeam = trainerTeamService.findByName(teamVerification.defenderTeam)!!
        val challengerTrainerTeam = trainerTeamService.findByName(teamVerification.challengerTeam)!!
        val formats = leagueFormatService.findAllFormatsByLeague(teamVerification.leagueId!!)

        val defenderPokemonTeam = pokemonTeamService.findAllTrainerPokemonByTrainerTeam(defenderTrainerTeam.id!!)
        val challengerPokemonTeam = pokemonTeamService.findAllTrainerPokemonByTrainerTeam(challengerTrainerTeam.id!!)

        formats.forEach { format ->
            FormatRule.validate(format, defenderPokemonTeam)
            FormatRule.validate(format, challengerPokemonTeam)
        }
    }

}
