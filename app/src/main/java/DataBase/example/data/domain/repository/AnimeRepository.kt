package DataBase.example.data.domain.repository

import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import DataBase.example.data.data.server.DTO.AnimeCharactersResponse
import DataBase.example.data.data.server.DTO.AnimeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodesResponse
import DataBase.example.data.data.server.DTO.AnimeFullResponse
import DataBase.example.data.domain.model.local.FavoriteAnimeModel
import DataBase.example.data.domain.model.server.AnimeDetailModel
import DataBase.example.data.domain.model.server.AnimeDetailResponseModel
import DataBase.example.data.domain.model.server.AnimeEpisodesModel
import DataBase.example.data.domain.model.server.AnimeFullModel
import DataBase.example.data.domain.model.server.CharacterItemModel
import DataBase.example.data.domain.model.server.CharactersResponseModel
import DataBase.example.data.domain.model.server.EpisodeDetailModel
import DataBase.example.data.domain.model.server.EpisodeDetailResponseModel
import DataBase.example.data.domain.model.server.EpisodeModel
import retrofit2.Response



interface AnimeRepository {
     suspend fun getAnimeFullInfo(id: Int): Result<AnimeFullModel>
     suspend fun getAnimeInfo(id: Int): Result<AnimeDetailResponseModel>
     suspend fun getAnimeCharacters(id: Int): Result<CharactersResponseModel>
     suspend fun  getAnimeEpisodes(id: Int, page: Int): Result<AnimeEpisodesModel>
     suspend fun  getAnimeEpisodeDetail(id: Int, episodeNum: Int): Result<EpisodeDetailResponseModel>


     suspend fun getAllAnime(): Result<List<FavoriteAnimeModel>>
     suspend fun insertAnime(anime: FavoriteAnimeModel): Result<Unit>
     suspend fun deleteAnime(anime: FavoriteAnimeModel): Result<Unit>
     suspend fun isFavorite(anime: FavoriteAnimeModel): Result<Boolean>

}


