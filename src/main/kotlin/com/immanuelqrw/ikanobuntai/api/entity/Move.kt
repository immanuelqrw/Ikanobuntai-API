package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "`Move`")
data class Move(

    @Column(unique = true)
    val name: String,

    @Enumerated
    val type: Type,

    @Enumerated
    val damage: Damage,

    val accuracy: Int?,

    val pp: Int,

    val effect: String?

) : BaseUniqueEntity()
