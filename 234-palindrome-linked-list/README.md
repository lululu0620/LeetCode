## Solution

Find the middle node and reverse the second part of linked list.
Middle node location will have two cases with fast-slow method:
1. The number of linked list is odd 
   e.g. 
   ```
            fast
             |
   1 -> 2 -> 1 -> null
        |
      slow
   ```
    
2. The number of linked list is even 
   e.g.
   ```
                       fast
                        |   
   1 -> 2 -> 2 -> 1 -> null
             |
            slow
   ```