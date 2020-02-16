package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.Pokemon
import com.immanuelqrw.ikanobuntai.api.repository.PokemonRepository
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
internal class PokemonSeekServiceTest {

    private val validNumber: String = C.VALID_POKEMON_NUMBER
    private val invalidNumber: String = C.INVALID_POKEMON_NUMBER

    private val validName: String = C.VALID_POKEMON_NAME
    private val invalidName: String = C.INVALID_POKEMON_NAME

    private val validForm: String? = C.VALID_POKEMON_FORM
    private val invalidForm: String? = C.INVALID_POKEMON_FORM

    private val validIsLegendary: Boolean = C.VALID_IS_LEGENDARY
    private val invalidIsLegendary: Boolean = C.INVALID_IS_LEGENDARY

    private val validIsMythical: Boolean = C.VALID_IS_MYTHICAL
    private val invalidIsMythical: Boolean = C.INVALID_IS_MYTHICAL

    private val validIsMega: Boolean = C.VALID_IS_MEGA
    private val invalidIsMega: Boolean = C.INVALID_IS_MEGA

    @Mock
    private lateinit var validPokemon: Pokemon

    @Mock
    private lateinit var pokemonRepository: PokemonRepository

    @InjectMocks
    private lateinit var pokemonSeekService: PokemonSeekService

    @BeforeAll
    fun setUp() {
        whenever(pokemonRepository.findByNameAndFormAndRemovedOnIsNull(validName, validForm)).thenReturn(validPokemon)

        whenever(pokemonRepository.findByNameAndFormAndRemovedOnIsNull(invalidName, invalidForm)).thenReturn(null)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid name and form - when findByNameAndForm - then return valid pokemon`() {
            val expectedPokemon: Pokemon = validPokemon

            val actualPokemon: Pokemon = pokemonSeekService.findByNameAndForm(validName, validForm)

            actualPokemon shouldEqual expectedPokemon
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid name and form - when findByNameAndForm - then throw EntityNotFoundException`() {
            invoking { pokemonSeekService.findByNameAndForm(invalidName, invalidForm) } shouldThrow EntityNotFoundException::class
        }

    }

}
