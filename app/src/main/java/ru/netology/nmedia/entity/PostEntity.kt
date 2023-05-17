package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val views: Int = 1500,
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val share: Int = 0,
    val videoLink: String? = null,
) {
    fun toDto() = Post(id, author, content, published, views, likes, likedByMe, share, videoLink)

    companion object {
        fun fromDto(post: Post) = with(post) {
            PostEntity(
                id,
                author,
                content,
                published,
                views,
                likes,
                likedByMe,
                share,
                videoLink
            )
        }
    }
}