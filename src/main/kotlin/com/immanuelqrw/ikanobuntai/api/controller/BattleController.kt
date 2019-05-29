package com.immanuelqrw.ikanobuntai.api.controller

import com.immanuelqrw.core.api.controller.BaseUniqueController
import com.immanuelqrw.ikanobuntai.api.dto.PokemonBattle
import com.immanuelqrw.ikanobuntai.api.entity.Battle
import com.immanuelqrw.ikanobuntai.api.service.BattleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/battle")
class BattleController : BaseUniqueController<Battle>() {

    @Autowired
    private lateinit var battleService: BattleService

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody entity: PokemonBattle): Battle {
        return battleService.create(entity)
    }

}
