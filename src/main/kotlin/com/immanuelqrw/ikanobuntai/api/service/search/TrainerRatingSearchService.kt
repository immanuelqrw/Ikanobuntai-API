package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.Tier
import com.immanuelqrw.ikanobuntai.api.entity.TrainerRating
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrainerRatingSearchService : SearchService<TrainerRating>()
