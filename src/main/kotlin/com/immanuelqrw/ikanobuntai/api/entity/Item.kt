package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Item`")
data class Item(

    @Column(unique = true)
    val name: String,

    val effect: String

) : BaseUniqueEntity()
