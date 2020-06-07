class Solution {
	public static void main(String[] args) {
		System.out.println(getSum(3,1));
	}
	
	public static int getSum(int a, int b)
	{
		// Keep adding until we have no more carries
		while(b != 0)
		{
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}
		
		return a;
	}
}