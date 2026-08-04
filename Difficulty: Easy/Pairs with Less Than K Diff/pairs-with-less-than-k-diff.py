class Solution:
    def countPairs(self, arr: list[int], k: int) -> int:
        arr.sort()
        left=0
        ans=0
        for right in range(len(arr)):
            while arr[right]-arr[left]>=k:
                left+=1
            ans+=(right-left)
        return ans