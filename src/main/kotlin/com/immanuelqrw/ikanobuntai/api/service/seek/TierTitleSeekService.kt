package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.TierTitle
import org.springframework.stereotype.Service

@Service
class TierTitleSeekService : BaseUniqueService<TierTitle>(TierTitle::class.java)
