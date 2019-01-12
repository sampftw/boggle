package boggle.solver

import boggle.Board


interface Solver {

    var results: MutableSet<String>
    fun solve(board: Board): Unit
}
