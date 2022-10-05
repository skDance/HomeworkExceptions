interface Attachments {
    var type: String
}

class Audio(
    val id: Int = 0,
    val album_id: Int = 0,
    val ownerId: Int = 0,
    val user_id: Int = 0
)

class Video(
    val id: Int = 0,
    val album_id: Int = 0,
    val ownerId: Int = 0,
    val user_id: Int = 0
)

class Photo(
    val id: Int = 0,
    val album_id: Int = 0,
    val ownerId: Int = 0,
    val user_id: Int = 0
)

class File(
    val id: Int = 0,
    val album_id: Int = 0,
    val ownerId: Int = 0,
    val user_id: Int = 0
)

class Link(
    val id: Int = 0,
    val album_id: Int = 0,
    val ownerId: Int = 0,
    val user_id: Int = 0
)

class AudioAttachment(override var type: String, val file: Audio) : Attachments
class VideoAttachment(override var type: String, val file: Video) : Attachments
class PhotoAttachment(override var type: String, val file: Photo) : Attachments
class FileAttachment(override var type: String, val file: File) : Attachments
class LinkAttachment(override var type: String, val file: Link) : Attachments