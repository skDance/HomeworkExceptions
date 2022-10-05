import Exceptions.CommentNotFoundException
import Exceptions.IncorrectReasonException
import Exceptions.PostNotFoundException
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post1 = Post(ownerId = 14, fromId = 55, text = "Hello Test!", date = 1662740214)
        val post2 = Post(ownerId = 11, fromId = 102, text = "Hello Test!", date = 1662740214)
        WallService.add(post1)
        val result = WallService.add(post2)
        assertTrue(result.id != 0)
    }

    @Test
    fun updateTrue() {
        val post1 = Post(ownerId = 14, fromId = 55, text = "Hello Test!", date = 1662740214)
        WallService.add(post1)
        val updatePost = Post(id = 0, ownerId = 11, fromId = 102, text = "Hello Test!", date = 1662740214)
        val result = WallService.update(updatePost)
        assertEquals(true, result)
    }

    @Test
    fun updateFalse() {
        val post1 = Post(ownerId = 14, fromId = 55, text = "Hello Test!", date = 1662740214)
        WallService.add(post1)
        val post2 = Post(id = 2, ownerId = 11, fromId = 102, text = "Hello Test!", date = 1662740214)
        val result = WallService.update(post2)
        assertEquals(false, result)

    }

    @Test
    fun addCommentWithCorrectId() {
        val post1 = Post(ownerId = 14, fromId = 55, text = "Hello Test!", date = 1662740214)
        val comment1 = Comment(0, 34, 1662110255, "Test comment")
        WallService.add(post1)
        val result = WallService.createComment(0, 0, 34, 1662110255, "Test comment")
        assertEquals(result, comment1)
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentWithIncorrectId() {
        val post1 = Post(ownerId = 14, fromId = 55, text = "Hello Test!", date = 1662740214)
        WallService.add(post1)
        WallService.createComment(1, 0, 34, 1662110255, "Test comment")
    }

    @Test
    fun addComplaintSuccess() {
        val post1 = Post(ownerId = 11, fromId = 11, text = "Test post!", date = 1662740214)
        WallService.add(post1)
        WallService.createComment(0, 0, 34, 1662110255, "Test comment")
        val result = WallService.addComplaint(11,0,8)
        assertEquals(true, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun addComplaintIncorrectCommentId() {
        val post1 = Post(ownerId = 11, fromId = 11, text = "Test post!", date = 1662740214)
        WallService.add(post1)
        WallService.createComment(0, 0, 34, 1662110255, "Test comment")
        val result = WallService.addComplaint(11,1,8)
        assertEquals(true, result)
    }

    @Test(expected = IncorrectReasonException::class)
    fun addComplaintIncorrectReasonId() {
        val post1 = Post(ownerId = 11, fromId = 11, text = "Test post!", date = 1662740214)
        WallService.add(post1)
        WallService.createComment(0, 0, 34, 1662110255, "Test comment")
        val result = WallService.addComplaint(11,0,9)
        assertEquals(true, result)
    }
}