package DataBase.example.data.domain.repository

import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import DataBase.example.data.data.server.DTO.AnimeCharactersResponse
import DataBase.example.data.data.server.DTO.AnimeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodesResponse
import DataBase.example.data.data.server.DTO.AnimeFullResponse
import retrofit2.Response
interface AnimeRepository {
     suspend fun getAnimeFullInfo(id: Int): Response<AnimeFullResponse>
     suspend fun getAnimeInfo(id: Int): Response<AnimeDetailResponse>
     suspend fun getAnimeCharacters(id: Int): Response<AnimeCharactersResponse>
     suspend fun  getAnimeEpisodes(id: Int, page: Int): Response<AnimeEpisodesResponse>
     suspend fun  getAnimeEpisodeDetail(id: Int, episodeNum: Int): Response<AnimeEpisodeDetailResponse>

     suspend fun getAllAnime(): List<FavoriteAnimeEntity>
     suspend fun insertAnime(anime: FavoriteAnimeEntity)
     suspend fun deleteAnime(anime: FavoriteAnimeEntity)
     suspend fun isFavorite(anime: FavoriteAnimeEntity): Boolean

}


