package com.immanuelqrw.ikanobuntai.api.service.unit

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.api.test.unit.service.BaseUniqueServiceTest
import com.immanuelqrw.ikanobuntai.api.entity.PokemonGeneration
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
 * Unit tests for PokemonGenerationService
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonGenerationServiceTest : BaseUniqueServiceTest<PokemonGeneration>() {

    override val classType: Class<PokemonGeneration> = PokemonGeneration::class.java

    @InjectMocks
    override lateinit var service: PokemonGenerationService

    @Mock
    override lateinit var repository: BaseUniqueRepository<PokemonGeneration>

    @Mock
    override lateinit var searchService: SearchService<PokemonGeneration>

    override val validId: UUID = UUID.randomUUID()
    override val invalidId: UUID = UUID.randomUUID() // ! Need method of failing

    @Mock
    override lateinit var validEntity: PokemonGeneration

    @Mock
    override lateinit var invalidEntity: PokemonGeneration

    @Mock
    override lateinit var replacedEntity: PokemonGeneration

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
    override lateinit var validPage: Page<PokemonGeneration>

    override val validSearch: String = "id:$validId"
    override val invalidSearch: String = "id@$validId"

    @Mock
    override lateinit var validSearchSpecification: Specification<PokemonGeneration>

    @Mock
    override lateinit var invalidSearchSpecification: Specification<PokemonGeneration>

    @Mock
    override lateinit var objectMapper: ObjectMapper

}
