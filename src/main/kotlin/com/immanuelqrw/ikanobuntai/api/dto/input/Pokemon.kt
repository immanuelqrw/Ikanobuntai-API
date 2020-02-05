package com.immanuelqrw.ikanobuntai.api.dto.input

data class Pokemon(

    val number: String,

    val name: String,

    val form: String?,

    val isLegendary: Boolean = false,

    val isMythical: Boolean = false,

    val isMega: Boolean = false

)
