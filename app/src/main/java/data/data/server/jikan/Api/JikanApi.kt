package data.data.server.jikan.Api

import data.data.server.jikan.DTO.AnimeCharactersResponse
import data.data.server.jikan.DTO.AnimeDetailResponse
import data.data.server.jikan.DTO.AnimeEpisodeDetailResponse
import data.data.server.jikan.DTO.AnimeEpisodesResponse
import data.data.server.jikan.DTO.AnimeFullResponse
import data.data.server.jikan.DTO.AnimeResponse
import data.data.server.jikan.DTO.StreamingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JikanApi {

    @GET("anime/{id}/streaming")
    suspend fun getAnimeStreamingLink(@Query("id")animeId: Int): StreamingResponse

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
