package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Move")
data class Move(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @Enumerated
    @Column(name = "type", nullable = false)
    val type: Type,

    @Enumerated
    @Column(name = "damage", nullable = false)
    val damage: Damage,

    @Column(name = "accuracy")
    val accuracy: Int?,

    @Column(name = "pp", nullable = false)
    val pp: Int,

    @Column(name = "effect")
    val effect: String?

) : BaseUniqueEntity()
