package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.FetchCategoriesException
import com.dreamsoftware.melodiqtv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.melodiqtv.domain.model.CategoryBO

interface ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    suspend fun getCategories(): List<CategoryBO>

    @Throws(FetchCategoryByIdException::class)
    suspend fun getCategoryById(id: String): CategoryBO
}