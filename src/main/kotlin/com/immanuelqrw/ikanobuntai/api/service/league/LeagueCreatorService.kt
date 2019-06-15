package com.immanuelqrw.ikanobuntai.api.service.league

import com.immanuelqrw.ikanobuntai.api.dto.PlannedBattle
import com.immanuelqrw.ikanobuntai.api.dto.PokemonLeague
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueService
import com.immanuelqrw.ikanobuntai.api.service.search.LeagueTrainerService
import com.immanuelqrw.ikanobuntai.api.service.search.TrainerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class LeagueCreatorService {

    @Autowired
    private lateinit var leagueService: LeagueService

    @Autowired
    private lateinit var trainerService: TrainerService

    @Autowired
    private lateinit var leagueTrainerService: LeagueTrainerService

    @Autowired
    private lateinit var battleSchedulerService: BattleSchedulerService

    fun create(pokemonLeague: PokemonLeague): League {
        val league = pokemonLeague.run {
            League(
                name = name,
                type = type,
                stage = stage,
                tier = tier,
                stdDev = stdDev,
                kFactor = kFactor,
                startedOn = LocalDateTime.now(ZoneOffset.UTC),
                prizeMin = prizeMin
            )
        }

        val createdLeague = leagueService.create(league)

        addTrainersToLeague(createdLeague, pokemonLeague.trainers)
        generateBattles(createdLeague.name, pokemonLeague.trainers)


        return createdLeague
    }

    fun generateBattles(leagueName: String, trainers: Collection<String>) {
        val trainerCombos: Collection<Pair<String, String>> = generateCombinations(trainers.toList())

        trainerCombos.forEach { (defender, challenger) ->
            val plannedBattle = PlannedBattle(
                type = BattleType.LEAGUE,
                defender = defender,
                challenger = challenger,
                league = leagueName,
                // ! Need to think of ways to generate list of dates to schedule matches by
                toBeFoughtOn = LocalDateTime.now(ZoneOffset.UTC).plusDays(10L)
            )

            battleSchedulerService.schedule(plannedBattle)

        }

    }

    private fun addTrainersToLeague(league: League, trainerNames: Collection<String>) {
        trainerNames.forEach { trainerName ->
            trainerService.findByName(trainerName)?.let { trainer ->
                val leagueTrainer = LeagueTrainer(
                    league = league,
                    trainer = trainer
                )

                leagueTrainerService.create(leagueTrainer)
            }
        }
    }

    // - Move to Util
    private fun generateCombinations(collection: List<String>): Collection<Pair<String, String>> {
        val collectionSize = collection.size
        val trainerCombos: MutableList<Pair<String, String>> = mutableListOf()

        for (i in 0..collectionSize) {
            for (j in i + 1..collectionSize) {
                trainerCombos.add(Pair(collection[i], collection[j]))
            }
        }

        return trainerCombos
    }

}
