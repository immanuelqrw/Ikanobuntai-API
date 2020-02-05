package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Configuration")
data class Configuration(

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueFormatId", referencedColumnName = "id", nullable = false)
    val leagueFormat: LeagueFormat,

    @Column(name = "value", nullable = false)
    val value: String,

    @Enumerated
    @Column(name = "type", nullable = false)
    val type: ConfigurationType

) : BaseUniqueEntity() {

    val trueValue: Any?
        get() {
            return when(type) {
                ConfigurationType.BOOLEAN -> value.toBoolean()
                ConfigurationType.SCALAR -> value.toInt()
                ConfigurationType.STRING -> value
                ConfigurationType.COLLECTION -> value.split("|")
            }
        }

}
