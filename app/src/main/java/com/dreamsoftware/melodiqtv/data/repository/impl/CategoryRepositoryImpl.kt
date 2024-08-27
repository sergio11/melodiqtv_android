package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.ICategoryRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchCategoryByIdRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.domain.exception.FetchCategoriesException
import com.dreamsoftware.melodiqtv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.melodiqtv.domain.model.CategoryBO
import com.dreamsoftware.melodiqtv.domain.repository.ICategoryRepository
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class CategoryRepositoryImpl(
    private val categoryRemoteDataSource: ICategoryRemoteDataSource,
    private val categoryMapper: IOneSideMapper<CategoryDTO, CategoryBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ICategoryRepository {

    @Throws(FetchCategoriesException::class)
    override suspend fun getCategories(): List<CategoryBO> = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategories()
                .let(categoryMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchCategoriesRemoteException) {
            throw FetchCategoriesException("An error occurred when fetching categories", ex)
        }
    }

    @Throws(FetchCategoryByIdException::class)
    override suspend fun getCategoryById(id: String): CategoryBO = safeExecute {
        try {
            categoryRemoteDataSource
                .getCategoryById(id)
                .let(categoryMapper::mapInToOut)
        } catch (ex: FetchCategoryByIdRemoteException) {
            throw FetchCategoryByIdException("An error occurred when fetching category by $id", ex)
        }
    }
}