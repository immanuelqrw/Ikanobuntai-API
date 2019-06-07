package com.immanuelqrw.ikanobuntai.api.entity

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Embeddable
data class PokemonAbility(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`abilityId`", referencedColumnName = "`id`")
    val main: Ability,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`alternateAbilityId`", referencedColumnName = "`id`")
    val alternate: Ability?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`hiddenAbilityId`", referencedColumnName = "`id`")
    val hidden: Ability?

)
