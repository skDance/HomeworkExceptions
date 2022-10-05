class Complaints(val ownerId: Int, val commentId: Int, val reasonId: Int) {
    override fun toString(): String {
        return "[ownerId = $ownerId, commentId = $commentId, reasonId= $reasonId]"
    }
}