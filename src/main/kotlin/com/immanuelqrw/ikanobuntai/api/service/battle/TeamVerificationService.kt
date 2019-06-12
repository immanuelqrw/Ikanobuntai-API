package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.TeamVerification
import com.immanuelqrw.ikanobuntai.api.service.rule.FormatRuleService
import com.immanuelqrw.ikanobuntai.api.service.search.ConfigurationService
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

    @Autowired
    private lateinit var formatRuleService: FormatRuleService

    @Autowired
    private lateinit var configurationService: ConfigurationService

    fun validateTeams(teamVerification: TeamVerification) {
        val defenderTrainerTeam = trainerTeamService.findByName(teamVerification.defenderTeam)!!
        val challengerTrainerTeam = trainerTeamService.findByName(teamVerification.challengerTeam)!!
        val leagueFormats = leagueFormatService.findAllLeagueFormatsByLeague(teamVerification.leagueId!!)

        val defenderPokemonTeam = pokemonTeamService.findAllTrainerPokemonByTrainerTeam(defenderTrainerTeam.id!!)
        val challengerPokemonTeam = pokemonTeamService.findAllTrainerPokemonByTrainerTeam(challengerTrainerTeam.id!!)

        leagueFormats.forEach { leagueFormat ->
            val format = leagueFormat.format
            val formatConfiguration = configurationService.findByLeagueFormat(leagueFormat.id!!)

            val formatLimiter: Any? = formatConfiguration?.trueValue

            formatRuleService.validate(format, defenderPokemonTeam, formatLimiter)
            formatRuleService.validate(format, challengerPokemonTeam, formatLimiter)
        }
    }

}
