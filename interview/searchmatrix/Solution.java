class Solution {
	public static void main(String[] args) {
		
	}
	
	public boolean searchMatrix(int[][] matrix, int target)
	{
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		
		int height = matrix.length;
		int width = matrix[0].length;
		int x = 0;
		int y = width - 1;
		while (x >= 0 && x < height && y >= 0 && y < width)
		{
			if (matrix[x][y] == target)
				return true;
			else if (matrix[x][y] < target)
			{
				x++;
			} else {
				y--;
			}
		}
		
		return true;
	}
}