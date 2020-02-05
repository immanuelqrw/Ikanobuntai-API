package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Item
import com.immanuelqrw.ikanobuntai.api.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ItemSeekService : BaseUniqueService<Item>(Item::class.java) {

    @Autowired
    private lateinit var itemRepository: ItemRepository

    fun findByName(name: String): Item {
        return itemRepository.findByNameAndRemovedOnIsNull(name) ?: throw EntityNotFoundException()
    }

}
