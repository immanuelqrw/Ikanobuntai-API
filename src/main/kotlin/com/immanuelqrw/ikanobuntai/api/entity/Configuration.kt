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

    val type: VarType

) : BaseUniqueEntity() {

    val trueValue: Any?
        get() {
            return when(type) {
                VarType.BOOLEAN -> value.toBoolean()
                VarType.SCALAR -> value.toInt()
                VarType.STRING -> value
                VarType.COLLECTION -> value.split("|")
            }
        }

}
