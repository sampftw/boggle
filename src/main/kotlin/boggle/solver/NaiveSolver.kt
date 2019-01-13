package boggle.solver

import boggle.Board
import boggle.Dictionary


class NaiveSolver(val dictionary: Dictionary, val minimumLength: Int = 3, val maximumLength: Int =8) : Solver {

    override var results: MutableSet<String> = mutableSetOf()

    override fun solve(board: Board) {
        for (i in board.board.indices) {
            for (j in board.board[i].indices) {
                findWords(board, 0, dictionary, i, j, "")
            }
        }
    }


    private fun findWords(board: Board,
                          visited: Int,
                          dictionary: Dictionary,
                          x: Int,
                          y: Int,
                          str: String) {

        if (plausible(board, x, y, visited) && str.length <= maximumLength) {
            // next step in the path
            // there are 8 possible directions
            val soFar = if (board.board[x][y] == "q") {
                str + "qu"
            } else {
                str + board.board[x][y]
            }

            if (legal(soFar) && dictionary.words.contains(soFar.toLowerCase())) {
                results.add(soFar)
            }

            // up
            findWords(board, visit(visited, x, y), dictionary, x - 1, y, soFar)
            // down
            findWords(board, visit(visited, x, y), dictionary, x + 1, y, soFar)
            // left
            findWords(board, visit(visited, x, y), dictionary, x, y - 1, soFar)
            // right
            findWords(board, visit(visited, x, y), dictionary, x, y + 1, soFar)
            // up left
            findWords(board, visit(visited, x, y), dictionary, x - 1, y - 1, soFar)
            // up right
            findWords(board, visit(visited, x, y), dictionary, x - 1, y + 1, soFar)
            // down left
            findWords(board, visit(visited, x, y), dictionary, x + 1, y - 1, soFar)
            // down right
            findWords(board, visit(visited, x, y), dictionary, x + 1, y + 1, soFar)
        }
    }

    /**
     * If x and y are within the bounds of the source and we haven't visited
     * that coordinate yet
     */
    private fun plausible(board: Board, x: Int, y: Int, visited: Int): Boolean {
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
        return str.length in minimumLength..maximumLength
    }

    private fun visit(visited: Int, x: Int, y: Int): Int {
        return visited or visitedBit(x, y)
    }
}