package com.immanuelqrw.ikanobuntai.api.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class BaseStat(

    @Column(name = "`baseStatTotal`")
    val total: Int,

    @Column(name = "`hpBaseStat`")
    val hp: Int,

    @Column(name = "`attackBaseStat`")
    val attack: Int,

    @Column(name = "`defenseBaseStat`")
    val defense: Int,

    @Column(name = "`specialAttackBaseStat`")
    val specialAttack: Int,

    @Column(name = "`specialDefenseBaseStat`")
    val specialDefense: Int,

    @Column(name = "`speedBaseStat`")
    val speed: Int

)
