package com.immanuelqrw.ikanobuntai.api.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.immanuelqrw.core.entity.BaseUniqueEntity
import com.immanuelqrw.ikanobuntai.api.dto.output.Trainer as TrainerOutput
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Trainer")
data class Trainer(

    @Column(name = "name", unique = true, nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "trainerUserId", referencedColumnName = "id", nullable = false)
    val trainerUser: TrainerUser

) : BaseUniqueEntity() {

    val output: TrainerOutput
        get() {
            return TrainerOutput(
                name = name,
                trainerUser = trainerUser.output
            )
        }

}
