package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.repository.LeagueRepository
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
internal class LeagueSeekServiceTest {

    private val validName: String = C.VALID_LEAGUE_NAME
    private val invalidName: String = C.INVALID_LEAGUE_NAME

    @Mock
    private lateinit var validLeague: League

    @Mock
    private lateinit var leagueRepository: LeagueRepository

    @InjectMocks
    private lateinit var leagueSeekService: LeagueSeekService

    @BeforeAll
    fun setUp() {
        whenever(leagueRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validLeague)

        whenever(leagueRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid league`() {
            val expectedLeague: League = validLeague

            val actualLeague: League = leagueSeekService.findByName(validName)

            actualLeague shouldEqual expectedLeague
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { leagueSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
