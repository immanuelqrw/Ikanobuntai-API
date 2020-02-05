package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueTrainer
import com.immanuelqrw.ikanobuntai.api.entity.Trainer
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LeagueTrainerSearchService : SearchService<LeagueTrainer>()
