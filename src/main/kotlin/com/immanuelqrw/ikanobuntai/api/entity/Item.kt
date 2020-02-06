package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.Item as ItemOutput
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Item")
data class Item(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @Column(name = "effect", nullable = false)
    val effect: String

) : BaseUniqueEntity() {

    val output: ItemOutput
        get() {
            return ItemOutput(
                name = name,
                effect = effect
            )
        }

}
