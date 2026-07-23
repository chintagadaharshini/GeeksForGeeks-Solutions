class Solution:
    def preOrder(self, root):
        if root is None:
            return []

        stack = [root]
        ans = []

        while stack:
            node = stack.pop()
            ans.append(node.data)

            if node.right:
                stack.append(node.right)

            if node.left:
                stack.append(node.left)

        return ans