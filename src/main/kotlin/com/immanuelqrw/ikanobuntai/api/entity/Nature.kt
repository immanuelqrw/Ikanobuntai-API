package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.ikanobuntai.api.entity.Stat.ATTACK
import com.immanuelqrw.ikanobuntai.api.entity.Stat.DEFENSE
import com.immanuelqrw.ikanobuntai.api.entity.Stat.SPECIAL_ATTACK
import com.immanuelqrw.ikanobuntai.api.entity.Stat.SPECIAL_DEFENSE
import com.immanuelqrw.ikanobuntai.api.entity.Stat.SPEED

enum class Nature(val riseStat: Stat?, val dropStat: Stat?) {
    HARDY(null, null),
    LONELY(ATTACK, DEFENSE),
    BRAVE(ATTACK, SPEED),
    ADAMANT(ATTACK, SPECIAL_ATTACK),
    NAUGHTY(ATTACK, SPECIAL_DEFENSE),
    BOLD(DEFENSE, ATTACK),
    DOCILE(null, null),
    RELAXED(DEFENSE, SPEED),
    IMPISH(DEFENSE, SPECIAL_ATTACK),
    LAX(DEFENSE, SPECIAL_DEFENSE),
    TIMID(SPEED, ATTACK),
    HASTY(SPEED, DEFENSE),
    SERIOUS(null, null),
    JOLLY(SPEED, SPECIAL_ATTACK),
    NAIVE(SPEED, SPECIAL_DEFENSE),
    MODEST(SPECIAL_ATTACK, ATTACK),
    MILD(SPECIAL_ATTACK, DEFENSE),
    QUIET(SPECIAL_ATTACK, SPEED),
    BASHFUL(null, null),
    RASH(SPECIAL_ATTACK, SPECIAL_DEFENSE),
    CALM(SPECIAL_DEFENSE, ATTACK),
    GENTLE(SPECIAL_DEFENSE, DEFENSE),
    SASSY(SPECIAL_DEFENSE, SPEED),
    CAREFUL(SPECIAL_DEFENSE, SPECIAL_ATTACK),
    QUIRKY(null, null)
}
