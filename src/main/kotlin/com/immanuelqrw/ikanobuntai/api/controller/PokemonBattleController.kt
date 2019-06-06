package com.immanuelqrw.ikanobuntai.api.controller

import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.service.PokemonBattleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemonBattle")
class PokemonBattleController {

    @Autowired
    private lateinit var pokemonBattleService: PokemonBattleService

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody entity: PokemonBattle): Battle {
        return pokemonBattleService.create(entity)
    }

}
