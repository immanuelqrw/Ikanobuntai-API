package com.immanuelqrw.ikanobuntai.api.dto.output

data class Pokemon(

    val number: String,

    val name: String,

    val form: String?,

    val isLegendary: Boolean,

    val isMythical: Boolean,

    val isMega: Boolean

)
