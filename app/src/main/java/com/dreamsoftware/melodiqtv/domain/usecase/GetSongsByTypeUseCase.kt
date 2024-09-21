package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.model.LanguageEnum
import com.dreamsoftware.melodiqtv.domain.model.SortTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.VideoLengthEnum
import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongGenreEnum
import com.dreamsoftware.melodiqtv.domain.model.SongMoodEnum
import com.dreamsoftware.melodiqtv.domain.model.SongTypeEnum

class GetSongsByTypeUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val songsRepository: ISongRepository
) : FudgeTvUseCaseWithParams<GetSongsByTypeUseCase.Params, List<SongBO>>() {

    override suspend fun onExecuted(params: Params): List<SongBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return songsRepository.getSongs(
            data = params.toTrainingFilterData(),
            includePremium = hasActiveSubscription
        ).toList()
    }

    private fun Params.toTrainingFilterData() = SongFilterDataBO(
        type = type,
        language = language,
        genre = genre,
        mood = mood,
        videoLength = videoLength,
        sortType = sortType,
        artist = artist
    )

    data class Params(
        val type: SongTypeEnum,
        val language: LanguageEnum,
        val genre: SongGenreEnum,
        val mood: SongMoodEnum,
        val videoLength: VideoLengthEnum,
        val sortType: SortTypeEnum,
        val artist: String
    )
}