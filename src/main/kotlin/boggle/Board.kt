package boggle

data class Board(val boardValue: String?, val boardWidth: Int = 4) {
    val board: String

    init {
        if (boardValue == null) {
            throw RuntimeException("missing board value")
        }
        board = validate(boardValue)
    }

    private fun validate(boardValue: String): String {
        val trimmed = boardValue.replace("\\s".toRegex(), "")
        if (trimmed.length != (boardWidth * boardWidth)) {
            throw RuntimeException("board is wrong length")
        }
        return trimmed
    }
}