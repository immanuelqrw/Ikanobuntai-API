package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Prize
import com.immanuelqrw.ikanobuntai.api.repository.PrizeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PrizeSeekService : BaseUniqueService<Prize>(Prize::class.java) {

    @Autowired
    private lateinit var prizeRepository: PrizeRepository

    fun findByName(name: String): Prize {
        return prizeRepository.findByNameAndRemovedOnIsNull(name) ?: throw EntityNotFoundException()
    }

    // - Add image insert
}
