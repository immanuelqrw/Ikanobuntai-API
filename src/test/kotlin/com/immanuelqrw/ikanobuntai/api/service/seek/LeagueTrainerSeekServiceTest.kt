package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import com.immanuelqrw.ikanobuntai.api.repository.LeagueTrainerRepository
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
import com.immanuelqrw.ikanobuntai.api.service.TestEntityConstants as EC

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LeagueTrainerSeekServiceTest {

    private val validLeagueName: String = C.VALID_LEAGUE_NAME
    private val invalidLeagueName: String = C.INVALID_LEAGUE_NAME

    private val validTrainerName: String = C.VALID_TRAINER_NAME
    private val invalidTrainerName: String = C.INVALID_TRAINER_NAME

    private val noLeagueTrainers: List<LeagueTrainer> = emptyList()
    private val validLeagueTrainer: LeagueTrainer = EC.VALID_LEAGUE_TRAINER
    private val validLeagueTrainers: List<LeagueTrainer> = listOf(validLeagueTrainer)

    private val noTrainers: List<Trainer> = emptyList()
    private val validTrainer: Trainer = EC.VALID_TRAINER
    private val validTrainers: List<Trainer> = listOf(validTrainer)

    private val noLeagues: List<League> = emptyList()
    private val validLeague: League = EC.VALID_LEAGUE
    private val validLeagues: List<League> = listOf(validLeague)

    @Mock
    private lateinit var leagueTrainerRepository: LeagueTrainerRepository

    @InjectMocks
    private lateinit var leagueTrainerSeekService: LeagueTrainerSeekService

    @BeforeAll
    fun setUp() {
        whenever(leagueTrainerRepository.findAllByLeagueNameAndRemovedOnIsNull(validLeagueName)).thenReturn(validLeagueTrainers)
        whenever(leagueTrainerRepository.findAllByTrainerNameAndRemovedOnIsNull(validTrainerName)).thenReturn(validLeagueTrainers)
        whenever(leagueTrainerRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(validLeagueName, validTrainerName)).thenReturn(validLeagueTrainer)

        whenever(leagueTrainerRepository.findAllByLeagueNameAndRemovedOnIsNull(invalidLeagueName)).thenReturn(noLeagueTrainers)
        whenever(leagueTrainerRepository.findAllByTrainerNameAndRemovedOnIsNull(invalidTrainerName)).thenReturn(noLeagueTrainers)
        whenever(leagueTrainerRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(invalidLeagueName, invalidTrainerName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid league name - when findAllTrainersByLeague - then return valid trainers`() {
            val expectedTrainers: List<Trainer> = validTrainers

            val actualTrainers: List<Trainer> = leagueTrainerSeekService.findAllTrainersByLeague(validLeagueName)

            actualTrainers shouldEqual expectedTrainers
        }

        @Test
        fun `given valid trainer name - when findAllLeaguesByTrainer - then return valid leagues`() {
            val expectedLeagues: List<League> = validLeagues

            val actualLeagues: List<League> = leagueTrainerSeekService.findAllLeaguesByTrainer(validTrainerName)

            actualLeagues shouldEqual expectedLeagues
        }

        @Test
        fun `given valid league and trainer name - when findTrainerByLeagueAndTrainer - then return valid trainer`() {
            val expectedTrainer: Trainer = validTrainer

            val actualTrainer: Trainer = leagueTrainerSeekService.findTrainerByLeagueAndTrainer(validLeagueName, validTrainerName)

            actualTrainer shouldEqual expectedTrainer
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid league name - when findAllTrainersByLeague - then return no trainers`() {
            val expectedTrainers: List<Trainer> = noTrainers

            val actualTrainers: List<Trainer> = leagueTrainerSeekService.findAllTrainersByLeague(invalidLeagueName)

            actualTrainers shouldEqual expectedTrainers
        }

        @Test
        fun `given invalid trainer name - when findAllLeaguesByTrainer - then return no leagues`() {
            val expectedLeagues: List<League> = noLeagues

            val actualLeagues: List<League> = leagueTrainerSeekService.findAllLeaguesByTrainer(invalidTrainerName)

            actualLeagues shouldEqual expectedLeagues
        }

        @Test
        fun `given invalid league and trainer name - when findTrainerByLeagueAndTrainer - then throw EntityNotFoundException`() {
            invoking { leagueTrainerSeekService.findTrainerByLeagueAndTrainer(invalidLeagueName, invalidTrainerName) } shouldThrow EntityNotFoundException::class
        }

    }

}
