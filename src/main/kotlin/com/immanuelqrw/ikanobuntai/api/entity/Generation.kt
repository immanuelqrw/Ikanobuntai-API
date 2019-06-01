package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Generation`")
data class Generation(

    val number: Int,

    val region: String

) : BaseUniqueEntity()
