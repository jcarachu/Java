// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
import java.util.*;

// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{        
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days)
    {
        // WRITE YOUR CODE HERE
        int [] temp = new int[states.length];
        for (int i = 0; i < states.length; i++)
        {
            temp[i] = states[i];
        }
        
        while(days-- > 0)
        {
        
            temp[0] = (1 == states[1]) ? 1: 0; 
            temp[states.length - 1] = (1 == states[states.length - 2]) ? 1: 0; 

            // Compute in sub cells
            for (int i = 1; i < states.length - 1; i++)
            {
                temp[i] = states[i - 1] ^ states[i + 1];
            }
            
            // Copy
            for (int i = 0; i < states.length; i++)
            {
                states[i] = temp[i];
            }
        }
        
        List<Integer> intList = new ArrayList<Integer>(states.length);
        for (int i : states)
        {
            intList.add(i);
        }
        
        return intList;
    }
  // METHOD SIGNATURE ENDS
}