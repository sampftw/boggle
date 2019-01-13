package boggle.trie

internal class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var endOfWord: Boolean = false
}