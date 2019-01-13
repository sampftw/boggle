package boggle.trie


/**
 * See https://github.com/eugenp/tutorials/tree/master/data-structures
 */
internal class Trie {
    val root: TrieNode = TrieNode()

    val isEmpty: Boolean
        get() = root == null

    fun insert(word: String) {
        var current = root

        for (i in 0 until word.length) {
            current = current.children.getOrPut(word[i]) { TrieNode() }
        }
        current.endOfWord = true
    }

    fun delete(word: String): Boolean {
        return delete(root, word, 0)
    }

    fun containsNode(word: String): Boolean {
        var current = root

        for (i in 0 until word.length) {
            val ch = word[i]
            val node = current.children.get(ch) ?: return false
            current = node
        }
        return current.endOfWord
    }

    private fun delete(current: TrieNode, word: String, index: Int): Boolean {
        if (index == word.length) {
            if (!current.endOfWord) {
                return false
            }
            current.endOfWord = false
            return current.children.isEmpty()
        }
        val ch = word[index]
        val node = current.children.get(ch) ?: return false
        val shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.endOfWord

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch)
            return current.children.isEmpty()
        }
        return false
    }
}
