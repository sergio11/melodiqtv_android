package com.dreamsoftware.melodiqtv.ui.screens.home

import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface HomeScreenActionListener: IFudgeTvScreenActionListener {

    fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO)
    fun onCategorySelected(categoryId: String)
}