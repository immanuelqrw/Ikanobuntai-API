package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Pokemon

/**
 * Repository interface for [Pokemon]
 */
interface PokemonRepository : BaseUniqueRepository<Pokemon> {

    fun findByNameAndFormAndRemovedOnIsNull(name: String, form: String?): Pokemon?

}
