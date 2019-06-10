package com.immanuelqrw.ikanobuntai.api.service.unit

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import org.springframework.stereotype.Service

@Service("UnitPokemonGenerationService")
class PokemonGenerationService : BaseUniqueService<PokemonGeneration>()
