package com.dreamsoftware.melodiqtv.data.remote.mapper

import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.google.firebase.Timestamp

internal class SongRemoteMapper: IOneSideMapper<Map<String, Any?>, SongDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
        const val CATEGORY_KEY = "category"
        const val ALBUM_KEY = "album"
        const val GENRE_KEY = "genre"
        const val MOOD_KEY = "mood"
        const val TYPE_KEY = "type"
        const val ARTIST_KEY = "artist"
        const val IS_PREMIUM_KEY = "isPremium"
        const val RELEASED_DATE_KEY = "releasedDate"
        const val LANGUAGE_KEY = "language"
        const val DURATION_KEY = "duration"
        const val IS_TRENDING_KEY = "isTrending"
        const val RATING_KEY = "rating"
        const val PLAY_COUNT_KEY = "playCount"
        const val LYRICS_KEY = "lyrics"
        const val VIDEO_URL_KEY = "videoUrl"
        const val AUDIO_URL_KEY = "audioUrl"
    }


    override fun mapInToOut(input: Map<String, Any?>): SongDTO = with(input) {
        SongDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            category = get(CATEGORY_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
            album = get(ALBUM_KEY) as String,
            genre = get(GENRE_KEY) as String,
            mood = get(MOOD_KEY) as String,
            type = get(TYPE_KEY) as String,
            artist = get(ARTIST_KEY) as String,
            isPremium = get(IS_PREMIUM_KEY) as Boolean,
            releasedDate = get(RELEASED_DATE_KEY) as Timestamp,
            language = get(LANGUAGE_KEY) as String,
            duration = get(DURATION_KEY) as Long,
            isTrending = get(IS_TRENDING_KEY) as Boolean,
            rating = get(RATING_KEY) as Double,
            playCount = get(PLAY_COUNT_KEY) as Long,
            lyrics = get(LYRICS_KEY) as String,
            audioUrl = get(AUDIO_URL_KEY) as String,
            videoUrl = get(VIDEO_URL_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<SongDTO> =
        input.map(::mapInToOut)
}