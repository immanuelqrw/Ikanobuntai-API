package com.immanuelqrw.ikanobuntai.api.service.unit

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.api.test.unit.service.BaseUniqueServiceTest
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

/**
 * Unit tests for LeagueFormatService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LeagueFormatServiceTest : BaseUniqueServiceTest<LeagueFormat>() {

    override val classType: Class<LeagueFormat> = LeagueFormat::class.java

    @InjectMocks
    override lateinit var service: LeagueFormatService

    @Mock
    override lateinit var repository: BaseUniqueRepository<LeagueFormat>

    @Mock
    override lateinit var searchService: SearchService<LeagueFormat>

    override val validId: UUID = UUID.randomUUID()
    override val invalidId: UUID = UUID.randomUUID() // ! Need method of failing

    @Mock
    override lateinit var validEntity: LeagueFormat

    @Mock
    override lateinit var invalidEntity: LeagueFormat

    @Mock
    override lateinit var replacedEntity: LeagueFormat

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
    override lateinit var validPage: Page<LeagueFormat>

    override val validSearch: String = "id:$validId"
    override val invalidSearch: String = "id@$validId"

    @Mock
    override lateinit var validSearchSpecification: Specification<LeagueFormat>

    @Mock
    override lateinit var invalidSearchSpecification: Specification<LeagueFormat>

    @Mock
    override lateinit var objectMapper: ObjectMapper

}
