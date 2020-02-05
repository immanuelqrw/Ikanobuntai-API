package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.EffortValue
import org.springframework.stereotype.Service

@Service
class EffortValueSeekService : BaseUniqueService<EffortValue>(EffortValue::class.java)
