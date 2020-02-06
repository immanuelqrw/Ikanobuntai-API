package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.IndividualValue as IndividualValueOutput
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

// ? Limit values in code
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IndividualValue")
data class IndividualValue(

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

    val output: IndividualValueOutput
        get() {
            return IndividualValueOutput(
                hp = hp,
                attack = attack,
                defense = defense,
                specialAttack = specialAttack,
                specialDefense = specialDefense,
                speed = speed
            )
        }

}
