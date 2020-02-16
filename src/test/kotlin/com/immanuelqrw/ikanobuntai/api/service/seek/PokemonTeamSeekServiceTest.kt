package com.immanuelqrw.ikanobuntai.api.service.seek

import com.immanuelqrw.ikanobuntai.api.entity.PokemonTeam
import com.immanuelqrw.ikanobuntai.api.entity.TrainerPokemon
import com.immanuelqrw.ikanobuntai.api.entity.TrainerTeam
import com.immanuelqrw.ikanobuntai.api.repository.PokemonTeamRepository
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
internal class PokemonTeamSeekServiceTest {

    private val validTrainerTeamName: String = C.VALID_TRAINER_TEAM_NAME
    private val invalidTrainerTeamName: String = C.INVALID_TRAINER_TEAM_NAME

    private val validTrainerName: String = C.VALID_TRAINER_NAME
    private val invalidTrainerName: String = C.INVALID_TRAINER_NAME

    private val noPokemonTeams: List<PokemonTeam> = emptyList()

    private val validPokemonTeam: PokemonTeam = EC.VALID_POKEMON_TEAM
    private val validPokemonTeams: List<PokemonTeam> = listOf(validPokemonTeam)

    private val noTrainerTeams: List<TrainerTeam> = emptyList()

    private val validTrainerTeam: TrainerTeam = EC.VALID_TRAINER_TEAM
    private val validTrainerTeams: List<TrainerTeam> = listOf(validTrainerTeam)

    private val noTrainerPokemons: List<TrainerPokemon> = emptyList()

    private val validTrainerPokemon: TrainerPokemon = EC.VALID_TRAINER_POKEMON
    private val validTrainerPokemons: List<TrainerPokemon> = listOf(validTrainerPokemon)

    @Mock
    private lateinit var pokemonTeamRepository: PokemonTeamRepository

    @InjectMocks
    private lateinit var pokemonTeamSeekService: PokemonTeamSeekService

    @BeforeAll
    fun setUp() {
        whenever(pokemonTeamRepository.findAllByTrainerTeamNameAndRemovedOnIsNull(validTrainerTeamName)).thenReturn(validPokemonTeams)
        whenever(pokemonTeamRepository.findAllByTrainerPokemonTrainerNameAndRemovedOnIsNull(validTrainerName)).thenReturn(validPokemonTeams)

        whenever(pokemonTeamRepository.findAllByTrainerTeamNameAndRemovedOnIsNull(invalidTrainerTeamName)).thenReturn(noPokemonTeams)
        whenever(pokemonTeamRepository.findAllByTrainerPokemonTrainerNameAndRemovedOnIsNull(invalidTrainerName)).thenReturn(noPokemonTeams)
    }

    @Nested
    inner class Success {

        @Test
        fun `given valid trainerTeam name - when findAllTrainerPokemonByTrainerTeam - then return valid trainerPokemons`() {
            val expectedTrainerPokemons: List<TrainerPokemon> = validTrainerPokemons

            val actualTrainerPokemons: List<TrainerPokemon> = pokemonTeamSeekService.findAllTrainerPokemonByTrainerTeam(validTrainerTeamName)

            actualTrainerPokemons shouldEqual expectedTrainerPokemons
        }

        @Test
        fun `given valid trainer name - when findAllTrainerTeamsByTrainer - then return valid trainerTeams`() {
            val expectedTrainerTeams: List<TrainerTeam> = validTrainerTeams

            val actualTrainerTeams: List<TrainerTeam> = pokemonTeamSeekService.findAllTrainerTeamsByTrainer(validTrainerName)

            actualTrainerTeams shouldEqual expectedTrainerTeams
        }

    }

    @Nested
    inner class Failure {

        @Test
        fun `given invalid trainerTeam name - when findAllTrainerPokemonByTrainerTeam - then return no trainerPokemons`() {
            val expectedTrainerPokemons: List<TrainerPokemon> = noTrainerPokemons

            val actualTrainerPokemons: List<TrainerPokemon> = pokemonTeamSeekService.findAllTrainerPokemonByTrainerTeam(invalidTrainerTeamName)

            actualTrainerPokemons shouldEqual expectedTrainerPokemons
        }

        @Test
        fun `given invalid trainer name - when findAllTrainerTeamsByTrainer - then return no trainerTeams`() {
            val expectedTrainerTeams: List<TrainerTeam> = noTrainerTeams

            val actualTrainerTeams: List<TrainerTeam> = pokemonTeamSeekService.findAllTrainerTeamsByTrainer(invalidTrainerName)

            actualTrainerTeams shouldEqual expectedTrainerTeams
        }

    }

}
