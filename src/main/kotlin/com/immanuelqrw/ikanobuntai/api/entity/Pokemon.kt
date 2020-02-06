package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.Pokemon as PokemonOutput
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Pokemon", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "form"])])
data class Pokemon(

    @Column(name = "number", nullable = false)
    val number: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "form")
    val form: String?,

    @Column(name = "isLegendary", nullable = false)
    val isLegendary: Boolean = false,

    @Column(name = "isMythical", nullable = false)
    val isMythical: Boolean = false,

    @Column(name = "isMega", nullable = false)
    val isMega: Boolean = false

) : BaseUniqueEntity() {

    val output: PokemonOutput
        get() {
            return PokemonOutput(
                number = number,
                name = name,
                form = form,
                isLegendary = isLegendary,
                isMythical = isMythical,
                isMega = isMega
            )
        }

}
