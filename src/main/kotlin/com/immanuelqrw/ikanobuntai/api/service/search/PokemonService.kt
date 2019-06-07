package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PokemonService as UnitPokemonService

@Service
class PokemonService {

    @Autowired
    private lateinit var pokemonService: UnitPokemonService

    fun findByNameForm(name: String, form: String?): Pokemon? {
        return pokemonService.findAll("name:$name;form:${form ?: ""}").firstOrNull()
    }

}
