package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.rule.BattleFormat
import com.immanuelqrw.ikanobuntai.api.rule.FormatMapper
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "`Format`")
data class Format(

    @Column(unique = true)
    val name: String,

    val description: String,

    val hasRuleset: Boolean = false

) : BaseUniqueEntity() {
    val rule: BattleFormat by lazy {
        FormatMapper.map(name)
    }
}
