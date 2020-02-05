package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import org.springframework.stereotype.Service

@Service
class TrainerPrizeSeekService : BaseUniqueService<TrainerPrize>(TrainerPrize::class.java)
