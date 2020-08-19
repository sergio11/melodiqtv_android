package com.sanchezsergio.newsapp.persistence.network.mapper

import com.sanchezsergio.newsapp.domain.models.ArticleSource
import com.sanchezsergio.newsapp.persistence.network.dto.ArticleSourceDTO

object ArticleSourceMapper {

    fun dtoToModel(dto: ArticleSourceDTO) =
            ArticleSource(id = dto.id, name = dto.name)

}