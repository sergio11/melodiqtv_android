package com.dreamsoftware.melodiqtv.data.remote.datasource

import com.dreamsoftware.melodiqtv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchInstructorByIdRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.FetchInstructorsRemoteException

interface IInstructorsRemoteDataSource {

    @Throws(FetchInstructorsRemoteException::class)
    suspend fun getInstructors(): List<InstructorDTO>

    @Throws(FetchInstructorByIdRemoteException::class)
    suspend fun getInstructorById(id: String): InstructorDTO
}