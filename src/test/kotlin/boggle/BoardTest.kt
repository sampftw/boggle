package boggle

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
        val b = Board("abcd efgh ijkl mnop")
        assertEquals("abcdefghijklmnop", b.source)
    }

}