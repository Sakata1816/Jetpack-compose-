package data.data.source

import data.data.server.Api.JikanApi
import javax.inject.Inject

class UserServerDataSource @Inject constructor(private val api: JikanApi) {

    suspend fun getAllAnime(page: Int,name: String?) = api.getAllAnime(page,name)
    suspend fun getAnimeFullInfo(animeId: Int) = api.getAnimeFullInfo(animeId)
    suspend fun getAnimeInfo(animeId: Int) = api.getAnimeInfo(animeId)
    suspend fun getAnimeCharacters(animeId: Int) = api.getAnimeCharacters(animeId)
    suspend fun getAnimeEpisodes(animeId: Int, page: Int ) = api.getAnimeEpisodes(animeId, page)
    suspend fun getAnimeEpisodeDetail(animeId: Int, episodeNum: Int) = api.getAnimeEpisodeDetail(animeId, episodeNum)


}