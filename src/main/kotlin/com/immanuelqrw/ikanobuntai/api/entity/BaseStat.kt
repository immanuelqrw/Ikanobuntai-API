package com.immanuelqrw.ikanobuntai.api.entity

import javax.persistence.Embeddable

@Embeddable
data class BaseStat(

    val total: Int,

    val hp: Int,

    val attack: Int,

    val defense: Int,

    val specialAttack: Int,

    val specialDefense: Int,

    val speed: Int

)
