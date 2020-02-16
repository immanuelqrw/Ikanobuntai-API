package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import com.immanuelqrw.ikanobuntai.api.repository.TrainerRatingRepository
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
internal class TrainerRatingSeekServiceTest {

    private val validTrainerName: String = C.VALID_TRAINER_NAME
    private val invalidTrainerName: String = C.INVALID_TRAINER_NAME

    private val validTier: Tier = C.VALID_TIER
    private val invalidTier: Tier = C.INVALID_TIER

    @Mock
    private lateinit var validTrainerRating: TrainerRating

    @Mock
    private lateinit var trainerRatingRepository: TrainerRatingRepository

    @InjectMocks
    private lateinit var trainerRatingSeekService: TrainerRatingSeekService

    @BeforeAll
    fun setUp() {
        whenever(trainerRatingRepository.findByTrainerNameAndTierAndRemovedOnIsNull(validTrainerName, validTier)).thenReturn(validTrainerRating)

        whenever(trainerRatingRepository.findByTrainerNameAndTierAndRemovedOnIsNull(invalidTrainerName, invalidTier)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid trainer name and tier - when findByTrainerAndTier - then return valid trainerRating`() {
            val expectedTrainerRating: TrainerRating = validTrainerRating

            val actualTrainerRating: TrainerRating = trainerRatingSeekService.findByTrainerAndTier(validTrainerName, validTier)

            actualTrainerRating shouldEqual expectedTrainerRating
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid trainer name and tier - when findByTrainerAndTier - then throw EntityNotFoundException`() {
            invoking { trainerRatingSeekService.findByTrainerAndTier(invalidTrainerName, invalidTier) } shouldThrow EntityNotFoundException::class
        }

    }

}
