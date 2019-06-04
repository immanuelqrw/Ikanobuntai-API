package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PokemonService as UnitPokemonService

@Service
class PokemonService {

    @Autowired
    private lateinit var pokemonService: UnitPokemonService

    fun findByNameForm(name: String, form: String?): Pokemon? {
        return pokemonService.findAll(UNIQUE_PAGE_REQUEST, "name:$name;form:${form ?: ""}").content.firstOrNull()
    }

}
