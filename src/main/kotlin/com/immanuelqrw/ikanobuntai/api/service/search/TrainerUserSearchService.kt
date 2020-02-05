package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.TrainerUser
import org.springframework.stereotype.Service

@Service
class TrainerUserSearchService : SearchService<TrainerUser>()
