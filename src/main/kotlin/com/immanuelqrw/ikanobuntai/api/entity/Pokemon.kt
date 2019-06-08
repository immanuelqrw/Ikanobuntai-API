package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "`Pokemon`", uniqueConstraints = [UniqueConstraint(columnNames = ["`name`", "`form`"])])
data class Pokemon(

    val number: String,

    val name: String,

    val form: String?,

    val isLegendary: Boolean = false,

    val isMythical: Boolean = false,

    val isMega: Boolean = false

) : BaseUniqueEntity()
