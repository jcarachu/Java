import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] array = {10,4,6,4,8,-13,2,3};
        array = mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static int[] mergeSort(int[] array)
    {
        int n = array.length;
        if (n == 1)
            return array;
        
        // Split array into two parts
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, n/2));
        int[] right = mergeSort(Arrays.copyOfRange(array, n/2, n));

        return mergeSort(left, right);
    }

    public static int[] mergeSort(int[] left, int[] right)
    {
        int leftlength = left.length;
        int rightlength = right.length;
        int totallength = leftlength + rightlength; 
        int iterleft = 0;
        int iterright = 0;
        int[] arr = new int[totallength];

        for (int i = 0; i < totallength; i++)
        {
            if (iterleft == leftlength)
                arr[i] = right[iterright++];
            else if (iterright == rightlength)
                arr[i] = left[iterleft++];
            else 
            {
                if (left[iterleft] < right[iterright])
                    arr[i] = left[iterleft++];
                else
                    arr[i] = right[iterright++];
            }
        }

        return arr;
    }
}