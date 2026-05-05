package AnimeJ.data.repository.server

import AnimeJ.data.source.local.UserLocalDataSource
import AnimeJ.data.source.server.UserServerDataSource
import AnimeJ.domain.model.server.AnimeDetailResponseModel
import AnimeJ.domain.model.server.AnimeEpisodesModel
import AnimeJ.domain.model.server.AnimeFullModel
import AnimeJ.domain.model.server.AnimeResponseModel
import AnimeJ.domain.model.server.CharactersResponseModel
import AnimeJ.domain.model.server.EpisodeDetailResponseModel
import AnimeJ.domain.model.server.StreamingResponseModel
import AnimeJ.domain.repository.server.AnimeRepository
import AnimeJ.mapper.animeLocalMapper.toDomain
import AnimeJ.mapper.animeLocalMapper.toEntity
import AnimeJ.mapper.animeServerMapper.toModel
import AnimeJ.presentation.screens.components.AnimeStatus
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



}