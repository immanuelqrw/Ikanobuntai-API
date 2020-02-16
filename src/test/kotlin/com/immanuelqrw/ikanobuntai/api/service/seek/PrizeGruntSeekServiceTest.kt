package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.PrizeGrunt
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.Title
import com.immanuelqrw.ikanobuntai.api.repository.PrizeGruntRepository
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
internal class PrizeGruntSeekServiceTest {

    private val validLeagueName: String = C.VALID_LEAGUE_NAME
    private val invalidLeagueName: String = C.INVALID_LEAGUE_NAME

    private val validTrainerName: String = C.VALID_TRAINER_NAME
    private val invalidTrainerName: String = C.INVALID_TRAINER_NAME

    private val validTier: Tier = C.VALID_TIER
    private val invalidTier: Tier = C.INVALID_TIER

    private val validTitle: Title = C.VALID_TITLE
    private val invalidTitle: Title = C.INVALID_TITLE

    @Mock
    private lateinit var validPrizeGrunt: PrizeGrunt

    private val noPrizeGrunts: List<PrizeGrunt> = emptyList()

    @Mock
    private lateinit var validPrizeGrunts: List<PrizeGrunt>

    @Mock
    private lateinit var prizeGruntRepository: PrizeGruntRepository

    @InjectMocks
    private lateinit var prizeGruntSeekService: PrizeGruntSeekService

    @BeforeAll
    fun setUp() {
        whenever(prizeGruntRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(validLeagueName, validTrainerName)).thenReturn(validPrizeGrunt)
        whenever(prizeGruntRepository.findAllByLeagueNameAndTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(validLeagueName, validTier, validTitle)).thenReturn(validPrizeGrunts)

        whenever(prizeGruntRepository.findByLeagueNameAndTrainerNameAndRemovedOnIsNull(invalidLeagueName, invalidTrainerName)).thenReturn(null)
        whenever(prizeGruntRepository.findAllByLeagueNameAndTierTitleTierAndTierTitleTitleAndRemovedOnIsNull(invalidLeagueName, invalidTier, invalidTitle)).thenReturn(noPrizeGrunts)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid league name and trainer name - when findByLeagueAndTrainer - then return valid prizeGrunt`() {
            val expectedPrizeGrunt: PrizeGrunt = validPrizeGrunt

            val actualPrizeGrunt: PrizeGrunt = prizeGruntSeekService.findByLeagueAndTrainer(validLeagueName, validTrainerName)

            actualPrizeGrunt shouldEqual expectedPrizeGrunt
        }

        @Test
        fun `given valid league name and tier and title - when findAllByLeagueAndTierAndTitle - then return valid prizeGrunts`() {
            val expectedPrizeGrunts: List<PrizeGrunt> = validPrizeGrunts

            val actualPrizeGrunts: List<PrizeGrunt> = prizeGruntSeekService.findAllByLeagueAndTierAndTitle(validLeagueName, validTier, validTitle)

            actualPrizeGrunts shouldEqual expectedPrizeGrunts
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid league name and trainer name - when findByLeagueAndTrainer - then throw EntityNotFoundException`() {
            invoking { prizeGruntSeekService.findByLeagueAndTrainer(invalidLeagueName, invalidTrainerName) } shouldThrow EntityNotFoundException::class
        }

        @Test
        fun `given invalid league name and tier and title - when findAllByLeagueAndTierAndTitle - then return no prizeGrunts`() {
            val expectedPrizeGrunts: List<PrizeGrunt> = noPrizeGrunts

            val actualPrizeGrunts: List<PrizeGrunt> = prizeGruntSeekService.findAllByLeagueAndTierAndTitle(invalidLeagueName, invalidTier, invalidTitle)

            actualPrizeGrunts shouldEqual expectedPrizeGrunts
        }

    }

}
