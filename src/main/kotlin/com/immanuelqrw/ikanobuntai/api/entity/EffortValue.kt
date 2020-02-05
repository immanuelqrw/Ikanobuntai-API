package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
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

) : BaseUniqueEntity()
