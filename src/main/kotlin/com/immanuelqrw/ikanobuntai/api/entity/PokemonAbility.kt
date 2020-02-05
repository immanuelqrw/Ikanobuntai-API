package com.immanuelqrw.ikanobuntai.api.entity

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Embeddable
data class PokemonAbility(

    val main: Ability,

    val alternate: Ability?,

    val hidden: Ability?

)
