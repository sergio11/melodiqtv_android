package com.dreamsoftware.melodiqtv.ui.screens.player.audio

import androidx.annotation.OptIn
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.melodiqtv.ui.utils.EMPTY
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.player.FudgeTvPlayerTitle
import com.dreamsoftware.fudge.component.player.audio.FudgeTvAudioPlayerControlsIcon
import com.dreamsoftware.fudge.component.player.audio.FudgeTvAudioPlayerSeeker
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(UnstableApi::class)
@Composable
internal fun AudioPlayerScreenContent(
    state: AudioPlayerUiState,
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekBackIncrementMs(10)
            .setSeekForwardIncrementMs(10)
            .build()
            .apply {
                playWhenReady = true
                repeatMode = Player.REPEAT_MODE_OFF
            }
    }

    LaunchedEffect(exoPlayer, state) {
        exoPlayer.setMediaItem(
            MediaItem.Builder()
                .setUri(state.audioUrl)
                .build()
        )
        exoPlayer.prepare()
    }
    var contentCurrentPosition by remember { mutableLongStateOf(0L) }
    var isPlaying: Boolean by remember { mutableStateOf(exoPlayer.isPlaying) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            contentCurrentPosition = exoPlayer.currentPosition
            isPlaying = exoPlayer.isPlaying
        }
    }
    val infiniteTransition = rememberInfiniteTransition(label = String.EMPTY)
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = String.EMPTY
    )
    with(state) {
        Column(
            modifier = Modifier
                .focusable()
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(196.dp)
                    .scale(scale)
                    .clip(RoundedCornerShape(16.dp)),
                model = state.imageUrl,
                contentDescription = stringResource(R.string.image_cannot_be_loaded),
                contentScale = ContentScale.Crop,
            )

            FudgeTvPlayerTitle(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                descriptionModifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                title = title,
                description = "$artistName • $category ${ album.takeIf { it.isNotBlank() }?.let { " • $it" } }"
            )

            FudgeTvAudioPlayerSeeker(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .align(alignment = Alignment.CenterHorizontally),
                onSeek = { exoPlayer.seekTo(exoPlayer.duration.times(it).toLong()) },
                contentProgress = contentCurrentPosition.milliseconds,
                contentDuration = exoPlayer.duration.milliseconds
            )

            AudioPlayerControls(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                exoPlayer = exoPlayer,
                isPlaying = isPlaying,
            )
        }
    }

}

@Composable
private fun AudioPlayerControls(
    exoPlayer: ExoPlayer,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    with(exoPlayer) {
        FudgeTvFocusRequester { focusRequester ->
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FudgeTvAudioPlayerControlsIcon(icon = R.drawable.shuffle) {

                }
                FudgeTvAudioPlayerControlsIcon(icon = R.drawable.forrward_back) {
                    seekBack()
                }
                FudgeTvAudioPlayerControlsIcon(
                    modifier = Modifier.focusRequester(focusRequester),
                    icon = if (!isPlaying) R.drawable.play_icon else R.drawable.pause,
                    buttonColor = MaterialTheme.colorScheme.onBackground,
                    size = 56.dp
                ) {
                    with(exoPlayer) {
                        if (isPlaying) {
                            pause()
                        } else {
                            play()
                        }
                    }
                }
                FudgeTvAudioPlayerControlsIcon(icon = R.drawable.forrward) {
                    seekForward()
                }
                FudgeTvAudioPlayerControlsIcon(icon = R.drawable.repeat) {
                    repeatMode = Player.REPEAT_MODE_ALL
                }
            }
        }
    }
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerScreen() {
    FitFlexTVTheme {
        AudioPlayerScreenContent(AudioPlayerUiState())
    }
}
