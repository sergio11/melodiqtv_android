package com.dreamsoftware.melodiqtv.ui.screens.artistdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.fudge.component.FudgeTvBackRowSchema
import com.dreamsoftware.fudge.component.FudgeTvCardDetails
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
internal fun ArtistDetailScreenContent(
    uiState: ArtistDetailUiState,
    actionListener: ArtistDetailActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            if (isLoading) {
                FudgeTvLoadingState(modifier = Modifier.fillMaxSize())
            } else if (artistDetail == null) {
                FudgeTvNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.instructor_detail_not_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.instructor_detail_title,
                        textBold = true
                    )
                    with(artistDetail) {
                        FudgeTvCardDetails(
                            modifier = Modifier.width(400.dp),
                            title = name,
                            description = description,
                            imageUrl = imageUrl
                        )
                    }
                    FudgeTvBackRowSchema(
                        onClickBack = actionListener::onBackPressed
                    )
                }
            }
        }
    }
}