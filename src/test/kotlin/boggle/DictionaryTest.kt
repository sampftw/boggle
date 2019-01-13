package boggle

import org.junit.Test
import kotlin.test.assertEquals

class DictionaryTest {

    @Test
    fun `simple dictionary`() {
        val d: Dictionary = SimpleDictionary(listOf("a", "b"))
        assertEquals(listOf("a", "b"), d.words)
    }

    @Test
    fun `classpath dictionary`() {
        val d: Dictionary = ClasspathDictionary("dictionaries/test.txt")
        assertEquals(listOf("the", "wow"), d.words)
    }

}