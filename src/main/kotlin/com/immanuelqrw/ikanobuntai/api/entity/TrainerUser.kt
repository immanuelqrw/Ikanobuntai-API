package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`TrainerUser`")
data class TrainerUser(

    @Column(unique = true)
    val name: String,

    @Column(unique = true)
    val email: String,

    val passwordHash: String,

    val passwordSalt: String,

    val oAuthId: String

) : BaseUniqueEntity()
