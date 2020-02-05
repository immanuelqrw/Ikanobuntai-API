package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Ability")
data class Ability(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @Column(name = "effect", unique = true, nullable = false)
    val effect: String

) : BaseUniqueEntity()
