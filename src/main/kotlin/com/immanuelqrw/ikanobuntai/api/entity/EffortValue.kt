package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

// ? Limit values in code
@Entity
@Table(name = "`EffortValue`")
data class EffortValue(

    val hp: Int,

    val attack: Int,

    val defense: Int,

    val specialAttack: Int,

    val specialDefense: Int,

    val speed: Int

) : BaseUniqueEntity()
