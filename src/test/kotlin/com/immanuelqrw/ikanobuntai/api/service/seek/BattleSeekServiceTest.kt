package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.entity.BattleType
import com.immanuelqrw.ikanobuntai.api.repository.BattleRepository
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

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BattleSeekServiceTest {

    private val validType: BattleType = C.VALID_BATTLE_TYPE
    private val invalidType: BattleType = C.INVALID_BATTLE_TYPE

    private val noBattles: List<Battle> = emptyList()

    @Mock
    private lateinit var validBattles: List<Battle>

    @Mock
    private lateinit var battleRepository: BattleRepository

    @InjectMocks
    private lateinit var battleSeekService: BattleSeekService

    @BeforeAll
    fun setUp() {
        whenever(battleRepository.findAllByTypeAndRemovedOnIsNull(validType)).thenReturn(validBattles)

        whenever(battleRepository.findAllByTypeAndRemovedOnIsNull(invalidType)).thenReturn(noBattles)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid type - when findAllByType - then return valid battles`() {
            val expectedBattles: List<Battle> = validBattles

            val actualBattles: List<Battle> = battleSeekService.findAllByType(validType)

            actualBattles shouldEqual expectedBattles
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid type - when findAllByType - then return no battles`() {
            val expectedBattles: List<Battle> = noBattles

            val actualBattles: List<Battle> = battleSeekService.findAllByType(invalidType)

            actualBattles shouldEqual expectedBattles
        }

    }

}
