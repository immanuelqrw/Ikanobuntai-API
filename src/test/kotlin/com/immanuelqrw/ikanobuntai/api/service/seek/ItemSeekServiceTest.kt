package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Item
import com.immanuelqrw.ikanobuntai.api.repository.ItemRepository
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
internal class ItemSeekServiceTest {

    private val validName: String = C.VALID_ITEM_NAME
    private val invalidName: String = C.INVALID_ITEM_NAME

    @Mock
    private lateinit var validItem: Item

    @Mock
    private lateinit var itemRepository: ItemRepository

    @InjectMocks
    private lateinit var itemSeekService: ItemSeekService

    @BeforeAll
    fun setUp() {
        whenever(itemRepository.findByNameAndRemovedOnIsNull(validName)).thenReturn(validItem)

        whenever(itemRepository.findByNameAndRemovedOnIsNull(invalidName)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name - when findByName - then return valid item`() {
            val expectedItem: Item = validItem

            val actualItem: Item = itemSeekService.findByName(validName)

            actualItem shouldEqual expectedItem
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name - when findByName - then throw EntityNotFoundException`() {
            invoking { itemSeekService.findByName(invalidName) } shouldThrow EntityNotFoundException::class
        }

    }

}
