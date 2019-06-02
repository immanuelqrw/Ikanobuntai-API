package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "`Configuration`", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "tier"])])
data class Configuration(

    val name: String,

    val value: String,

    val type: String,

    @Enumerated
    val tier: Tier

) : BaseUniqueEntity()
