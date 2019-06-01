package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "`Nature`")
data class Nature(

    @Column(unique = true)
    val name: String,

    @Enumerated
    val dropStat: Stat,

    @Enumerated
    val riseStat: Stat
) : BaseUniqueEntity()
