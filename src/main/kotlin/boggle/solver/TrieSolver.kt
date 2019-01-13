package boggle.solver

import boggle.Board
import boggle.Dictionary
import boggle.trie.Trie
import boggle.trie.TrieNode
import java.util.HashMap


class TrieSolver(val dictionary: Dictionary, val minimumLength: Int = 3) : Solver {

    override var results: MutableSet<String> = mutableSetOf()
    private val trie = Trie()

    init {
        dictionary.words.forEach {
            trie.insert(it)
        }
    }

    override fun solve(board: Board) {
        for (i in board.board.indices) {
            for (j in board.board[i].indices) {
                findWords(trie.root, board, 0, dictionary, i, j, "")
            }
        }
    }


    private fun findWords(root: TrieNode,
                          board: Board,
                          visited: Int,
                          dictionary: Dictionary,
                          x: Int,
                          y: Int,
                          str: String) {

        // if we found word in trie / dictionary
        if (root.endOfWord && legal(str)) {
            results.add(dictionary.recreateQ(str))
        }

        if (plausible(board, x, y, visited)) {
            // make it visited
            val newVisited = visit(visited, x, y)

            // traverse all childs of current root
            root.children.forEach {
                val c = it.key

                if (plausible(board, x + 1, y + 1, newVisited) && board.board[x + 1][y + 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x + 1, y + 1, str + c)
                }
                if (plausible(board, x, y + 1, newVisited) && board.board[x][y + 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x, y + 1, str + c)
                }
                if (plausible(board, x - 1, y + 1, newVisited) && board.board[x - 1][y + 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x - 1, y + 1, str + c)
                }
                if (plausible(board, x + 1, y, newVisited) && board.board[x + 1][y].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x + 1, y, str + c)
                }
                if (plausible(board, x + 1, y - 1, newVisited) && board.board[x + 1][y - 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x + 1, y - 1, str + c)
                }
                if (plausible(board, x, y - 1, newVisited) && board.board[x][y - 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x, y - 1, str + c)
                }
                if (plausible(board, x - 1, y - 1, newVisited) && board.board[x - 1][y - 1].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x - 1, y - 1, str + c)
                }
                if (plausible(board, x - 1, y, newVisited) && board.board[x - 1][y].equals(c.toString(), true)) {
                    findWords(it.value, board, newVisited, dictionary, x - 1, y, str + c)
                }
            }

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
        return str.length >= minimumLength
    }

    private fun visit(visited: Int, x: Int, y: Int): Int {
        return visited or visitedBit(x, y)
    }
}
