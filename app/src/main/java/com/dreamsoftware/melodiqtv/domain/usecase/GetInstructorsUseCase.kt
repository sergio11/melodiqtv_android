package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.InstructorBO
import com.dreamsoftware.melodiqtv.domain.repository.IInstructorRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetInstructorsUseCase(
    private val instructorRepository: IInstructorRepository
) : FudgeTvUseCase<List<InstructorBO>>() {
    override suspend fun onExecuted():  List<InstructorBO> =
        instructorRepository.getInstructors()
}