package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Prize
import org.springframework.stereotype.Service

@Service
class PrizeService : BaseUniqueService<Prize>() {

    fun findByName(name: String): Prize? {
        return findAll("name:$name").firstOrNull()
    }

    // - Add image insert
}
