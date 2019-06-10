package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.stereotype.Service

@Service("UnitTrainerUserService")
class TrainerUserService : BaseUniqueService<TrainerUser>()
