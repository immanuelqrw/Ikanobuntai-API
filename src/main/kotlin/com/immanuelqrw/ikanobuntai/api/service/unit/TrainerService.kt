package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.stereotype.Service

@Service("UnitTrainerService")
class TrainerService : BaseUniqueService<Trainer>()
