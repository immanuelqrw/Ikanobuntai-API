package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.ikanobuntai.api.entity.Item
import org.springframework.stereotype.Service

@Service
class ItemSearchService : SearchService<Item>()
