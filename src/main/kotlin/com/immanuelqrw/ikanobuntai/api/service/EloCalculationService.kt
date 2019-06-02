package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleResult
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class EloCalculationService {

    companion object {
        // ? Move into configuration file
        private const val S_FACTOR_WINNER = 1.0
        private const val S_FACTOR_DRAW = 0.5
        private const val S_FACTOR_LOSS = 0.0

        // - Consider changing standard value
        private const val STANDARD_DEVIATION = 400.0
        private const val CONVERSION_BASE = 10.0
    }

    data class BattleElo(
        val defenderElo: Int,
        val challengerElo: Int
    )

    private data class SFactor(
        val defenderValue: Double,
        val challengerValue: Double
    )

    private fun calculateR(elo: Int): Double = CONVERSION_BASE.pow(elo / STANDARD_DEVIATION)

    private fun calculateE(trainerR: Double, opponentR: Double): Double = trainerR / (trainerR + opponentR)

    private fun calculateSFactor(battleResult: BattleResult): SFactor {
        return when (battleResult) {
            BattleResult.WIN -> SFactor(defenderValue = S_FACTOR_WINNER, challengerValue = S_FACTOR_LOSS)
            BattleResult.DRAW -> SFactor(defenderValue = S_FACTOR_DRAW, challengerValue = S_FACTOR_DRAW)
            BattleResult.LOSS -> SFactor(defenderValue = S_FACTOR_LOSS, challengerValue = S_FACTOR_WINNER)
        }
    }

    private fun calculateChange(defenderE: Double, challengerE: Double, sFactor: SFactor, kFactor: Int): BattleElo {
        val defenderElo = kFactor * (sFactor.defenderValue - defenderE)
        val challengerElo = kFactor * (sFactor.challengerValue - challengerE)

        return BattleElo(defenderElo.toInt(), challengerElo.toInt())
    }

    fun calculateBattle(battle: Battle): BattleElo {
        val defender = battle.defender
        val challenger = battle.challenger
        val kFactor = battle.value

        val battleResult = when(battle.winner) {
            defender -> BattleResult.WIN
            challenger -> BattleResult.LOSS
            else -> BattleResult.DRAW
        }

        val sFactor: SFactor = calculateSFactor(battleResult)

        val defenderR: Double = calculateR(defender.rating)
        val challengerR: Double = calculateR(challenger.rating)
        val defenderE = calculateE(defenderR, challengerR)
        val challengerE = calculateE(challengerR, defenderR)

        return calculateChange(defenderE, challengerE, sFactor, kFactor)
    }

}
