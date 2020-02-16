package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.repository.ConfigurationRepository
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
internal class ConfigurationSeekServiceTest {

    private val validLeagueName: String = C.VALID_LEAGUE_NAME
    private val invalidLeagueName: String = C.INVALID_LEAGUE_NAME

    private val validFormat: Format = C.VALID_FORMAT
    private val invalidFormat: Format = C.INVALID_FORMAT

    @Mock
    private lateinit var validConfiguration: Configuration

    @Mock
    private lateinit var configurationRepository: ConfigurationRepository

    @InjectMocks
    private lateinit var configurationSeekService: ConfigurationSeekService

    @BeforeAll
    fun setUp() {
        whenever(configurationRepository.findByLeagueFormatLeagueNameAndLeagueFormatFormatAndRemovedOnIsNull(validLeagueName, validFormat)).thenReturn(validConfiguration)

        whenever(configurationRepository.findByLeagueFormatLeagueNameAndLeagueFormatFormatAndRemovedOnIsNull(invalidLeagueName, invalidFormat)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid league name and format - when findByLeagueAndFormat - then return valid configuration`() {
            val expectedConfiguration: Configuration = validConfiguration

            val actualConfiguration: Configuration = configurationSeekService.findByLeagueAndFormat(validLeagueName, validFormat)

            actualConfiguration shouldEqual expectedConfiguration
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid league name and format - when findByLeagueAndFormat - then throw EntityNotFoundException`() {
            invoking { configurationSeekService.findByLeagueAndFormat(invalidLeagueName, invalidFormat) } shouldThrow EntityNotFoundException::class
        }

    }

}
