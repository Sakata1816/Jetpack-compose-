package com.example.networkexperience.Repositories

import com.example.networkexperience.api.CommentApi
import com.example.networkexperience.api.UserApi
import com.example.networkexperience.data.Post
import javax.inject.Inject

//репозитории для каждого интерфейса

class UserRepository @Inject constructor(private val api: UserApi){
    suspend fun getPosts()=api.getPosts()
    suspend fun getPosts(userId:Int)=api.getPosts(userId)
    suspend fun getPostsComments(postId:Int)=api.getPostsComments(postId)
    suspend fun postPosts(post: Post)=api.postPosts(post)
    suspend fun putPosts(postId: Int,post: Post)=api.putPosts(postId,post)
    suspend fun patchPosts(postId: Int,post: Post)=api.patchPosts(postId,post)
    suspend fun deletePosts(postId: Int)=api.deletePosts(postId)

}

class CommentRepository @Inject constructor(private val api: CommentApi){
    suspend fun getComments(postId:Int)=api.getComments(postId)
}