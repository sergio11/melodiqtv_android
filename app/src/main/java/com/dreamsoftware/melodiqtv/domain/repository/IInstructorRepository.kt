package com.dreamsoftware.melodiqtv.domain.repository

import com.dreamsoftware.melodiqtv.domain.exception.FetchInstructorByIdException
import com.dreamsoftware.melodiqtv.domain.exception.FetchInstructorsException
import com.dreamsoftware.melodiqtv.domain.model.InstructorBO

interface IInstructorRepository {

    @Throws(FetchInstructorsException::class)
    suspend fun getInstructors(): List<InstructorBO>

    @Throws(FetchInstructorByIdException::class)
    suspend fun getInstructorById(id: String): InstructorBO
}