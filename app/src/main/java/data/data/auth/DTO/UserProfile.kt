package data.data.auth.DTO

data class UserProfile(
    val uid: String="",         // совпадает с FirebaseAuth uid
    val email: String="",
    val username: String = "",
    val avatarUrl: String = "",
)