package data.data.repository

import data.data.source.UserLocalDataSource
import data.data.source.UserServerDataSource
import data.domain.model.local.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeEpisodesModel
import data.domain.model.server.AnimeFullModel
import data.domain.model.server.AnimeResponseModel
import data.domain.model.server.CharactersResponseModel
import data.domain.model.server.EpisodeDetailResponseModel
import data.domain.model.server.StreamingResponseModel
import data.domain.repository.AnimeRepository
import data.mapper.animeLocalMapper.toDomain
import data.mapper.animeLocalMapper.toEntity
import data.mapper.animeServerMapper.toModel
import data.presentation.screens.components.AnimeStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AnimeRepositoryImpl @Inject constructor(
    private val api: UserServerDataSource,
    private val local: UserLocalDataSource
) : AnimeRepository {

    // ---------------- SERVER ----------------

    override suspend fun getAllAnimeList(page: Int,name: String): Result<AnimeResponseModel> =
        runCatching {
            api.getAllAnime(page,name).toModel()
        }

    override suspend fun getAnimeFullInfo(id: Int): Result<AnimeFullModel> =
        runCatching {
            api.getAnimeFullInfo(id).toModel()
        }

    override suspend fun getAnimeInfo(id: Int): Result<AnimeDetailResponseModel> =
        runCatching {
            api.getAnimeInfo(id).toModel()
        }

    override suspend fun getAnimeCharacters(id: Int): Result<CharactersResponseModel> =
        runCatching {
            api.getAnimeCharacters(id).toModel()
        }

    override suspend fun getAnimeEpisodes(id: Int, page: Int): Result<AnimeEpisodesModel> =
        runCatching {
            api.getAnimeEpisodes(id, page).toModel()
        }

    override suspend fun getAnimeEpisodeDetail(id: Int, episodeNum: Int): Result<EpisodeDetailResponseModel> =
        runCatching {
            api.getAnimeEpisodeDetail(id, episodeNum).toModel()
        }

    override suspend fun getAnimeStreamingLink(id: Int): Result<StreamingResponseModel> =
       runCatching {
           api.getAnimeStreamingLink(id).toModel()
       }


    // ---------------- LOCAL ----------------

    override fun getAllAnime(query: String): Flow<List<FavoriteAnimeModel>> =
        local.searchAnime(query).map { list->
            list.map { it.toDomain() }
        }


    override suspend fun insertAnime(anime: FavoriteAnimeModel): Unit =
            local.insertAnime(anime.toEntity())


    override suspend fun deleteAnime(id: Int): Unit =
            local.deleteAnime(id)


    override suspend fun isFavorite(id: Int): Boolean =
            local.isFavorite(id)


    override fun getAnimeByStatus(status: AnimeStatus): Flow<List<FavoriteAnimeModel>> =
        local.getAnimeByStatus(status).map { list ->
            list.map { it.toDomain() }
        }

    override fun searchAnime(query: String): Flow<List<FavoriteAnimeModel>> =
        local.searchAnime(query).map { list->
            list.map { it.toDomain() }
        }




}