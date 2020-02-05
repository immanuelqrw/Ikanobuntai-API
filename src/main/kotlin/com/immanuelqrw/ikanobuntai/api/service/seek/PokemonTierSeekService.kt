package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTier
import org.springframework.stereotype.Service

@Service
class PokemonTierSeekService : BaseUniqueService<PokemonTier>(PokemonTier::class.java)
