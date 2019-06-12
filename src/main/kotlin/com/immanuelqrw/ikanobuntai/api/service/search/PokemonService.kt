package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import org.springframework.stereotype.Service

@Service
class PokemonService : BaseUniqueService<Pokemon>() {

    fun findByNameForm(name: String, form: String?): Pokemon? {
        return findAll("name:$name;form:${form ?: ""}").firstOrNull()
    }

}
