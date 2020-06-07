class Solution {
	
	public static void main(String[] args) {
		
	}
	
	public boolean searchMatrix(int[][] matrix, int target)
	{
		if (matrix.length == 0)
			return false;
		
		int totalRows = matrix.length;
		int totalColumns = matrix[0].length;
		
		int left = 0;
		int right = totalRows * totalColumns - 1;
		
		while (left <= right)
		{
			int middle = left + (right - left) / 2;
			int middleElementValue = matrix[middle / totalRows][middle % totalColumns];
			if (middleElementValue == target)
			{
				return true;
			} else if (middleElementValue < target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		
		return false;
	}
	
	public boolean searchMatrix2(int[][] matrix, int target)
	{
		if (matrix.length == 0)
			return false;
		
		int col = matrix[0].length - 1;
		int row = 0;
		
		while (col >= 0 && row <= matrix.length - 1)
		{
			if (target == matrix[row][col])
				return true;
			else if (target < matrix[row][col])
				col--;
			else
				row++;
		}
		
		return false;
	}
	
	
}