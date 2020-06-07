public class MedianOfTwoSortedArrays {
	public MedianOfTwoSortedArrays () {}

	public double Solution(int input1[], int input2[])
	{
		if (input1.length > input2.length)
			return Solution(input2, input1);
		
		int x = input1.length;
		int y = input2.length;
		
		int low = 0;
		int high = x;
		while (low <= high)
		{
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX;
			
			// If partitionX || paritionY is 0 it means, theres nothing on the left side
			// If partitionX || paritionY is length of input, is means theres nothing on right side
			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];
			
			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];
			
			if (maxLeftX <= minRightY && maxLeftY <= minRightX)
			{
				// Partitioned array at correct place
				// Get max of left elements, and min of right elements to get median,
				// if even, combine  max of left and right and divide by two
				if ((x + y) % 2 == 0)
					return (double) (Math.max(maxLeftX, maxLeftY) + 
							 Math.min(minRightX, minRightY)) / 2;
				// if odd get the max of left
				else
					return (double) Math.max(maxLeftX, maxLeftY);
			} else if (maxLeftX > minRightY) // To far on the right side. Move to left side
				high = partitionX - 1;
			else // To far on the left side. Move to the right side
				low = partitionX + 1;
		}
		// Only if the input arrays are not sorted
		throw new IllegalArgumentException();
	}
	
	public static void  main(String args[])
	{
		int x [] = { 1,3,8,9,15 };
		int y [] = { 7,11,19,21,25,33};
		System.out.println(new MedianOfTwoSortedArrays().Solution(x,y)); 
	}
}
