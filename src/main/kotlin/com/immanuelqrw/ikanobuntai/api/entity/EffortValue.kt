package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.EffortValue as EffortValueOutput
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

// ? Limit values in code
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "EffortValue")
data class EffortValue(

    @Column(name = "hp", nullable = false)
    val hp: Int,

    @Column(name = "attack", nullable = false)
    val attack: Int,

    @Column(name = "defense", nullable = false)
    val defense: Int,

    @Column(name = "specialAttack", nullable = false)
    val specialAttack: Int,

    @Column(name = "specialDefense", nullable = false)
    val specialDefense: Int,

    @Column(name = "speed", nullable = false)
    val speed: Int

) : BaseUniqueEntity() {

    val output: EffortValueOutput
        get() {
            return EffortValueOutput(
                hp = hp,
                attack = attack,
                defense = defense,
                specialAttack = specialAttack,
                specialDefense = specialDefense,
                speed = speed
            )
        }

}
