package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import com.immanuelqrw.ikanobuntai.api.repository.TrainerTeamRepository
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
internal class TrainerTeamSeekServiceTest {

    private val validName: String = C.VALID_TRAINER_TEAM_NAME
    private val invalidName: String = C.INVALID_TRAINER_TEAM_NAME

    @Mock
    private lateinit var validTrainerTeam: TrainerTeam

    @Mock
    private lateinit var trainerTeamRepository: TrainerTeamRepository

    @InjectMocks
    private lateinit var trainerTeamSeekService: TrainerTeamSeekService

    @BeforeAll
    fun setUp() {
        whenever(trainerTeamRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validTrainerTeam)

        whenever(trainerTeamRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid trainerTeam`() {
            val expectedTrainerTeam: TrainerTeam = validTrainerTeam

            val actualTrainerTeam: TrainerTeam = trainerTeamSeekService.findByName(validName)

            actualTrainerTeam shouldEqual expectedTrainerTeam
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { trainerTeamSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
