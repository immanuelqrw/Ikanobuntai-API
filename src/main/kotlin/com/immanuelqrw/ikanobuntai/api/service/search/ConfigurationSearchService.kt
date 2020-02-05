package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.Configuration
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ConfigurationSearchService : SearchService<Configuration>()
