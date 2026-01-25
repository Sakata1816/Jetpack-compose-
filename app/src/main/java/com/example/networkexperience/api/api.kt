package com.example.networkexperience.api

import com.example.networkexperience.data.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


//интерфейсы для запросов к серверу
//нужно делить на множество интерфейсов по логике запроса
interface UserApi{
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts/{userId}")
    suspend fun getPosts(@Path("userId") userId: Int): List<Post>

    @GET("/posts/{userId}/comments")
    suspend fun getPostsComments(@Path("userId") userId: Int): List<Comment>

    @POST("/posts")
    suspend fun postPosts(@Body post: Post): Post

    @PUT("/posts/{postId}")
    suspend fun putPosts(@Path("postId") postId: Int, @Body post: Post): Post

    @PATCH("/posts/{postId}")
    suspend fun patchPosts(@Path("postId") postId: Int, @Body post: Post): Post

    @DELETE("/posts/{postId}")
    suspend fun deletePosts(@Path("postId") postId: Int): List<Post>
}

interface CommentApi{
    @GET("/comments")
    suspend fun getComments(@Query("postId") postId: Int): List<Comment>
}






