public class HeapMain {
	public static void main(String args[])
	{
		Heap<String> heap = new Heap<String>();
		String sample [] = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
		heap.sort(sample);
		heap.show(sample); 
	}
}
