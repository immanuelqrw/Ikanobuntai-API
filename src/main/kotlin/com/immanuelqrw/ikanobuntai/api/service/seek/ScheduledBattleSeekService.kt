package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.ScheduledBattle
import org.springframework.stereotype.Service

@Service
class ScheduledBattleSeekService : BaseUniqueService<ScheduledBattle>(ScheduledBattle::class.java)
