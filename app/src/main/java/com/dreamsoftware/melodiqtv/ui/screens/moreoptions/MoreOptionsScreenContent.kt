package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.ui.utils.formatTimeAndType
import com.dreamsoftware.fudge.component.FudgeTvBackRowSchema
import com.dreamsoftware.fudge.component.FudgeTvCardDetails
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvMoreOptionsButton
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@Composable
internal fun MoreOptionsScreenContent(
    state: MoreOptionsUiState,
    actionListener: MoreOptionsScreenActionListener
) {
    with(state) {
        FudgeTvScreenContent(
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ConstraintLayout {
                    val (
                        songDetails,
                        backRowSchema,
                        startButton,
                        favoritesButton,
                        moreInfoButton,
                        viewInstructorButton,
                        shareButton
                    ) = createRefs()

                    FudgeTvCardDetails(
                        modifier = Modifier.width(268.dp).constrainAs(songDetails) {},
                        title = song?.title.orEmpty(),
                        subtitle = song.formatTimeAndType(),
                        description = song?.description.orEmpty(),
                        imageUrl = song?.imageUrl.orEmpty()
                    )
                    FudgeTvBackRowSchema(
                        modifier = Modifier.constrainAs(backRowSchema) {
                            top.linkTo(songDetails.bottom, margin = 50.dp)
                        },
                        onClickBack = actionListener::onBackPressed
                    )
                    FudgeTvFocusRequester { focusRequester ->
                        FudgeTvMoreOptionsButton(
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .constrainAs(startButton) {
                                    top.linkTo(songDetails.top)
                                    start.linkTo(songDetails.end, margin = 164.dp)
                                },
                            textRes = R.string.more_options_play_song_video_clip_button_text,
                            icon = R.drawable.ic_rounded_play,
                            onClick = actionListener::onPlaySongVideoClip
                        )
                    }

                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(favoritesButton) {
                            top.linkTo(startButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = if (isFavorite) {
                            R.string.more_options_remove_from_favorites_button_text
                        } else {
                            R.string.more_options_add_to_favorites_button_text
                        },
                        icon = if (isFavorite) {
                            R.drawable.favorite
                        } else {
                            R.drawable.ic_outline_favorite
                        },
                        onClick = actionListener::onFavouriteClicked
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(moreInfoButton) {
                            top.linkTo(favoritesButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.more_options_play_song_button_text,
                        icon = R.drawable.ic_play_song,
                        onClick = actionListener::onPlaySong
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewInstructorButton) {
                            top.linkTo(moreInfoButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.more_options_view_artist_detail_button_text,
                        icon = R.drawable.ic_music_artist,
                        onClick = actionListener::onOpenArtistDetail
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(shareButton) {
                            top.linkTo(viewInstructorButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.more_options_share_button_text,
                        icon = R.drawable.ic_share
                    )
                }
            }
        }
    }
}