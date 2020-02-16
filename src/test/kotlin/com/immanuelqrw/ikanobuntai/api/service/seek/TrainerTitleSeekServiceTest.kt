package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTitle
import com.immanuelqrw.ikanobuntai.api.repository.TrainerTitleRepository
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
internal class TrainerTitleSeekServiceTest {

    private val validTrainerName: String = C.VALID_TRAINER_NAME
    private val invalidTrainerName: String = C.INVALID_TRAINER_NAME

    private val validTier: Tier = C.VALID_TIER
    private val invalidTier: Tier = C.INVALID_TIER

    private val validTitle: Title = C.VALID_TITLE
    private val invalidTitle: Title = C.INVALID_TITLE

    @Mock
    private lateinit var validTrainerTitle: TrainerTitle

    @Mock
    private lateinit var trainerTitleRepository: TrainerTitleRepository

    @InjectMocks
    private lateinit var trainerTitleSeekService: TrainerTitleSeekService

    @BeforeAll
    fun setUp() {
        whenever(trainerTitleRepository.findByTrainerNameAndTierTitleTierAndRemovedOnIsNull(validTrainerName, validTier)).thenReturn(validTrainerTitle)
        whenever(trainerTitleRepository.findByTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(validTier, validTitle)).thenReturn(validTrainerTitle)

        whenever(trainerTitleRepository.findByTrainerNameAndTierTitleTierAndRemovedOnIsNull(invalidTrainerName, invalidTier)).thenReturn(null)
        whenever(trainerTitleRepository.findByTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(invalidTier, invalidTitle)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid trainer name and tier - when findByTrainerAndTier - then return valid trainerTitle`() {
            val expectedTrainerTitle: TrainerTitle = validTrainerTitle

            val actualTrainerTitle: TrainerTitle = trainerTitleSeekService.findByTrainerAndTier(validTrainerName, validTier)

            actualTrainerTitle shouldEqual expectedTrainerTitle
        }

        @Test
        fun `given valid tier and title - when findByTrainerAndTier - then return valid trainerTitle`() {
            val expectedTrainerTitle: TrainerTitle = validTrainerTitle

            val actualTrainerTitle: TrainerTitle = trainerTitleSeekService.findByTierAndTitle(validTier, validTitle)

            actualTrainerTitle shouldEqual expectedTrainerTitle
        }

        // ! Missing transferTitle testing

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid trainer name and tier - when findByTrainerAndTier - then throw EntityNotFoundException`() {
            invoking { trainerTitleSeekService.findByTrainerAndTier(invalidTrainerName, invalidTier) } shouldThrow EntityNotFoundException::class
        }

        @Test
        fun `given invalid tier and title - when findByTierAndTitle - then throw EntityNotFoundException`() {
            invoking { trainerTitleSeekService.findByTierAndTitle(invalidTier, invalidTitle) } shouldThrow EntityNotFoundException::class
        }

    }

}
