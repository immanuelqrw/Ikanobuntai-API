package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TrainerUser")
data class TrainerUser(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @Column(name = "email", unique = true, nullable = false)
    val email: String,

    @Column(name = "passwordHash", nullable = false)
    val passwordHash: String,

    @Column(name = "passwordSalt", nullable = false)
    val passwordSalt: String,

    @Column(name = "oAuthId", nullable = false)
    val oAuthId: String

) : BaseUniqueEntity()
