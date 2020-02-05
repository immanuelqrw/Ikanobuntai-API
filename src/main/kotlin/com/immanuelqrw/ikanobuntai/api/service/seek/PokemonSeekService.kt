package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import com.immanuelqrw.ikanobuntai.api.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PokemonSeekService : BaseUniqueService<Pokemon>(Pokemon::class.java) {

    @Autowired
    private lateinit var pokemonRepository: PokemonRepository

    fun findByNameAndForm(name: String, form: String? = null): Pokemon {
        return pokemonRepository.findByNameAndFormAndRemovedOnIsNull(name, form) ?: throw EntityNotFoundException()
    }

}
