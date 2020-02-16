package com.immanuelqrw.ikanobuntai.api.repository

import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.ikanobuntai.api.entity.Item
import org.springframework.stereotype.Repository

/**
 * Repository interface for [Item]
 */
@Repository
interface ItemRepository : BaseUniqueRepository<Item> {

    fun findByNameAndRemovedOnIsNull(name: String): Item?

}
