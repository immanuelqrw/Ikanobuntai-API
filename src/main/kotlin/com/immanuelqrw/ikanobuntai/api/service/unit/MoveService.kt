package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Move
import org.springframework.stereotype.Service

@Service("UnitMoveService")
class MoveService : BaseUniqueService<Move>()
