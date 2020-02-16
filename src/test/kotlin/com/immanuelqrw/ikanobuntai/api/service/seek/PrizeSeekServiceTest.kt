package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Prize
import com.immanuelqrw.ikanobuntai.api.repository.PrizeRepository
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
internal class PrizeSeekServiceTest {

    private val validName: String = C.VALID_PRIZE_NAME
    private val invalidName: String = C.INVALID_PRIZE_NAME

    @Mock
    private lateinit var validPrize: Prize

    @Mock
    private lateinit var prizeRepository: PrizeRepository

    @InjectMocks
    private lateinit var prizeSeekService: PrizeSeekService

    @BeforeAll
    fun setUp() {
        whenever(prizeRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validPrize)

        whenever(prizeRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid prize`() {
            val expectedPrize: Prize = validPrize

            val actualPrize: Prize = prizeSeekService.findByName(validName)

            actualPrize shouldEqual expectedPrize
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { prizeSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
