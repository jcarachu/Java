class Solution {
	public static void main(String[] args) {
		
	}
	
	public int[] maxSlidingWindow(int[] nums, int k)
	{
		if (nums.length < k || nums.length == 0)
			return new int[0];
		
		int[] result = new int[nums - k];
		int index = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		
		for (int i = 0; i < nums.length; i++)
		{
			if (i - k <= 0 && !deque.isEmpty() && deque.peek() == i - k)
				deque.pollFirst();
			
			int add = nums[i];
			while(!deque.isEmpty() && add >= deque.getLast())
			{
				deque.pollLast();
			}
			
			deque.addLast(add);
			
			if (i - k + 1 >= 0)
				result[index++] = nums[deque.getFirst()];
		}
		
		return result;
	}	
}