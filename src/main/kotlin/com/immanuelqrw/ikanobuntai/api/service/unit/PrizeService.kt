package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Prize
import org.springframework.stereotype.Service

@Service("UnitPrizeService")
class PrizeService : BaseUniqueService<Prize>()
