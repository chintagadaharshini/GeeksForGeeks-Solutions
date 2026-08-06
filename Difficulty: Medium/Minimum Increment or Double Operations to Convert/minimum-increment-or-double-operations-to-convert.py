class Solution:
    def countMinOperations(self, arr):
        
        operations = 0
        
        while True:
            
            # Check if all elements are 0
            if sum(arr)==0:
                break
            # Handle all odd elements
            odd = False
            for i in range(len(arr)):
                if arr[i] % 2 == 1:
                    arr[i] -= 1
                    operations += 1
                    odd = True
            
            # If no odd elements, divide all by 2
            if not odd:
                for i in range(len(arr)):
                    arr[i] //= 2
                operations += 1
        
        return operations