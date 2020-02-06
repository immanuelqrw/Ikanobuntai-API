package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.TierTitle as TierTitleOutput
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TierTitle")
data class TierTitle(

    @Enumerated
    @Column(name = "tier", nullable = false)
    val tier: Tier,

    @Enumerated
    @Column(name = "title", nullable = false)
    val title: Title,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "prizeId", referencedColumnName = "id", nullable = false)
    val prize: Prize

) : BaseUniqueEntity() {

    val output: TierTitleOutput
        get() {
            return TierTitleOutput(
                tier = tier,
                title = title,
                prizeName = prize.name
            )
        }

}
