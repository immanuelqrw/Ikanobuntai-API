package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.Format
import com.immanuelqrw.ikanobuntai.api.entity.League
import com.immanuelqrw.ikanobuntai.api.entity.LeagueFormat
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LeagueFormatSearchService : SearchService<LeagueFormat>()
