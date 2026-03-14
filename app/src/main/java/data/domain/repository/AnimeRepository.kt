package data.domain.repository

import data.data.local.entity.FavoriteAnimeEntity
import data.data.server.DTO.AnimeCharactersResponse
import data.data.server.DTO.AnimeDetailResponse
import data.data.server.DTO.AnimeEpisodeDetailResponse
import data.data.server.DTO.AnimeEpisodesResponse
import data.data.server.DTO.AnimeFullResponse
import data.domain.model.local.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeEpisodesModel
import data.domain.model.server.AnimeFullModel
import data.domain.model.server.AnimeResponseModel
import data.domain.model.server.CharacterItemModel
import data.domain.model.server.CharactersResponseModel
import data.domain.model.server.EpisodeDetailModel
import data.domain.model.server.EpisodeDetailResponseModel
import data.domain.model.server.EpisodeModel
import retrofit2.Response



interface AnimeRepository {
     suspend fun getAllAnimeList(page: Int,name: String?): Result<AnimeResponseModel>
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


