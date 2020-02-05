package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.IndividualValue
import org.springframework.stereotype.Service

@Service
class IndividualValueSeekService : BaseUniqueService<IndividualValue>(IndividualValue::class.java)
