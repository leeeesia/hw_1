package ru.netology.nmedia.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.adapter.PostListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val viewModel: PostViewModel by viewModels()

        val adapter = PostAdapter(
            object : PostListener{
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onEdit(post: Post) {
                    activityMainBinding.editGroup.visibility = View.VISIBLE
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)
                }

            }
        )

        viewModel.edited.observe(this){
            if (it.id == 0L){
                return@observe
            }

            activityMainBinding.content.requestFocus()
            activityMainBinding.content.setText(it.content)
        }

        activityMainBinding.save.setOnClickListener {
            with(activityMainBinding.content) {
                val content = text.toString()
                if (content.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.empty_post_error,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(content)
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                activityMainBinding.editGroup.visibility = View.GONE
            }
        }
        activityMainBinding.editClose.setOnClickListener {
            with(activityMainBinding.content) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                activityMainBinding.editGroup.visibility = View.GONE
            }
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        activityMainBinding.list.adapter = adapter
    }
}