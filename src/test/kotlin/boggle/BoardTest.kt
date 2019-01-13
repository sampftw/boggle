package boggle

import org.junit.Assert.assertArrayEquals
import kotlin.math.exp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class BoardTest {
    @Test
    fun `null board`() {
        assertFailsWith<RuntimeException>() {
            Board(null)
        }
    }

    @Test
    fun `wrong length board`() {
        assertFailsWith<RuntimeException>() {
            Board("a")
        }
    }

    @Test
    fun `valid board`() {
        val b = Board("qbcd Efgh ijkl mnop")
        assertEquals("qbcdEfghijklmnop", b.source)

        val expectedDisplay = arrayOf(arrayOf("Qu", "B", "C", "D"), arrayOf("E", "F", "G", "H"), arrayOf("I", "J", "K", "L"), arrayOf("M", "N", "O", "P"))
        assertEquals(expectedDisplay.size, b.display_board.size)
        for(i in expectedDisplay.indices) {
            assertArrayEquals(expectedDisplay[i], b.display_board[i])
        }

        val expected = arrayOf(arrayOf("q", "b", "c", "d"), arrayOf("e", "f", "g", "h"), arrayOf("i", "j", "k", "l"), arrayOf("m", "n", "o", "p"))
        assertEquals(expected.size, b.board.size)
        for(i in expected.indices) {
            assertArrayEquals(expected[i], b.board[i])
        }
    }

}