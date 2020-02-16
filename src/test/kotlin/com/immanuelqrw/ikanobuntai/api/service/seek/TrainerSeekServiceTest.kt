package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.repository.TrainerRepository
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
internal class TrainerSeekServiceTest {

    private val validName: String = C.VALID_TRAINER_NAME
    private val invalidName: String = C.INVALID_TRAINER_NAME

    @Mock
    private lateinit var validTrainer: Trainer

    @Mock
    private lateinit var trainerRepository: TrainerRepository

    @InjectMocks
    private lateinit var trainerSeekService: TrainerSeekService

    @BeforeAll
    fun setUp() {
        whenever(trainerRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validTrainer)

        whenever(trainerRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid trainer`() {
            val expectedTrainer: Trainer = validTrainer

            val actualTrainer: Trainer = trainerSeekService.findByName(validName)

            actualTrainer shouldEqual expectedTrainer
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { trainerSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
