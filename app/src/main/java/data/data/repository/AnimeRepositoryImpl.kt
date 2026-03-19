package data.data.repository

import data.data.local.entity.FavoriteAnimeEntity
import data.data.server.Api.JikanApi
import data.data.source.UserLocalDataSource
import data.data.source.UserServerDataSource
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
import data.domain.model.server.StreamingLinkModel
import data.domain.model.server.StreamingResponseModel
import data.domain.repository.AnimeRepository
import data.mapper.animeLocalMapper.toDomain
import data.mapper.animeLocalMapper.toEntity
import data.mapper.animeServerMapper.toModel
import org.w3c.dom.NameList
import javax.inject.Inject


class AnimeRepositoryImpl @Inject constructor(
    private val api: UserServerDataSource,
    private val local: UserLocalDataSource
) : AnimeRepository {

    // ---------------- SERVER ----------------

    override suspend fun getAllAnimeList(page: Int,name: String?): Result<AnimeResponseModel> =
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