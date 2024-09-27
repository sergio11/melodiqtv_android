package com.dreamsoftware.melodiqtv.ui.screens.lyrics

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvPreviewContent
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.melodiqtv.R

@Composable
internal fun LyricsDetailScreenContent(
    uiState: LyricsDetailUiState,
    actionListener: LyricsDetailActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            FudgeTvPreviewContent(
                imageUrl = songImageUrl,
                defaultImagePlaceholderRes = R.drawable.main_logo,
                mainTitleRes = R.string.song_lyrics_detail_main_title_text,
                secondaryTitleRes = R.string.song_lyrics_detail_secondary_title_text,
                onBuildContent = {
                    FudgeTvText(
                        titleText = lyrics,
                        type = FudgeTvTextTypeEnum.BODY_SMALL,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                },
                onBuildActions = {
                    FudgeTvButton(
                        type = FudgeTvButtonTypeEnum.LARGE,
                        style = FudgeTvButtonStyleTypeEnum.NORMAL,
                        textRes = R.string.song_lyrics_detail_confirm_button_text,
                        onClick = actionListener::onCompleted
                    )
                }
            )
        }
    }
}