package boggle

import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader



abstract class Dictionary {

    abstract val words: List<String>
    private val hasNakedQregex = Regex("""q[^u]""")
    private val endsWithQregex = Regex("""q$""")

    fun isWord(word: String): Boolean {
        return words.contains(word)
    }

    /**
     * @return true if the incoming word as a q without a u after it
     */
    fun hasNakedQ(word: String) = hasNakedQregex.matches(word) || endsWithQregex.matches(word)

    /**
     * replaces "qu" with "q"
     */
    fun sanitizeQ(word: String): String {
        return word.replace("qu", "q")
    }

    /**
     * If a word had a q in it, replace with "qu"
     */
    fun recreateQ(word: String): String {
        return word.replace("q", "qu")
    }
}

class ClasspathDictionary(val sourceFile: String) : Dictionary() {
    override val words: List<String>

    init {
        val resource = ClassLoader.getSystemResourceAsStream(sourceFile)
        if (resource == null) {
            throw RuntimeException("Unable to load dictionary.")
        }
        val reader = BufferedReader(InputStreamReader(resource))
        words = reader.readLines().filter { !hasNakedQ(it) }.map { sanitizeQ(it) }
        reader.close()
    }

}

class SimpleDictionary(private val incomingWords: List<String>) : Dictionary() {
    override val words: List<String>

    init {
        words = incomingWords.filter { !hasNakedQ(it) }.map { sanitizeQ(it) }
    }
}
