package boggle

data class Board(val boardValue: String?, val boardWidth: Int = 4) {
    val source: String
    val board: Array<Array<String>>

    init {
        if (boardValue == null) {
            throw RuntimeException("missing source value")
        }
        source = validate(boardValue)
        board = twoDBoard()
    }

    private fun validate(boardValue: String): String {
        val trimmed = boardValue.replace("\\s".toRegex(), "")
        if (trimmed.length != (boardWidth * boardWidth)) {
            throw RuntimeException("source is wrong length")
        }
        return trimmed
    }

    private fun twoDBoard(): Array<Array<String>> {
        return Array(boardWidth) { i ->
            Array(boardWidth) { j ->
                val value = source[i * boardWidth + j].toString()
                if (value.equals("q", true)) {
                    "Qu"
                } else {
                    value.toUpperCase()
                }
            }
        }
    }

}