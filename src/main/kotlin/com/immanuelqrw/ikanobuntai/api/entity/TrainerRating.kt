package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TrainerRating", uniqueConstraints = [UniqueConstraint(columnNames = ["trainerId", "tier"])])
data class TrainerRating(

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerId", referencedColumnName = "id", nullable = false)
    val trainer: Trainer,

    @Enumerated
    @Column(name = "tier", nullable = false)
    val tier: Tier,

    @Enumerated
    @Column(name = "rank", nullable = false)
    val rank: Rank,

    @Column(name = "elo", nullable = false)
    val elo: Int = 1000

) : BaseUniqueEntity()
