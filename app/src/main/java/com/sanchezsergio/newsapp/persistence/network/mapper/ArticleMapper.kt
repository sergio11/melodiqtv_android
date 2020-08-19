package com.sanchezsergio.newsapp.persistence.network.mapper

import com.sanchezsergio.newsapp.domain.models.Article
import com.sanchezsergio.newsapp.persistence.network.dto.ArticleDTO

object ArticleMapper {

    fun dtoToModel(dto: ArticleDTO) =
            Article(
                    author = dto.author,
                    title = dto.title,
                    description = dto.description,
                    url = dto.url,
                    urlToImage = dto.urlToImage,
                    publishedAt = dto.publishedAt,
                    content = dto.content,
                    source = ArticleSourceMapper.dtoToModel(dto.source)
            )

    fun dtoToModel(dtoList: List<ArticleDTO>) =
            dtoList.map { dtoToModel(it) }.toList()

}