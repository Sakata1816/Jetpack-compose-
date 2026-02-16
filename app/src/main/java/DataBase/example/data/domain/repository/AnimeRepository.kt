package DataBase.example.data.domain.repository

import DataBase.example.data.data.server.DTO.AnimeCharactersResponse
import DataBase.example.data.data.server.DTO.AnimeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodesResponse
import DataBase.example.data.data.server.DTO.AnimeFullResponse
import retrofit2.Response

abstract class AnimeRepository {
    abstract suspend fun getAnimeFullInfo(id: Int): Response<AnimeFullResponse>
    abstract suspend fun getAnimeInfo(id: Int): Response<AnimeDetailResponse>
    abstract suspend fun getAnimeCharacters(id: Int): Response<AnimeCharactersResponse>
    abstract suspend fun  getAnimeEpisodes(id: Int, page: Int): Response<AnimeEpisodesResponse>
    abstract suspend fun  getAnimeEpisodeDetail(id: Int, episodeNum: Int): Response<AnimeEpisodeDetailResponse>

}


