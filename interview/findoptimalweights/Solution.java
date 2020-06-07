class Solution {
	public static void main(String[] args) {
		
	}
	
	public static int[] findOptimalWeights(int[] weights, int target)
	{
		int len = weights.length;
		Arrays.sort(weight);
		int slow = 0;
		int fast = len - 1;
		int[] result = new int[2];
		int diff = Integer.MAX_VALUE;
		while (slow < fast)
		{
			int curSum = weights[slow] + weights[fast];
			if (curSum == target)
			{
				return int[] {weights[slow][fast]; }
			} else if (curSum > target) {
				fast--;
			}  else {
				if (target - curSum < dif)
				{
					diff = target - curSum;
					result[0] = weights[slow];
					result[1] = weights[fast];
				}
				
				slow++;
			}
		}
		
		return result;
	}
}