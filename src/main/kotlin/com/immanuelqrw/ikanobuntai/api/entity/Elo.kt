package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Elo`")
data class Elo(

    val divider: Int,

    val k: Int

) : BaseUniqueEntity()
