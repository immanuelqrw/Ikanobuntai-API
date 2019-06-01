package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Stage`")
data class Stage(

    @Column(unique = true)
    val name: String

) : BaseUniqueEntity()
