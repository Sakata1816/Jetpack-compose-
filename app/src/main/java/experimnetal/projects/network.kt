package experimnetal.projects
import kotlinx.coroutines.*

fun main() = runBlocking {
    // логин
 /*   val loginResponse = NetworkModule.api.login(
        LoginRequest("user", "password")
    )
    println("Token: ${loginResponse.token}")

    // получить одного пользователя
    val user = NetworkModule.api.getUser(5)
    println(user)

    // получить список
    val users = NetworkModule.api.getUsers(page = 1)
    println(users)*/
    val posts = NetworkModule.api.getPosts()
    println(posts)
}

