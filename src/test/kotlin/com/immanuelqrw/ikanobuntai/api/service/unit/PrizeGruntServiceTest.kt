package com.immanuelqrw.ikanobuntai.api.service.unit

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.api.test.unit.service.BaseUniqueServiceTest
import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
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
 * Unit tests for PrizeGruntService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrizeGruntServiceTest : BaseUniqueServiceTest<PrizeGrunt>() {

    override val classType: Class<PrizeGrunt> = PrizeGrunt::class.java

    @InjectMocks
    override lateinit var service: PrizeGruntService

    @Mock
    override lateinit var repository: BaseUniqueRepository<PrizeGrunt>

    @Mock
    override lateinit var searchService: SearchService<PrizeGrunt>

    override val validId: UUID = UUID.randomUUID()
    override val invalidId: UUID = UUID.randomUUID() // ! Need method of failing

    @Mock
    override lateinit var validEntity: PrizeGrunt

    @Mock
    override lateinit var invalidEntity: PrizeGrunt

    @Mock
    override lateinit var replacedEntity: PrizeGrunt

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
    override lateinit var validPage: Page<PrizeGrunt>

    override val validSearch: String = "id:$validId"
    override val invalidSearch: String = "id@$validId"

    @Mock
    override lateinit var validSearchSpecification: Specification<PrizeGrunt>

    @Mock
    override lateinit var invalidSearchSpecification: Specification<PrizeGrunt>

    @Mock
    override lateinit var objectMapper: ObjectMapper

}
