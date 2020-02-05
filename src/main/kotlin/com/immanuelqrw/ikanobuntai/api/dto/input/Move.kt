package com.immanuelqrw.ikanobuntai.api.dto.input

import com.immanuelqrw.ikanobuntai.api.entity.Damage
import com.immanuelqrw.ikanobuntai.api.entity.Type

data class Move(

    val name: String,

    val type: Type,

    val damage: Damage,

    val accuracy: Int?,

    val pp: Int,

    val effect: String?

)
