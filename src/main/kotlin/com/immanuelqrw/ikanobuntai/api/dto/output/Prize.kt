package com.immanuelqrw.ikanobuntai.api.dto.output

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

data class Prize(

    val name: String,

    val imageUrl: String

)
