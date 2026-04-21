package AnimeJ.data.remote.jikan.Api

import AnimeJ.data.remote.jikan.DTO.AnimeCharactersResponse
import AnimeJ.data.remote.jikan.DTO.AnimeDetailResponse
import AnimeJ.data.remote.jikan.DTO.AnimeEpisodeDetailResponse
import AnimeJ.data.remote.jikan.DTO.AnimeEpisodesResponse
import AnimeJ.data.remote.jikan.DTO.AnimeFullResponse
import AnimeJ.data.remote.jikan.DTO.AnimeResponse
import AnimeJ.data.remote.jikan.DTO.StreamingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JikanApi {

    @GET("anime")
    suspend fun getAllAnime(@Query("page") page: Int,
                            @Query("q")name: String?): AnimeResponse


    @GET("anime/{id}/full")
    suspend fun getAnimeFullInfo(@Path("id") animeId: Int): AnimeFullResponse


    @GET("anime/{id}")
    suspend fun getAnimeInfo(@Path("id") animeId: Int): AnimeDetailResponse


    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id: Int): AnimeCharactersResponse


    @GET("anime/{id}/episodes")
    suspend fun getAnimeEpisodes(
        @Path("id") animeId: Int,
        @Query("page") page: Int = 1
    ): AnimeEpisodesResponse


    @GET("anime/{id}/episodes/{episode}")
    suspend fun getAnimeEpisodeDetail(
        @Path("id") animeId: Int,
        @Path("episode") episodeNum: Int
    ): AnimeEpisodeDetailResponse


}
