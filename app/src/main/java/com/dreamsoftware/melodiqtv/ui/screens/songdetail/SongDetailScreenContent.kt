package com.dreamsoftware.melodiqtv.ui.screens.songdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.fudge.component.FudgeTvRoundedGradientImage
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.melodiqtv.ui.screens.songdetail.components.SongEntityDetails

@Composable
internal fun SongDetailScreenContent(
    state: SongDetailUiState,
    actionListener: SongDetailScreenActionListener
) {
    FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(contentAlignment = Alignment.BottomStart) {
                FudgeTvRoundedGradientImage(imageUrl = state.imageUrl)
                SongEntityDetails(
                    state = state,
                    onPlaySongVideoClip = actionListener::onPlaySongVideoClip,
                    onMoreInfoClicked = actionListener::onOpenMoreInfo,
                    onSongFavoriteClicked = actionListener::onSongFavoriteClicked
                )
            }
        }
    }
}