package boggle

import java.io.File

abstract class Dictionary {

    abstract val words: List<String>

    fun isWord(word: String): Boolean {
        return words.contains(word)
    }

}

class ClasspathDictionary(val sourceFile: String) : Dictionary() {
    override val words: List<String>

    init {
        val resource = ClassLoader.getSystemResource(sourceFile)
        if (resource == null) {
            throw RuntimeException("Unable to load dictionary.")
        }
        words = File(resource.file).readLines()
    }

}

class SimpleDictionary(override val words: List<String>) : Dictionary()
