package ru.evtukhov.android.homework5.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import ru.evtukhov.android.homework5.R
import ru.evtukhov.android.homework5.posts.Post

class PostViewHolder (adapter: PostAdapter, view: View): BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            likeBtn.setOnClickListener{
                if(adapterPosition != RecyclerView.NO_POSITION) {
                    val itemPost = adapter.posts[adapterPosition]
                    itemPost.likedByMe = !itemPost.likedByMe
                    if (itemPost.likedByMe) {
                        likeBtn.setImageResource(R.drawable.ic_favorite_active_24dp)
                        itemPost.likedCount += 1
                        likeCount.text = itemPost.likedCount.toString()
                        likeCount.setTextColor(resources.getColor(R.color.colorActive))
                    }
                    else {
                        likeBtn.setImageResource(R.drawable.ic_favorite_inactive_24dp)
                        itemPost.likedCount -= 1
                        likeCount.text = (
                                if (itemPost.likedCount == 0) {
                                    ""
                                }
                                else {
                                    itemPost.likedCount
                                }).toString()
                        likeCount.setTextColor(resources.getColor(R.color.colorInActive))
                    }
                }
            }
            commentsBtn.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION) {
                    val itemPost = adapter.posts[adapterPosition]
                    itemPost.commentsByMe = !itemPost.commentsByMe
                    if (itemPost.commentsByMe) {
                        commentsBtn.setImageResource(R.drawable.ic_chat_active_24dp)
                        itemPost.commentsCount += 1
                        commentsCount.text = itemPost.commentsCount.toString()
                        commentsCount.setTextColor(resources.getColor(R.color.colorActive))
                    }
                    else {
                        commentsBtn.setImageResource(R.drawable.ic_chat_inactive_24dp)
                        itemPost.commentsCount -= 1
                        commentsCount.text = (
                                if (itemPost.commentsCount == 0) {
                                    ""
                                }
                                else {
                                    itemPost.commentsCount
                                }).toString()
                        commentsCount.setTextColor(resources.getColor(R.color.colorInActive))
                    }
                }
            }
            shareBtn.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION) {
                    val itemPost = adapter.posts[adapterPosition]
                    itemPost.sharedByMe = !itemPost.sharedByMe
                    if (itemPost.sharedByMe) {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT, """
                                ${itemPost.author} (${itemPost.dateText})
                
                                 ${itemPost.content}
                                """.trimIndent())
                            type = "text/plain"
                        }
                        itemView.context.startActivity(intent)
                        itemPost.sharedCount += 1
                        shareCount.text = itemPost.sharedCount.toString()
                        shareBtn.setImageResource(R.drawable.ic_share_active_24dp)
                        shareCount.setTextColor(resources.getColor(R.color.colorActive))
                    }
                    else {
                        shareBtn.setImageResource(R.drawable.ic_share_inactive_24dp)
                        itemPost.sharedCount -= 1
                        shareCount.text = (
                                if (itemPost.sharedCount == 0) {
                                    ""
                                }
                                else {
                                    itemPost.sharedCount
                                }).toString()
                        shareCount.setTextColor(resources.getColor(R.color.colorInActive))
                    }
                }
            }
            closeButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapter.removeItem(adapterPosition)
                    adapter.notifyItemRemoved(adapterPosition)
                }
            }
        }
    }
    override fun join(post: Post) {
        with(itemView) {
            avatar.setImageResource(R.drawable.ic_android_24dp)
            textViewDate.text = post.dateText
            author.text = post.author
            content.text = post.content
            eventTitle.text = "POST"
            likeCount.text = (
                if (post.likedCount == 0) {
                    ""
                }
                else {
                    post.likedCount
                }
            ).toString()
            commentsCount.text = (
                if (post.commentsCount == 0) {
                    ""
                }
                else {
                   post.commentsCount
                }
            ).toString()
            shareCount.text = (
                if(post.sharedCount == 0) {
                    ""
                }
                else {
                    post.sharedCount
                }
            ).toString()
        }
    }
}