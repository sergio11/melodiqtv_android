package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.repository.ICategoryRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetCategoriesUseCase(
    private val categoryRepository: ICategoryRepository
) : FudgeTvUseCase<List<CategoryBO>>() {
    override suspend fun onExecuted(): List<CategoryBO> =
        categoryRepository.getCategories()
}