package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.PokemonService as UnitPokemonService

@Service
class PokemonService {

    @Autowired
    private lateinit var pokemonService: UnitPokemonService

    fun findByNameForm(name: String, form: String?): Pokemon? {
        val page = PageRequest.of(1, 1)

        return pokemonService.findAll(page, "name:$name;form:${form ?: ""}").content.firstOrNull()
    }

}
