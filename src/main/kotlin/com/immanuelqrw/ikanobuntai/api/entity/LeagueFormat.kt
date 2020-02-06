package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.LeagueFormat as LeagueFormatOutput
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LeagueFormat", uniqueConstraints = [UniqueConstraint(columnNames = ["leagueId", "format"])])
data class LeagueFormat(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "leagueId", referencedColumnName = "id", nullable = false)
    val league: League,

    @Column(name = "format", nullable = false)
    val format: Format

) : BaseUniqueEntity() {

    val output: LeagueFormatOutput
        get() {
            return LeagueFormatOutput(
                leagueName = league.name,
                format = format
            )
        }

}
