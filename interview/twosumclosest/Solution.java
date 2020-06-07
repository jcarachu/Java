class Solution {

	
	public static void main(String[] args) {

	}
	
	private int[] twoSumClosest(int[] nums, int target)
	{
		/**
		 * O(nlogn + n)
		 */
		
		int[] res = new int[2];
		int diff = Integer.MAX_VALUE;
		Arrays.sort(nums); // O(nlogn)
		int low = 0;
		int high = nums.length -1;
		while (low < high)
		{
			int curSum = nums[low] + nums[high];
			if (curSum > target)
			{
				high--;
			} else if (curSum == target) {
				return new int[]{nums[low], nums[high]; }
			} else {
				diff = target - curSum;
				res[0] = nums[low];
				res[1] = nums[high];
				low++;
			} else {
				low++;
			}
		}
		
		return res;
	}
}