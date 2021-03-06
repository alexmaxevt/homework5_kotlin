package ru.evtukhov.android.homework5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.evtukhov.android.homework5.R
import ru.evtukhov.android.homework5.posts.Post
import ru.evtukhov.android.homework5.posts.PostType

const val TYPE_POST: Int = 1
const val TYPE_REPOST: Int = 2
const val TYPE_REPLAY: Int = 3
const val TYPE_VIDEO: Int = 4
const val TYPE_EVENT: Int = 5
const val TYPE_ADVERTISING: Int = 6

fun viewTypePost (viewType: Int): PostType {
    return when (viewType) {
        TYPE_POST -> PostType.POST
        TYPE_REPOST -> PostType.REPOST
        TYPE_REPLAY -> PostType.REPLAY
        TYPE_VIDEO -> PostType.VIDEO
        TYPE_EVENT -> PostType.EVENT
        TYPE_ADVERTISING -> PostType.ADVERTISING
        else -> TODO("unknown view type")
    }
}

class PostAdapter (val posts: List<Post>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewTypePost(viewType)) {
            PostType.POST -> PostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
            )
            PostType.REPLAY -> ReplayViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
            )
            PostType.REPOST -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
            )
            PostType.VIDEO -> VideoViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_video_card, parent, false)
            )
            PostType.ADVERTISING -> AdvertisingViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_advertising_card, parent, false)
            )
            PostType.EVENT -> EventViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_event_card, parent, false)
            )
        }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = posts[holder.adapterPosition]
        when(post.postType) {
            PostType.POST -> with(holder as PostViewHolder) {
                join(posts[position])
            }
            PostType.REPLAY -> with(holder as ReplayViewHolder) {
                join(posts[position])
            }
            PostType.REPOST -> with(holder as RepostViewHolder) {
                join(posts[position])
            }
            PostType.VIDEO -> with(holder as VideoViewHolder) {
                join(posts[position])
            }
            PostType.ADVERTISING -> with(holder as AdvertisingViewHolder) {
                join(posts[position])
            }
            PostType.EVENT -> with(holder as EventViewHolder) {
                join(posts[position])
            }
        }
    }

    override fun getItemId(position: Int):Long {
        return posts[position].id
    }

    override fun getItemViewType(position: Int): Int {
        return when (posts[position].postType) {
            PostType.POST -> TYPE_POST
            PostType.REPOST -> TYPE_REPOST
            PostType.REPLAY -> TYPE_REPLAY
            PostType.VIDEO -> TYPE_VIDEO
            PostType.EVENT -> TYPE_EVENT
            PostType.ADVERTISING -> TYPE_ADVERTISING
        }
    }

    fun removeItem(position: Int) {
        posts.drop(position)
    }
}