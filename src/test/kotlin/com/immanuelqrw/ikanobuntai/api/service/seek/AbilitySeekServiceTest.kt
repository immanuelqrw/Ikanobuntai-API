package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Ability
import com.immanuelqrw.ikanobuntai.api.repository.AbilityRepository
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.persistence.EntityNotFoundException
import com.immanuelqrw.ikanobuntai.api.service.TestConstants as C

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AbilitySeekServiceTest {

    private val validName: String = C.VALID_ABILITY_NAME
    private val invalidName: String = C.INVALID_ABILITY_NAME

    @Mock
    private lateinit var validAbility: Ability

    @Mock
    private lateinit var abilityRepository: AbilityRepository

    @InjectMocks
    private lateinit var abilitySeekService: AbilitySeekService

    @BeforeAll
    fun setUp() {
        whenever(abilityRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validAbility)

        whenever(abilityRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid ability`() {
            val expectedAbility: Ability = validAbility

            val actualAbility: Ability = abilitySeekService.findByName(validName)

            actualAbility shouldEqual expectedAbility
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { abilitySeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
