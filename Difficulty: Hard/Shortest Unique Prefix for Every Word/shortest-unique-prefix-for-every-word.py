class TrieNode:
    def __init__(self):
        self.children = {}
        self.count = 0


class Solution:
    def findPrefixes(self, arr):
        root = TrieNode()

        # Insert all words into Trie
        for word in arr:
            curr = root
            for ch in word:
                if ch not in curr.children:
                    curr.children[ch] = TrieNode()

                curr = curr.children[ch]
                curr.count += 1

        ans = []

        # Find shortest unique prefix for each word
        for word in arr:
            curr = root
            prefix = ""

            for ch in word:
                curr = curr.children[ch]
                prefix += ch

                if curr.count == 1:
                    break

            ans.append(prefix)

        return ans