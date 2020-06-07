class Solution {
	public static void main(String[] args) {
		
	}
	
	public static int maxMinPath(int[][] grid)
	{
		if (grid == null || grid.length == 0 || grid[0].length)
			return 0;
		int width = grid.length;
		int height = grid[0].length;
		int[][] dp = new int[height][width];
		dp[0][0] = grid[0][0];
		
		for (int row = 1; i <width; i++)
		{
			dp[row][0] = Math.min(dp[row - 1][0], grid[row][0]);
		}
		
		for (int col = 1; i < height; i++)
		{
			dp[0][col] = Math.min(dp[0][col - 1], grid[0][col]);
		}
		
		for (int row = 1; row < width; row++)
		{
			for (int col = 1; col < height; col++)
			{
				dp[row][col] = Math.max((dp[row-1][j], dp[row][col-1]));
				dp[]
			}
		}
		
		
	}
}