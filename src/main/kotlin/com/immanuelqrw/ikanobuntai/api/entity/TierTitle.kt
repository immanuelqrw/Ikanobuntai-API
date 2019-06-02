package com.immanuelqrw.ikanobuntai.api.entity

import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "`TierTitle`")
data class TierTitle(

    @Enumerated
    val tier: Tier,

    @Enumerated
    val title: Title

) : BaseUniqueEntity()
