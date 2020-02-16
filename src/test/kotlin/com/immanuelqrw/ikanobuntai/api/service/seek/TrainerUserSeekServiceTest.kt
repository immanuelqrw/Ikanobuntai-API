package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import com.immanuelqrw.ikanobuntai.api.repository.TrainerUserRepository
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
internal class TrainerUserSeekServiceTest {

    private val validName: String = C.VALID_TRAINER_NAME
    private val invalidName: String = C.INVALID_TRAINER_NAME

    private val validEmail: String = C.VALID_TRAINER_USER_EMAIL
    private val invalidEmail: String = C.INVALID_TRAINER_USER_EMAIL

    @Mock
    private lateinit var validTrainerUser: TrainerUser

    @Mock
    private lateinit var trainerUserRepository: TrainerUserRepository

    @InjectMocks
    private lateinit var trainerUserSeekService: TrainerUserSeekService

    @BeforeAll
    fun setUp() {
        whenever(trainerUserRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validTrainerUser)
        whenever(trainerUserRepository.findByEmailAndRemovedOnIsNull(validEmail)).thenReturn(validTrainerUser)

        whenever(trainerUserRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
        whenever(trainerUserRepository.findByEmailAndRemovedOnIsNull(invalidEmail)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid trainerUser`() {
            val expectedTrainerUser: TrainerUser = validTrainerUser

            val actualTrainerUser: TrainerUser = trainerUserSeekService.findByName(validName)

            actualTrainerUser shouldEqual expectedTrainerUser
        }

        @Test
        fun `given valid email - when findByEmail - then return valid trainerUser`() {
            val expectedTrainerUser: TrainerUser = validTrainerUser

            val actualTrainerUser: TrainerUser = trainerUserSeekService.findByEmail(validEmail)

            actualTrainerUser shouldEqual expectedTrainerUser
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { trainerUserSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

        @Test
        fun `given invalid email - when findByEmail - then throw EntityNotFoundException`() {
            invoking { trainerUserSeekService.findByEmail(invalidEmail) } shouldThrow EntityNotFoundException::class
        }

    }

}
