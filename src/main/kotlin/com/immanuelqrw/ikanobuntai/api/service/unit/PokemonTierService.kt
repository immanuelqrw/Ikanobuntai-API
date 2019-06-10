package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonTier
import org.springframework.stereotype.Service

@Service("UnitPokemonTierService")
class PokemonTierService : BaseUniqueService<PokemonTier>()
