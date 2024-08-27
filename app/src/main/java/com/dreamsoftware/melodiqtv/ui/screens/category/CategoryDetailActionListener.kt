package com.dreamsoftware.melodiqtv.ui.screens.category

import com.dreamsoftware.melodiqtv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface CategoryDetailActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO)
}