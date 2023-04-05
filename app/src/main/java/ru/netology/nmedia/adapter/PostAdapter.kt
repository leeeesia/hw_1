package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Format

interface PostListener{
    fun onRemove(post: Post)
    fun onEdit(post: Post)
    fun onLike(post: Post)
    fun onShare(post: Post)
}

class PostAdapter(
    private val listener: PostListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: PostListener
): RecyclerView.ViewHolder(binding.root) {
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
                listener.onLike(post)
            }

            shareIcon.setOnClickListener {
                listener.onShare(post)
            }


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when(item.itemId){
                            R.id.remove ->{
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit ->{
                                listener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }
                    .show()
            }
        }
    }
}


class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem
}