package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Move
import com.immanuelqrw.ikanobuntai.api.repository.MoveRepository
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
internal class MoveSeekServiceTest {

    private val validName: String = C.VALID_MOVE_NAME
    private val invalidName: String = C.INVALID_MOVE_NAME

    @Mock
    private lateinit var validMove: Move

    @Mock
    private lateinit var moveRepository: MoveRepository

    @InjectMocks
    private lateinit var moveSeekService: MoveSeekService

    @BeforeAll
    fun setUp() {
        whenever(moveRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validMove)

        whenever(moveRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid move`() {
            val expectedMove: Move = validMove

            val actualMove: Move = moveSeekService.findByName(validName)

            actualMove shouldEqual expectedMove
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { moveSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
