package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "`Configuration`")
data class Configuration(

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "`leagueFormatId`", referencedColumnName = "`id`")
    val leagueFormat: LeagueFormat,

    val value: String,

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
