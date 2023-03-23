package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 3967,
    var likedByMe: Boolean = false,
    var share: Int  = 0
)