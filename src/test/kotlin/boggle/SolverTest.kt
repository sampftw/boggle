package boggle

import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    //@Test
    fun solve() {
        val b = Board("abcd efgh ijkl mnop")
        val dictionary = SimpleDictionary(listOf("abc", "aei", "xyz"))
        val solver = Solver(dictionary)
        solver.solve(b)
        assertEquals(setOf("abc", "aei"), solver.results)
    }

    @Test
    fun `solve 3x3`() {
        val b = Board("abc def ghi", 3)
        val dictionary = SimpleDictionary(listOf("abc", "adg", "xyz"))
        val solver = Solver(dictionary)
        solver.solve(b)
        assertEquals(setOf("abc", "adg"), solver.results)
    }

    @Test
    fun `solve 2x2`() {
        val b = Board("ab cd", 2)
        val dictionary = SimpleDictionary(listOf("ab", "ac", "xyz"))
        val solver = Solver(dictionary, minimumLength = 2)
        solver.solve(b)
        assertEquals(setOf("ab", "ac"), solver.results)
    }

}
