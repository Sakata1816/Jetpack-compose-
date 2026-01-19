package experimnetal.projects

import retrofit2.http.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MyApi {

    @GET("posts") // обязательно без начального /
    suspend fun getPosts(): List<User>

    @POST("login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): User

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): List<User>
}


object NetworkModule {

    val api: MyApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/") // из Swagger
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)
}
