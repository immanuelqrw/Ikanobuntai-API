package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
import org.springframework.stereotype.Service

@Service
class PokemonGenerationSeekService : BaseUniqueService<PokemonGeneration>(PokemonGeneration::class.java)
