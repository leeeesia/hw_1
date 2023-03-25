package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val views: Int = 1500,
    var likes: Int = 1200999,
    var likedByMe: Boolean = false,
    var share: Int  = 0
)