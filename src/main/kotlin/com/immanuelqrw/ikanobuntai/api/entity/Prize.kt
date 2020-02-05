package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Prize")
data class Prize(

    @Column(name = "stage", unique = true, nullable = false)
    val name: String,

    @Column(name = "imageUrl", nullable = false)
    val imageUrl: String

) : BaseUniqueEntity()
