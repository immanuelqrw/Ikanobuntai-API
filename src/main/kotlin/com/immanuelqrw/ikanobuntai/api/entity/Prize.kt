package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Prize`")
data class Prize(

    @Column(unique = true)
    val name: String,

    val imageUrl: String

) : BaseUniqueEntity()
