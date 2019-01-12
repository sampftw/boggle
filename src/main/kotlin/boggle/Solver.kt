package boggle

class Solver(val dictionary: Dictionary, val minimumLength: Int = 3, val maximumLength: Int =8) {

    var results: MutableSet<String> = mutableSetOf()

    fun solve(board: Board): Unit {
        val boggle = twoDBoard(board)
        for (i in boggle.indices) {
            for(j in boggle[i].indices) {
                val visited = Array(board.boardWidth) { Array(board.boardWidth) { false } }
                findWords(0, board, boggle, 0, dictionary, i, j, "")
            }
        }
    }


    private fun findWords(depth: Int,
                          board:Board,
                          boggle: Array<Array<String>>,
                          visited: Int,
                          dictionary: Dictionary,
                          x: Int,
                          y: Int,
                          str: String): Unit {

        if(x >= 0 && x < board.boardWidth && y >= 0 && y < board.boardWidth) {
            println("Checking $depth $x $y ${boggle[x][y]} $str ${Integer.toBinaryString(visited).padStart(board.boardWidth * board.boardWidth, '0')}")
        }

        if (plausible(board, x, y, visited) && str.length <= maximumLength) {
            // next step in the path
            // there are 8 possible directions
            val soFar = str + boggle[x][y]

            if (legal(soFar) && dictionary.words.contains(soFar)) {
                results.add(soFar)
            }

            // up
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x - 1, y, soFar)
            // down
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x + 1, y, soFar)
            // left
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x, y - 1, soFar)
            // right
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x, y + 1, soFar)
            // up left
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x - 1, y - 1, soFar)
            // up right
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x - 1, y +  1, soFar)
            // down left
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x + 1, y - 1, soFar)
            // down right
            findWords(depth + 1, board, boggle, visit(visited, x, y), dictionary, x + 1, y + 1, soFar)
        }
    }

    /**
     * If x and y are within the bounds of the board and we haven't visited
     * that coordinate yet
     */
    private fun plausible(board: Board, x: Int, y: Int, visited:Int): Boolean {
        return x >= 0 && x < board.boardWidth && y >= 0 && y < board.boardWidth && !wasVisited(visited, x, y)
    }

    private fun wasVisited(visited: Int, x: Int, y: Int): Boolean {
        val flag = visitedBit(x, y)
        return (visited and flag) > 0
    }

    private fun visitedBit(x: Int, y: Int): Int {
        return (1 shl (x * 4)) shl y
    }

    private fun legal(str: String): Boolean {
        return str.length >= minimumLength && str.length <= maximumLength
    }

    private fun twoDBoard(board: Board): Array<Array<String>> {
        var b: Array<Array<String>> = Array(board.boardWidth) {i ->
            Array(board.boardWidth) { j ->
                board.board[i * board.boardWidth + j].toString()
            }
        }
        return b
    }

    private fun visit(visited: Int, x: Int, y: Int): Int {
        return visited or visitedBit(x, y)
    }
}