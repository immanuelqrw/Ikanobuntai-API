package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.LeaguePokemon
import org.springframework.stereotype.Service

@Service
class LeaguePokemonSeekService : BaseUniqueService<LeaguePokemon>(LeaguePokemon::class.java)
