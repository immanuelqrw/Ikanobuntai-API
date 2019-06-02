package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import com.immanuelqrw.ikanobuntai.api.service.unit.TrainerTitleService as UnitTrainerTitleService

@Service
class TrainerTitleService {

    @Autowired
    private lateinit var trainerTitleService: UnitTrainerTitleService

    fun findByTrainerId(trainerId: UUID): TrainerTitle? {
        val page = PageRequest.of(1, 1, Sort.by("rank"))

        return trainerTitleService.findAll(page, "trainerId:$trainerId").content.firstOrNull()
    }

}
