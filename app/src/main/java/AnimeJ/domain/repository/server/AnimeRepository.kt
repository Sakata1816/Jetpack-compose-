package AnimeJ.domain.repository.server

import AnimeJ.domain.model.server.AnimeDetailResponseModel
import AnimeJ.domain.model.server.AnimeEpisodesModel
import AnimeJ.domain.model.server.AnimeFullModel
import AnimeJ.domain.model.server.AnimeResponseModel
import AnimeJ.domain.model.server.CharactersResponseModel
import AnimeJ.domain.model.server.EpisodeDetailResponseModel

interface AnimeRepository {
     suspend fun getAllAnimeList(page: Int,name: String): Result<AnimeResponseModel>
     suspend fun getAnimeFullInfo(id: Int): Result<AnimeFullModel>
     suspend fun getAnimeInfo(id: Int): Result<AnimeDetailResponseModel>
     suspend fun getAnimeCharacters(id: Int): Result<CharactersResponseModel>
     suspend fun  getAnimeEpisodes(id: Int, page: Int): Result<AnimeEpisodesModel>
     suspend fun  getAnimeEpisodeDetail(id: Int, episodeNum: Int): Result<EpisodeDetailResponseModel>


}