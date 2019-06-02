package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
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

    val isMega: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`prevolvedPokemonId`", referencedColumnName = "`id`")
    val prevolvedPokemon: Pokemon?
) : BaseUniqueEntity()
