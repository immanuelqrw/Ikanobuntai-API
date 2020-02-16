package com.immanuelqrw.ikanobuntai.api.service.battle

import com.immanuelqrw.ikanobuntai.api.dto.TeamVerification
import com.immanuelqrw.ikanobuntai.api.entity.*
import com.immanuelqrw.ikanobuntai.api.service.rule.FormatRuleService
import com.immanuelqrw.ikanobuntai.api.service.seek.ConfigurationSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.LeagueFormatSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.PokemonTeamSeekService
import com.immanuelqrw.ikanobuntai.api.service.seek.TrainerTeamSeekService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeamVerificationService {

    @Autowired
    private lateinit var trainerTeamSeekService: TrainerTeamSeekService

    @Autowired
    private lateinit var pokemonTeamSeekService: PokemonTeamSeekService

    @Autowired
    private lateinit var leagueFormatSeekService: LeagueFormatSeekService

    @Autowired
    private lateinit var formatRuleService: FormatRuleService

    @Autowired
    private lateinit var configurationSeekService: ConfigurationSeekService

    fun validateTeams(teamVerification: TeamVerification) {
        val defenderTrainerTeam: TrainerTeam = trainerTeamSeekService.findByName(teamVerification.defenderTeam)
        val challengerTrainerTeam: TrainerTeam = trainerTeamSeekService.findByName(teamVerification.challengerTeam)
        val leagueFormats: List<LeagueFormat> = leagueFormatSeekService.findAllLeagueFormatsByLeague(teamVerification.leagueName)

        val defenderPokemonTeam: List<TrainerPokemon> = pokemonTeamSeekService.findAllTrainerPokemonByTrainerTeam(defenderTrainerTeam.name)
        val challengerPokemonTeam: List<TrainerPokemon> = pokemonTeamSeekService.findAllTrainerPokemonByTrainerTeam(challengerTrainerTeam.name)

        leagueFormats.forEach { leagueFormat ->
            val format: Format = leagueFormat.format
            val formatConfiguration: Configuration = configurationSeekService.findByLeagueAndFormat(leagueFormat.league.name, leagueFormat.format)

            val formatLimiter: Any? = formatConfiguration.trueValue

            formatRuleService.validate(format, defenderPokemonTeam, formatLimiter)
            formatRuleService.validate(format, challengerPokemonTeam, formatLimiter)
        }
    }

}
