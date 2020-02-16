package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import com.immanuelqrw.ikanobuntai.api.repository.LeagueFormatRepository
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import com.immanuelqrw.ikanobuntai.api.service.TestConstants as C
import com.immanuelqrw.ikanobuntai.api.service.TestEntityConstants as EC

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LeagueFormatSeekServiceTest {

    private val validLeagueName: String = C.VALID_LEAGUE_NAME
    private val invalidLeagueName: String = C.INVALID_LEAGUE_NAME

    private val validFormat: Format = C.VALID_FORMAT
    private val invalidFormat: Format = C.INVALID_FORMAT

    private val noLeagueFormats: List<LeagueFormat> = emptyList()
    private val validLeagueFormats: List<LeagueFormat> = listOf(EC.VALID_LEAGUE_FORMAT)

    private val noFormats: List<Format> = emptyList()
    private val validFormats: List<Format> = listOf(C.VALID_FORMAT)

    private val noLeagues: List<League> = emptyList()
    private val validLeagues: List<League> = listOf(EC.VALID_LEAGUE)

    @Mock
    private lateinit var leagueFormatRepository: LeagueFormatRepository

    @InjectMocks
    private lateinit var leagueFormatSeekService: LeagueFormatSeekService

    @BeforeAll
    fun setUp() {
        whenever(leagueFormatRepository.findAllByLeagueNameAndRemovedOnIsNull(validLeagueName)).thenReturn(validLeagueFormats)
        whenever(leagueFormatRepository.findAllByFormatAndRemovedOnIsNull(validFormat)).thenReturn(validLeagueFormats)

        whenever(leagueFormatRepository.findAllByLeagueNameAndRemovedOnIsNull(invalidLeagueName)).thenReturn(noLeagueFormats)
        whenever(leagueFormatRepository.findAllByFormatAndRemovedOnIsNull(invalidFormat)).thenReturn(noLeagueFormats)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid league name - when findAllLeagueFormatsByLeague - then return valid leagueFormat`() {
            val expectedLeagueFormats: List<LeagueFormat> = validLeagueFormats

            val actualLeagueFormats: List<LeagueFormat> = leagueFormatSeekService.findAllLeagueFormatsByLeague(validLeagueName)

            actualLeagueFormats shouldEqual expectedLeagueFormats
        }

        @Test
        fun `given valid format - when findAllLeagueFormatsByFormat - then return valid leagueFormat`() {
            val expectedLeagueFormats: List<LeagueFormat> = validLeagueFormats

            val actualLeagueFormats: List<LeagueFormat> = leagueFormatSeekService.findAllLeagueFormatsByFormat(validFormat)

            actualLeagueFormats shouldEqual expectedLeagueFormats
        }

        @Test
        fun `given valid league name - when findAllFormatsByLeague - then return valid formats`() {
            val expectedFormats: List<Format> = validFormats

            val actualFormats: List<Format> = leagueFormatSeekService.findAllFormatsByLeague(validLeagueName)

            actualFormats shouldEqual expectedFormats
        }

        @Test
        fun `given valid format - when findAllLeaguesByFormat - then return valid leagues`() {
            val expectedLeagues: List<League> = validLeagues

            val actualLeagues: List<League> = leagueFormatSeekService.findAllLeaguesByFormat(validFormat)

            actualLeagues shouldEqual expectedLeagues
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid league name - when findAllLeagueFormatsByLeague - then return no leagueFormats`() {
            val expectedLeagueFormats: List<LeagueFormat> = noLeagueFormats

            val actualLeagueFormats: List<LeagueFormat> = leagueFormatSeekService.findAllLeagueFormatsByLeague(invalidLeagueName)

            actualLeagueFormats shouldEqual expectedLeagueFormats
        }

        @Test
        fun `given invalid format - when findAllLeagueFormatsByLeague - then return no leagueFormats`() {
            val expectedLeagueFormats: List<LeagueFormat> = noLeagueFormats

            val actualLeagueFormats: List<LeagueFormat> = leagueFormatSeekService.findAllLeagueFormatsByFormat(invalidFormat)

            actualLeagueFormats shouldEqual expectedLeagueFormats
        }

        @Test
        fun `given invalid league name - when findAllFormatsByLeague - then return no formats`() {
            val expectedFormats: List<Format> = noFormats

            val actualFormats: List<Format> = leagueFormatSeekService.findAllFormatsByLeague(invalidLeagueName)

            actualFormats shouldEqual expectedFormats
        }

        @Test
        fun `given invalid format - when findAllLeaguesByFormat - then return no leagues`() {
            val expectedLeagues: List<League> = noLeagues

            val actualLeagues: List<League> = leagueFormatSeekService.findAllLeaguesByFormat(invalidFormat)

            actualLeagues shouldEqual expectedLeagues
        }

    }

}
