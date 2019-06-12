package com.immanuelqrw.ikanobuntai.api.service.search

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.api.test.unit.service.BaseUniqueServiceTest
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPrize
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID

/**
 * Unit tests for TrainerPrizeService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TrainerPrizeServiceTest : BaseUniqueServiceTest<TrainerPrize>() {

    override val classType: Class<TrainerPrize> = TrainerPrize::class.java

    @InjectMocks
    override lateinit var service: TrainerPrizeService

    @Mock
    override lateinit var repository: BaseUniqueRepository<TrainerPrize>

    @Mock
    override lateinit var searchService: SearchService<TrainerPrize>

    override val validId: UUID = UUID.randomUUID()
    override val invalidId: UUID = UUID.randomUUID() // ! Need method of failing

    @Mock
    override lateinit var validEntity: TrainerPrize

    @Mock
    override lateinit var invalidEntity: TrainerPrize

    @Mock
    override lateinit var replacedEntity: TrainerPrize

    @Mock
    override lateinit var validPatchedFields: Map<String, Any>

    @Mock
    override lateinit var invalidPatchedFields: Map<String, Any>

    @Mock
    override lateinit var originalFields: Map<String, Any>

    @Mock
    override lateinit var patchedFields: Map<String, Any>

    @Mock
    override lateinit var validPageable: Pageable

    @Mock
    override lateinit var invalidPageable: Pageable

    @Mock
    override lateinit var validPage: Page<TrainerPrize>

    @Mock
    override lateinit var validEntities: List<TrainerPrize>

    override val validSearch: String = "id:$validId"
    override val invalidSearch: String = "id@$validId"

    @Mock
    override lateinit var validEntityIds: Iterable<UUID>

    @Mock
    override lateinit var invalidEntityIds: Iterable<UUID>

    override val validCount: Long = 0

    @Mock
    override lateinit var validSearchSpecification: Specification<TrainerPrize>

    @Mock
    override lateinit var invalidSearchSpecification: Specification<TrainerPrize>

    @Mock
    override lateinit var objectMapper: ObjectMapper

}
