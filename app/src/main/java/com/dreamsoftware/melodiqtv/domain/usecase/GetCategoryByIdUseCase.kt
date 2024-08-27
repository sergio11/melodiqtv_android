package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.repository.ICategoryRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetCategoryByIdUseCase(
    private val categoryRepository: ICategoryRepository
) : FudgeTvUseCaseWithParams<GetCategoryByIdUseCase.Params, CategoryBO>() {

    override suspend fun onExecuted(params: Params): CategoryBO =
        categoryRepository.getCategoryById(params.id)

    data class Params(
        val id: String
    )
}