package DataBase.example.data.data.repository

import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import DataBase.example.data.data.server.Api.JikanApi
import DataBase.example.data.data.source.UserLocalDataSource
import DataBase.example.data.data.source.UserServerDataSource
import DataBase.example.data.domain.model.local.FavoriteAnimeModel
import DataBase.example.data.domain.model.server.AnimeDetailModel
import DataBase.example.data.domain.model.server.AnimeDetailResponseModel
import DataBase.example.data.domain.model.server.AnimeEpisodesModel
import DataBase.example.data.domain.model.server.AnimeFullModel
import DataBase.example.data.domain.model.server.CharacterModel
import DataBase.example.data.domain.model.server.EpisodeDetailModel
import DataBase.example.data.domain.model.server.EpisodeModel
import DataBase.example.data.domain.repository.AnimeRepository
import DataBase.example.data.mapper.animeLocalMapper.toDomain
import DataBase.example.data.mapper.animeLocalMapper.toEntity
import DataBase.example.data.mapper.animeServerMapper.toModel
import javax.inject.Inject


class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi,
    private val local: UserLocalDataSource
) : AnimeRepository {

    // ---------------- SERVER ----------------

    override suspend fun getAnimeFullInfo(id: Int): Result<AnimeFullModel> =
        runCatching {
            api.getAnimeFullInfo(id).toModel()
        }

    override suspend fun getAnimeInfo(id: Int): Result<AnimeDetailResponseModel> =
        runCatching {
            api.getAnimeInfo(id).toModel()
        }

    override suspend fun getAnimeCharacters(id: Int): Result<List<CharacterModel>> =
        runCatching {
            api.getAnimeCharacters(id).toModel()
        }

    override suspend fun getAnimeEpisodes(id: Int, page: Int): Result<AnimeEpisodesModel> =
        runCatching {
            api.getAnimeEpisodes(id, page).toModel()
        }

    override suspend fun getAnimeEpisodeDetail(id: Int, episodeNum: Int): Result<EpisodeDetailModel> =
        runCatching {
            api.getAnimeEpisodeDetail(id, episodeNum).toModel()
        }

    // ---------------- LOCAL ----------------

    override suspend fun getAllAnime(): Result<List<FavoriteAnimeModel>> =
        runCatching {
            local.getAllAnime().map { it.toDomain() }
        }

    override suspend fun insertAnime(anime: FavoriteAnimeModel): Result<Unit> =
        runCatching {
            local.insertAnime(anime.toEntity())
        }

    override suspend fun deleteAnime(anime: FavoriteAnimeModel): Result<Unit> =
        runCatching {
            local.deleteAnime(anime.toEntity())
        }

    override suspend fun isFavorite(anime: FavoriteAnimeModel): Result<Boolean> =
        runCatching {
            local.isFavorite(anime.toEntity())
        }
}