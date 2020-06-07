class Solution {
	public static void main(String[] args) {
		
	}
	
	/*Cubic Time */
	public int maxSumArray1(int[] nums)
	{
		int n = nums.length;
		int maximumSubArraySum = Integer.MIN_VALUE;
		
		for (int left = 0; left < n; left++)
		{
			for (int right = left; right < n; right++)
			{
				int window = 0;
				
				for (int k = left; k <= right; k++)
				{
					window += nums[k];
				}
				
				maximumSubArraySum = Math.max(window, maximumSubArraySum);
			}
		}
		
		return maximumSubArraySum;
	}
	
	/* Quadratic */
	public int maxSubArray(int[] nums)
	{
		int n = nums.length;
		int maximumSubArray = Integer.MIN_VALUE;
		
		for (int left = 0; left < n; left++)
		{
			int runningWindowSum = 0;
			for (int right = 0; right < n; right++)
			{
				runningWindowSum += nums[right];
				maximumSubArray = Math.max(maximumSubArray, runningWindowSum);
			}
		}
	}
	
	/* Kadane's Algorithm */
	public int maxSubArray(int[] nums)
	{
		int maxSoFar = nums[0];
		int maxEndingHere = nums[0];
		
		for (int i = 1; i < nums.length; i++)
		{
			maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		
		return maxSoFar;
	}
}