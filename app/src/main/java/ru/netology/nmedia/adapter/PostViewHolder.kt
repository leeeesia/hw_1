package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.Format
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClicked: (Post)->Unit,
    private val onShareClicked: (Post)->Unit
): ViewHolder(binding.root) {
    fun bind(post: Post){
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            viewValue.text = Format.value(post.views)
            likeValue.text = Format.value(post.likes)
            shareValue.text = Format.value(post.share)

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.ic_favorite_24)
            } else {
                likeIcon.setImageResource(R.drawable.ic_favorite_border_24)
            }

            likeIcon.setOnClickListener {
                onLikeClicked(post)
            }

            shareIcon.setOnClickListener {
                onShareClicked(post)
            }
        }
    }
}