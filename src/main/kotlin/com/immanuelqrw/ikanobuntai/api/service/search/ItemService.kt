package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Item
import org.springframework.stereotype.Service

@Service
class ItemService : BaseUniqueService<Item>() {

    fun findByName(name: String): Item? {
        return findAll("name:$name").firstOrNull()
    }

}
