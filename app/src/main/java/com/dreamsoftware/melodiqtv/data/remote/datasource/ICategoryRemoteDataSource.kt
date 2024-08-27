package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.response.CategoryDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchCategoryByIdRemoteException

interface ICategoryRemoteDataSource {

    @Throws(FetchCategoriesRemoteException::class)
    suspend fun getCategories(): List<CategoryDTO>

    @Throws(FetchCategoryByIdRemoteException::class)
    suspend fun getCategoryById(id: String): CategoryDTO
}