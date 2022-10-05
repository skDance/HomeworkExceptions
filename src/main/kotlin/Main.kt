import Exceptions.CommentNotFoundException
import Exceptions.IncorrectReasonException
import Exceptions.PostNotFoundException

private var postIdCounter: Int = 0
private var commentIdCounter: Int = 0
private var complaintIdCounter: Int = 0
private var posts = emptyArray<Post>()
private var complaintsReport = emptyArray<Complaints>()

fun main() {
    val post = Post(ownerId = 5, fromId = 4, text = "Hello!", date = 1662740214)
    val post2 = Post(ownerId = 5, fromId = 4, text = "Hello!", date = 1662740214)
    val post3 = Post(ownerId = 5, fromId = 4, text = "Hello!", date = 1662740214)

    println(WallService.add(post))
    println(WallService.add(post2))
    println(WallService.add(post3))

    val post7 = Post(id = 3, ownerId = 24, fromId = 55, text = "Hello everybody!", date = 1662740255)
    println(WallService.update(post7))
    println(posts[2])

    println(WallService.createComment(2, commentIdCounter,25, 1662740255, "NICE POST!"))
    println(posts[2])

    println(WallService.addComplaint(11, 0, 6))
    println(complaintsReport[0])

}

data class Post(
    val id: Int = postIdCounter,
    val ownerId: Int,
    val fromId: Int,
    val likesCount: Likes = Likes(0),
    val text: String?,
    val date: Long?,
    val friendsOnly: Boolean = false,
    val repostsCount: Int = 0,
    val views: Int = 0,
    val attachments: Array<Attachments> = emptyArray(),
    var comments: Array<Comment> = emptyArray()
) {
    class Likes(count: Int) {
        var count = count
        override fun toString(): String {
            return "$count"
        }
    }
}


object WallService {

    fun clear() {
        posts = emptyArray()
        complaintsReport = emptyArray()
        postIdCounter = 0
        commentIdCounter = 0
        complaintIdCounter = 0
    }

    fun add(post: Post): Post {
        val uniqPost = post.copy(id = postIdCounter)
        posts += uniqPost
        postIdCounter++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index) in posts.withIndex()) {
            val checkPost = posts[index]
            if (post.id == checkPost.id) {
                val updatePost = checkPost.copy(
                    id = post.id,
                    fromId = post.fromId,
                    text = post.text,
                    friendsOnly = post.friendsOnly,
                    repostsCount = post.repostsCount,
                    views = post.views
                )
                posts.set(index, updatePost)
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, id: Int, from_id: Int, date: Long, text: String): Comment {
        val newComment = Comment (id, from_id, date, text)
        for ((index) in posts.withIndex()) {
            val searchPost = posts[index]
            if (postId == searchPost.id) {
                val updatePost = searchPost.copy()
                updatePost.comments += newComment
                posts.set(index, updatePost)
                return posts[index].comments.last()
            }
        }
        throw PostNotFoundException("No post with id = $postId")
    }

    fun addComplaint(ownerId: Int, commentId: Int, reasonId: Int): Boolean {
        val complaint = Complaints (ownerId, commentId, reasonId)
        when {
            commentId !in 0..commentIdCounter -> return throw CommentNotFoundException("No comment with id = $commentId")
            reasonId !in 0..8 -> return throw IncorrectReasonException("No reason with id = $reasonId")
            else -> {
                complaintsReport += complaint
                return true
            }
        }
    }
}