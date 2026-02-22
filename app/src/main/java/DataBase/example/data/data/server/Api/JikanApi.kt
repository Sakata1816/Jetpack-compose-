package DataBase.example.data.data.server.Api

import DataBase.example.data.data.server.DTO.AnimeCharactersResponse
import DataBase.example.data.data.server.DTO.AnimeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodeDetailResponse
import DataBase.example.data.data.server.DTO.AnimeEpisodesResponse
import DataBase.example.data.data.server.DTO.AnimeFullResponse
import DataBase.example.data.data.server.DTO.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JikanApi {

    @GET("/anime")
    suspend fun getAllAnime(): AnimeResponse


    @GET("/anime/{id}/full")
    suspend fun getAnimeFullInfo(@Path("id") animeId: Int): AnimeFullResponse


    @GET("/anime/{id}")
    suspend fun getAnimeInfo(@Path("id") animeId: Int): AnimeDetailResponse


    @GET("/anime/{id}/characters")
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
