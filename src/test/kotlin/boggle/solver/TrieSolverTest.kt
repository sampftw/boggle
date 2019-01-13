package boggle.solver

import boggle.Board
import boggle.ClasspathDictionary
import boggle.SimpleDictionary
import org.junit.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class TrieSolverTest {

    @Test
    fun `solve 3x3 with real dictionary`() {
        val b = Board("wow ice nam", 3)
        val dictionary = ClasspathDictionary("dictionaries/sowpods.txt")
        val solver = TrieSolver(dictionary)
        solver.solve(b)
        // TODO: Why isn't "mace" in this list? The NaiveSolver finds it!
        val expected = setOf("win", "wince", "wice", "wow", "woe", "oca", "ocean", "owe", "wem", "wean", "ice", "iceman", "cow", "coin", "can", "cam", "came", "cameo", "cain", "eco", "ean", "nice", "nam", "name", "nae", "ace", "acme", "ani", "ance", "ain", "mew", "meow", "mean", "mac", "man", "mani", "manic", "manioc", "main", "mae").sorted()
        assertEquals(expected, solver.results.sorted())
    }

    @Test
    fun `solve 4x4`() {
        val b = Board("abcd efgh ijkl mnop")
        val dictionary = SimpleDictionary(listOf("abc", "aei", "xyz"))
        val solver = TrieSolver(dictionary)
        solver.solve(b)
        assertEquals(setOf("abc", "aei"), solver.results)
    }

    @Test
    fun `solve 3x3`() {
        val b = Board("abc def ghi", 3)
        val dictionary = SimpleDictionary(listOf("abc", "adg", "xyz"))
        val solver = TrieSolver(dictionary)
        solver.solve(b)
        assertEquals(setOf("abc", "adg"), solver.results)
    }

    @Test
    fun `solve 2x2`() {
        val b = Board("ab cd", 2)
        val dictionary = SimpleDictionary(listOf("ab", "ac", "xyz"))
        val solver = TrieSolver(dictionary, minimumLength = 2)
        solver.solve(b)
        assertEquals(setOf("ab", "ac"), solver.results)
    }

    @Test
    @Ignore("Trie doesn't handle Qu yet")
    fun `solve with q`() {
        val b = Board("ab qd", 2)
        val dictionary = SimpleDictionary(listOf("qud", "aqu"))
        val solver = TrieSolver(dictionary, minimumLength = 2)
        solver.solve(b)
        assertEquals(setOf("qud", "aqu"), solver.results)
    }
}
