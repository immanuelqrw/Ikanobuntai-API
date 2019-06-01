package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "`Configuration`", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "tierId"])])
data class Configuration(

    val name: String,

    val value: String,

    val type: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`tierId`", referencedColumnName = "`id`")
    val tier: Tier

) : BaseUniqueEntity()
