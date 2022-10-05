class Comment(
    val id: Int,
    val from_id: Int,
    val date: Long,
    val text: String
) {
    override fun toString(): String {
        return "[id=$id, fromid=$from_id, date=$date, text=$text]"
    }

    override fun equals(other: Any?): Boolean {
        return (other is Comment && (id == other.id && from_id == other.from_id && date == other.date && text == other.text))
    }
}